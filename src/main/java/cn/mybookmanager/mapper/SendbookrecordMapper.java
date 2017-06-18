package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Sendbookrecord;

import java.util.List;

public interface SendbookrecordMapper {


    int insert(Sendbookrecord record);

    int insertSelective(Sendbookrecord record);

    /**
     * 查询发书记录
     * @return
     */
       List<Sendbookrecord> selectSendbookrecord( Sendbookrecord record);


}