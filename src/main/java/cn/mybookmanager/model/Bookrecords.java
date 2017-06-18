package cn.mybookmanager.model;


public class Bookrecords implements java.io.Serializable {
    private String bookmemuid;

    private String bookid;
    private String   bookcontent;//教材全称
    private String supplier;

    private Integer booknum;//要买的书的数量

    private String purchasetime;

    public Bookrecords() {
    }

    public Bookrecords(String bookmemuid, String bookid, String bookcontent, String supplier, Integer booknum, String purchasetime) {
        this.bookmemuid = bookmemuid;
        this.bookid = bookid;
        this.bookcontent = bookcontent;
        this.supplier = supplier;
        this.booknum = booknum;
        this.purchasetime = purchasetime;
    }

    public String getBookcontent() {
        return bookcontent;
    }

    public void setBookcontent(String bookcontent) {
        this.bookcontent = bookcontent;
    }

    public String getBookmemuid() {
        return bookmemuid;
    }

    public void setBookmemuid(String bookmemuid) {
        this.bookmemuid = bookmemuid == null ? null : bookmemuid.trim();
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid == null ? null : bookid.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public Integer getBooknum() {
        return booknum;
    }

    public void setBooknum(Integer booknum) {
        this.booknum = booknum;
    }

    public String getPurchasetime() {
        return purchasetime;
    }

    public void setPurchasetime(String purchasetime) {
        this.purchasetime = purchasetime== null ? null : purchasetime.trim();
    }
}