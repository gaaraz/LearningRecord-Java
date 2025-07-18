package com.example.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;


    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> CommonResult success(T data){
        return new CommonResult(200, "success", data);
    }

    public static <T> CommonResult success(String msg, T data){
        return new CommonResult(200, msg, data);
    }

    public static <T> CommonResult error(){
        return new CommonResult(0, "error");
    }

    public static <T> CommonResult error(Integer code, String msg){
        return new CommonResult(code, msg);
    }
}
