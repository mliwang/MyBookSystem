package cn.mybookmanager.test;

import cn.mybookmanager.service.CleanGraduateService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by Administrator on 2017/5/21.
 */

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    public CleanGraduateService cleanGraduateService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.print("****应用开始运行后执行*****");
        // CleanGraduateServiceimp cleanGraduateServiceimp = event.getApplicationContext()
        //cleanGraduateService.deleteGraduateInOrder(2017);

    }

    }