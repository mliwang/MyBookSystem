/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service.serviceimpl;

import cn.mybookmanager.mapper.BookinfoMapper;
import cn.mybookmanager.model.Bookinfo;
import cn.mybookmanager.service.BookinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookinfoService")
public class BookinfoServiceimp implements BookinfoService {
    @Autowired
    public BookinfoMapper bookinfoMapper;

    /**
     * 查询所有书籍信息
     * @return
     */
    public List<Bookinfo> selectAllBook(){
        return  bookinfoMapper.selectAllBook();
    }

    /**
     * 添加教材
     * @param bookinfo
     * @return
     */
    public  int insertBook(Bookinfo bookinfo){
        return  bookinfoMapper.insert(bookinfo);
    }

    /**
     * 删除教材
     * @param bookid
     * @return
     */
    public  int deleteBook(String bookid){
        return bookinfoMapper.deleteByPrimaryKey(bookid);
    }

    /**
     * 根据教材id查找教材信息
     * @param bookid
     * @return
     */
    public Bookinfo selectByPrimaryKey(String bookid){
        return bookinfoMapper.selectByPrimaryKey(bookid);
    }

    /**
     * 根据教材id修改教材信息
     * @param bookinfo
     * @return
     */
    public  int updateBookinfo(Bookinfo bookinfo){
        return bookinfoMapper.updateByPrimaryKey(bookinfo);
    }

    /**
     * 输入教材的关键信息查找完整的教材信息
     * @param record
     * @return
     */
    public Bookinfo selectbookid(Bookinfo record){return bookinfoMapper.selectbookid(record);}

    /**
     * 由教材名找相关教材
     * @param bookname
     * @return
     */
    public List<Bookinfo> findbooksByname(String bookname){
        if (bookname!=null){
            Bookinfo bookinfo = new Bookinfo();
            bookinfo.setBookname(bookname);
            List<Bookinfo> bookinfos = bookinfoMapper.selectbookBysomething(bookinfo);
          //  System.out.print("service层"+bookinfos);
            return bookinfos;
        }
        return null;
    }

}
