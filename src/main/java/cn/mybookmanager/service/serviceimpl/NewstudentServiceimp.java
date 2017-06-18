package cn.mybookmanager.service.serviceimpl;

import cn.mybookmanager.mapper.BookinfoMapper;
import cn.mybookmanager.mapper.ClassinfoMapper;
import cn.mybookmanager.mapper.GradeinfoMapper;
import cn.mybookmanager.mapper.TeachingplanMapper;
import cn.mybookmanager.model.Classinfo;
import cn.mybookmanager.model.Gradeinfo;
import cn.mybookmanager.model.Teachingplan;
import cn.mybookmanager.service.NewstudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/23.
 */
@Service("newstudentServiceimp")
public class NewstudentServiceimp implements NewstudentService {
    @Autowired
    public BookinfoMapper bookinfoMapper;
    @Autowired
    public TeachingplanMapper teachingplanMapper;
    @Autowired
    public GradeinfoMapper gradeinfoMapper;
    @Autowired
    public ClassinfoMapper classinfoMapper;

    /**
     * 初始化教学计划
     * @param map
     * @return
     */
    @Transactional
    @Override
    public Boolean initializenewstudentPlan( Map<String, Gradeinfo> map) {
        Boolean result=true;
        if (map.size()<=0)return false;
        try{
            for (Map.Entry<String, Gradeinfo> entry : map.entrySet()) {
             //   System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                List<Teachingplan> teachingplens = teachingplanMapper.selectPlanBygradeId(entry.getKey());
                if (teachingplens.size()<=0)continue;//该专业暂时没有教学计划就跳过
                //得到某专业上届的班级
                for (Teachingplan teachingplan:teachingplens  ) {
                    teachingplan.setGradeid(entry.getValue().getGradeid());//重置后插入
                    int insert = teachingplanMapper.insert(teachingplan);
                    if (insert<=0)
                    {   result=false;//插入返回值小于零则表明有异常
                        throw new RuntimeException("插入教学计划异常");
                    }
                }
            }
        }  catch ( RuntimeException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 初始化班级
     * @param map
     * @return
     */
    @Transactional
    @Override
    public Boolean initializenewstudentClass( Map<String, Gradeinfo> map) {
        Boolean result=true;
        if (map.size()<=0)return false;
        try{
            for (Map.Entry<String, Gradeinfo> entry : map.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                List<Classinfo> classinfos = classinfoMapper.selectClassByGradeId(entry.getKey());//得到某专业上届的班级
                if (classinfos.size()<=0)continue;//该专业下面没有班级就跳过
                for (Classinfo classinfo:classinfos  ) {
                    classinfo.setGradeid(entry.getValue().getGradeid());
                    int insert = classinfoMapper.insert(classinfo);
                    if (insert<=0)
                    {   result=false;//插入返回值小于零则表明有异常
                        throw new RuntimeException("插入班级异常");
                    }
                }


            }


        }  catch ( RuntimeException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 初始化新生年级
     * @param grade
     * @return
     */
    @Transactional
    @Override
    public  Map<String,Gradeinfo> initializenewstudentGrade(int grade) {

        Map<String,Gradeinfo> newAndOld=new HashMap<String,Gradeinfo>();//新旧对照的map,key为上届的gradeid,value为本届同专业的年级
        List<String> strings = gradeinfoMapper.selectBygrade(grade - 1);//拿到上届所有的年级id
        try{
            newAndOld.clear();//先清零
        for (String str:strings ) {
            Gradeinfo oldgradeinfo = gradeinfoMapper.selectByPrimaryKey(str);
            oldgradeinfo.setGrade(grade);
            int insert = gradeinfoMapper.insert(oldgradeinfo);
            if (insert<=0)
            {   newAndOld=null;//插入返回值则表明有异常
                throw new RuntimeException("插入年级异常");
            }
            newAndOld.put(str,oldgradeinfo);

        }

        }  catch ( RuntimeException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return newAndOld;
    }


    /**
     * 初始化第grade届新生的所有信息
     * @param grade 要生成的那一届
     * @return
     */
    @Override
    public Boolean initializenewstudentInOrder(int grade) {
        List<String> strings = gradeinfoMapper.selectBygrade(grade);
        if (strings.size()>0) return false;//该届学生已存在

        //1.生成第grade届新生
        Map<String, Gradeinfo> GradeinfoMap = initializenewstudentGrade(grade);
        if (GradeinfoMap.size()<=0)   return false;
        //2.初始化第grade届的班级
        Boolean initclass = initializenewstudentClass(GradeinfoMap);
        if (!initclass) return false;
        //3.初始化第grade届的教学计划
        Boolean initplan = initializenewstudentPlan(GradeinfoMap);
        if (!initplan) return false;
        return true;
    }
}
