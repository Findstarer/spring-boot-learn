package com.findstar.springbootlearn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.findstar.springbootlearn.contant.Sex;
import com.findstar.springbootlearn.model.vo.SimpleResponse;
import com.findstar.springbootlearn.model.vo.User;
import com.findstar.springbootlearn.server.UserServer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * @Author: findStar
 * @Date: 2021/10/6 3:52 下午
 */
@Slf4j
@Api("用户管理接口")
@RestController
@RequestMapping("/user")
@WebServlet()
public class UserManageController {

    private final ObjectMapper mapper = new ObjectMapper();

    private final UserServer userServer;

    public UserManageController(UserServer userServer) {
        this.userServer = userServer;
    }

    @PostMapping("/insert")
    public SimpleResponse<Boolean> insert(@RequestBody User user) throws JsonProcessingException {
        boolean done = userServer.insert(user);
        log.info("insert user: {}, done: {}", mapper.writeValueAsString(user), done);
        return SimpleResponse.<Boolean>builder().code(1).data(done).build();
    }

    @GetMapping("/select")
    public SimpleResponse<List<User>> selectByName(@RequestParam("name") String name) throws JsonProcessingException {
        List<User> users = userServer.getUserByName(name);
        log.info("select name: {}, user: {}", name, mapper.writeValueAsString(users));
        return SimpleResponse.<List<User>>builder().code(1).data(users).build();
    }

    @GetMapping("/all")
    public SimpleResponse<List<User>> getAll() {
        List<User> users = userServer.getAll();
        return SimpleResponse.<List<User>>builder().code(1).data(users).build();
    }

    @PutMapping("/update/hometown")
    public SimpleResponse<Boolean> updateHometown(@RequestBody User user) {
        boolean update = userServer.updateHometown(user);
        return SimpleResponse.<Boolean>builder().code(1).data(update).build();
    }

    @DeleteMapping("/delete")
    public SimpleResponse<Boolean> delete(@RequestParam("name") String name, @RequestParam("sex") Sex sex) {
        boolean delete = userServer.delete(name, sex);
        return SimpleResponse.<Boolean>builder().code(1).data(delete).build();
    }

}
