package com.eteam.frame.persistence;

import com.eteam.frame.domain.Account;


public interface AccountMapper {

    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(String username, String password);

    int insertAccount(Account account);

    int insertProfile(Account account);

    int insertSignon(Account account);

    int updateAccount(Account account);

    int updateProfile(Account account);

    int updateSignon(Account account);

}
