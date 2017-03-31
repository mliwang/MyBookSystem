package cn.mybookmanager.controller;

import cn.mybookmanager.pojo.FullClassinfo;
import cn.mybookmanager.service.ClassinfoService;
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
public class ClassinfoContrller {
    private Logger logger = Logger.getLogger(ClassinfoContrller.class);
    @Autowired
    public ClassinfoService classinfoService;

    @RequestMapping("/addClassinfo")
    @ResponseBody
    public int addClassinfo(FullClassinfo fullClassinfo ){

        return 0;
    }

    /**
     * 得到所有年级信息
     * @return
     */
    @RequestMapping("/getAllClassinfo")
    @ResponseBody
    public List<FullClassinfo> getAllClassinfo() {

        List<FullClassinfo> allclassinfo = classinfoService.getAllClassinfo();
       /* for (FullClassinfo f:allclassinfo) {
            logger.info(f.getClassname()+"*****"+f.getProfession());
        }
*/
        return allclassinfo;
    }




}
