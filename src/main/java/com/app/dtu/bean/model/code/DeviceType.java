package com.app.dtu.bean.model.code;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "device_type")
public class DeviceType {
    // 终端类别编码
    private int code;
    // 终端类别名称
    private String name;

}
