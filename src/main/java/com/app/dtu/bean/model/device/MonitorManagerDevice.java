package com.app.dtu.bean.model.device;

import com.app.dtu.bean.DataMsg;
import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;
import com.app.dtu.config.DtuConfig;
import com.app.dtu.service.ServiceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * 监控管理设备实体-00
 */
@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"monitor_manager_device")
public class MonitorManagerDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<MonitorManagerDevice> {
    private static final Logger logger = LoggerFactory.getLogger(MonitorManagerDevice.class);
    private int x1;
    private int x2;
    private int x3;
    private int x4;
    private int x5;
    private int x6;
    private int x7;
    private int x8;


    public MonitorManagerDevice(Message message) {
        setMessage(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    private

    
    @Override
    public MonitorManagerDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }

    /**
     * 解析消息实体到数据传输对象实体
     * @param message
     * @return
     */
    @Override
    public MonitorManagerDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        for (int i=0; i < message.getDataMsgs().size(); i++){
            DataMsg dataMsg = message.getDataMsgs().get(i);
            if (dataMsg.getType() == 0x0001){
                dataMsg.getDatas();
                this.x1 = dataMsg.getDatas()
            }
        }
        return this;
    }

    // 执行数据存储
    @Override
    public boolean execute() {
        try{
            ServiceItem.monitorManagerService.save(this.generateEntity(getMessage()));
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
        }
        return true;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getX4() {
        return x4;
    }

    public void setX4(int x4) {
        this.x4 = x4;
    }

    public int getX5() {
        return x5;
    }

    public void setX5(int x5) {
        this.x5 = x5;
    }

    public int getX6() {
        return x6;
    }

    public void setX6(int x6) {
        this.x6 = x6;
    }

    public int getX7() {
        return x7;
    }

    public void setX7(int x7) {
        this.x7 = x7;
    }

    public int getX8() {
        return x8;
    }

    public void setX8(int x8) {
        this.x8 = x8;
    }
}
