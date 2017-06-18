package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.pojo.KeyfiledOfGradeinfo;

import java.util.List;

public interface GradeinfoMapper {
    /**
     * 根据年级id查询年级信息信息
     * @param gradeid
     * @return
     */
    Gradeinfo selectByPrimaryKey(String gradeid);

    /**
     * 查询所有年级信息
     * @return
     */
    List<Gradeinfo> selectAllgrade();

    /**
     * 查询某一届所有的年级
     * @param grade
     * @return
     */
    List<String> selectBygrade(Integer grade);

    /**
     * 查询某个专业各个年级信息
     * @param profession
     * @return
     */
    List<Gradeinfo> selectgradeByProfession(String profession);

    /**
     * 查询某个学院的所有专业
     * @return
     */
    List<String> selectAllProfessionByCollege(String college);
    /**
     * 查询所有学院
     * @return
     */
    List<String> selectAllCollege();

    /**
     * 查询所有专业
     * @return
     */
    List<String> selectAllProfession();

    /**
     * 按学院专业年级校区查找对应年级
     * @param keyfiledOfGradeinfo
     * @return
     */
    Gradeinfo  selectgradeId(KeyfiledOfGradeinfo keyfiledOfGradeinfo);
    /**
     * 删除年级id为gradeid的年级信息
     * @param gradeid
     * @return
     */


    int deleteByPrimaryKey(String gradeid);
    /**
     * 插入年级信息
     * @param record
     * @return
     */

    int insert(Gradeinfo record);
    /**
     * 修改年级信息
     * @param record
     * @return
     */

    int updateByPrimaryKey(Gradeinfo record);

    /**
     * 统计各个年级的购书人数及班级数目
     * @return
     */
    List<Gradeinfo> calculateBygradeId();

}