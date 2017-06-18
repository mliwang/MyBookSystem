package cn.mybookmanager.model;

public class Classinfo implements java.io.Serializable {
    private String classid;

    private String classname;

    private Integer numofclassstu;

    private String gradeid;

    private Integer numoforderbookstu;

    private Boolean getbooks;

    public Classinfo() {
    }

    public Classinfo(String classid, String classname, Integer numofclassstu, String gradeid, Integer numoforderbookstu, Boolean getbooks) {
        this.classid = classid;
        this.classname = classname;
        this.numofclassstu = numofclassstu;
        this.gradeid = gradeid;
        this.numoforderbookstu = numoforderbookstu;
        this.getbooks = getbooks;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public Integer getNumofclassstu() {
        return numofclassstu;
    }

    public void setNumofclassstu(Integer numofclassstu) {
        this.numofclassstu = numofclassstu;
    }

    public String getGradeid() {
        return gradeid;
    }

    public void setGradeid(String gradeid) {
        this.gradeid = gradeid == null ? null : gradeid.trim();
    }

    public Integer getNumoforderbookstu() {
        return numoforderbookstu;
    }

    public void setNumoforderbookstu(Integer numoforderbookstu) {
        this.numoforderbookstu = numoforderbookstu;
    }

    public Boolean getGetbooks() {
        return getbooks;
    }

    public void setGetbooks(Boolean getbooks) {
        this.getbooks = getbooks;
    }
}