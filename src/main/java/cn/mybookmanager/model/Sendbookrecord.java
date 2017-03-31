package cn.mybookmanager.model;

public class Sendbookrecord {
    private Integer sendbookid;

    private Integer classid;

    private Integer bookid;

    private String sendtime;

    private String principal;

    private Integer phoneofprincipal;

    public Integer getSendbookid() {
        return sendbookid;
    }

    public void setSendbookid(Integer sendbookid) {
        this.sendbookid = sendbookid;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime == null ? null : sendtime.trim();
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public Integer getPhoneofprincipal() {
        return phoneofprincipal;
    }

    public void setPhoneofprincipal(Integer phoneofprincipal) {
        this.phoneofprincipal = phoneofprincipal;
    }
}