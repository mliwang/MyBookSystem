package cn.mybookmanager.service.serviceimpl;

import cn.mybookmanager.mapper.BookrecordsMapper;
import cn.mybookmanager.mapper.SendbookrecordMapper;
import cn.mybookmanager.model.Bookrecords;
import cn.mybookmanager.model.Sendbookrecord;
import cn.mybookmanager.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13.
 */
@Service("historyService")
public class HistoryServiceimp implements HistoryService {
    @Autowired
    public SendbookrecordMapper sendbookrecordMapper;
    @Autowired
    public BookrecordsMapper bookrecordsMapper;
    @Override
    public List<Bookrecords> selectAllpurchasingrecord( String purchasingTime) {
        Bookrecords bookrecords = new Bookrecords();
        bookrecords.setPurchasetime(purchasingTime);
        return bookrecordsMapper.selectBookrecordsByPrimary(bookrecords);
    }
    @Override
    public Map<String, List<Sendbookrecord>> findAllsendbookrecords(String forsemster) {
        Sendbookrecord sendbookrecord3 = new Sendbookrecord();
        sendbookrecord3.setForsemeter(forsemster);
        List<Sendbookrecord> sendbookrecords = sendbookrecordMapper.selectSendbookrecord(sendbookrecord3);
       Map<String, List<Sendbookrecord>> stringListHashMap = new HashMap<String, List<Sendbookrecord>>();//装各次的发书记录
        for (Sendbookrecord s:sendbookrecords) {
            String sendtime = s.getSendtime();
            if (stringListHashMap.containsKey(sendtime)){
                List<Sendbookrecord> sendbookrecords1 = stringListHashMap.get(sendtime);
                sendbookrecords1.add(s);
                stringListHashMap.put(sendtime,sendbookrecords1);
            }
            else{
                ArrayList<Sendbookrecord> sendbookrecords1 = new ArrayList<Sendbookrecord>();
                sendbookrecords1.add(s);
                stringListHashMap.put(sendtime,sendbookrecords1);
            }

        }

        return stringListHashMap;
    }
}
