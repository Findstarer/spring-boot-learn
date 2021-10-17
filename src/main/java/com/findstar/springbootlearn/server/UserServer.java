package com.findstar.springbootlearn.server;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.findstar.springbootlearn.contant.Sex;
import com.findstar.springbootlearn.mapper.UserMapper;
import com.findstar.springbootlearn.model.vo.User;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return userMapper.insert(user) > 0;
    }

    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    public List<User> getUserByName(String name) {
        Map<String, Object> map = ImmutableMap.of("name", name);
        return userMapper.selectByMap(map);
    }

    public boolean updateHometown(User user) {
        LambdaUpdateChainWrapper<User> wrapper = new LambdaUpdateChainWrapper<>(userMapper);
        wrapper.like(User::getName, user.getName()).eq(User::getSex, user.getSex());
        wrapper.set(User::getHometown, user.getHometown());
        return wrapper.update();
    }

    public boolean delete(String name, Sex sex) {
        ImmutableMap<String, Object> map = ImmutableMap.of("name", name, "sex", sex);
        return userMapper.deleteByMap(map) > 0;
    }
}
