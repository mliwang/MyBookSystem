package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Classinfo;
import cn.mybookmanager.pojo.FullClassinfo;

import java.util.List;

public interface ClassinfoMapper {

    int deleteByPrimaryKey(String classid);

    int insert(Classinfo record);

    /**
     * 根据年级id删除对应年级的班级
     * @param gradeid
     * @return
     */
    int deleteByGradeid(String gradeid);

    /**
     * 查询所有班级信息
     * @return
     */
    List<FullClassinfo> selectAllClassinfo();

    /**
     * 根据年级id找该年级的班级
     * @param gradeid
     * @return
     */
    List<Classinfo> selectClassByGradeId(String gradeid);

    /**
     * 输入不含id的班级查找包含id的班级
     * @param classinfo
     * @return
     */
    Classinfo selectClassId(Classinfo classinfo);
    /**
     * 按id查找班级
     * @param classid
     * @return
     */
    Classinfo selectByPrimaryKey(String classid);

    /**
     * 修改班级信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Classinfo record);
}