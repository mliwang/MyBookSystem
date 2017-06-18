package cn.mybookmanager.service;

import java.util.List;

/**
 * 清除已毕业学生的教学计划，班级，年级
 * Created by Administrator on 2017/5/21.
 */
public interface CleanGraduateService {
    /**
     * 删除毕业学生的教学计划
     * @param gradeids
     * @return
     */
    int deleteAllGraduatePlan(List<String> gradeids);

    /**
     * 删除毕业班级
     * @param gradeids
     * @return
     */
    int deleteAllGraduateClass(List<String> gradeids);

    /**
     * 删除毕业年级
     * @return
     */
    int deleteAllGraduateGrade(List<String> gradeids);

    /**
     * 按删除计划，班级，年级的顺序依次删除
     * @param grade 要删的那一届
     */
    Boolean deleteGraduateInOrder(int grade);
}
