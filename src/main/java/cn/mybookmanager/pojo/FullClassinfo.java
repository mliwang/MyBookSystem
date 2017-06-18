package cn.mybookmanager.pojo;

import cn.mybookmanager.model.Classinfo;

/**
 * Created by Administrator on 2017/3/19.
 */
public class FullClassinfo extends Classinfo implements java.io.Serializable {
    private String college;//学院

    private Integer grade;//年级

    private String profession;//专业
    private String campus;//校区

    public FullClassinfo() {
    }


    public FullClassinfo(String classid, String classname, Integer numofclassstu, String gradeid, Integer numoforderbookstu, Boolean getbooks, String college, Integer grade, String profession, String campus) {
        super(classid, classname, numofclassstu, gradeid, numoforderbookstu, getbooks);
        this.college = college;
        this.grade = grade;
        this.profession = profession;
        this.campus = campus;
    }

    public FullClassinfo(String classid, String classname, Integer numofclassstu, String gradeid, Integer numoforderbookstu, Boolean getbooks, String college) {
        super(classid, classname, numofclassstu, gradeid, numoforderbookstu, getbooks);
        this.college = college;
    }

    public FullClassinfo(String classid, String classname, Integer numofclassstu, String gradeid, Integer numoforderbookstu, Boolean getbooks) {
        super(classid, classname, numofclassstu, gradeid, numoforderbookstu, getbooks);
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
