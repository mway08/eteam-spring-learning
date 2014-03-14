package com.eteam.frame.web.account;

import com.eteam.frame.domain.Account;
import com.eteam.frame.service.account.AccountService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liufm on 14-3-12.
 */
@Controller
public class AccountController {
    @Resource
    AccountService accountService;

    @RequestMapping
    @ResponseBody
    public Map getAccount( String id,  String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        Account account = new Account();
        account.setUsername(name);
        account.setStatus(id);
        List<Account> accountList = accountService.getAccountList(account);
        return map;
    }

    @RequestMapping(value = "/{day}", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap getModel(@PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date day) {
        ModelMap model = new ModelMap();
        return model;
    }
}
