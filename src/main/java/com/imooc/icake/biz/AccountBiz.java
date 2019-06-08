package com.imooc.icake.biz;

import com.imooc.icake.entity.Account;

public interface AccountBiz {
    Account login(String name,String password);
}
