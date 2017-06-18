package cn.mybookmanager.pojo;

import cn.mybookmanager.model.Bookinfo;

/**
 * Created by Administrator on 2017/5/27.
 */
public class Courseplan extends Bookinfo implements java.io.Serializable {
    private String coursename;

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }
    public Courseplan(){}
    public Courseplan(String coursename) {
        this.coursename = coursename;
    }

    public Courseplan(String bookid, String bookname, String author, Integer edition, String publishunit, String isbn, Integer inventory, String supplier, String coursename) {
        super(bookid, bookname, author, edition, publishunit, isbn, inventory, supplier);
        this.coursename = coursename;
    }
}
