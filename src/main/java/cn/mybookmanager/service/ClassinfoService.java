/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service;

import cn.mybookmanager.mapper.ClassinfoMapper;
import cn.mybookmanager.pojo.FullClassinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassinfoService {
    @Autowired
    public ClassinfoMapper classinfoMapper;
    /**
     * 查询所有班级信息
     * @return
     * @throws Exception
     */
    public  List<FullClassinfo> getAllClassinfo() {

        return classinfoMapper.selectAllClassinfo();
    }



}
