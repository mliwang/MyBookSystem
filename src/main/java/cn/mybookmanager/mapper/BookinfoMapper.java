package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Bookinfo;
import cn.mybookmanager.model.BookinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookinfoMapper {
    int countByExample(BookinfoExample example);

    int deleteByExample(BookinfoExample example);

    int deleteByPrimaryKey(Integer bookid);

    int insert(Bookinfo record);

    int insertSelective(Bookinfo record);

    List<Bookinfo> selectByExample(BookinfoExample example);

    Bookinfo selectByPrimaryKey(Integer bookid);

    int updateByExampleSelective(@Param("record") Bookinfo record, @Param("example") BookinfoExample example);

    int updateByExample(@Param("record") Bookinfo record, @Param("example") BookinfoExample example);

    int updateByPrimaryKeySelective(Bookinfo record);

    int updateByPrimaryKey(Bookinfo record);
}