package cn.mybookmanager.model;

public class Gradeinfo implements java.io.Serializable {
    private String gradeid;//年级id

    private String college;//学院

    private Integer grade;//年级

    private String profession;//专业

    private Integer numofclass;//班级数量

    private Integer numofstu;//改年级订书总人数

    private String campus;//校区
    private Integer schoolSystem;//对应专业几年制

    public Gradeinfo() {
    }

    public Gradeinfo(String gradeid, String college, Integer grade, String profession, Integer numofclass, Integer numofstu, String campus, Integer schoolSystem) {
        this.gradeid = gradeid;
        this.college = college;
        this.grade = grade;
        this.profession = profession;
        this.numofclass = numofclass;
        this.numofstu = numofstu;
        this.campus = campus;
        this.schoolSystem = schoolSystem;
    }

    public Integer getSchoolSystem() {
        return schoolSystem;
    }

    public void setSchoolSystem(Integer schoolSystem) {
        this.schoolSystem = schoolSystem== null ? 0 : schoolSystem;
    }

    public String getGradeid() {
        return gradeid;
    }

    public void setGradeid(String gradeid) {
        this.gradeid = gradeid == null ? null : gradeid.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade== null ? 0 : grade;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public Integer getNumofclass() {
        return numofclass;
    }

    public void setNumofclass(Integer numofclass) {
        this.numofclass = numofclass== null ? 0 : numofclass;
    }

    public Integer getNumofstu() {
        return numofstu;
    }

    public void setNumofstu(Integer numofstu) {
        this.numofstu = numofstu== null ? 0 : numofstu;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus == null ? null : campus.trim();
    }
}