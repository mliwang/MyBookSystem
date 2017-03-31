package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Sendbookrecord;
import cn.mybookmanager.model.SendbookrecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SendbookrecordMapper {
    int countByExample(SendbookrecordExample example);

    int deleteByExample(SendbookrecordExample example);

    int insert(Sendbookrecord record);

    int insertSelective(Sendbookrecord record);

    List<Sendbookrecord> selectByExample(SendbookrecordExample example);

    int updateByExampleSelective(@Param("record") Sendbookrecord record, @Param("example") SendbookrecordExample example);

    int updateByExample(@Param("record") Sendbookrecord record, @Param("example") SendbookrecordExample example);
}