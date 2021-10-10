package com.findstar.springbootlearn.server;

import com.findstar.springbootlearn.contant.Sex;
import com.findstar.springbootlearn.mapper.UserMapper;
import com.findstar.springbootlearn.model.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : findStar
 * @date : 2021/10/7 3:24 下午
 */
@Service
public class UserServer {

    private final UserMapper userMapper;

    public UserServer(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean insert(User user) {
        return userMapper.insert(user);
    }

    public List<User> getAll() {
        return userMapper.getAll();
    }

    public List<User> getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public boolean update(User user) {
        return userMapper.update(user);
    }

    public boolean delete(String name, Sex sex) {
        return userMapper.delete(name, sex);
    }
}
