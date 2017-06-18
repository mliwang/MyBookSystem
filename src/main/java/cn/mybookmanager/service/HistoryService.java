/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service;

import cn.mybookmanager.model.Bookrecords;
import cn.mybookmanager.model.Sendbookrecord;

import java.util.List;
import java.util.Map;

public interface HistoryService {
    /**
     * 展示所有的购书操作相关的
     * @return
     */
    List<Bookrecords> selectAllpurchasingrecord(String purchasingTime);

    /**
     *
     * @param forsemster
     * @return
     */

    Map<String, List<Sendbookrecord>> findAllsendbookrecords(String forsemster);


}
