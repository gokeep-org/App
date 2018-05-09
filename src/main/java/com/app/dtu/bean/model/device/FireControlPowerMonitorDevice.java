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
 * 消防设备电源监控-04
 */

@Entity
@Table(name = DtuConfig.DTU_TABLE_PRIFIX +"fire_control_power_monitor_device")
public class FireControlPowerMonitorDevice  extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<FireControlPowerMonitorDevice> {

    private static final Logger logger = LoggerFactory.getLogger(FireControlPowerMonitorDevice.class);

    public FireControlPowerMonitorDevice(Message message) {
        setMessage(message);
    }

    public FireControlPowerMonitorDevice() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ua1;
    private Integer ub1;
    private Integer uc1;
    private Integer ua2;
    private Integer ub2;
    private Integer uc2;

    private Integer st;
    private Integer pt;

    private Integer ua;
    private Integer ub;
    private Integer uc;
    private Integer ia;
    private Integer ib;
    private Integer ic;

    private Integer ia1;
    private Integer ib1;
    private Integer ic1;
    private Integer ia2;
    private Integer ib2;
    private Integer ic2;

    private Integer ua3;
    private Integer ub3;
    private Integer uc3;
    private Integer ua4;
    private Integer ub4;
    private Integer uc4;

    private Integer umax;
    private Integer umin;
    private Integer imax1;
    private Integer imax2;
    private Integer imax3;
    private Integer imax4;
    private Integer imax5;
    private Integer imax6;
    private Integer ptmax;
    private Integer stmax;



    @Override
    public FireControlPowerMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        if (CollectionUtils.isEmpty(message.getDataMsgs())){
            return this;
        }
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0401) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    ua1 = DtuUtil.getValue(dataMsgs, 0);
                    ub1 = DtuUtil.getValue(dataMsgs, 1);
                    uc1 = DtuUtil.getValue(dataMsgs, 2);
                    ua2 = DtuUtil.getValue(dataMsgs, 3);
                    ub2 = DtuUtil.getValue(dataMsgs, 4);
                    ub3 = DtuUtil.getValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83) {
                    umax = DtuUtil.getValue(dataMsgs, 0);
                    umin = DtuUtil.getValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax1 = DtuUtil.getValue(dataMsgs, 0);
                }
            } else if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0402) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    ua = DtuUtil.getValue(dataMsgs, 0);
                    ub = DtuUtil.getValue(dataMsgs, 1);
                    uc = DtuUtil.getValue(dataMsgs, 2);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {
                    ia = DtuUtil.getValue(dataMsgs, 0);
                    ib = DtuUtil.getValue(dataMsgs, 1);
                    ic = DtuUtil.getValue(dataMsgs, 2);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83) {
                    umax = DtuUtil.getValue(dataMsgs, 0);
                    umin = DtuUtil.getValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax1 = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82) {
                    ptmax = DtuUtil.getValue(dataMsgs, 0);
                }
            } else if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0403) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {
                    ia1 = DtuUtil.getValue(dataMsgs, 0);
                    ib1 = DtuUtil.getValue(dataMsgs, 1);
                    ic1 = DtuUtil.getValue(dataMsgs, 2);
                    ia2 = DtuUtil.getValue(dataMsgs, 3);
                    ib2 = DtuUtil.getValue(dataMsgs, 4);
                    ic2 = DtuUtil.getValue(dataMsgs, 5);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax1 = DtuUtil.getValue(dataMsgs, 0);
                    imax2 = DtuUtil.getValue(dataMsgs, 1);
                    imax3 = DtuUtil.getValue(dataMsgs, 2);
                    imax4 = DtuUtil.getValue(dataMsgs, 3);
                    imax5 = DtuUtil.getValue(dataMsgs, 4);
                    imax6 = DtuUtil.getValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82) {
                    ptmax = DtuUtil.getValue(dataMsgs, 0);
                }
            } else if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0404) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {
                    ia1 = DtuUtil.getValue(dataMsgs, 0);
                    ib1 = DtuUtil.getValue(dataMsgs, 1);
                    ic1 = DtuUtil.getValue(dataMsgs, 2);
                    ia2 = DtuUtil.getValue(dataMsgs, 3);
                    ib2 = DtuUtil.getValue(dataMsgs, 4);
                    ic2 = DtuUtil.getValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    ua1 = DtuUtil.getValue(dataMsgs, 0);
                    ub1 = DtuUtil.getValue(dataMsgs, 1);
                    uc1 = DtuUtil.getValue(dataMsgs, 2);
                    ua2 = DtuUtil.getValue(dataMsgs, 3);
                    ub2 = DtuUtil.getValue(dataMsgs, 4);
                    uc2 = DtuUtil.getValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83) {
                    umax = DtuUtil.getValue(dataMsgs, 0);
                    umin = DtuUtil.getValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax1 = DtuUtil.getValue(dataMsgs, 0);
                    imax2 = DtuUtil.getValue(dataMsgs, 1);
                    imax3 = DtuUtil.getValue(dataMsgs, 2);
                    imax4 = DtuUtil.getValue(dataMsgs, 3);
                    imax5 = DtuUtil.getValue(dataMsgs, 4);
                    imax6 = DtuUtil.getValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82) {
                    ptmax = DtuUtil.getValue(dataMsgs, 0);
                }
            } else if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0405) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {
                    ia1 = DtuUtil.getValue(dataMsgs, 0);
                    ib1 = DtuUtil.getValue(dataMsgs, 1);
                    ic1 = DtuUtil.getValue(dataMsgs, 2);
                    ia2 = DtuUtil.getValue(dataMsgs, 3);
                    ib2 = DtuUtil.getValue(dataMsgs, 4);
                    ic2 = DtuUtil.getValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    ua1 = DtuUtil.getValue(dataMsgs, 0);
                    ub1 = DtuUtil.getValue(dataMsgs, 1);
                    uc1 = DtuUtil.getValue(dataMsgs, 2);
                    ua2 = DtuUtil.getValue(dataMsgs, 3);
                    ub2 = DtuUtil.getValue(dataMsgs, 4);
                    uc2 = DtuUtil.getValue(dataMsgs, 5);
                    ua3 = DtuUtil.getValue(dataMsgs, 6);
                    ub3 = DtuUtil.getValue(dataMsgs, 7);
                    uc3 = DtuUtil.getValue(dataMsgs, 8);
                    ua4 = DtuUtil.getValue(dataMsgs, 9);
                    ub4 = DtuUtil.getValue(dataMsgs, 10);
                    ub4 = DtuUtil.getValue(dataMsgs, 11);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83) {
                    umax = DtuUtil.getValue(dataMsgs, 0);
                    umin = DtuUtil.getValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax1 = DtuUtil.getValue(dataMsgs, 0);
                    imax2 = DtuUtil.getValue(dataMsgs, 1);
                    imax3 = DtuUtil.getValue(dataMsgs, 2);
                    imax4 = DtuUtil.getValue(dataMsgs, 3);
                    imax5 = DtuUtil.getValue(dataMsgs, 4);
                    imax6 = DtuUtil.getValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82) {
                    ptmax = DtuUtil.getValue(dataMsgs, 0);
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

    @Override
    public boolean execute() {
        try{
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)){
                return false;
            }
            ServiceItem.fireControlPowerService.updateOldDataStatus(getMessageId());
            ServiceItem.fireControlPowerService.save(deviceDataDeal);
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
            return false;
        }
        return true;
    }

    @Override
    public FireControlPowerMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }



    public static Logger getLogger() {
        return logger;
    }

    public Integer getUa1() {
        return ua1;
    }

    public void setUa1(Integer ua1) {
        this.ua1 = ua1;
    }

    public Integer getUb1() {
        return ub1;
    }

    public void setUb1(Integer ub1) {
        this.ub1 = ub1;
    }

    public Integer getUc1() {
        return uc1;
    }

    public void setUc1(Integer uc1) {
        this.uc1 = uc1;
    }

    public Integer getUa2() {
        return ua2;
    }

    public void setUa2(Integer ua2) {
        this.ua2 = ua2;
    }

    public Integer getUb2() {
        return ub2;
    }

    public void setUb2(Integer ub2) {
        this.ub2 = ub2;
    }

    public Integer getUc2() {
        return uc2;
    }

    public void setUc2(Integer uc2) {
        this.uc2 = uc2;
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

    public Integer getIa1() {
        return ia1;
    }

    public void setIa1(Integer ia1) {
        this.ia1 = ia1;
    }

    public Integer getIb1() {
        return ib1;
    }

    public void setIb1(Integer ib1) {
        this.ib1 = ib1;
    }

    public Integer getIc1() {
        return ic1;
    }

    public void setIc1(Integer ic1) {
        this.ic1 = ic1;
    }

    public Integer getIa2() {
        return ia2;
    }

    public void setIa2(Integer ia2) {
        this.ia2 = ia2;
    }

    public Integer getIb2() {
        return ib2;
    }

    public void setIb2(Integer ib2) {
        this.ib2 = ib2;
    }

    public Integer getIc2() {
        return ic2;
    }

    public void setIc2(Integer ic2) {
        this.ic2 = ic2;
    }

    public Integer getUa3() {
        return ua3;
    }

    public void setUa3(Integer ua3) {
        this.ua3 = ua3;
    }

    public Integer getUb3() {
        return ub3;
    }

    public void setUb3(Integer ub3) {
        this.ub3 = ub3;
    }

    public Integer getUc3() {
        return uc3;
    }

    public void setUc3(Integer uc3) {
        this.uc3 = uc3;
    }

    public Integer getUa4() {
        return ua4;
    }

    public void setUa4(Integer ua4) {
        this.ua4 = ua4;
    }

    public Integer getUb4() {
        return ub4;
    }

    public void setUb4(Integer ub4) {
        this.ub4 = ub4;
    }

    public Integer getUc4() {
        return uc4;
    }

    public void setUc4(Integer uc4) {
        this.uc4 = uc4;
    }

    @Override
    public String toString() {
        return "FireControlPowerMonitorDevice{" +
                "id=" + id +
                ", ua1=" + ua1 +
                ", ub1=" + ub1 +
                ", uc1=" + uc1 +
                ", ua2=" + ua2 +
                ", ub2=" + ub2 +
                ", uc2=" + uc2 +
                ", st=" + st +
                ", pt=" + pt +
                ", ua=" + ua +
                ", ub=" + ub +
                ", uc=" + uc +
                ", ia=" + ia +
                ", ib=" + ib +
                ", ic=" + ic +
                ", ia1=" + ia1 +
                ", ib1=" + ib1 +
                ", ic1=" + ic1 +
                ", ia2=" + ia2 +
                ", ib2=" + ib2 +
                ", ic2=" + ic2 +
                ", ua3=" + ua3 +
                ", ub3=" + ub3 +
                ", uc3=" + uc3 +
                ", ua4=" + ua4 +
                ", ub4=" + ub4 +
                ", uc4=" + uc4 +
                ", umax=" + umax +
                ", umin=" + umin +
                ", imax1=" + imax1 +
                ", imax2=" + imax2 +
                ", imax3=" + imax3 +
                ", imax4=" + imax4 +
                ", imax5=" + imax5 +
                ", imax6=" + imax6 +
                ", ptmax=" + ptmax +
                ", stmax=" + stmax +
                '}';
    }
}
