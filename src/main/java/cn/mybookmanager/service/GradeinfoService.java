/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service;

import cn.mybookmanager.mapper.GradeinfoMapper;
import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.pojo.KeyfiledOfGradeinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeinfoService {
    @Autowired
    public GradeinfoMapper gradeinfoMapper;
    /**
     * 查询所有班级信息
     * @return
     * @throws Exception
     */
    public  List<Gradeinfo> getAllgradeinfo() {

        return gradeinfoMapper.selectAllgrade();
    }

    /**
     * 按年级id查找
     * @param gradeid
     * @return
     */
    public  Gradeinfo selectBygradeid(Integer gradeid){
        return gradeinfoMapper.selectByPrimaryKey(gradeid);
    }

    /**
     * 得到所有学院
     * @return
     */
    public  List<String> getAllCollege() {

        return gradeinfoMapper.selectAllCollege();
    }
    /**
     * 得到所有专业
     * @return
     */
    public  List<String> getAllProfession() {

        return gradeinfoMapper.selectAllProfession();
    }

    /**
     * 输入年级信息中的关键信息（学院，专业，年级，校区）查找年级id
     * @param keyfiledOfGradeinfo
     * @return
     */
    public  int getGradeId(KeyfiledOfGradeinfo keyfiledOfGradeinfo){
        return gradeinfoMapper.selectgradeId( keyfiledOfGradeinfo );
    }

    /**
     * 增加一条年级信息
     * @param gradeinfo
     * @return
     * @throws Exception
     */
   public int add (Gradeinfo gradeinfo){
       return gradeinfoMapper.insert(gradeinfo);
    }
    /**
     *
     * 删除年级信息（删除已毕业学生也通过该方法，在前台获得已毕业年级id）
     * @param gradeid
     * @return
     */
    public int deleteByGradeid(Integer gradeid){
        return gradeinfoMapper.deleteByPrimaryKey(gradeid);
    }

    /**
     * 根据gradeId修改年级信息表
     * @param gradeinfo
     * @return
     */
    public int updateByGradeid(Gradeinfo gradeinfo){
        return gradeinfoMapper.updateByPrimaryKey(gradeinfo);
    }


}
