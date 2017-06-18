package cn.mybookmanager.controller;

import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.pojo.KeyfiledOfGradeinfo;
import cn.mybookmanager.service.GradeinfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */

@Controller
@RequestMapping("/GradeinfoContrller")
public class GradeinfoContrller {
    private Logger logger = Logger.getLogger(GradeinfoContrller.class);
    @Autowired
    public GradeinfoService gradeinfoService;
    /**
     * 得到所有年级信息
     * @return
     */
    @RequestMapping("/getAllGrade")
    @ResponseBody
    public List<Gradeinfo> getAllGrade() {
        List<Gradeinfo> allgradeinfo = gradeinfoService.getAllgradeinfo();
     //   logger.info("进入");
       /* for (Gradeinfo g:allgradeinfo) {
            logger.info(g.getCollege()+g.getProfession()+g.getNumofclass()+"++"+g.getNumofstu());
        }*/
        return allgradeinfo;
    }

    /**
     * 得到所有专业
     * @return
     */
    @RequestMapping("/getAllProfession")
    @ResponseBody
    public List<String> getAllProfession() {
        List<String> allProfession = gradeinfoService.getAllProfession();
        return allProfession;
    }

    /**
     * 得到某学院的所有专业
     * @param college
     * @return
     */
    @RequestMapping("/getProfessionByCollege")
    @ResponseBody
    public List<String> getProfessionByCollege(String college) {
        if (college==null) return null;
        List<String> allProfession = gradeinfoService.getProfessionBycollege(college);
        return allProfession;
    }

    /**
     * 得到所有院系
     * @return
     */
    @RequestMapping("/getAllCollege")
    @ResponseBody
    public List<String> getAllCollege() {
        List<String> allCollege = gradeinfoService.getAllCollege();
        return allCollege;
    }

    /**
     *根据年级的关键信息找年级
     * @param keyfiledOfGradeinfo 年级信息的关键信息（学院，专业，年级，校区）
     * @return 年级id
     */
    @RequestMapping("/getfindGrade")
    @ResponseBody
    public Gradeinfo getfindGrade(KeyfiledOfGradeinfo keyfiledOfGradeinfo) {
          logger.info("查年级id**********"+keyfiledOfGradeinfo.getCollege()+keyfiledOfGradeinfo.getGrade());
        Gradeinfo gradeinfo = gradeinfoService.getGradeId(keyfiledOfGradeinfo);
        return gradeinfo;
    }


    /**
     * 根据专业返回该专业几年制
     * @param profession
     * @return
     */
    @RequestMapping("/getschoolSystemByProfession")
    @ResponseBody
    public int getschoolSystemByProfession(String  profession) {
        List<Gradeinfo> gradeinfos = gradeinfoService.findgradeByProfession(profession);
        if(gradeinfos.size()>0){
           // logger.info("开始找几年制"+gradeinfos.get(0).getSchoolSystem());
           return gradeinfos.get(0).getSchoolSystem();
        }
        return 0;
    }



    /**
     * 添加年级信息
     * @param gradeinfo
     * @return
     */
    @RequestMapping("/addGradeInfo")
    @ResponseBody
    public String addGradeInfo(Gradeinfo gradeinfo) {
        //先查找看添加的年级是否重复
        KeyfiledOfGradeinfo keyfiledOfGradeinfo = new KeyfiledOfGradeinfo(gradeinfo.getCollege(),gradeinfo.getGrade(),gradeinfo.getProfession(),gradeinfo.getCampus());
        Gradeinfo gradeinfo1 = gradeinfoService.getGradeId(keyfiledOfGradeinfo);//根据年级关键信息查找该年级，如果找到就说明重复添加
        if (gradeinfo1!=null){
            //添加重复
            return "-2000";
        }
        int i = gradeinfoService.add(gradeinfo);
        logger.info("添加年级:"+gradeinfo.getGradeid());
        if(i!= 0) return gradeinfo.getGradeid();
        else {return"-1000";}

    }
    @RequestMapping("/delete")
    @ResponseBody
    public int delete(String gradeid) {
        Gradeinfo gradeinfo = gradeinfoService.selectBygradeid(gradeid);
        if(gradeinfo==null){return -2;}
        int i=gradeinfoService.deleteByGradeid(gradeid);
        return i;
    }



}
