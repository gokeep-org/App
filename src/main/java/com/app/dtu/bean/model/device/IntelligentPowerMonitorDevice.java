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

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * 智能电力设监控设别-02
 */
@Entity
@Table(name = DtuConfig.DTU_TABLE_PRIFIX + "intelligent_power_monitor_device")
public class IntelligentPowerMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<IntelligentPowerMonitorDevice> {
    private static final Logger logger = LoggerFactory.getLogger(IntelligentPowerMonitorDevice.class);

    public IntelligentPowerMonitorDevice(Message message) {
        setMessage(message);
    }

    public IntelligentPowerMonitorDevice() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ua;
    private Integer ub;
    private Integer uc;
    private Integer ia;
    private Integer ib;
    private Integer ic;
    private Integer st;
    private Integer pt;
    private Integer umax;
    private Integer umin;
    private Integer imax;



    @Override
    public boolean execute() {
        try {
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)){
                return false;
            }
            ServiceItem.intelligentPowerService.updateOldDataStatus(getMessageId());
            ServiceItem.intelligentPowerService.save(deviceDataDeal);
        } catch (Throwable e) {
            logger.error("Execute add data to db or generate entity is error");
            return false;
        }
        return true;
    }

    @Override
    public IntelligentPowerMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }

    @Override
    public IntelligentPowerMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        if (CollectionUtils.isEmpty(message.getDataMsgs())){
            return this;
        }
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.INTELLIGENT_POWER_MONITOR_0201) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    ua = DtuUtil.getValue(dataMsgs, 0);
                    ub = DtuUtil.getValue(dataMsgs, 1);
                    uc = DtuUtil.getValue(dataMsgs, 2);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {
                    ia = DtuUtil.getValue(dataMsgs, 0);
                    ib = DtuUtil.getValue(dataMsgs, 1);
                    ic = DtuUtil.getValue(dataMsgs, 2);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83){
                    umax = DtuUtil.getValue(dataMsgs, 0);
                    umin = DtuUtil.getValue(dataMsgs, 1);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84){
                    imax = DtuUtil.getValue(dataMsgs, 0);
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

    public Integer getUa() {
        return ua;
    }

    public void setUa(Integer ua) {
        this.ua = ua;
    }

    public Integer getUb() {
        return ub;
    }

    public void setUb(Integer ub) {
        this.ub = ub;
    }

    public Integer getUc() {
        return uc;
    }

    public void setUc(Integer uc) {
        this.uc = uc;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }

    public Integer getPt() {
        return pt;
    }

    public void setPt(Integer pt) {
        this.pt = pt;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Integer getIa() {
        return ia;
    }

    public void setIa(Integer ia) {
        this.ia = ia;
    }

    public Integer getIb() {
        return ib;
    }

    public void setIb(Integer ib) {
        this.ib = ib;
    }

    public Integer getIc() {
        return ic;
    }

    public void setIc(Integer ic) {
        this.ic = ic;
    }

    public Integer getUmax() {
        return umax;
    }

    public void setUmax(Integer umax) {
        this.umax = umax;
    }

    public Integer getUmin() {
        return umin;
    }

    public void setUmin(Integer umin) {
        this.umin = umin;
    }

    public Integer getImax() {
        return imax;
    }

    public void setImax(Integer imax) {
        this.imax = imax;
    }

    @Override
    public String toString() {
        return "IntelligentPowerMonitorDevice{" +
                "id=" + id +
                ", ua=" + ua +
                ", ub=" + ub +
                ", uc=" + uc +
                ", ia=" + ia +
                ", ib=" + ib +
                ", ic=" + ic +
                ", st=" + st +
                ", pt=" + pt +
                ", umax=" + umax +
                ", umin=" + umin +
                ", imax=" + imax +
                '}';
    }
}
