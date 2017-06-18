/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service.serviceimpl;

import cn.mybookmanager.mapper.BookinfoMapper;
import cn.mybookmanager.mapper.GradeinfoMapper;
import cn.mybookmanager.mapper.TeachingplanMapper;
import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.model.Teachingplan;
import cn.mybookmanager.pojo.Courseplan;
import cn.mybookmanager.pojo.FullTeachingPlan;
import cn.mybookmanager.pojo.KeyfiledOfGradeinfo;
import cn.mybookmanager.pojo.QueryConditions;
import cn.mybookmanager.service.TeachingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("teachingPlanServiceimp")
public class TeachingPlanServiceimp implements TeachingPlanService {
    @Autowired
    public BookinfoMapper bookinfoMapper;
    @Autowired
    public TeachingplanMapper teachingplanMapper;
    @Autowired
    public GradeinfoMapper gradeinfoMapper;

    /**
     * 找使用该教材的未实施教学计划
     * @param bookid
     * @return
     */
    @Override
    public List<FullTeachingPlan> findPlanBybookid(String bookid){
        Calendar a=Calendar.getInstance();
        int year = a.get(Calendar.YEAR);//得到年
        int mounth = a.get(Calendar.MONTH)+1;
//TODO
        List<FullTeachingPlan> fullTeachingPlan = teachingplanMapper.selectPlanBybookid(bookid);//找到所有使用该教材的教学计划
        ArrayList<FullTeachingPlan> result = new ArrayList<>();//结果
        for (FullTeachingPlan f:fullTeachingPlan) {//过滤掉已实施的
if(mounth>=1&&mounth<7){
    //可以确定本学年上学期的教学计划实施完了
    if (f.getSemester()>=(2*(year-f.getGradeinfo().getGrade()))){
        result.add(f);
    }

}
            else{
    //可以确定本学年下学期的教学计划实施完了

    if (f.getSemester()>=(2*(year-f.getGradeinfo().getGrade())+1)){
        result.add(f);
    }

}



        }
return result;
    }

    /**
     * 查询所有的教学计划
     * @return
     */
    @Override
    public List<FullTeachingPlan> findAllTeachingPlan(){
        return teachingplanMapper.selectAllPlan();
    }
    /**
     * 查询所有的课程计划
     * @return
     */
    @Override
    public List<Courseplan> findAllCoursePlan(){
        return teachingplanMapper.selectAllCoursePlan();
    }
    /**
     * 按条件找教学计划
     * @return
     */
    @Override
    public List<FullTeachingPlan> findPlanByQuery(QueryConditions queryConditions){
        return teachingplanMapper.selectAllPlanByQuery(queryConditions);

    }


    /**
     * 添加教学计划
     * @param record
     * @return
     */
    @Override
    public  int insert(Teachingplan record){
        Teachingplan t = teachingplanMapper.selectPlanBykeyfiled(record);
        if (t==null){  return  teachingplanMapper.insert(record);}
        else { return -1;//重复了失败
        }
    }
    @Override
    public  int update(Teachingplan record){
        Teachingplan t = teachingplanMapper.selectPlanBykeyfiled(record);//对比关键信息是否一致
        if (t==null){return teachingplanMapper.updateByPrimaryKey(record);}
        else { return -1;//重复了失败
        }

    }
    @Override
    public  int delete(Teachingplan record){
        return teachingplanMapper.deleteByPrimaryKey(record.getPlanid());
    }


    /**
     * 重置某个年级某学期的教学计划
     * @param gradeid
     * @param semster
     * @return
     */
    @Override
    public boolean resetplan(String gradeid, int semster){

        int i = teachingplanMapper.deleteplanBygradeIdAndSemster(gradeid, semster);
        if (i<0)return false;

        return true;
    }



    /**
     *批量插入，为当前届学生批量插教学计划,如果其中有一条计划之前添加过则添加失败全部回滚，如果该学期之前添加过也会回滚
     * @param records 当前届某学期的完整教学计划（可能含有未变动的被继续执行的往届教学计划）
     * @return 1000代表全部插入;-1中途停，有重复，之前没添加过该学期的，但提交的计划中包含有与之前添加的学期重复的; 1一条都没插入成功，之前添加过该教学计划
     */
    @Transactional
    @Override
    public int insertAll(List<FullTeachingPlan> records) {
        int count=0;//重复的计划的条数
        if (records.size()>0){
            Calendar a=Calendar.getInstance();
          int year=a.get(Calendar.YEAR);//得到当前年
           Teachingplan t=null;
            Gradeinfo gradeinfo=null;//装当前届年级
        //TODO
        try {
            for (FullTeachingPlan f:records) {
             if (year> f.getGradeinfo().getGrade()){
                 //沿用往届的
                 //根据往届得到当前届的关键信息
                 KeyfiledOfGradeinfo keyfiledOfGradeinfo = new KeyfiledOfGradeinfo(f.getGradeinfo().getCollege(), f.getGradeinfo().getGrade()+1, f.getGradeinfo().getProfession(), f.getGradeinfo().getCampus());
                  gradeinfo = gradeinfoMapper.selectgradeId(keyfiledOfGradeinfo);
                 f.setGradeid(gradeinfo.getGradeid());
                 f.setGradeinfo(gradeinfo);

             }
                else{
                 gradeinfo=f.getGradeinfo();//新设计的教学计划
             }
                t= teachingplanMapper.selectPlanBykeyfiled( new Teachingplan(null,f.getGradeid(),f.getCoursename(), f.getBookid(),0));//对比找该年级某课程某教材之前是否已添加
                if (t!=null){
                    throw new RuntimeException(gradeinfo.getGrade()+"届"+ gradeinfo.getProfession()+"课程:"+f.getCoursename()+""+f.getBookinfo().getBookname()+"已添加");
                }
                else{//前面没有重复的
                    teachingplanMapper.insert(f);
                    count++;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }}
        if (count>=records.size()){return 1000;//首次插且全部插入
        }
      else if (count>0&&count<records.size()){
            return -1;//中途停，有重复，之前没添加过该学期的，但提交的计划中包含有与之前添加的学期重复的
       }
        else{ return  1;}//一条都没插入成功，之前添加过该教学计划

    }

    /**
     * 批量修改教学计划，只要有一个失败则失败回滚
     * @param planlists
     * @return
     */
    @Transactional
    @Override
    public boolean Updateplan(List<Teachingplan> planlists){
        Boolean result=true;
        if (planlists.size()<=0) return false;
        try{
            for (Teachingplan teachingplan :planlists) {
                int i = teachingplanMapper.updateByPrimaryKey(teachingplan);
                    if (i<0)
                    {   result=false;//修改返回值小于零则表明有异常
                        throw new RuntimeException("插入班级异常");
                    }
            }


        }  catch ( RuntimeException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 找到对应时间段要实施的教学计划
     * @param practiceTime    如2017/9-2018/1、2018/2-2018/7
     * @return
     */
    @Override
    public  List<FullTeachingPlan> findPlanBytime(String practiceTime){
        String endyearstring= practiceTime.substring(7, 11);
        String endmonthstring = practiceTime.substring(12, 13);
        int endyear = Integer.parseInt(endyearstring);
        int endmonth = Integer.parseInt(endmonthstring);
       // System.out.print(endyearstring+"年"+endmonthstring);
        List<FullTeachingPlan> fullTeachingPlens = teachingplanMapper.selectAllPlan();
        List<FullTeachingPlan> resultfullTeachingPlan=new ArrayList<FullTeachingPlan>();
        //过滤，只要purchasingTime时段的
        for (FullTeachingPlan teachingplan:fullTeachingPlens) {
            Integer grade = teachingplan.getGradeinfo().getGrade();
            Integer schoolSystem = teachingplan.getGradeinfo().getSchoolSystem();
            Integer semester = teachingplan.getSemester();
            if(endyear-grade>schoolSystem){
                //该时段已毕业，不考虑
                continue;
            }

            //上学期
            if (endmonth==1&&semester==((endyear-grade)*2-1)){
                resultfullTeachingPlan.add(teachingplan);

            }
            //下学期
            if (endmonth==7&&semester==((endyear-grade)*2)){
                resultfullTeachingPlan.add(teachingplan);
            }

        }

     return resultfullTeachingPlan;
    }
}
