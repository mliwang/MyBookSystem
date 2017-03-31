package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Bookrecords;
import cn.mybookmanager.model.BookrecordsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookrecordsMapper {
    int countByExample(BookrecordsExample example);

    int deleteByExample(BookrecordsExample example);

    int insert(Bookrecords record);

    int insertSelective(Bookrecords record);

    List<Bookrecords> selectByExample(BookrecordsExample example);

    int updateByExampleSelective(@Param("record") Bookrecords record, @Param("example") BookrecordsExample example);

    int updateByExample(@Param("record") Bookrecords record, @Param("example") BookrecordsExample example);
}