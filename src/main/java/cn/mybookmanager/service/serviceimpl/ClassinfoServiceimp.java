/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service.serviceimpl;

import cn.mybookmanager.mapper.ClassinfoMapper;
import cn.mybookmanager.model.Classinfo;
import cn.mybookmanager.pojo.FullClassinfo;
import cn.mybookmanager.service.ClassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classinfoService")
public class ClassinfoServiceimp implements ClassinfoService {
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

    /**
     * 按班级id查找班级信息
     * @param key
     * @return
     */
    public Classinfo selectByPrimaryKey(String key){
        return classinfoMapper.selectByPrimaryKey(key);
    }

    /**
     * 添加新班级
     * @param record
     * @return
     */
    public  int insertClass(Classinfo record){
        return classinfoMapper.insert(record);
    }
    public  int deleteClass(String classid){
        return classinfoMapper.deleteByPrimaryKey(classid);
    }
    /**
     * 根据班级的部分信息查找班级（主要找id）
     * @param classinfo
     * @return
     */
    public Classinfo getClassId(Classinfo classinfo){
        return classinfoMapper.selectClassId(classinfo);
    }
    public int updateClassinfo(Classinfo classinfo){
        return classinfoMapper.updateByPrimaryKey(classinfo);
    }


}
