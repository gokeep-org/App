package com.app.dtu.bean.model.device;

import com.app.dtu.bean.DataMsg;
import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.*;
import com.app.dtu.config.DtuConfig;
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
 * 可燃气体监控-06
 */
@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"combustible_gas_monitor_device")
public class CombustibleGasMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<CombustibleGasMonitorDevice>{
    private static final Logger logger = LoggerFactory.getLogger(CombustibleGasMonitorDevice.class);


    public CombustibleGasMonitorDevice(Message message) {
        setMessage(message);
    }

    public CombustibleGasMonitorDevice() {
    }

    private Integer trq1;
    private Integer maxtrq1;
    private Integer yhq1;
    private Integer maxyhq1;
    private Integer mzq1;
    private Integer maxmzq1;



    @Override
    public CombustibleGasMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        if (CollectionUtils.isEmpty(message.getDataMsgs())){
            return this;
        }
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.COMBUSTIBLE_GAS_MONITOR_0601) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_08) {
                    trq1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_88){
                    maxtrq1 =  DtuUtil.getIntegerValue(dataMsgs, 0);
                }
            }else if (message.parseDeviceModelEnum() == DeviceTypeName.COMBUSTIBLE_GAS_MONITOR_0602) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_09) {
                    yhq1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_89){
                    maxyhq1 =  DtuUtil.getIntegerValue(dataMsgs, 0);
                }
            }else if (message.parseDeviceModelEnum() == DeviceTypeName.COMBUSTIBLE_GAS_MONITOR_0603) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_0A) {
                    mzq1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_8A){
                    maxmzq1 =  DtuUtil.getIntegerValue(dataMsgs, 0);
                }
            }
        }
        return this;
    }


    @Override
    public boolean isChange() {
        String value = client.get(getMessageId());
        if (value == null || !value.equalsIgnoreCase(String.valueOf(getWarnList()))){
            client.set(getMessageId(), String.valueOf(getWarnList()));
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean execute() {
        try{
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)){
                return false;
            }
            // 一定注意前后顺序，这个很重要
            ServiceItem.combustibleGasMonitorService.updateOldDataStatus(getMessageId());
            ServiceItem.combustibleGasMonitorService.save(deviceDataDeal);
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
            return false;
        }
        return true;
    }


    public CombustibleGasMonitorDevice getOfflineDeviceData(String messageId){
        return generateEntity(getOfflineMessage(messageId));
    }



    @Override
    public CombustibleGasMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }

    public static Logger getLogger() {
        return logger;
    }

    public Integer getTrq1() {
        return trq1;
    }

    public void setTrq1(Integer trq1) {
        this.trq1 = trq1;
    }

    public Integer getYhq1() {
        return yhq1;
    }

    public void setYhq1(Integer yhq1) {
        this.yhq1 = yhq1;
    }

    public Integer getMzq1() {
        return mzq1;
    }

    public void setMzq1(Integer mzq1) {
        this.mzq1 = mzq1;
    }

    public Integer getMaxtrq1() {
        return maxtrq1;
    }

    public void setMaxtrq1(Integer maxtrq1) {
        this.maxtrq1 = maxtrq1;
    }

    public Integer getMaxyhq1() {
        return maxyhq1;
    }

    public void setMaxyhq1(Integer maxyhq1) {
        this.maxyhq1 = maxyhq1;
    }

    public Integer getMaxmzq1() {
        return maxmzq1;
    }

    public void setMaxmzq1(Integer maxmzq1) {
        this.maxmzq1 = maxmzq1;
    }


    @Override
    public String toString() {
        return "CombustibleGasMonitorDevice{" +
                "id=" + getId() +
                "trq1=" + trq1 +
                ", maxtrq1=" + maxtrq1 +
                ", yhq1=" + yhq1 +
                ", maxyhq1=" + maxyhq1 +
                ", mzq1=" + mzq1 +
                ", maxmzq1=" + maxmzq1 +
                '}';
    }
}
