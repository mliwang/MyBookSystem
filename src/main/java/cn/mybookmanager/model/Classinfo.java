package cn.mybookmanager.model;

public class Classinfo {
    private Integer classid;

    private String classname;

    private Integer numofclassstu;

    private Integer gradeid;

    private Integer numoforderbookstu;

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
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

    public Integer getGradeid() {
        return gradeid;
    }

    public void setGradeid(Integer gradeid) {
        this.gradeid = gradeid;
    }

    public Integer getNumoforderbookstu() {
        return numoforderbookstu;
    }

    public void setNumoforderbookstu(Integer numoforderbookstu) {
        this.numoforderbookstu = numoforderbookstu;
    }
}