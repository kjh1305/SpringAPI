package com.boot.springapi.mapper.user;

import com.boot.springapi.domain.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (email, password, name) VALUES(#{email}, #{password}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT * FROM user")
    List<User> select();

    @Update("UPDATE user SET email=#{email}, password=#{password}, name={#name} WHERE id=#{id}")
    User update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(@Param("id") long id);
}
