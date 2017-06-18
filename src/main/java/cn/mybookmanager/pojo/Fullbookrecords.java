package cn.mybookmanager.pojo;

import cn.mybookmanager.model.Bookinfo;
import cn.mybookmanager.model.Bookrecords;

/**
 * Created by Administrator on 2017/5/13.
 */
public class Fullbookrecords extends Bookrecords implements java.io.Serializable {
   private Bookinfo bookinfo;

    public Fullbookrecords(Bookinfo bookinfo) {
        this.bookinfo = bookinfo;
    }

    public Fullbookrecords(String bookmemuid, String bookid, String bookcontent, String supplier, Integer booknum, String purchasetime, Bookinfo bookinfo) {
        super(bookmemuid, bookid, bookcontent, supplier, booknum, purchasetime);
        this.bookinfo = bookinfo;
    }

    public Fullbookrecords() {

    }

    public Bookinfo getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(Bookinfo bookinfo) {
        this.bookinfo = bookinfo;
    }

}
