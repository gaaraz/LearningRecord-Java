package com.example.server.session;

import lombok.Data;

import java.util.Collections;
import java.util.Set;

/**
 * @description: 聊天群组
 * @author: zzy
 * @createDate: 2025/6/24
 */
@Data
public class Group {
    // 聊天群组名称
    private String name;

    // 聊天群组成员
    private Set<String> members;

    public static final Group EMPTY_GROUP = new Group("empty", Collections.emptySet());

    public Group(String name, Set<String> members) {
        this.name = name;
        this.members = members;
    }
}
