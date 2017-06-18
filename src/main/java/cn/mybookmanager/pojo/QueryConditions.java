package cn.mybookmanager.pojo;

/**
 * Created by 汪冉冉 on 2017/4/30.
 */
public class QueryConditions implements java.io.Serializable {
     private String college;//学院

    private Integer grade;//年级

    private String profession;//专业

    private String campus;//校区

    private Integer semester;//学期

    public QueryConditions() {
    }

    public QueryConditions(String college, Integer grade, String profession, String campus, Integer semester) {
        this.college = college;
        this.grade = grade;
        this.profession = profession;
        this.campus = campus;
        this.semester = semester;
    }

    public String getCollege() {

        return college;
    }
    public void setCollege(String college) {
        this.college = college == null ? "" : college.trim();
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
        this.profession = profession == null ? "" : profession.trim();
    }


    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus == null ? "" : campus.trim();
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
