/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service;

import cn.mybookmanager.model.Bookinfo;

import java.util.List;


public interface BookinfoService {

     List<Bookinfo> selectAllBook() ;

    /**
     * 添加教材
     * @param bookinfo
     * @return
     */
    int insertBook(Bookinfo bookinfo);

    /**
     * 删除教材
     * @param bookid
     * @return
     */
  int deleteBook(String bookid);

    /**
     * 根据教材id查找教材信息
     * @param bookid
     * @return
     */
    Bookinfo selectByPrimaryKey(String bookid);

    /**
     * 根据教材id修改教材信息
     * @param bookinfo
     * @return
     */
  int updateBookinfo(Bookinfo bookinfo);

    /**
     * 输入教材的关键信息查找完整的教材信息
     * @param record
     * @return
     */
     Bookinfo selectbookid(Bookinfo record);

    /**
     * 由教材名找相关教材
     * @param bookname
     * @return
     */
  List<Bookinfo> findbooksByname(String bookname);

}
