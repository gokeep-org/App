package com.app.dtu.bean.model.device;

import com.app.dtu.bean.DataMsg;
import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DataType;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;
import com.app.dtu.config.DtuConfig;
import com.app.dtu.service.ServiceItem;
import com.app.dtu.util.DtuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * 监控管理设备实体-00
 */
@Entity
@Table(name = DtuConfig.DTU_TABLE_PRIFIX + "monitor_manager_device")
public class MonitorManagerDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<MonitorManagerDevice> {
    private static final Logger logger = LoggerFactory.getLogger(MonitorManagerDevice.class);
    private Integer x1;
    private Integer x2;
    private Integer x3;
    private Integer x4;
    private Integer x5;
    private Integer x6;
    private Integer x7;
    private Integer x8;
    private Integer zdx;
    private Integer bdx;
    private Integer scx;

    public MonitorManagerDevice(Message message) {
        setMessage(message);
    }

    public MonitorManagerDevice() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
     *
     * @param message
     * @return
     */
    @Override
    public MonitorManagerDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_80) {
                List<Integer> dataMsgs = dataMsg.getDatas();
                x1 = DtuUtil.getValue(dataMsgs, 0);
                x2 = DtuUtil.getValue(dataMsgs, 1);
                x3 = DtuUtil.getValue(dataMsgs, 2);
                x4 = DtuUtil.getValue(dataMsgs, 3);
                x5 = DtuUtil.getValue(dataMsgs, 4);
                x6 = DtuUtil.getValue(dataMsgs, 5);
                x7 = DtuUtil.getValue(dataMsgs, 6);
                x8 = DtuUtil.getValue(dataMsgs, 7);
                zdx = DtuUtil.getValue(dataMsgs, 8);
                bdx = DtuUtil.getValue(dataMsgs, 9);
                scx = DtuUtil.getValue(dataMsgs, 10);

            }
        }
        return this;
    }

    // 执行数据存储
    @Override
    public boolean execute() {
        try {
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)){
                return false;
            }
            ServiceItem.monitorManagerService.updateOldDataStatus(getMessageId());
            ServiceItem.monitorManagerService.save(deviceDataDeal);
        } catch (Throwable e) {
            logger.error("Execute add data to db or generate entity is error");
            return false;
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

    @Override
    public String toString() {
        return "MonitorManagerDevice{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                ", x4=" + x4 +
                ", x5=" + x5 +
                ", x6=" + x6 +
                ", x7=" + x7 +
                ", x8=" + x8 +
                ", id=" + id +
                '}';
    }
}
