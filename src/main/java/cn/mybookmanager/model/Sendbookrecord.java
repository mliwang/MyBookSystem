package cn.mybookmanager.model;

public class Sendbookrecord implements java.io.Serializable {
    private String sendbookid;//发书的单号
    private String classid;//领书班级
    private Integer booksnum;//所有要发的书的数量
    private String classcontent;//班级全称，便于形成记录
    private String sendtime;//形成发书单的时间
    private String bookid;//教材id
    private String   bookcontent;//教材全称
    private String forsemeter;//那个时间段的书

    public Sendbookrecord() {
    }

    public Sendbookrecord(String sendbookid, String classid, Integer booksnum, String classcontent, String sendtime, String bookid, String bookcontent, String forsemeter) {
        this.sendbookid = sendbookid;
        this.classid = classid;
        this.booksnum = booksnum;
        this.classcontent = classcontent;
        this.sendtime = sendtime;
        this.bookid = bookid;
        this.bookcontent = bookcontent;
        this.forsemeter = forsemeter;
    }

    public String getSendbookid() {
        return sendbookid;
    }

    public void setSendbookid(String sendbookid) {
        this.sendbookid = sendbookid == null ? null : sendbookid.trim();
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid == null ? null : bookid.trim();
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime == null ? null : sendtime.trim();
    }

    public Integer getBooksnum() {
        return booksnum;
    }

    public void setBooksnum(Integer booksnum) {
        this.booksnum = booksnum;
    }

    public String getClasscontent() {
        return classcontent;
    }

    public void setClasscontent(String classcontent) {
        this.classcontent = classcontent == null ? null : classcontent.trim();
    }

    public String getBookcontent() {
        return bookcontent;
    }

    public void setBookcontent(String bookcontent) {
        this.bookcontent = bookcontent == null ? null : bookcontent.trim();
    }

    public String getForsemeter() {
        return forsemeter;
    }

    public void setForsemeter(String forsemeter) {
        this.forsemeter = forsemeter == null ? null : forsemeter.trim();
    }
}