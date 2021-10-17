package com.findstar.springbootlearn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.findstar.springbootlearn.contant.Sex;
import com.findstar.springbootlearn.model.vo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : findStar
 * @date :  2021/10/7 2:23 下午
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    // @Insert("INSERT INTO `users` (`name`, `sex`, `hometown`) " +
    //         "VALUES(#{name}, #{sex}, #{hometown}, #{isVip})")
    // // 实现id字段回填
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    // int insert(User user);

    // @Select("SELECT * FROM `users`")
    // List<User> getAll();

    // @Select("SELECT * FROM users where name = #{name}")
    // List<User> getUserByName(String name);

    // @Update("UPDATE users SET hometown=#{hometown} WHERE name=#{name} AND sex=#{sex}")
    // boolean update(User user);

    // @Delete("DELETE FROM users WHERE name=#{name} AND sex=#{sex}")
    // boolean delete(String name, Sex sex);

}
