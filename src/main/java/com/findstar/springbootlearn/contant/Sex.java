package com.findstar.springbootlearn.contant;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @Author: findStar
 * @Date: 2021/10/6 8:31 下午
 */
public enum Sex {
    MAN(0, "男性"),
    WOMAN(1, "女性"),
    ;

    @EnumValue
    private final int code;
    private final String Decs;

    Sex(int code, String decs) {
        this.code = code;
        Decs = decs;
    }

    public int getCode() {
        return code;
    }

    public String getDecs() {
        return Decs;
    }
}
