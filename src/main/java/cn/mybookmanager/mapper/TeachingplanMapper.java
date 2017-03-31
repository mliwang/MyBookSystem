package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Teachingplan;
import cn.mybookmanager.model.TeachingplanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeachingplanMapper {
    int countByExample(TeachingplanExample example);

    int deleteByExample(TeachingplanExample example);

    int deleteByPrimaryKey(Integer planid);

    int insert(Teachingplan record);

    int insertSelective(Teachingplan record);

    List<Teachingplan> selectByExample(TeachingplanExample example);

    Teachingplan selectByPrimaryKey(Integer planid);

    int updateByExampleSelective(@Param("record") Teachingplan record, @Param("example") TeachingplanExample example);

    int updateByExample(@Param("record") Teachingplan record, @Param("example") TeachingplanExample example);

    int updateByPrimaryKeySelective(Teachingplan record);

    int updateByPrimaryKey(Teachingplan record);
}