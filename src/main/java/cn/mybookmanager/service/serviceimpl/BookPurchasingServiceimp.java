package cn.mybookmanager.service.serviceimpl;

import cn.mybookmanager.mapper.*;
import cn.mybookmanager.model.*;
import cn.mybookmanager.pojo.FullDistribute;
import cn.mybookmanager.pojo.FullTeachingPlan;
import cn.mybookmanager.pojo.Fullbookrecords;
import cn.mybookmanager.service.BookPurchasingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/6/5.
 */
@Service("bookPurchasingService")
public class BookPurchasingServiceimp implements BookPurchasingService {
    @Autowired
    public BookinfoMapper bookinfoMapper;
    @Autowired
    public TeachingplanMapper teachingplanMapper;
    @Autowired
    public ClassinfoMapper classinfoMapper;
    
    @Autowired
    public GradeinfoMapper gradeinfoMapper;
    @Autowired
    public BookrecordsMapper bookrecordsMapper;
    @Autowired
    public SendbookrecordMapper sendbookrecordMapper;
    /**
     *  某个时段实施的教学计划里所有书要买的数量
     * @param plans 该时间段实施的教学计划
     * @param purchasingtime
     * @return
     */
    @Override
    public List<Fullbookrecords> findAllBookPurchasing( List<FullTeachingPlan> plans, String purchasingtime) {
        //TODO
        HashMap<String, Integer> temMap = new HashMap<String, Integer>();//各种书要买的本数
        int needbuynum=0;//统计要买书的数量
        for (FullTeachingPlan f:plans ) {
            Bookinfo bookinfo1 = f.getBookinfo();
            bookinfo1.setBookid(f.getBookid());
            if(temMap.containsKey(f.getBookid())){
                needbuynum=temMap.get(f.getBookid())+f.getGradeinfo().getNumofstu();
            }
            else{//如果第一次统计到就将该书的需求减去库存
                needbuynum=f.getGradeinfo().getNumofstu()-f.getBookinfo().getInventory();
            }
            temMap.put(f.getBookid(),needbuynum);
        }
        ArrayList<Fullbookrecords> bookrecordses = new ArrayList<Fullbookrecords>();
        //封装返回值
        for(Map.Entry<String,Integer> entry : temMap.entrySet()){
            if(entry.getValue()<=0)continue;//如果库存可以满足需求则不列入购书单
            Bookinfo bookinfo = bookinfoMapper.selectByPrimaryKey(entry.getKey().toString().trim());//教材
            Date date=new Date();
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=format.format(date);
            String bookmemuid=time;//生成当前时间做id

            Fullbookrecords fullbookrecords = new Fullbookrecords(bookmemuid, bookinfo.getBookid(),null, bookinfo.getSupplier(), entry.getValue(), purchasingtime, bookinfo);
            bookrecordses.add(fullbookrecords);
        }

        return bookrecordses;
    }
    /**
     * 形成购书记录
     * @param allrecords
     * @return
     */
    @Transactional
    @Override
   public boolean addToRecords(List<Fullbookrecords> allrecords){
        //TODO
        boolean r=true;
        String purchasetime = allrecords.get(0).getPurchasetime();
        String syearstring= purchasetime.substring(0, 4);
        String smonthstring = purchasetime.substring(5,6);
        String endyearstring= purchasetime.substring(7, 11);
        String endmonthstring = purchasetime.substring(12, 13);
        purchasetime=syearstring+"年"+smonthstring+"月至"+endyearstring+"年"+endmonthstring+"月";
         try {
             //  public Bookrecords(String bookmemuid, String bookid, String bookcontent, String supplier, Integer booknum, String purchasetime)
             for (Fullbookrecords fbookrecords:allrecords) {
                Bookinfo bookinfo = fbookrecords.getBookinfo();
                String bookcontent=bookinfo.getPublishunit()+"出版第"+bookinfo.getEdition()+"版"
                        +bookinfo.getBookname()+"("+bookinfo.getAuthor()+"著)";
                fbookrecords.setBookcontent(bookcontent);
                fbookrecords.setPurchasetime(purchasetime);
                 //看之前是否已经买了这个书
                 List<Bookrecords> bookrecordses = bookrecordsMapper.selectBookrecordsByPrimary(new Bookrecords(null, fbookrecords.getBookid(), null, null, null, purchasetime));
                 if (bookrecordses.size()<=0){//没产生过购书单，更新库存
                     Bookinfo bookinfo1 = bookinfoMapper.selectByPrimaryKey(fbookrecords.getBookid());
                     bookinfo1.setInventory(bookinfo1.getInventory()+fbookrecords.getBooknum());
                     bookinfoMapper.updateByPrimaryKey(bookinfo1);
                 }
                 int insert = bookrecordsMapper.insert(fbookrecords);
                if (insert<0)throw new RuntimeException("形成记录失败");
            }
        } catch ( RuntimeException e){
            r=false;
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Exception e){
            e.printStackTrace();
        }

       return r;
   }

    /**
     *找到所有要发书班级及其教学计划
     * @param purchasing
     * @param plans
     * @return
     */
    @Override
    public List<FullDistribute> findClassDis(String purchasing, List<FullTeachingPlan> plans) {
     Map<String, List<FullTeachingPlan>> temMap = new HashMap<String,  List<FullTeachingPlan>>();//各个要发书的年级及他们的课程计划
        ArrayList<FullDistribute> fullDistributes = new ArrayList<>();//各个要发书的年级及他们的课程计划
        for (FullTeachingPlan f:plans) {
            Gradeinfo gradeinfo = f.getGradeinfo();
            if(temMap.containsKey(f.getGradeid())){
                List<FullTeachingPlan> fullTeachingPlen = temMap.get(f.getGradeid());
                fullTeachingPlen.add(f);
                temMap.put(f.getGradeid(),fullTeachingPlen);
            }
            else{//如果第一次统计
                List<FullTeachingPlan> fullTeachingPlen = new ArrayList<>();
                fullTeachingPlen.add(f);
                temMap.put(f.getGradeid(),fullTeachingPlen);
            }
        }
        for (Map.Entry<String, List<FullTeachingPlan>> entry : temMap.entrySet()) {
             String key = entry.getKey();
            List<FullTeachingPlan> plansForGarde = entry.getValue();
            List<Classinfo> classinfos = classinfoMapper.selectClassByGradeId(key);
            for (Classinfo classinfo:classinfos) {

                FullDistribute fullDistribute = new FullDistribute(classinfo, plansForGarde, purchasing);
                fullDistributes.add(fullDistribute);
            }
        }


        return fullDistributes;
    }

    /**
     * 记录发书记录单
     * @param allrecords
     * @return
     */
    @Transactional
    @Override
    public boolean addToDistributeRecords(List<Sendbookrecord> allrecords) {
        boolean r=true;
        String purchasetime = allrecords.get(0).getForsemeter();
        String syearstring= purchasetime.substring(0, 4);
        String smonthstring = purchasetime.substring(5,6);
        String endyearstring= purchasetime.substring(7, 11);
        String endmonthstring = purchasetime.substring(12, 13);
        purchasetime=syearstring+"年"+smonthstring+"月至"+endyearstring+"年"+endmonthstring+"月";
        try {
            for (Sendbookrecord sendbookrecord:allrecords) {
                sendbookrecord.setForsemeter(purchasetime);
                //看之前是否已经发了这个班的
                List<Sendbookrecord> sendbookrecords1 = sendbookrecordMapper.selectSendbookrecord(new Sendbookrecord(null, sendbookrecord.getClassid(), null, null, null, sendbookrecord.getBookid(), null, purchasetime));
                if (sendbookrecords1.size()<=0){//该班没产生过purchasetime时段用的发书单，更新库存
                    Bookinfo bookinfo1 = bookinfoMapper.selectByPrimaryKey(sendbookrecord.getBookid());
                    Integer numoforderbookstu = classinfoMapper.selectByPrimaryKey(sendbookrecord.getClassid()).getNumoforderbookstu();
                    bookinfo1.setInventory(bookinfo1.getInventory()-numoforderbookstu);
                    bookinfoMapper.updateByPrimaryKey(bookinfo1);
                }

                int insert =sendbookrecordMapper.insert(sendbookrecord);
                if (insert<0)throw new RuntimeException("形成记录失败");
            }

        } catch ( RuntimeException e){
            r=false;
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return r;
    }
}
