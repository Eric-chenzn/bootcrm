package com.itchenzn.bootcrm.dao;

import com.itchenzn.bootcrm.pojo.BaseDict;
import com.itchenzn.bootcrm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典
 */


@Repository
public interface BaseDictDao {
   //根据类别代码查询数据字典
    List<BaseDict> selectBaseDictByTypeCode(String typecode);

}
