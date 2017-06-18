package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Teachingplan;
import cn.mybookmanager.pojo.Courseplan;
import cn.mybookmanager.pojo.FullTeachingPlan;
import cn.mybookmanager.pojo.QueryConditions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachingplanMapper {
    /**
     * 查询所有的教学计划
     * @return
     */
    List<FullTeachingPlan> selectAllPlan();

    /**
     * 查询所有的课程计划
     * @return
     */
    List<Courseplan>  selectAllCoursePlan();

    /**
     * 按条件查询
     * @param queryConditions
     * @return
     */
    List<FullTeachingPlan>selectAllPlanByQuery(QueryConditions queryConditions);

    /**
     * 找使用该教材的教学计划
     * @param bookid
     * @return
     */
    List<FullTeachingPlan> selectPlanBybookid(String bookid);

    /**
     * 根据主键删除
     * @param planid
     * @return
     */
    int deleteByPrimaryKey(String planid);

    /**
     * 根据年级id删除
     * @param gradeid
     * @return
     */
    int deleteplanBygradeId(String gradeid);

    /**
     * 删除某年级某个学期的所有教学计划
     * @param gradeid
     * @param semester
     * @return
     */
    int deleteplanBygradeIdAndSemster(@Param("gradeid")String gradeid,@Param("semester")int semester);



    /**
     * 添加教学计划
     * @param record
     * @return
     */

    int insert(Teachingplan record);

    /**
     * 根据教学计划的主要信息查找教学计划（主要用于查重）
     * @param teachingplan
     * @return
     */
    Teachingplan selectPlanBykeyfiled(Teachingplan teachingplan);


    Teachingplan selectByPrimaryKey(String planid);

    /**
     * 找某个年级的所有教学计划
     * @param gradeid
     * @return
     */
    List<Teachingplan> selectPlanBygradeId(String gradeid);

    /**
     * 按主键修改教学计划
     * @param record
     * @return
     */

    int updateByPrimaryKey(Teachingplan record);
}