package com.app.dtu.bean.model.device;

import com.app.dtu.bean.DataMsg;
import com.app.dtu.bean.Message;
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
 * 防火门监控设备
 */
@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"prven_fir_door_monitor_device")
public class PrventFireDoorMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<PrventFireDoorMonitorDevice> {
    private static final Logger logger = LoggerFactory.getLogger(PrventFireDoorMonitorDevice.class);

    public PrventFireDoorMonitorDevice(Message message) {
        setMessage(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer status;

    @Override
    public PrventFireDoorMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            status = DtuUtil.getValue(dataMsgs, 0);
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean execute() {
        try{
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)){
                return false;
            }
            ServiceItem.preventFireDoorService.save(deviceDataDeal);
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
        }
        return true;
    }

    @Override
    public PrventFireDoorMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PrventFireDoorMonitorDevice{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
