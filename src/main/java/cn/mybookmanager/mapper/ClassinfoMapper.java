package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Classinfo;
import cn.mybookmanager.pojo.FullClassinfo;

import java.util.List;

public interface ClassinfoMapper {

    int deleteByPrimaryKey(Integer classid);

    int insert(Classinfo record);

    /**
     * 查询所有班级信息
     * @return
     */
    List<FullClassinfo> selectAllClassinfo();



    Classinfo selectByPrimaryKey(Integer classid);


    int updateByPrimaryKey(Classinfo record);
}