package cn.mybookmanager.pojo;

import cn.mybookmanager.model.Bookinfo;
import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.model.Teachingplan;

/**
 * Created by 汪冉冉 on 2017/4/26.
 */
public class FullTeachingPlan extends Teachingplan implements java.io.Serializable {
    private Gradeinfo gradeinfo;
    private Bookinfo bookinfo;

    public FullTeachingPlan() {
    }

    public FullTeachingPlan(Gradeinfo gradeinfo, Bookinfo bookinfo) {
        this.gradeinfo = gradeinfo;
        this.bookinfo = bookinfo;
    }

    public FullTeachingPlan(String planid, String gradeid, String coursename, String bookid, Integer semester, Gradeinfo gradeinfo, Bookinfo bookinfo) {
        super(planid, gradeid, coursename, bookid, semester);
        this.gradeinfo = gradeinfo;
        this.bookinfo = bookinfo;
    }


    public Gradeinfo getGradeinfo() {
        return gradeinfo;
    }

    public void setGradeinfo(Gradeinfo gradeinfo) {
        this.gradeinfo = gradeinfo;
    }

    public Bookinfo getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(Bookinfo bookinfo) {
        this.bookinfo = bookinfo;
    }
}
