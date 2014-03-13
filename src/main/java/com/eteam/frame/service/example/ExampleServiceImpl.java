package com.eteam.frame.service.example;

import com.eteam.frame.dao.example.ExampleDaoImpl;
import com.eteam.frame.service.BaseService;
import com.eteam.frame.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mway on 14-3-4.
 */
@Service
public class ExampleServiceImpl extends BaseService {

    @Autowired
    ExampleDaoImpl exampleDao;

    // @Transactional(rollbackFor = ServiceException.class, propagation = Propagation.REQUIRED)
    public void doInsert() {
        int iRtn = 0;
        // iRtn = exampleDao.doInsert(1);
        iRtn = exampleDao.doInsert(1);
        throw new ServiceException("111");

    }
}
