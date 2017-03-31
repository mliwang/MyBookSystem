package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Booksupplierinfo;
import cn.mybookmanager.model.BooksupplierinfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksupplierinfoMapper {
    int countByExample(BooksupplierinfoExample example);

    int deleteByExample(BooksupplierinfoExample example);

    int deleteByPrimaryKey(Integer supplierid);

    int insert(Booksupplierinfo record);

    int insertSelective(Booksupplierinfo record);

    List<Booksupplierinfo> selectByExample(BooksupplierinfoExample example);

    Booksupplierinfo selectByPrimaryKey(Integer supplierid);

    int updateByExampleSelective(@Param("record") Booksupplierinfo record, @Param("example") BooksupplierinfoExample example);

    int updateByExample(@Param("record") Booksupplierinfo record, @Param("example") BooksupplierinfoExample example);

    int updateByPrimaryKeySelective(Booksupplierinfo record);

    int updateByPrimaryKey(Booksupplierinfo record);
}