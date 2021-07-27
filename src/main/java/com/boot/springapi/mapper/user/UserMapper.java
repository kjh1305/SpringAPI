package com.boot.springapi.mapper.user;

import com.boot.springapi.domain.user.User;
import com.boot.springapi.pagination.Pagination;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (email, password, name) VALUES(#{email}, #{password}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT * FROM user")
    List<User> select();

    @Select("SELECT * FROM user limit #{startIndex}, #{pageSize}")
    List<User> selectPaging(Pagination pagination);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User selectUserId(long id);

    @Select("SELECT * FROM user WHERE name=#{name}")
    User selectUserName(String name);

    @Select("SELECT * FROM user WHERE email=#{email}")
    Optional<User> selectUserEmail(String email);

    @Update("UPDATE user SET email=#{email}, password=#{password}, name=#{name}, det=#{det} WHERE id=#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(@Param("id") long id);

    @Select("SELECT COUNT(*) FROM user")
    int userCnt();
}
