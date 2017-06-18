package cn.mybookmanager.controller;

import cn.mybookmanager.model.Classinfo;
import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.pojo.FullClassinfo;
import cn.mybookmanager.pojo.KeyfiledOfGradeinfo;
import cn.mybookmanager.service.ClassinfoService;
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
@RequestMapping("/ClassinfoContrller")
public class ClassinfoContrller {
    private Logger logger = Logger.getLogger(ClassinfoContrller.class);
    @Autowired
    public GradeinfoService gradeinfoService;
    @Autowired
    public ClassinfoService classinfoService;

   /* @RequestMapping("/addClassinfo")
    @ResponseBody
    public int addClassinfo(FullClassinfo fullClassinfo ){

        return 0;
    }
*/
    /**
     * 得到所有年级信息
     * @return
     */
    @RequestMapping("/getAllClassinfo")
    @ResponseBody
    public List<FullClassinfo> getAllClassinfo() {

        List<FullClassinfo> allclassinfo = classinfoService.getAllClassinfo();
       // logger.info("进入");
       /* for (FullClassinfo f:allclassinfo) {
            logger.info(f.getClassname()+"*****"+f.getProfession());
        }
*/
        return allclassinfo;
    }

    /**
     * 给完整班级信息添加
     * @param fnewclass
     * @return
     */
    @RequestMapping("/savenewClassinfo")
    @ResponseBody
    public String savenewClassinfo(FullClassinfo fnewclass) {//添加新班级的时候，更新每个年级对应应发书的人数
        if (fnewclass==null) return "";
         logger.info("进入添加新班级"+fnewclass);
        KeyfiledOfGradeinfo k= new KeyfiledOfGradeinfo(fnewclass.getCollege(),fnewclass.getGrade(),fnewclass.getProfession(),fnewclass.getCampus());
        Gradeinfo gradeinfo = gradeinfoService.getGradeId(k);//得到相关年级
        if(gradeinfo==null){
            logger.info("没找到对应年级");
            return "-50000";
        }//没找到相关年级不添加

        Classinfo newclass =new Classinfo(fnewclass.getClassid(),fnewclass.getClassname(),fnewclass.getNumofclassstu(),gradeinfo.getGradeid(),fnewclass.getNumoforderbookstu(),false);
        Classinfo classfind = classinfoService.getClassId(newclass);//看所添加班级是否已经存在
        if(classfind!=null){//已经有了就返回-20000
            return "-20000";
        }
        int i=classinfoService.insertClass(newclass);
        if(i!=0){ //添加新班级成功，更新各年级订书总人数
            gradeinfo.setNumofstu(gradeinfo.getNumofstu()+newclass.getNumoforderbookstu());
            gradeinfoService.updateByGradeid(gradeinfo);//更新班级
            logger.info("进入添加新班级"+newclass.getClassid());
            return newclass.getClassid();}
        else{
            logger.info("插入失败"+i);
            return "-10000";//插入失败
        }
    }

  /*  *//**
     * 添加班级
     * @param newclass
     * @return
     *//*
    @RequestMapping("/addClassInfo")
    @ResponseBody
    public int addClassInfo(Classinfo newclass) {
     //   logger.info("进入添加班级");
        int i=classinfoService.insertClass(newclass);
        System.out.print("******"+newclass.getClassid());
        return i;
    }
*/
    /**
     * 得到年级id
     * @param newclass
     * @return
     */
    @RequestMapping("/getClassInfoId")
    @ResponseBody
    public String getClassInfoId(Classinfo newclass) {
        logger.info("找班级");
        Classinfo findclass=classinfoService.getClassId(newclass);
        if (findclass!=null){
            logger.info("找到了这个班级"+findclass.getClassname()+findclass.getClassid());
            return findclass.getClassid();
        }
        else {
            return "-10000";//表示没找到该班级
        }

    }

    @RequestMapping("/deleteClassInfo")
    @ResponseBody
    public int deleteClassInfo(String classid) {
        logger.info("删除班级"+classid);
        int i=-1;
        if (classid!=null){
            Classinfo classinfo= classinfoService.selectByPrimaryKey(classid);

            if(classinfo==null){
                logger.info("删除班级"+classid+classinfo.getClassname());
                return -2;//表示没有该班级
                }
                else{
               i= classinfoService.deleteClass(classid);
                //删除成功就重新统计人数
                Gradeinfo gradeinfo = gradeinfoService.selectBygradeid(classinfo.getGradeid());
                gradeinfo.setNumofstu(gradeinfo.getNumofstu()-classinfo.getNumoforderbookstu());
                gradeinfoService.updateByGradeid(gradeinfo);//更新班级
            }
        }
        return i;
    }
    /**
     * 修改班级信息
     * @param fnewclass
     * @return
     */
    @RequestMapping("/updateClass")
    @ResponseBody
    public int updateClass(FullClassinfo fnewclass,int oldnumoforderbookstu){
       logger.info("进入修改班级"+fnewclass);
        KeyfiledOfGradeinfo k= new KeyfiledOfGradeinfo(fnewclass.getCollege(),fnewclass.getGrade(),fnewclass.getProfession(),fnewclass.getCampus());
        Gradeinfo gradeinfo = gradeinfoService.getGradeId(k);//得到相关年级
        logger.info("进入修改班级"+fnewclass);
        int i=classinfoService.updateClassinfo(fnewclass);
     if(i>0){
        //插入成功,重新统计各年级购书人数
         gradeinfo.setNumofstu(gradeinfo.getNumofstu()-oldnumoforderbookstu+fnewclass.getNumoforderbookstu());
         gradeinfoService.updateByGradeid(gradeinfo);//更新班级
     }
        return i;
    }

}
