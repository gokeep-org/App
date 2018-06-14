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
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 烟感设备
 */

@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"smoke_feel_monitor_device")
public class SmokeFeelMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<SmokeFeelMonitorDevice> {
    private static final Logger logger = LoggerFactory.getLogger(SmokeFeelMonitorDevice.class);

    public SmokeFeelMonitorDevice(Message message) {
        setMessage(message);
    }

    public SmokeFeelMonitorDevice() {
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private Integer pt;
    private Integer ptstatus;
    private Integer y1;
    private Integer maxy1;

    @Override
    public SmokeFeelMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        if (CollectionUtils.isEmpty(message.getDataMsgs())){
            return this;
        }
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.SMOKE_FEE_MONITOR_0501) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_06) {
                    y1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_07) {
                    ptstatus = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_87) {
                    maxy1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }
            }
        }
        return this;
    }

    @Override
    public boolean execute() {
        try{
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)){
                return false;
            }
            ServiceItem.somkeFeeService.updateOldDataStatus(getMessageId());
            ServiceItem.somkeFeeService.save(deviceDataDeal);
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
            return false;
        }
        return true;
    }

    @Override
    public boolean isChange() {
        boolean isChange = false;
        List<Object> values = redisClient.opsForHash().multiGet(getMessageId(),  Arrays.asList(new String[]{"warn", "id"}));
        if (CollectionUtils.isEmpty(values) || values.size() < 2) {
            isChange = true;
        }else {
            if (values.get(0) == null || values.get(1) == null || !String.valueOf(values.get(0)).equalsIgnoreCase(String.valueOf(getWarnList()))){
                isChange = true;
            }else{
                isChange = false;
            }
        }
        if (isChange) {
            Map<String, String> hashValue = new HashMap<>();
            hashValue.put("warn", String.valueOf(getWarnList()));
            hashValue.put("id", String.valueOf(getId()));
            redisClient.expire(getMessageId(), DtuConfig.CACHE_EXPRIE_TIME_FOR_DAY, TimeUnit.DAYS);
            redisClient.opsForHash().putAll(getMessageId(),hashValue);
            logger.info("Redis set cache is [device_id: {}], [value: {}]", hashValue.toString());
        }else {
            ServiceItem.somkeFeeService.updatePreviousDataStatus(String.valueOf(values.get(1)), 2);
        }
        return isChange;
    }
    @Override
    public SmokeFeelMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }

    public Integer getPtstatus() {
        return ptstatus;
    }

    public void setPtstatus(Integer ptstatus) {
        this.ptstatus = ptstatus;
    }

    public Integer getMaxy1() {
        return maxy1;
    }

    public void setMaxy1(Integer maxy1) {
        this.maxy1 = maxy1;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Integer getPt() {
        return pt;
    }

    public void setPt(Integer pt) {
        this.pt = pt;
    }

    public Integer getY1() {
        return y1;
    }

    public void setY1(Integer y1) {
        this.y1 = y1;
    }

    @Override
    public RedisClient redisClient() {
        return redisClient();
    }

    @Override
    public String toString() {
        return "SmokeFeelMonitorDevice{" +
                "id=" + getId() +
                ", pt=" + pt +
                ", ptstatus=" + ptstatus +
                ", y1=" + y1 +
                ", maxy1=" + maxy1 +
                '}';
    }
}
