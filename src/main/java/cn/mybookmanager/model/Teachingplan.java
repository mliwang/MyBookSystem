package cn.mybookmanager.model;

public class Teachingplan implements java.io.Serializable {
    private String planid;

    private String gradeid;

    private String coursename;

    private String bookid;

    private Integer semester;

    public Teachingplan() {
    }

    public Teachingplan(String planid, String gradeid, String coursename, String bookid, Integer semester) {
        this.planid = planid;
        this.gradeid = gradeid;
        this.coursename = coursename;
        this.bookid = bookid;
        this.semester = semester;
    }

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid == null ? null : planid.trim();
    }

    public String getGradeid() {
        return gradeid;
    }

    public void setGradeid(String gradeid) {
        this.gradeid = gradeid == null ? null : gradeid.trim();
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid == null ? null : bookid.trim();
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}