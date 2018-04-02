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
 * 智能电力设监控设别-02
 */
@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"intelligent_power_monitor_device")
public class IntelligentPowerMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<IntelligentPowerMonitorDevice> {
    private static final Logger logger = LoggerFactory.getLogger(IntelligentPowerMonitorDevice.class);

    public IntelligentPowerMonitorDevice(Message message) {
        setMessage(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ua;
    private Integer ub;
    private Integer uc;
    private Integer la;
    private Integer lb;
    private Integer lc;
    private Integer st;
    private Integer pt;

    private Integer u;
    private Integer i;
    private Integer af;

    public Integer getU() {
        return u;
    }

    public void setU(Integer u) {
        this.u = u;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getAf() {
        return af;
    }

    public void setAf(Integer af) {
        this.af = af;
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

    public Integer getLa() {
        return la;
    }

    public void setLa(Integer la) {
        this.la = la;
    }

    public Integer getLb() {
        return lb;
    }

    public void setLb(Integer lb) {
        this.lb = lb;
    }

    public Integer getLc() {
        return lc;
    }

    public void setLc(Integer lc) {
        this.lc = lc;
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

    @Override
    public boolean execute() {
        try{
            ServiceItem.intelligentPowerService.save(this.generateEntity(getMessage()));
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
        }
        return true;
    }

    @Override
    public IntelligentPowerMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }

    @Override
    public IntelligentPowerMonitorDevice generateEntity(Message message) {
        return null;
    }
}
