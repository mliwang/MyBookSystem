/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service;

import cn.mybookmanager.model.Gradeinfo;

import java.util.Map;

/**
 * 新生入学（继承上一届的所有）
 */
public interface NewstudentService {

    /**
     * 继承上届学生的教学计划
     * @param GradeinfoMap
     * @return
     */
    Boolean initializenewstudentPlan( Map<String, Gradeinfo> GradeinfoMap);

    /**
     * 继承上届学生的班级
     * @param GradeinfoMap
     * @return
     */
    Boolean  initializenewstudentClass( Map<String, Gradeinfo> GradeinfoMap);

    /**
     * 继承上届学生的年级
     * @return
     */
    Map<String,Gradeinfo> initializenewstudentGrade(int grade);

    /**
     * 按生成年级、班级、教学计划的顺序依次初始化新一届学生信息
     * @param grade 要生成的那一届
     */
    Boolean initializenewstudentInOrder(int grade);


}
