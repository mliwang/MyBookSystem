/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service.serviceimpl;

import cn.mybookmanager.mapper.GradeinfoMapper;
import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.pojo.KeyfiledOfGradeinfo;
import cn.mybookmanager.service.GradeinfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("gradeinfoServiceimp")
public class GradeinfoServiceimp implements GradeinfoService {
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
    public  Gradeinfo selectBygradeid(String gradeid){

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
     * 查找某学院的所有专业
     * @param college
     * @return
     */
    public  List<String> getProfessionBycollege(String college) {

        return gradeinfoMapper.selectAllProfessionByCollege(college.trim());
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
    public  Gradeinfo getGradeId(KeyfiledOfGradeinfo keyfiledOfGradeinfo){
        return gradeinfoMapper.selectgradeId( keyfiledOfGradeinfo );
    }

    /**
     * 找某个专业的相关信息
     * @param profession
     * @return
     */
    public  List<Gradeinfo> findgradeByProfession(String profession){
        return gradeinfoMapper.selectgradeByProfession(profession);
    }

    /**
     * 增加一条年级信息
     * @param gradeinfo
     * @return
     * @throws Exception
     */
   public int add (Gradeinfo gradeinfo){
       //TODO

       return gradeinfoMapper.insert(gradeinfo);
    }
    /**
     *
     * 删除年级信息（删除已毕业学生也通过该方法，在前台获得已毕业年级id）
     * @param gradeid
     * @return
     */
    public int deleteByGradeid(String gradeid){
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
    /**
     * 统计各个年级订书的人数
     * @return
     */
    public boolean CaculateStudent(){
        int i=0;
        List<Gradeinfo> gradeinfos = gradeinfoMapper.calculateBygradeId();
       // System.out.println("统计到的年级数量"+gradeinfos.size());
        for (Gradeinfo g:gradeinfos) {
            Gradeinfo gradeinfo1 = gradeinfoMapper.selectByPrimaryKey(g.getGradeid());
            gradeinfo1.setNumofstu(g.getNumofstu());
            gradeinfo1.setNumofclass(g.getNumofclass());
            i = gradeinfoMapper.updateByPrimaryKey(gradeinfo1);
            if (i<=0)break;
        }
        if (i<=0){
            System.out.print("统计失败");
            return false;}
        return true;
    }

}
