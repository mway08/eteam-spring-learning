package com.eteam.frame.service.example;

import com.eteam.frame.service.BaseService;
import org.springframework.stereotype.Service;


@Service
public class ExampleServiceImpl extends BaseService {

    /*@Autowired
    ExampleDaoImpl exampleDao;

    // @Transactional(rollbackFor = ServiceException.class, propagation = Propagation.REQUIRED)
    public void doInsert() {
        int iRtn = 0;
        // iRtn = exampleDao.doInsert(1);
        iRtn = exampleDao.doInsert(1);
        throw new ServiceException("111");

    }*/
}
