package cn.mybookmanager.controller;

import cn.mybookmanager.model.Bookinfo;
import cn.mybookmanager.model.Classinfo;
import cn.mybookmanager.model.Sendbookrecord;
import cn.mybookmanager.pojo.FullDistribute;
import cn.mybookmanager.pojo.FullTeachingPlan;
import cn.mybookmanager.pojo.Fullbookrecords;
import cn.mybookmanager.service.BookPurchasingService;
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
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 汪冉冉 on 2017/6/1.
 */

@Controller
@RequestMapping("/BookPurchasingContrller")
public class BookPurchasingContrller {
    private Logger logger = Logger.getLogger(BookPurchasingContrller.class);
    @Autowired
    public BookinfoService bookinfoService;
    @Autowired
    public GradeinfoService gradeinfoService;
    @Autowired
    public TeachingPlanService teachingPlanService;
    @Autowired
    public BookPurchasingService bookPurchasingService;


      /**
     * 加载全部需要购买的教材清单
     * @return
     */
    @RequestMapping("/findAllneedBuy")
     @ResponseBody
public  List<Fullbookrecords> findAllneedBuy(String purchasingTime){
     //   logger.info("加载采购清单"+purchasingTime);
        boolean b = gradeinfoService.CaculateStudent();
     if (!b)    logger.info("没统计好，有危险");
        // return null;//没统计好就不返回
        List<FullTeachingPlan> planBytime = teachingPlanService.findPlanBytime(purchasingTime.trim());
       /* for (FullTeachingPlan fplan :planBytime) {
            logger.info(purchasingTime+"实施的所有教学计划"+fplan.getGradeinfo().getCampus()+fplan.getGradeinfo().getGrade()+fplan.getGradeinfo().getProfession()+""
            +fplan.getBookinfo().getBookname()+fplan.getCoursename()+fplan.getSemester());
        }*/
        List<Fullbookrecords> allBookPurchasing = bookPurchasingService.findAllBookPurchasing(planBytime, purchasingTime.trim());

        return allBookPurchasing;
    }


    /**
     * 查找所有在purchasingTime时段发书的所有班级及其教学计划
     * @param purchasingTime
     * @return
     */

    @RequestMapping("/findAllclassneedDistribute")
    @ResponseBody
    public  List<FullDistribute> findAllclassneedDistribute(String purchasingTime){
        //   logger.info("加载所有发书班级"+purchasingTime);
        List<FullTeachingPlan> planBytime = teachingPlanService.findPlanBytime(purchasingTime.trim());

        List<FullDistribute> classDis = bookPurchasingService.findClassDis(purchasingTime, planBytime);
        return classDis;
    }

    @RequestMapping(value = "/printPurchasing", method = {RequestMethod.POST})
    public ModelAndView printPurchasing(@RequestBody  List<Fullbookrecords> lists){
        logger.info("打印"+lists);
     ModelAndView modelAndView = new ModelAndView();
        if((lists.size()<=0)) {
            modelAndView.setViewName("index");
        }else{
         //   logger.info("教材全称："+lists.get(0).getBookcontent());
            //记录操作
            bookPurchasingService.addToRecords(lists);
  modelAndView.addObject("lists",lists);
        String purchasetime = lists.get(0).getPurchasetime();//购买时间
        modelAndView.addObject("myheader",purchasetime+"购买清单");
        modelAndView.setViewName("print");}
        return modelAndView;

    }

    /**
     * 发书记录入库并生成打印单
     * @param fullDistribute
     * @return
     */
    @RequestMapping(value = "/printDistribute", method = {RequestMethod.POST})
    public ModelAndView printDistribute(@RequestBody FullDistribute fullDistribute){

        ModelAndView modelAndView = new ModelAndView();
      List<Bookinfo> bookinfos = new ArrayList<Bookinfo>();
        Classinfo classinfo = fullDistribute.getClassinfo();
        List<FullTeachingPlan> plans = fullDistribute.getPlans();
        logger.info("打印发书单"+plans);
        if (plans.size()<=0){
            modelAndView.setViewName("index");
            return modelAndView;
        }


        String  classcontent=plans.get(0).getGradeinfo().getCollege()+plans.get(0).getGradeinfo().getGrade()+"级"
                +plans.get(0).getGradeinfo().getProfession()+"专业"+classinfo.getClassname();
        List<Sendbookrecord> sendbookrecords = new ArrayList<Sendbookrecord>();
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH");
        String time=format.format(date);//打印时间

        for (FullTeachingPlan plan:plans) {
            String bookcontent=plan.getBookinfo().getPublishunit()+"出版第"+plan.getBookinfo().getEdition()+"版"
                    +plan.getBookinfo().getBookname()+"("+plan.getBookinfo().getAuthor()+"著)";

            Sendbookrecord sendbookrecord = new Sendbookrecord(null, classinfo.getClassid(), classinfo.getNumoforderbookstu(), classcontent, time, plan.getBookid(), bookcontent, fullDistribute.getPurchasingtime());
            sendbookrecords.add(sendbookrecord);
            bookinfos.add(plan.getBookinfo());
        }
        boolean b = bookPurchasingService.addToDistributeRecords(sendbookrecords);
             modelAndView.addObject("lists",bookinfos);
        Integer semester = plans.get(0).getSemester();//当前班级的第几学期
        modelAndView.addObject("myheader",classcontent+"第"+semester+"学期教材发放清单");
        modelAndView.addObject("allnumber",classinfo.getNumoforderbookstu());
            modelAndView.setViewName("printDistribute");
        return modelAndView;

    }
}
