package com.itchenzn.bootcrm.service;

import com.itchenzn.bootcrm.pojo.BaseDict;
import com.itchenzn.bootcrm.pojo.User;

import java.util.List;

public interface BaseDictService {
    //根据类别代码查询
    List<BaseDict> findBaseDictByTypeCode(String typecode);


}
