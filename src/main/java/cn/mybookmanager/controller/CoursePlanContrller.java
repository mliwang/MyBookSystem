package cn.mybookmanager.controller;

import cn.mybookmanager.model.Teachingplan;
import cn.mybookmanager.pojo.Courseplan;
import cn.mybookmanager.pojo.FullTeachingPlan;
import cn.mybookmanager.service.BookinfoService;
import cn.mybookmanager.service.GradeinfoService;
import cn.mybookmanager.service.TeachingPlanService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 汪冉冉 on 2017/5/26.
 */

@Controller
@RequestMapping("/CoursePlanContrller")
public class CoursePlanContrller {
    private Logger logger = Logger.getLogger(CoursePlanContrller.class);
    @Autowired
    public BookinfoService bookinfoService;
    @Autowired
    public GradeinfoService gradeinfoService;
    @Autowired
    public TeachingPlanService teachingPlanService;

    /**
     * 加载全部课程计划（什么课程使用什么教材）
     * @return
     */
    @RequestMapping("/findAllCoursePlan")
   @ResponseBody
public  List<Courseplan> findAllCoursePlan(){
      //  logger.info("加载全部课程计划&&&&&&");
        List<Courseplan> allTeachingPlans = teachingPlanService.findAllCoursePlan();
    /*  for (Courseplan f:allTeachingPlans) {
        logger.info(f.getBookid());
        }*/
        return allTeachingPlans;
        }

    /**
     * 找使用该教材的未实施教学计划
     * @param bookid
     * @return
     */
    @RequestMapping("/findPerfessionLists")
    @ResponseBody
    public  List<FullTeachingPlan> findPerfessionLists(String bookid){
      //  logger.info("开始加载未实施计划");
if (bookid==null) return null;
        return teachingPlanService.findPlanBybookid(bookid);
    }

    /**
     * 批量修改某些教学计划的课程
     * @param lists
     * @return
     */
    @RequestMapping(value = "/UpdatebookInplan", method = {RequestMethod.POST})
    @ResponseBody
    public boolean  UpdatebookInplan(@RequestBody List<Teachingplan> lists){
        logger.info("修改教学计划"+lists);
        return teachingPlanService.Updateplan(lists);
    }
}
