package cn.mybookmanager.mapper;

import cn.mybookmanager.model.Supplierandbookrelations;
import cn.mybookmanager.model.SupplierandbookrelationsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierandbookrelationsMapper {
    int countByExample(SupplierandbookrelationsExample example);

    int deleteByExample(SupplierandbookrelationsExample example);

    int insert(Supplierandbookrelations record);

    int insertSelective(Supplierandbookrelations record);

    List<Supplierandbookrelations> selectByExample(SupplierandbookrelationsExample example);

    int updateByExampleSelective(@Param("record") Supplierandbookrelations record, @Param("example") SupplierandbookrelationsExample example);

    int updateByExample(@Param("record") Supplierandbookrelations record, @Param("example") SupplierandbookrelationsExample example);
}