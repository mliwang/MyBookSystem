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
    Gradeinfo selectByPrimaryKey(Integer gradeid);

    /**
     * 查询所有年级信息
     * @return
     */
    List<Gradeinfo> selectAllgrade();

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
     * 按学院专业年级校区查找年级id
     * @param keyfiledOfGradeinfo
     * @return
     */
    int selectgradeId(KeyfiledOfGradeinfo keyfiledOfGradeinfo);
    /**
     * 删除年级id为gradeid的年级信息
     * @param gradeid
     * @return
     */


    int deleteByPrimaryKey(Integer gradeid);
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


}