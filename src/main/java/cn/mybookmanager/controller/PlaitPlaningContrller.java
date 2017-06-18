package cn.mybookmanager.controller;

import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.pojo.KeyfiledOfGradeinfo;
import cn.mybookmanager.service.BookinfoService;
import cn.mybookmanager.service.GradeinfoService;
import cn.mybookmanager.service.TeachingPlanService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 汪冉冉 on 2017/5/24.
 */

@Controller
@RequestMapping("/PlaitPlaningContrller")
public class PlaitPlaningContrller {
    private Logger logger = Logger.getLogger(PlaitPlaningContrller.class);
    @Autowired
    public BookinfoService bookinfoService;
    @Autowired
    public GradeinfoService gradeinfoService;
    @Autowired
    public TeachingPlanService teachingPlanService;


    /**
     * 重置某个年级某个学期的教学计划
     * @param keyfiledOfGradeinfo
     * @param semester
     * @return
     */
    @RequestMapping("/resetplan")
    @ResponseBody
    public boolean resetplan(KeyfiledOfGradeinfo keyfiledOfGradeinfo,Integer semester){
        logger.info("重置"+semester+keyfiledOfGradeinfo.getCollege()+""+ keyfiledOfGradeinfo.getProfession()+keyfiledOfGradeinfo.getCampus()+keyfiledOfGradeinfo.getGrade());
        if (semester==null||keyfiledOfGradeinfo==null||keyfiledOfGradeinfo.getCollege()==null ||
                keyfiledOfGradeinfo.getProfession()==null||keyfiledOfGradeinfo.getCampus()==null||semester==0||keyfiledOfGradeinfo.getGrade()==0)
            return false;
               Gradeinfo gradeinfo = gradeinfoService.getGradeId(keyfiledOfGradeinfo);
        boolean resetplan = teachingPlanService.resetplan(gradeinfo.getGradeid(), semester);
        logger.info(resetplan);
        return  resetplan;
    }






}
