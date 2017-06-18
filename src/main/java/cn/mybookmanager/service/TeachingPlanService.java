/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service;

import cn.mybookmanager.model.Teachingplan;
import cn.mybookmanager.pojo.Courseplan;
import cn.mybookmanager.pojo.FullTeachingPlan;
import cn.mybookmanager.pojo.QueryConditions;

import java.util.List;


public interface TeachingPlanService {

    /**
     * 查询所有的教学计划
     * @return
     */
    List<FullTeachingPlan> findAllTeachingPlan();

    /**
     * 查询所有的课程计划
     * @return
     */
    List<Courseplan> findAllCoursePlan();
    /**
     * 按条件找教学计划
     * @return
     */
     List<FullTeachingPlan> findPlanByQuery(QueryConditions queryConditions);


    /**
     * 添加教学计划
     * @param record
     * @return
     */
    int insert(Teachingplan record);

    /**
     * 修改教学计划
     * @param record
     * @return
     */
      int update(Teachingplan record);

    /**
     * 批量修改教学计划
     * @param planlists
     * @return
     */
     boolean Updateplan(List<Teachingplan> planlists);

    /**
     * 删除教学计划
     * @param record
     * @return
     */
      int delete(Teachingplan record);

    /**
     * 重置某年级某学期教学计划
     * @param gradeid
     * @param semster
     * @return
     */
    boolean resetplan(String gradeid,int semster);

    /**
     * 找使用该教材的未实施教学计划
     * @param bookid
     * @return
     */
    List<FullTeachingPlan> findPlanBybookid(String bookid);

    /**
     * 某个时段实施的教学计划
     * @param practiceTime
     * @return
     */
    List<FullTeachingPlan> findPlanBytime(String practiceTime);
    /**
     *
     */
    int insertAll(List<FullTeachingPlan> record);
}
