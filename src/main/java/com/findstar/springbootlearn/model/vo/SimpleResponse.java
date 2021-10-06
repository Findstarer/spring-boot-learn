package com.findstar.springbootlearn.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: findStar
 * @Date: 2021/10/6 4:10 下午
 */
@ApiModel("api请求通用返回类型")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResponse<T> {

    @ApiModelProperty("业务code")
    private int code;

    @ApiModelProperty("相应信息")
    private String msg;

    @ApiModelProperty("接口返回数据")
    private T data;
}
