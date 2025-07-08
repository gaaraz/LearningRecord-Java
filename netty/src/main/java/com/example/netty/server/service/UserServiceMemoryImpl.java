package com.example.netty.server.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: zzy
 * @createDate: 2025/6/24
 */
public class UserServiceMemoryImpl implements UserService{
    private Map<String, String> allUserMap = new ConcurrentHashMap<>();

    {
        allUserMap.put("zhangsan", "123");
        allUserMap.put("lisi", "123");
        allUserMap.put("wangwu", "123");
        allUserMap.put("zhaoliu", "123");
        allUserMap.put("qianqi", "123");
    }

    @Override
    public boolean login(String username, String password) {
        String pwd = allUserMap.get(username);
        if(pwd == null){
            return false;
        }
        return pwd.equals(password);
    }

    @Override
    public String sayHello(String name) {
        return "你好," + name;
    }
}
