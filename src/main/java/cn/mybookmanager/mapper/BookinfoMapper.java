package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Bookinfo;

import java.util.List;

public interface BookinfoMapper {
    /**
     * 查询所有书的相关信息
     * @return
     */
      List<Bookinfo> selectAllBook();

    /**
     * 根据教材的某些信息查找符合条件的教材
     * @param bookinfo
     * @return
     */
    List<Bookinfo> selectbookBysomething(Bookinfo bookinfo);

    /**
     * 根据bookid删除教材
     * @param bookid
     * @return
     */
    int deleteByPrimaryKey(String bookid);

    /**
     * 添加教材
     * @param record
     * @return
     */
    int insert(Bookinfo record);

    /**
     * 按教材id查询教材信息
     * @param bookid
     * @return
     */
     Bookinfo selectByPrimaryKey(String bookid);


    /**
     * 按id修改教材信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Bookinfo record);

    /**
     * 输入教材的关键信息查找完整的教材信息
     * @param record
     * @return
     */
    Bookinfo selectbookid(Bookinfo record);
}