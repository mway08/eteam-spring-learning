package com.eteam.frame.service.account;

import com.eteam.frame.domain.Account;

import java.util.List;

/**
 * Created by liufm on 14-3-13.
 */
public interface AccountService {
    public int doNewAccount(Account account);
    public List<Account> getAccountList(Account account);
}
