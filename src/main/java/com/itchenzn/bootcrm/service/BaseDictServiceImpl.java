package com.itchenzn.bootcrm.service;

import com.itchenzn.bootcrm.dao.BaseDictDao;
import com.itchenzn.bootcrm.dao.UserDao;
import com.itchenzn.bootcrm.pojo.BaseDict;
import com.itchenzn.bootcrm.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("baseDictService")
public class BaseDictServiceImpl implements BaseDictService {
    @Autowired
    private BaseDictDao baseDictDao;


    @Override
    public List<BaseDict> findBaseDictByTypeCode(String typecode) {
       return baseDictDao.selectBaseDictByTypeCode(typecode);

    }
}
