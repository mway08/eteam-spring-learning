package com.eteam.frame.service.account;

import com.eteam.frame.domain.Account;
import com.eteam.frame.persistence.AccountMapper;
import com.eteam.frame.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liufm on 14-3-12.
 */
@Service
public class AccountServiceImpl extends BaseService implements AccountService{
    @Resource
    AccountMapper accountMapper;

    /**
     * 新增用户
     * @param account
     * @return
     */
    public int doNewAccount(Account account) {
        int iRtn = 0;
        accountMapper.insertAccount(account);
        return iRtn;
    }

    public List<Account> getAccountList(Account account) {
        return new ArrayList<Account>();
    }

}
