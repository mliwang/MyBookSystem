package cn.mybookmanager.controller;

import cn.mybookmanager.service.NewstudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    public NewstudentService newstudentService;
    @RequestMapping("/")
    public String greeting() {

        return "index";
    }


    @RequestMapping("/enterNewStudent")
    @ResponseBody
    public boolean enterNewStudent( int grade) {
        Boolean result=false;
        if (grade>2000){
          result = newstudentService.initializenewstudentInOrder(grade);
        }
        return result;
    }
    
}
