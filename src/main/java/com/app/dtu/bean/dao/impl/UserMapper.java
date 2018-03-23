package com.app.dtu.bean.dao.impl;

import com.app.dtu.bean.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserMapper {
    @Delete({
        "delete from user",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long userId);

    @Insert({
        "insert into user (user_id, username, ",
        "password, nickname, ",
        "sex, birth, cardid, ",
        "phone, dm_id, role_id, ",
        "state, userf, address, ",
        "houses, floor)",
        "values (#{userId,jdbcType=BIGINT}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{nickname,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=VARCHAR}, #{birth,jdbcType=VARCHAR}, #{cardid,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{dmId,jdbcType=VARCHAR}, #{roleId,jdbcType=BIGINT}, ",
        "#{state,jdbcType=VARCHAR}, #{userf,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{houses,jdbcType=VARCHAR}, #{floor,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @Select({
        "select",
        "user_id, username, password, nickname, sex, birth, cardid, phone, dm_id, role_id, ",
        "state, userf, address, houses, floor",
        "from user",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="birth", property="birth", jdbcType=JdbcType.VARCHAR),
        @Result(column="cardid", property="cardid", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="dm_id", property="dmId", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="userf", property="userf", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="houses", property="houses", jdbcType=JdbcType.VARCHAR),
        @Result(column="floor", property="floor", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Long userId);

    @Select({
        "select",
        "user_id, username, password, nickname, sex, birth, cardid, phone, dm_id, role_id, ",
        "state, userf, address, houses, floor",
        "from user"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="birth", property="birth", jdbcType=JdbcType.VARCHAR),
        @Result(column="cardid", property="cardid", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="dm_id", property="dmId", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="state", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="userf", property="userf", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="houses", property="houses", jdbcType=JdbcType.VARCHAR),
        @Result(column="floor", property="floor", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectAll();

    @Update({
        "update user",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "birth = #{birth,jdbcType=VARCHAR},",
          "cardid = #{cardid,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "dm_id = #{dmId,jdbcType=VARCHAR},",
          "role_id = #{roleId,jdbcType=BIGINT},",
          "state = #{state,jdbcType=VARCHAR},",
          "userf = #{userf,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "houses = #{houses,jdbcType=VARCHAR},",
          "floor = #{floor,jdbcType=VARCHAR}",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);
}