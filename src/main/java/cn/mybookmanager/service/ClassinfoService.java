/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service;

import cn.mybookmanager.model.Classinfo;
import cn.mybookmanager.pojo.FullClassinfo;

import java.util.List;


public interface ClassinfoService {

    /**
     * 查询所有班级信息
     * @return
     * @throws Exception
     */
      List<FullClassinfo> getAllClassinfo();

    /**
     * 按班级id查找班级信息
     * @param key
     * @return
     */
 Classinfo selectByPrimaryKey(String key);
    /**
     * 添加新班级
     * @param record
     * @return
     */
    int insertClass(Classinfo record);
      int deleteClass(String classid);
    /**
     * 根据班级的部分信息查找班级（主要找id）
     * @param classinfo
     * @return
     */
     Classinfo getClassId(Classinfo classinfo);
     int updateClassinfo(Classinfo classinfo);



}
