package com.app.dtu.bean.model;

import com.app.dtu.bean.Message;
import com.app.dtu.config.DtuConfig;
import com.app.dtu.redis.RedisClient;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 设备的基础类
 * 用于存储设备的基础信息
 * 其他的走具体的设备处理， 数据库中会表示为一个存储具体设备信息的码表
 * 其他的表根据device_id与之做关联，其他的表示具体上报的数据信息存储
 * 现在需要完全分离出来，仅仅只只是设备类别的码表
 */

@Entity
@Table(name = DtuConfig.DTU_TABLE_PRIFIX + "device")
public class Device implements DeviceDataDeal, ParseToEntityAdapter<Device>, Serializable{

    private static final Logger logger = LoggerFactory.getLogger(Device.class);

    public Device(){}

    public Device(Message message) {
        this.message = message;
    }
    // 表唯一性ID,表示设备在数据库中唯一存储的ID， 业务ID， 与数据无关
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 类别编号
    private String typeCode;

    // 类别名称
    private String typeName;


    // 型号编号
    private String modelCode;

    // 型号名称
    private String modelName;

    // 型号
    private String model;

    private long createTime;
    // 该字段不应该被数据库所存储


    @Transient
    private Message message;

    public Message getMessage() {
        return message;
    }

    @Override
    public boolean isChange() {
        return false;
    }

    @Override
    public StringRedisTemplate getRedisClient() {
        return null;
    }

    @Override
    public DataService getService() {
        return null;
    }

    @Override
    public void buildWarnTime() {
        return;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public Device buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return message;
    }

    @Override
    public RedisClient redisClient() {
        return redisClient();
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    /**
     * 此方法需要子类去重写,否则无法生成实体
     * @param message
     * @return
     */
    @Override
    public Device generateEntity(Message message) {
        DeviceTypeName deviceTypeNameEnum = message.parseDeviceModelEnum();
        if (Objects.nonNull(deviceTypeNameEnum)){
            this.createTime = new Date().getTime();
            this.modelCode = deviceTypeNameEnum.getModelCode();
            this.modelName = deviceTypeNameEnum.getModelName();
            this.model = deviceTypeNameEnum.getDeviceModelCode();
            this.typeCode = deviceTypeNameEnum.getTypeCode();
            this.typeName = deviceTypeNameEnum.getTypeName();
            return this;
        }
        return null;
    }

    @Override
    public boolean execute() {
        try{
            ServiceItem.deviceService.save(getStorageEntity());
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
        }
        return true;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", typeCode='" + typeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", modelCode='" + modelCode + '\'' +
                ", modelName='" + modelName + '\'' +
                ", model='" + model + '\'' +
                ", createTime=" + createTime +
                ", message=" + message +
                '}';
    }
}
