package cn.mybookmanager.pojo;

import cn.mybookmanager.model.Classinfo;
import java.util.List;

/**
 * Created by 汪冉冉 on 2017/6/15.
 * 包装类，某班级某个时段用的教学计划
 */
public class FullDistribute {
    private Classinfo  classinfo;
    private List< FullTeachingPlan> plans;
    private String  purchasingtime;

    public FullDistribute() {
    }

    public FullDistribute(Classinfo classinfo, List<FullTeachingPlan> plans, String purchasingtime) {
        this.classinfo = classinfo;
        this.plans = plans;
        this.purchasingtime = purchasingtime;
    }

    public Classinfo getClassinfo() {
        return classinfo;
    }

    public void setClassinfo(Classinfo classinfo) {
        this.classinfo = classinfo;
    }

    public List<FullTeachingPlan> getPlans() {
        return plans;
    }

    public void setPlans(List<FullTeachingPlan> plans) {
        this.plans = plans;
    }

    public String getPurchasingtime() {
        return purchasingtime;
    }

    public void setPurchasingtime(String purchasingtime) {
        this.purchasingtime = purchasingtime;
    }
}
