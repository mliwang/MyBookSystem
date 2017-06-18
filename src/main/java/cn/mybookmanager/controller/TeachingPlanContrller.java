package cn.mybookmanager.controller;

import cn.mybookmanager.model.Bookinfo;
import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.model.Teachingplan;
import cn.mybookmanager.pojo.FullTeachingPlan;
import cn.mybookmanager.pojo.KeyfiledOfGradeinfo;
import cn.mybookmanager.pojo.QueryConditions;
import cn.mybookmanager.service.BookinfoService;
import cn.mybookmanager.service.GradeinfoService;
import cn.mybookmanager.service.TeachingPlanService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 汪冉冉 on 2017/4/26.
 */

@Controller
@RequestMapping("/TeachingPlan")
public class TeachingPlanContrller {
    private Logger logger = Logger.getLogger(TeachingPlanContrller.class);
    @Autowired
    public BookinfoService bookinfoService;
    @Autowired
    public GradeinfoService gradeinfoService;
    @Autowired
    public TeachingPlanService teachingPlanService;

    /**
     * 加载全部教学计划
     * @return
     */
    @RequestMapping("/findAllPlan")
   @ResponseBody
public  List<FullTeachingPlan> findAllPlan(){
        logger.info("&&&&&&");
        List<FullTeachingPlan> allTeachingPlans = teachingPlanService.findAllTeachingPlan();
      /* for (FullTeachingPlan f:allTeachingPlans) {
        logger.info(f.getBookinfo().getBookname()+f.getCoursename()+f.getGradeinfo().getProfession()+f.getSemester());
        }*/
        return allTeachingPlans;
        }

    /**
     * 精确查找教学计划(可以只给部分条件)
     * @param queryConditions
     * @return
     */
    @RequestMapping("/SearchPlanByinput")
    @ResponseBody
    public  List<FullTeachingPlan> SearchPlanByinput(QueryConditions queryConditions,int typegrade){
      // logger.info("查教学计划"+typegrade+"**"+queryConditions.getCampus()+queryConditions.getCollege()+queryConditions.getGrade()+queryConditions.getProfession()+queryConditions.getSemester());
        List<FullTeachingPlan> planByQuery = teachingPlanService.findPlanByQuery(queryConditions);
      //  logger.info(planByQuery);
        if (typegrade==1&&planByQuery.size()==0){//typegrade=1表示有当前年级的就查当前年级，没有当前年级就查上一年级,为2代表就查当前年级
             queryConditions.setGrade(queryConditions.getGrade()-1);//找上一届的
            planByQuery = teachingPlanService.findPlanByQuery(queryConditions);
        }
    // logger.info(planByQuery.get(0).getCoursename());
        return planByQuery;

    }

    /**
     * 添加教学计划
     * @param campus
     * @param college
     * @param grade
     * @param profession
     * @param bookid
     * @param coursename
     * @param semester
     * @return
     */
    @RequestMapping("/Addnewplan")
    @ResponseBody
    public FullTeachingPlan Addnewplan(String campus,String college,int grade,String profession,String bookid,String coursename,int semester ){
        logger.info("添加教学计划"+campus+college+grade+profession+bookid+coursename);
        KeyfiledOfGradeinfo keyfiledOfGradeinfo = new KeyfiledOfGradeinfo(college, grade, profession, campus);
        Gradeinfo gradeinfo = gradeinfoService.getGradeId(keyfiledOfGradeinfo);//根据年级关键信息找到相应年级
        Bookinfo bookinfo = bookinfoService.selectByPrimaryKey(bookid);//根据bookid找到对应教材
        if(gradeinfo==null||bookinfo==null){
            logger.info("年级或教材不存在");
            return null;//没有这条记录
        }else{
            Teachingplan teachingplan = new Teachingplan(null, gradeinfo.getGradeid(), coursename, bookid, semester);
            int insert = teachingPlanService.insert(teachingplan);
            logger.info("insert"+insert);
            if(insert!=0){//插入成功，新生成一个FullTeachingPlan返回给前端

                FullTeachingPlan fullTeachingPlan = new FullTeachingPlan(teachingplan.getPlanid(), gradeinfo.getGradeid(), coursename, bookid, semester,gradeinfo, bookinfo);//
       return fullTeachingPlan;//返回教学计划

            }
            logger.info("所查年级的id:"+gradeinfo.getGradeid());
            return null;//插入失败
        }


    }

    /**
     * 修改教学计划
     * @param teachingplan
     * @return
     */
    @RequestMapping("/updateplanByid")
    @ResponseBody
    public int updateplanByid(Teachingplan teachingplan){
       logger.info("开始修改教学计划"+teachingplan.getPlanid()+teachingplan.getSemester()+teachingplan.getGradeid()+teachingplan.getCoursename()+teachingplan.getBookid());
        int i=teachingPlanService.update(teachingplan);
        if(i>0){
            return i;
        }else {
            return -10000;
        }



    }

    @RequestMapping("/deleteplanByid")
    @ResponseBody
    public int deleteplanByid(Teachingplan teachingplan){
        logger.info("开始删除教学计划"+teachingplan.getPlanid()+teachingplan.getSemester()+teachingplan.getGradeid()+teachingplan.getCoursename()+teachingplan.getBookid());
        int i=teachingPlanService.delete(teachingplan);
        if(i>0){
            return i;
        }else {
            return -10000;
        }

}}
