/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service;

import cn.mybookmanager.model.Sendbookrecord;
import cn.mybookmanager.pojo.FullDistribute;
import cn.mybookmanager.pojo.FullTeachingPlan;
import cn.mybookmanager.pojo.Fullbookrecords;

import java.util.List;

public interface BookPurchasingService {
    /**
     * 找到所有需要买的书
     * @return
     */
    List<Fullbookrecords> findAllBookPurchasing(List<FullTeachingPlan> plans, String purchasing);

    /**
     * 形成购书记录
     * @param allrecords
     * @return
     */
     boolean addToRecords(List<Fullbookrecords> allrecords);

    /**
     * 找到所有要发书的班级，及其课程计划
     * @return
     */
    List<FullDistribute> findClassDis(String purchasing, List<FullTeachingPlan> plans);
    /**
     * 形成发书记录
     * @param allrecords
     * @return
     */
    boolean addToDistributeRecords(List<Sendbookrecord> allrecords);
}
