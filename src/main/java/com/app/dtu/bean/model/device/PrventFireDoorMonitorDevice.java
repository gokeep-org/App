package com.app.dtu.bean.model.device;

import com.app.dtu.bean.DataMsg;
import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;
import com.app.dtu.config.DtuConfig;
import com.app.dtu.redis.RedisClient;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceItem;
import com.app.dtu.util.DtuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
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

    public PrventFireDoorMonitorDevice() {
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private Integer status;

    @Override
    public PrventFireDoorMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        if (CollectionUtils.isEmpty(message.getDataMsgs())){
            return this;
        }
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            status = DtuUtil.getIntegerValue(dataMsgs, 0);
        }
        return this;
    }

//    @Override
//    public boolean isChange() {
//        boolean isChange = false;
//        List<Object> values = redisClient.opsForHash().multiGet(getMessageId(),  Arrays.asList(new String[]{"warn", "id"}));
//        if (CollectionUtils.isEmpty(values) || values.size() < 2) {
//            isChange = true;
//        }else {
//            if (values.get(0) == null || values.get(1) == null || !String.valueOf(values.get(0)).equalsIgnoreCase(String.valueOf(getWarnList()))){
//                isChange = true;
//            }else{
//                isChange = false;
//            }
//        }
//        if (isChange) {
//            Map<String, String> hashValue = new HashMap<>();
//            hashValue.put("warn", String.valueOf(getWarnList()));
//            hashValue.put("id", String.valueOf(getId()));
//            redisClient.expire(getMessageId(), DtuConfig.CACHE_EXPRIE_TIME_FOR_DAY, TimeUnit.DAYS);
//            redisClient.opsForHash().putAll(getMessageId(),hashValue);
//            logger.info("Redis set cache is [device_id: {}], [value: {}]", hashValue.toString());
//        }else {
//            ServiceItem.preventFireDoorService.updatePreviousDataStatus(String.valueOf(values.get(1)), 2);
//        }
//        return isChange;
//    }

    @Override
    public DataService getService() {
        return ServiceItem.preventFireDoorService;
    }

    @Override
    public RedisClient redisClient() {
        return redisClient();
    }

    @Override
    public boolean execute() {
        try{
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)){
                return false;
            }
            ServiceItem.preventFireDoorService.updateOldDataStatus(getMessageId());
            ServiceItem.preventFireDoorService.save(deviceDataDeal);
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
            return false;
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
                "id=" + getId() +
                ", status=" + status +
                '}';
    }
}
