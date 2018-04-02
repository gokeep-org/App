package com.app.dtu.bean.model.device;

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
 * 视屏监控08
 */

@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"scree_monitor_device")
public class ScreenMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<ScreenMonitorDevice> {
    private static final Logger logger = LoggerFactory.getLogger(ScreenMonitorDevice.class);

    public ScreenMonitorDevice(Message message) {
        setMessage(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public boolean execute() {
        try{
            ServiceItem.screenMonitorService.save(this.generateEntity(getMessage()));
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
        }
        return true;
    }

    @Override
    public ScreenMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }



    @Override
    public ScreenMonitorDevice generateEntity(Message message) {
        return null;
    }
}
