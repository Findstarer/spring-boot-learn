package com.findstar.springbootlearn.contant;

/**
 * @Author: findStar
 * @Date: 2021/10/6 8:31 下午
 */
public enum Sex {
    MAN(1, "男性"),
    WOMAN(2, "女性"),
    ;

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
