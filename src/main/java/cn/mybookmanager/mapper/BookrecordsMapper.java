package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Bookrecords;

import java.util.List;

public interface BookrecordsMapper {
    /**
     *生成 新购书记录
     * @param record
     * @return
     */
    int insert(Bookrecords record);

    /**
     * 根据购书记录的关键字段查看（主要有  bookMemuId, bookid, supplier,purchaseTime，可以是其中一些字段的组合）
     * @param bookrecord
     * @return
     */
    List<Bookrecords>  selectBookrecordsByPrimary(Bookrecords bookrecord);

}