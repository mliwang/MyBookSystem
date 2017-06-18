package cn.mybookmanager.controller;


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
 * Created by 汪冉冉 on 2017/4/26.
 */

@Controller
@RequestMapping("/NewstudentPlan")
public class NewPlanContrller {
    private Logger logger = Logger.getLogger(NewPlanContrller.class);
    @Autowired
    public BookinfoService bookinfoService;
    @Autowired
    public GradeinfoService gradeinfoService;
    @Autowired
    public TeachingPlanService teachingPlanService;

    /**
     * 批量为新生添加教学计划
     * @param plans
     * @return
     */
    @RequestMapping(value = "/AddnewstudentPlan", method = {RequestMethod.POST})
    @ResponseBody
    public int  AddnewstudentPlan(@RequestBody List<FullTeachingPlan> plans){
             //  logger.info("进入新生添加"+plans.get(0).getSemester());
             if(plans.size()<=0){    return 0;//插入空计划
              }
        int i=teachingPlanService.insertAll(plans);
     /*   if (i==1000) {//首次插入且插入成功，则清除已毕业的本专业学生的信息
            Calendar a = Calendar.getInstance();
            int year = a.get(Calendar.YEAR);//得到当前年
            Gradeinfo gradeinfo = plans.get(0).getGradeinfo();
            KeyfiledOfGradeinfo keyfiledOfGradeinfo =new KeyfiledOfGradeinfo();//得到本专业最往届


        }*/
        return  i;
        }


}
