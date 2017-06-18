package cn.mybookmanager.service.serviceimpl;

import cn.mybookmanager.mapper.BookinfoMapper;
import cn.mybookmanager.mapper.ClassinfoMapper;
import cn.mybookmanager.mapper.GradeinfoMapper;
import cn.mybookmanager.mapper.TeachingplanMapper;
import cn.mybookmanager.model.Classinfo;
import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.model.Teachingplan;
import cn.mybookmanager.service.CleanGraduateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */
@Service("cleanGraduateServiceimp")
public class CleanGraduateServiceimp implements CleanGraduateService {
    @Autowired
    public BookinfoMapper bookinfoMapper;
    @Autowired
    public TeachingplanMapper teachingplanMapper;
    @Autowired
    public GradeinfoMapper gradeinfoMapper;
    @Autowired
    public ClassinfoMapper classinfoMapper;
    private int year;//要删除哪一届

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    /**
     * 删除毕业学生的教学计划
     * @param gradeids
     * @return
     */
    @Transactional
    @Override
    public int deleteAllGraduatePlan(List<String> gradeids){
   int r=0;
        try{
            int i1;
        for (String i:gradeids) {
                List<Teachingplan> teachingplens = teachingplanMapper.selectPlanBygradeId(i);
                if (teachingplens.size()<=0)//表明该年级没有教学计划则跳过该年级
                    continue;
                r++;//记录有计划的年级个数
                i1 = teachingplanMapper.deleteplanBygradeId(i);
            if (i1<=0)
            {   r=-1;//删除条数小于0则表明有异常
                throw new RuntimeException("删除年级异常，在删除年级id为"+i+"的教学计划时异常");
            }
            }

        }  catch ( RuntimeException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return r;
    }

    /**
     * 删除毕业班级
     * @param gradeids
     * @return
     */
    @Transactional
    @Override
    public  int deleteAllGraduateGrade(List<String> gradeids){
        int r=0;
        try{
            int i1;
            for (String i:gradeids) {
                Gradeinfo gradeinfo = gradeinfoMapper.selectByPrimaryKey(i);
                if (gradeinfo==null)//表明没有该年级
                    continue;
                r++;//记录年级个数
                i1 = gradeinfoMapper.deleteByPrimaryKey(i);
                if (i1<=0)
                {   r=-1;//删除条数小于0则表明有异常
                    throw new RuntimeException("删除年级异常，在删除年级id为"+i+"时异常");
                }
            }

        }  catch ( RuntimeException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return r;
    }
    /**
     * 删除毕业年级
     * @return
     */
    @Transactional
    @Override
    public  int deleteAllGraduateClass(List<String> gradeids){
        int r=0;
        try{
            int i1;
            for (String i:gradeids) {
                List<Classinfo> classinfos = classinfoMapper.selectClassByGradeId(i);
                if (classinfos.size()<=0)//表明该年级没有对应班级则跳过该年级
                    continue;
                r++;//记录有班级的年级个数
                i1 = classinfoMapper.deleteByGradeid(i);
                if (i1<=0)
                {   r=-1;//删除条数小于0则表明有异常
                    throw new RuntimeException("删除班级异常，在删除年级id为"+i+"的班级时异常");
                }
            }

        }  catch ( RuntimeException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return r;
    }

    /**
     * 按删除计划，班级，年级的顺序依次删除
     * @param year 要删除的·届毕业生，例如传入2017表示删除2017届毕业生
     */
    @Override
    public Boolean deleteGraduateInOrder(int year){
        System.out.print("^^^^^^^进入service");
       this.year=year;
        List<Gradeinfo> gradeinfos =gradeinfoMapper.selectAllgrade();//先找到所有的年级信息
        List<String> gradeids =new ArrayList<String>();//找到要删除的年级信息(今年7月份毕业的年级)
        for (int j=0;j<gradeinfos.size();j++){
            Gradeinfo gradeinfo = gradeinfos.get(j);
            if ((gradeinfo.getGrade()+gradeinfo.getSchoolSystem())>=year) //删除已毕业的，比如
                gradeids.add(gradeinfo.getGradeid());
        }

        //1.删除教学计划
        int i = deleteAllGraduatePlan(gradeids);
        if(i<0){
            return false;//删除失败
        }
        //2.删除所有毕业班级
        int i1 = deleteAllGraduateClass(gradeids);
        if(i1<0){
            return false;//删除失败
        }
        //3.删除毕业年级
        int i2 = deleteAllGraduateGrade(gradeids);
        if(i2<0){
            return false;//删除失败
        }
        return true;
    }
}
