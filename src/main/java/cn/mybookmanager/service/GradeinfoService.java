/**
 * Created by WangRanran on 2017/3/15.
 */
package cn.mybookmanager.service;

import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.pojo.KeyfiledOfGradeinfo;


import java.util.List;


public interface GradeinfoService {

    /**
     * 查询所有班级信息
     * @return
     * @throws Exception
     */
      List<Gradeinfo> getAllgradeinfo() ;

    /**
     * 按年级id查找
     * @param gradeid
     * @return
     */
      Gradeinfo selectBygradeid(String gradeid);
    /**
     * 得到所有学院
     * @return
     */
      List<String> getAllCollege() ;

    /**
     * 查找某学院的所有专业
     * @param college
     * @return
     */
      List<String> getProfessionBycollege(String college) ;


    /**
     * 得到所有专业
     * @return
     */
      List<String> getAllProfession();
    /**
     * 输入年级信息中的关键信息（学院，专业，年级，校区）查找年级id
     * @param keyfiledOfGradeinfo
     * @return
     */
      Gradeinfo getGradeId(KeyfiledOfGradeinfo keyfiledOfGradeinfo);

    /**
     * 找某个专业的相关信息
     * @param profession
     * @return
     */
      List<Gradeinfo> findgradeByProfession(String profession);

    /**
     * 增加一条年级信息
     * @param gradeinfo
     * @return
     * @throws Exception
     */
    int add (Gradeinfo gradeinfo);
    /**
     *
     * 删除年级信息（删除已毕业学生也通过该方法，在前台获得已毕业年级id）
     * @param gradeid
     * @return
     */
     int deleteByGradeid(String gradeid);

    /**
     * 根据gradeId修改年级信息表
     * @param gradeinfo
     * @return
     */
     int updateByGradeid(Gradeinfo gradeinfo);

    /**
     * 重新统计各个年级订书的人数
     * @return
     */
    boolean CaculateStudent();


}
