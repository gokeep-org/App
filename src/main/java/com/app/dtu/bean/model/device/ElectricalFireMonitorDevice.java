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
 * 电器火灾监监控设备-01
 */
@Entity
@Table(name = DtuConfig.DTU_TABLE_PRIFIX + "electrical_fire_monitor_device")
public class ElectricalFireMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<ElectricalFireMonitorDevice> {
    private static final Logger logger = LoggerFactory.getLogger(ElectricalFireMonitorDevice.class);

    public ElectricalFireMonitorDevice(Message message) {
        setMessage(message);
    }

    public ElectricalFireMonitorDevice() {
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String ua;
    private String ub;
    private String uc;
    private String ia;
    private String ib;
    private String ic;
    private Integer st;
    private Integer pta;
    private Integer ptb;
    private Integer ptc;
    private Integer ptn;

    private Integer st1;
    private Integer st2;
    private Integer st3;
    private Integer st4;
    private Integer st5;
    private Integer st6;
    private Integer st7;
    private Integer st8;


    private Integer pt1;
    private Integer pt2;
    private Integer pt3;
    private Integer pt4;
    private Integer pt5;
    private Integer pt6;
    private Integer pt7;
    private Integer pt8;

    private Integer umax1;
    private Integer umax2;
    private Integer umax3;
    private Integer umax4;
    private Integer umax5;
    private Integer umax6;
    private Integer umax7;
    private Integer umax8;

    private Integer imax;
    private Integer imax1;
    private Integer imax2;
    private Integer imax3;
    private Integer imax4;
    private Integer imax5;
    private Integer imax6;
    private Integer imax7;
    private Integer imax8;

    private Integer ptmax1;
    private Integer ptmax2;
    private Integer ptmax3;
    private Integer ptmax4;
    private Integer ptmax5;
    private Integer ptmax6;
    private Integer ptmax7;
    private Integer ptmax8;

    private Integer stmax1;
    private Integer stmax2;
    private Integer stmax3;
    private Integer stmax4;
    private Integer stmax5;
    private Integer stmax6;
    private Integer stmax7;
    private Integer stmax8;



    @Override
    public boolean execute() {
        try {
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)){
                return false;
            }
            ServiceItem.electricalFireMonitorService.updateOldDataStatus(getMessageId());
            ServiceItem.electricalFireMonitorService.save(deviceDataDeal);
//            Thread.sleep(50);
        } catch (Throwable e) {
            logger.error("Execute add data to db or generate entity is error");
            return false;
        }
        return true;
    }

    @Override
    public ElectricalFireMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }

    @Override
    public ElectricalFireMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        if (CollectionUtils.isEmpty(message.getDataMsgs())){
            return this;
        }
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.ELECTRICAL_FIRE_MONITOR_0101){
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    ua = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 0));
                    ub = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 1));
                    uc = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 2));
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04){
                    ia = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 0) /10.0f);
                    ib = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 1) /10.0f);
                    ic = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 2) /10.0f);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01){
                    st = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02){
                    pta = DtuUtil.getIntegerValue(dataMsgs, 0);
                    ptb = DtuUtil.getIntegerValue(dataMsgs, 1);
                    ptc = DtuUtil.getIntegerValue(dataMsgs, 2);
                    ptn = DtuUtil.getIntegerValue(dataMsgs, 3);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83){
                    umax1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    umax2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81){
                    stmax1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82){
                    ptmax1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84){
                   imax = DtuUtil.getIntegerValue(dataMsgs, 0);
                }
            }else if (message.parseDeviceModelEnum() == DeviceTypeName.ELECTRICAL_FIRE_MONITOR_0102 ||
                    message.parseDeviceModelEnum() == DeviceTypeName.ELECTRICAL_FIRE_MONITOR_0105){
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01){
                    st = DtuUtil.getIntegerValue(dataMsgs, 0);
                    st1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    st2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    st3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    st4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    st5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    st6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                    st7 = DtuUtil.getIntegerValue(dataMsgs, 6);
                    st8 = DtuUtil.getIntegerValue(dataMsgs, 7);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81){
                    stmax1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    stmax2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    stmax3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    stmax4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    stmax5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    stmax6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                    stmax7 = DtuUtil.getIntegerValue(dataMsgs, 6);
                    stmax8 = DtuUtil.getIntegerValue(dataMsgs, 7);
                }
            }else if (message.parseDeviceModelEnum() == DeviceTypeName.ELECTRICAL_FIRE_MONITOR_0103 ||
                    message.parseDeviceModelEnum() == DeviceTypeName.ELECTRICAL_FIRE_MONITOR_0106){
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02){
                    pt1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    pt2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    pt3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    pt4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    pt5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    pt6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                    pt7 = DtuUtil.getIntegerValue(dataMsgs, 6);
                    pt8 = DtuUtil.getIntegerValue(dataMsgs, 7);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82){
                    ptmax1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    ptmax2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    ptmax3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    ptmax4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    ptmax5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    ptmax6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                    ptmax7 = DtuUtil.getIntegerValue(dataMsgs, 6);
                    ptmax8 = DtuUtil.getIntegerValue(dataMsgs, 7);
                }
            }else if (message.parseDeviceModelEnum() == DeviceTypeName.ELECTRICAL_FIRE_MONITOR_0104 ||
                    message.parseDeviceModelEnum() == DeviceTypeName.ELECTRICAL_FIRE_MONITOR_0107){
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01){
                    st1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    st2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    st3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    st4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    st5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    st6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                    st7 = DtuUtil.getIntegerValue(dataMsgs, 6);
                    st8 = DtuUtil.getIntegerValue(dataMsgs, 7);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02){
                    pt1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    pt2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    pt3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    pt4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    pt5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    pt6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                    pt7 = DtuUtil.getIntegerValue(dataMsgs, 6);
                    pt8 = DtuUtil.getIntegerValue(dataMsgs, 7);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81){
                    stmax1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    stmax2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    stmax3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    stmax4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    stmax5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    stmax6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                    stmax7 = DtuUtil.getIntegerValue(dataMsgs, 6);
                    stmax8 = DtuUtil.getIntegerValue(dataMsgs, 7);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82){
                    ptmax1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    ptmax2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    ptmax3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    ptmax4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    ptmax5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    ptmax6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                    ptmax7 = DtuUtil.getIntegerValue(dataMsgs, 6);
                    ptmax8 = DtuUtil.getIntegerValue(dataMsgs, 7);
                }
            }else if (message.parseDeviceModelEnum() == DeviceTypeName.ELECTRICAL_FIRE_MONITOR_0108){
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01){
                    st1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02){
                    pt1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81){
                    stmax1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82){
                    ptmax1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                }
            }
        }
        return this;
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
            ServiceItem.electricalFireMonitorService.updatePreviousDataStatus(String.valueOf(values.get(1)), 2);
        }
        return isChange;
    }

    @Override
    public RedisClient redisClient() {
        return redisClient();
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getUb() {
        return ub;
    }

    public void setUb(String ub) {
        this.ub = ub;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public Integer getImax() {
        return imax;
    }

    public void setImax(Integer imax) {
        this.imax = imax;
    }

    public String getIa() {
        return ia;
    }

    public void setIa(String ia) {
        this.ia = ia;
    }

    public String getIb() {
        return ib;
    }

    public void setIb(String ib) {
        this.ib = ib;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }

    public Integer getPta() {
        return pta;
    }

    public void setPta(Integer pta) {
        this.pta = pta;
    }

    public Integer getPtb() {
        return ptb;
    }

    public void setPtb(Integer ptb) {
        this.ptb = ptb;
    }

    public Integer getPtc() {
        return ptc;
    }

    public void setPtc(Integer ptc) {
        this.ptc = ptc;
    }

    public Integer getPtn() {
        return ptn;
    }

    public void setPtn(Integer ptn) {
        this.ptn = ptn;
    }

    public Integer getSt1() {
        return st1;
    }

    public void setSt1(Integer st1) {
        this.st1 = st1;
    }

    public Integer getSt2() {
        return st2;
    }

    public void setSt2(Integer st2) {
        this.st2 = st2;
    }

    public Integer getSt3() {
        return st3;
    }

    public void setSt3(Integer st3) {
        this.st3 = st3;
    }

    public Integer getSt4() {
        return st4;
    }

    public void setSt4(Integer st4) {
        this.st4 = st4;
    }

    public Integer getSt5() {
        return st5;
    }

    public void setSt5(Integer st5) {
        this.st5 = st5;
    }

    public Integer getSt6() {
        return st6;
    }

    public void setSt6(Integer st6) {
        this.st6 = st6;
    }

    public Integer getSt7() {
        return st7;
    }

    public void setSt7(Integer st7) {
        this.st7 = st7;
    }

    public Integer getSt8() {
        return st8;
    }

    public void setSt8(Integer st8) {
        this.st8 = st8;
    }

    public Integer getPt1() {
        return pt1;
    }

    public void setPt1(Integer pt1) {
        this.pt1 = pt1;
    }

    public Integer getPt2() {
        return pt2;
    }

    public void setPt2(Integer pt2) {
        this.pt2 = pt2;
    }

    public Integer getPt3() {
        return pt3;
    }

    public void setPt3(Integer pt3) {
        this.pt3 = pt3;
    }

    public Integer getPt4() {
        return pt4;
    }

    public void setPt4(Integer pt4) {
        this.pt4 = pt4;
    }

    public Integer getPt5() {
        return pt5;
    }

    public void setPt5(Integer pt5) {
        this.pt5 = pt5;
    }

    public Integer getPt6() {
        return pt6;
    }

    public void setPt6(Integer pt6) {
        this.pt6 = pt6;
    }

    public Integer getPt7() {
        return pt7;
    }

    public void setPt7(Integer pt7) {
        this.pt7 = pt7;
    }

    public Integer getPt8() {
        return pt8;
    }

    public void setPt8(Integer pt8) {
        this.pt8 = pt8;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Integer getUmax1() {
        return umax1;
    }

    public void setUmax1(Integer umax1) {
        this.umax1 = umax1;
    }

    public Integer getUmax2() {
        return umax2;
    }

    public void setUmax2(Integer umax2) {
        this.umax2 = umax2;
    }

    public Integer getUmax3() {
        return umax3;
    }

    public void setUmax3(Integer umax3) {
        this.umax3 = umax3;
    }

    public Integer getUmax4() {
        return umax4;
    }

    public void setUmax4(Integer umax4) {
        this.umax4 = umax4;
    }

    public Integer getUmax5() {
        return umax5;
    }

    public void setUmax5(Integer umax5) {
        this.umax5 = umax5;
    }

    public Integer getUmax6() {
        return umax6;
    }

    public void setUmax6(Integer umax6) {
        this.umax6 = umax6;
    }

    public Integer getUmax7() {
        return umax7;
    }

    public void setUmax7(Integer umax7) {
        this.umax7 = umax7;
    }

    public Integer getUmax8() {
        return umax8;
    }

    public void setUmax8(Integer umax8) {
        this.umax8 = umax8;
    }

    public Integer getImax1() {
        return imax1;
    }

    public void setImax1(Integer imax1) {
        this.imax1 = imax1;
    }

    public Integer getImax2() {
        return imax2;
    }

    public void setImax2(Integer imax2) {
        this.imax2 = imax2;
    }

    public Integer getImax3() {
        return imax3;
    }

    public void setImax3(Integer imax3) {
        this.imax3 = imax3;
    }

    public Integer getImax4() {
        return imax4;
    }

    public void setImax4(Integer imax4) {
        this.imax4 = imax4;
    }

    public Integer getImax5() {
        return imax5;
    }

    public void setImax5(Integer imax5) {
        this.imax5 = imax5;
    }

    public Integer getImax6() {
        return imax6;
    }

    public void setImax6(Integer imax6) {
        this.imax6 = imax6;
    }

    public Integer getImax7() {
        return imax7;
    }

    public void setImax7(Integer imax7) {
        this.imax7 = imax7;
    }

    public Integer getImax8() {
        return imax8;
    }

    public void setImax8(Integer imax8) {
        this.imax8 = imax8;
    }

    public Integer getPtmax1() {
        return ptmax1;
    }

    public void setPtmax1(Integer ptmax1) {
        this.ptmax1 = ptmax1;
    }

    public Integer getPtmax2() {
        return ptmax2;
    }

    public void setPtmax2(Integer ptmax2) {
        this.ptmax2 = ptmax2;
    }

    public Integer getPtmax3() {
        return ptmax3;
    }

    public void setPtmax3(Integer ptmax3) {
        this.ptmax3 = ptmax3;
    }

    public Integer getPtmax4() {
        return ptmax4;
    }

    public void setPtmax4(Integer ptmax4) {
        this.ptmax4 = ptmax4;
    }

    public Integer getPtmax5() {
        return ptmax5;
    }

    public void setPtmax5(Integer ptmax5) {
        this.ptmax5 = ptmax5;
    }

    public Integer getPtmax6() {
        return ptmax6;
    }

    public void setPtmax6(Integer ptmax6) {
        this.ptmax6 = ptmax6;
    }

    public Integer getPtmax7() {
        return ptmax7;
    }

    public void setPtmax7(Integer ptmax7) {
        this.ptmax7 = ptmax7;
    }

    public Integer getPtmax8() {
        return ptmax8;
    }

    public void setPtmax8(Integer ptmax8) {
        this.ptmax8 = ptmax8;
    }

    public Integer getStmax1() {
        return stmax1;
    }

    public void setStmax1(Integer stmax1) {
        this.stmax1 = stmax1;
    }

    public Integer getStmax2() {
        return stmax2;
    }

    public void setStmax2(Integer stmax2) {
        this.stmax2 = stmax2;
    }

    public Integer getStmax3() {
        return stmax3;
    }

    public void setStmax3(Integer stmax3) {
        this.stmax3 = stmax3;
    }

    public Integer getStmax4() {
        return stmax4;
    }

    public void setStmax4(Integer stmax4) {
        this.stmax4 = stmax4;
    }

    public Integer getStmax5() {
        return stmax5;
    }

    public void setStmax5(Integer stmax5) {
        this.stmax5 = stmax5;
    }

    public Integer getStmax6() {
        return stmax6;
    }

    public void setStmax6(Integer stmax6) {
        this.stmax6 = stmax6;
    }

    public Integer getStmax7() {
        return stmax7;
    }

    public void setStmax7(Integer stmax7) {
        this.stmax7 = stmax7;
    }

    public Integer getStmax8() {
        return stmax8;
    }

    public void setStmax8(Integer stmax8) {
        this.stmax8 = stmax8;
    }

    @Override
    public String toString() {
        return "ElectricalFireMonitorDevice{" +
                "id=" + getId() +
                ", ua=" + ua +
                ", ub=" + ub +
                ", uc=" + uc +
                ", ia='" + ia + '\'' +
                ", ib='" + ib + '\'' +
                ", ic='" + ic + '\'' +
                ", st=" + st +
                ", pta=" + pta +
                ", ptb=" + ptb +
                ", ptc=" + ptc +
                ", ptn=" + ptn +
                ", st1=" + st1 +
                ", st2=" + st2 +
                ", st3=" + st3 +
                ", st4=" + st4 +
                ", st5=" + st5 +
                ", st6=" + st6 +
                ", st7=" + st7 +
                ", st8=" + st8 +
                ", pt1=" + pt1 +
                ", pt2=" + pt2 +
                ", pt3=" + pt3 +
                ", pt4=" + pt4 +
                ", pt5=" + pt5 +
                ", pt6=" + pt6 +
                ", pt7=" + pt7 +
                ", pt8=" + pt8 +
                ", umax1=" + umax1 +
                ", umax2=" + umax2 +
                ", umax3=" + umax3 +
                ", umax4=" + umax4 +
                ", umax5=" + umax5 +
                ", umax6=" + umax6 +
                ", umax7=" + umax7 +
                ", umax8=" + umax8 +
                ", imax=" + imax +
                ", imax1=" + imax1 +
                ", imax2=" + imax2 +
                ", imax3=" + imax3 +
                ", imax4=" + imax4 +
                ", imax5=" + imax5 +
                ", imax6=" + imax6 +
                ", imax7=" + imax7 +
                ", imax8=" + imax8 +
                ", ptmax1=" + ptmax1 +
                ", ptmax2=" + ptmax2 +
                ", ptmax3=" + ptmax3 +
                ", ptmax4=" + ptmax4 +
                ", ptmax5=" + ptmax5 +
                ", ptmax6=" + ptmax6 +
                ", ptmax7=" + ptmax7 +
                ", ptmax8=" + ptmax8 +
                ", stmax1=" + stmax1 +
                ", stmax2=" + stmax2 +
                ", stmax3=" + stmax3 +
                ", stmax4=" + stmax4 +
                ", stmax5=" + stmax5 +
                ", stmax6=" + stmax6 +
                ", stmax7=" + stmax7 +
                ", stmax8=" + stmax8 +
                '}';
    }
}
