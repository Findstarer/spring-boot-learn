package com.findstar.springbootlearn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.findstar.springbootlearn.contant.Sex;
import com.findstar.springbootlearn.model.vo.SimpleResponse;
import com.findstar.springbootlearn.model.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: findStar
 * @Date: 2021/10/6 3:52 下午
 */
@Slf4j
@Api("用户管理接口")
@RestController
@RequestMapping("/user")
public class UserManageController {

    private static final String OK = "OK";

    private ObjectMapper mapper = new ObjectMapper();

    @ApiImplicitParams({
            @ApiImplicitParam(name = "managerName", value = "管理账号名", defaultValue = "root",
                    required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "managerToken", value = "token", defaultValue = "123",
                    required = true, paramType = "header", dataTypeClass = String.class)
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "插入成功")
    })
    @PostMapping("/insert")
    public SimpleResponse<String> insert(@RequestBody User user) throws JsonProcessingException {

        log.info("insert user: {}", mapper.writeValueAsString(user));

        return SimpleResponse.<String>builder().code(1).data("OK").build();
    }


    @GetMapping("/select")
    public SimpleResponse<User> selectByName(@RequestParam("name") String name, @RequestParam("sex") Sex sex,
                                             @ApiIgnore("无用的参数") @RequestAttribute("unused_params") String unUsedParams) {
        log.info("select name: {}, Sex: {}", name, sex.getDecs());
        User user = User.builder().name(name).hometown("i don't known").build();
        return SimpleResponse.<User>builder().code(1).data(user).build();
    }

}
