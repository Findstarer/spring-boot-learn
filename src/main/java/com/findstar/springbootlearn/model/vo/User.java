package com.findstar.springbootlearn.model.vo;

import com.findstar.springbootlearn.contant.Sex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: findStar
 * @Date: 2021/10/6 4:02 下午
 */
@ApiModel("用户信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("性别")
    private Sex sex;
    @ApiModelProperty("家乡")
    private String hometown;
}
