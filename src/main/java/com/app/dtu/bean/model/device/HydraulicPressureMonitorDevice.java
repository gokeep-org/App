package com.app.dtu.bean.model.device;

import com.app.dtu.bean.DataMsg;
import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.*;
import com.app.dtu.config.DtuConfig;
import com.app.dtu.redis.RedisClient;
import com.app.dtu.service.ServiceItem;
import com.app.dtu.util.DtuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public HydraulicPressureMonitorDevice() {
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private Integer sy1;

    // TODO: 要除1000
    private String maxsy1;
    @Override
    public HydraulicPressureMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        if (CollectionUtils.isEmpty(message.getDataMsgs())){
            return this;
        }
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.HYDRAULIC_PRESSURE_MONITOR_1201) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_10) {
                    sy1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_90){
                    maxsy1 = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 0)/ 1000f);
                }
            }
        }
        return this;
    }
    @Override
    public boolean isChange() {
        boolean isChange = false;
        List<String> values = client.hmget(getMessageId(), "warn", "id");
        if (CollectionUtils.isEmpty(values) || values.size() < 2) {
            isChange = true;
        }else {
            if (values.get(0) == null || values.get(1) == null || !values.get(0).equalsIgnoreCase(String.valueOf(getWarnList()))){
                isChange = true;
            }else{
                isChange = false;
            }
        }
        if (isChange) {
            Map<String, String> hashValue = new HashMap<>();
            hashValue.put("warn", String.valueOf(getWarnList()));
            hashValue.put("id", String.valueOf(getId()));
            client.hmset(getMessageId(),hashValue);
            logger.info("Redis set cache is [device_id: {}], [value: {}]", hashValue.toString());
        }else {
            ServiceItem.hydraulicPressureService.updatePreviousDataStatus(values.get(1), 2);
        }
        return isChange;
    }


    public Integer getSy1() {
        return sy1;
    }

    public void setSy1(Integer sy1) {
        this.sy1 = sy1;
    }

    public String getMaxsy1() {
        return maxsy1;
    }

    public void setMaxsy1(String maxsy1) {
        this.maxsy1 = maxsy1;
    }

    @Override
    public boolean execute() {
        try{
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)){
                return false;
            }
            ServiceItem.hydraulicPressureService.updateOldDataStatus(getMessageId());
            ServiceItem.hydraulicPressureService.save(deviceDataDeal);
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
            return false;
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


    @Override
    public RedisClient redisClient() {
        return redisClient();
    }

    @Override
    public String toString() {
        return "HydraulicPressureMonitorDevice{" +
                "id=" + getId() +
                ", sy1=" + sy1 +
                ", maxsy1=" + maxsy1 +
                '}';
    }
}
