//package com.app.dtu.bean.dao.impl;
//
//import com.app.dtu.bean.model.MonitorManagerDevice;
//import org.apache.ibatis.annotations.*;
//import org.apache.ibatis.type.JdbcType;
//
//import java.util.List;
//@Mapper
//public interface MonitorManagerDeviceMapper {
//    @Delete({
//        "delete from monitor_manager_device",
//        "where id = #{id,jdbcType=BIGINT}"
//    })
//    int deleteByPrimaryKey(Long id);
//
//    @Insert({
//        "insert into monitor_manager_device (id, create_date, ",
//        "device_id, warn_desc, ",
//        "warn_list)",
//        "values (#{id,jdbcType=BIGINT}, #{createDate,jdbcType=BIGINT}, ",
//        "#{deviceId,jdbcType=BIGINT}, #{warnDesc,jdbcType=VARCHAR}, ",
//        "#{warnList,jdbcType=INTEGER})"
//    })
//    int insert(MonitorManagerDevice record);
//
//    @Select({
//        "select",
//        "id, create_date, device_id, warn_desc, warn_list",
//        "from monitor_manager_device",
//        "where id = #{id,jdbcType=BIGINT}"
//    })
//    @Results({
//        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
//        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT),
//        @Result(column="device_id", property="deviceId", jdbcType=JdbcType.BIGINT),
//        @Result(column="warn_desc", property="warnDesc", jdbcType=JdbcType.VARCHAR),
//        @Result(column="warn_list", property="warnList", jdbcType=JdbcType.INTEGER)
//    })
//    MonitorManagerDevice selectByPrimaryKey(Long id);
//
//    @Select({
//        "select",
//        "id, create_date, device_id, warn_desc, warn_list",
//        "from monitor_manager_device"
//    })
//    @Results({
//        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
//        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT),
//        @Result(column="device_id", property="deviceId", jdbcType=JdbcType.BIGINT),
//        @Result(column="warn_desc", property="warnDesc", jdbcType=JdbcType.VARCHAR),
//        @Result(column="warn_list", property="warnList", jdbcType=JdbcType.INTEGER)
//    })
//    List<MonitorManagerDevice> selectAll();
//
//    @Update({
//        "update monitor_manager_device",
//        "set create_date = #{createDate,jdbcType=BIGINT},",
//          "device_id = #{deviceId,jdbcType=BIGINT},",
//          "warn_desc = #{warnDesc,jdbcType=VARCHAR},",
//          "warn_list = #{warnList,jdbcType=INTEGER}",
//        "where id = #{id,jdbcType=BIGINT}"
//    })
//    int updateByPrimaryKey(MonitorManagerDevice record);
//}