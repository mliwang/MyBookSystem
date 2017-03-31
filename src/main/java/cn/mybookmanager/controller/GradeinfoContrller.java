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
        logger.info("进入");
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
     *
     * @param keyfiledOfGradeinfo 年级信息的关键信息（学院，专业，年级，校区）
     * @return 年级id
     */
    @RequestMapping("/getGradeId")
    @ResponseBody
    public int getGradeId(KeyfiledOfGradeinfo keyfiledOfGradeinfo) {
        int id = gradeinfoService.getGradeId(keyfiledOfGradeinfo);
        return id;
    }

    /**
     * 添加年级信息
     * @param gradeinfo
     * @return
     */
    @RequestMapping("/addGradeInfo")
    @ResponseBody
    public int addGradeInfo(Gradeinfo gradeinfo) {
        int i = gradeinfoService.add(gradeinfo);
        return i;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public int delete(Integer gradeid) {
        Gradeinfo gradeinfo = gradeinfoService.selectBygradeid(gradeid);
        if(gradeinfo==null){return -2;}
        int i=gradeinfoService.deleteByGradeid(gradeid);
        return i;
    }



}
