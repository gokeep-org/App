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
 * 可燃气体监控-06
 */
@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"combustible_gas_monitor_device")
public class CombustibleGasMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<CombustibleGasMonitorDevice>{
    private static final Logger logger = LoggerFactory.getLogger(CombustibleGasMonitorDevice.class);

    public CombustibleGasMonitorDevice(Message message) {
        setMessage(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer trq1;
    private Integer yhq1;
    private Integer mzq1;

    @Override
    public CombustibleGasMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.COMBUSTIBLE_GAS_MONITOR_0601) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_08) {
                    trq1 = DtuUtil.getValue(dataMsgs, 0);
                }
            }else if (message.parseDeviceModelEnum() == DeviceTypeName.COMBUSTIBLE_GAS_MONITOR_0602) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_09) {
                    yhq1 = DtuUtil.getValue(dataMsgs, 0);
                }
            }else if (message.parseDeviceModelEnum() == DeviceTypeName.COMBUSTIBLE_GAS_MONITOR_0603) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_0A) {
                    mzq1 = DtuUtil.getValue(dataMsgs, 0);
                }
            }
        }
        return this;
    }


    @Override
    public boolean execute() {
        try{
            ServiceItem.combustibleGasMonitorService.save(parseEntity());
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
        }
        return true;
    }

    @Override
    public CombustibleGasMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CombustibleGasMonitorDevice{" +
                "id=" + id +
                ", trq1=" + trq1 +
                ", yhq1=" + yhq1 +
                ", mzq1=" + mzq1 +
                '}';
    }
}
