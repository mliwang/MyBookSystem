package cn.mybookmanager.model;

public class Bookrecords {
    private Integer bookmemuid;

    private Integer bookid;

    private Integer supplierid;

    private Integer number;

    private String purchasetime;

    public Integer getBookmemuid() {
        return bookmemuid;
    }

    public void setBookmemuid(Integer bookmemuid) {
        this.bookmemuid = bookmemuid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPurchasetime() {
        return purchasetime;
    }

    public void setPurchasetime(String purchasetime) {
        this.purchasetime = purchasetime == null ? null : purchasetime.trim();
    }
}