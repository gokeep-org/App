package com.app.dtu.bean.model.device;

import com.app.dtu.bean.DataMsg;
import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.*;
import com.app.dtu.config.DtuConfig;
import com.app.dtu.service.ServiceItem;
import com.app.dtu.util.DtuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

/**
 * 水压监控-12
 */
@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX + "hydraulic_pressure_monitor_device")
public class HydraulicPressureMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<HydraulicPressureMonitorDevice> {
    private static final Logger logger = LoggerFactory.getLogger(HydraulicPressureMonitorDevice.class);

    public HydraulicPressureMonitorDevice(Message message) {
        setMessage(message);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sy1;


    @Override
    public HydraulicPressureMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.HYDRAULIC_PRESSURE_MONITOR_1201) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_10) {
                    sy1 = DtuUtil.getValue(dataMsgs, 0);
                }
            }
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSy1() {
        return sy1;
    }

    public void setSy1(Integer sy1) {
        this.sy1 = sy1;
    }

    @Override
    public boolean execute() {
        try{
            ServiceItem.hydraulicPressureService.save(this.generateEntity(getMessage()));
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
        }
        return true;
    }

    @Override
    public HydraulicPressureMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }


}
