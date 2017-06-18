package cn.mybookmanager.controller;

import cn.mybookmanager.model.Bookrecords;
import cn.mybookmanager.model.Sendbookrecord;
import cn.mybookmanager.service.BookPurchasingService;
import cn.mybookmanager.service.HistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by 汪冉冉 on 2017/6/1.
 */

@Controller
@RequestMapping("/HistoryContrller")
public class HistoryContrller {
    private Logger logger = Logger.getLogger(HistoryContrller.class);
    @Autowired
    public HistoryService historyService;
    @Autowired
    public BookPurchasingService bookPurchasingService;
    public String parsePurchasingtime(String purchasingTime){
        if (purchasingTime==null)return null;
        String syearstring= purchasingTime.substring(0, 4);
        String smonthstring = purchasingTime.substring(5,6);
        String endyearstring= purchasingTime.substring(7, 11);
        String endmonthstring = purchasingTime.substring(12, 13);
        purchasingTime=syearstring+"年"+smonthstring+"月至"+endyearstring+"年"+endmonthstring+"月";
        return purchasingTime;
    }
      /**
     * 加载购买的教材清单记录
     * @return
     */
    @RequestMapping("/findAllBuyrecords")
     @ResponseBody
public  List<Bookrecords> findAllBuyrecords(String purchasingTime){
     if (purchasingTime==null)return null;

        return historyService.selectAllpurchasingrecord(parsePurchasingtime(purchasingTime));
    }
    @RequestMapping("/findAllDistrirecords")
    @ResponseBody
    public  Map<String, List<Sendbookrecord>> findAllDistrirecords(String purchasingTime){
        Map<String, List<Sendbookrecord>> allsendbookrecords = historyService.findAllsendbookrecords(parsePurchasingtime(purchasingTime));
logger.info(allsendbookrecords);
        return allsendbookrecords;

    }



}
