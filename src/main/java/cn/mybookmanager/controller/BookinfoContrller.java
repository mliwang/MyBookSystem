package cn.mybookmanager.controller;

import cn.mybookmanager.model.Bookinfo;
import cn.mybookmanager.service.BookinfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 汪冉冉 on 2017/3/15.
 */
@Controller
@RequestMapping("/BookinfoContrller")
public class BookinfoContrller {
    private Logger logger = Logger.getLogger(BookinfoContrller.class);
    @Autowired
    public BookinfoService bookinfoService;
    /**
     * 按教材名称找教材
     * @param bookname
     * @return
     */
    @RequestMapping("/findbookBybooknmae")
    @ResponseBody
    public List<Bookinfo> findbookBybooknmae(String bookname){
       // logger.info("查询教材信息"+bookname);
        List<Bookinfo> bookinfos = bookinfoService.findbooksByname(bookname);
       // logger.info("查询教材信息"+bookinfos);
        return bookinfos;
    }
    /**
     * 查询所有教材信息
     * @return
     */
    @RequestMapping("/findAllBookinfo")
    @ResponseBody
    public List<Bookinfo> findAllBookinfo(){
        List<Bookinfo> list = bookinfoService.selectAllBook();
       // logger.info("查询教材信息"+list.get(0));
        return list;
    }

    /**
     * 添加教材
     * @param bookinfo
     * @return
     */
    @RequestMapping("/addBook")
    @ResponseBody
    public String addBook(Bookinfo bookinfo){
        logger.info("添加教材信息"+bookinfo.getBookid()+bookinfo.getBookname()+bookinfo.getEdition()+bookinfo.getSupplier());
        Bookinfo bookinfo1 = bookinfoService.selectbookid(bookinfo);//查数据库中是否已经包含了当前添加的教材
        if(bookinfo1!=null){
            return "-20000";
        }
        int i = bookinfoService.insertBook(bookinfo);
        if (i>=0){
            logger.info("添加教材失败");
            return "-10000";

        }else{

            logger.info("添加教材信息"+bookinfo.getBookid());
            return  bookinfo.getBookid();
        }

    }

    /**
     * 根据id删除教材
     * @param bookid
     * @return
     */
    @RequestMapping("/deleteBookByid")
    @ResponseBody
    public int deleteBookByid(String bookid){

        logger.info("删除教材");
        int i=-1;
        if (bookid!=null){
            Bookinfo bookinfo = bookinfoService.selectByPrimaryKey(bookid);
            if(bookinfo==null){
                return -2;//表示没有该书
            }
            else{
                i= bookinfoService.deleteBook(bookid);

            }
        }
        return i;
    }
    @RequestMapping("/updateBookByid")
    @ResponseBody
    public int updateBookByid(Bookinfo book){
        int i=bookinfoService.updateBookinfo(book);
        if(i>0){
            return i;
        }else {
            return -10000;
        }

    }

}
