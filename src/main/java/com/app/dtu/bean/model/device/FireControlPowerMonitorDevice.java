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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 消防设备电源监控-04
 */

@Entity
@Table(name = DtuConfig.DTU_TABLE_PRIFIX + "fire_control_power_monitor_device")
public class FireControlPowerMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<FireControlPowerMonitorDevice> {

    private static final Logger logger = LoggerFactory.getLogger(FireControlPowerMonitorDevice.class);

    public FireControlPowerMonitorDevice(Message message) {
        setMessage(message);
    }

    public FireControlPowerMonitorDevice() {
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

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
    private String ia;
    private String ib;
    private String ic;
    // 电流变比
    private Integer ibb1;
    private Integer ibb2;
    private String ia1;
    private String ib1;
    private String ic1;
    private String ia2;
    private String ib2;
    private String ic2;

    private Integer ua3;
    private Integer ub3;
    private Integer uc3;
    private Integer ua4;
    private Integer ub4;
    private Integer uc4;

    private Integer umax;
    private Integer umin;
    private String imax1;
    private String imax2;
    private Integer imax3;
    private Integer imax4;
    private Integer imax5;
    private Integer imax6;
    private Integer ptmax;
    private Integer stmax;

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
    public FireControlPowerMonitorDevice generateEntity(Message message) {
        buildRedunancyDeviceInfo();
        if (CollectionUtils.isEmpty(message.getDataMsgs())) {
            return this;
        }
        // TODO: 把变比放到最前面， 调整到每一个设备类型中操作
        if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0402){
            message.setDataMsgs(setFirstValue(message.getDataMsgs(), 4));
        }else if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0403){
            message.setDataMsgs(setFirstValue(message.getDataMsgs(), 2));
        } else if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0404){
            message.setDataMsgs(setFirstValue(message.getDataMsgs(), 4));
        }
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0401) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    ua1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    ub1 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    uc1 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    ua2 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    ub2 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    uc2 = DtuUtil.getIntegerValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83) {
                    umax = DtuUtil.getIntegerValue(dataMsgs, 0);
                    umin = DtuUtil.getIntegerValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax1 = String.valueOf((DtuUtil.getIntegerValue(dataMsgs, 0) * ibb1)/1000f);
                }
            } else if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0402) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    ua = DtuUtil.getIntegerValue(dataMsgs, 0);
                    ub = DtuUtil.getIntegerValue(dataMsgs, 1);
                    uc = DtuUtil.getIntegerValue(dataMsgs, 2);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_8B) {
                    ibb1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {

                    float temIa =( DtuUtil.getIntegerValue(dataMsgs, 0) * ibb1 )/ 1000f;
                    if (temIa < 10) {
                        ia = getFloatString(temIa, 3);
                    } else if (temIa >= 10 && temIa < 100) {
                        ia = getFloatString(temIa, 2);
                    } else if (temIa >= 100 && temIa < 1000) {
                        ia = getFloatString(temIa, 1);
                    } else {
                        ia = String.valueOf(temIa);
                    }
                    float temIb = (DtuUtil.getIntegerValue(dataMsgs, 1) * ibb1) / 1000f;
                    if (temIb < 10) {
                        ib = getFloatString(temIb, 3);
                    } else if (temIb >= 10 && temIb < 100) {
                        ib = getFloatString(temIb, 2);
                    } else if (temIb >= 100 && temIb < 1000) {
                        ib = getFloatString(temIb, 1);
                    } else {
                        ib = String.valueOf(temIb);
                    }
                    float temIc = (DtuUtil.getIntegerValue(dataMsgs, 2) * ibb1) / 1000f;
                    if (temIc < 10) {
                        ic = getFloatString(temIc, 3);
                    } else if (temIc >= 10 && temIc < 100) {
                        ic = getFloatString(temIc, 2);
                    } else if (temIc >= 100 && temIc < 1000) {
                        ic = getFloatString(temIc, 1);
                    } else {
                        ic = String.valueOf(temIc);
                    }
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83) {
                    umax = DtuUtil.getIntegerValue(dataMsgs, 0);
                    umin = DtuUtil.getIntegerValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax1 = String.valueOf((DtuUtil.getIntegerValue(dataMsgs, 0) * ibb1)/1000f);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82) {
                    ptmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                }
            } else if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0403) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_8B) {
                    ibb1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    ibb2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {
                    ia1 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 0), ibb1);
                    ib1 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 1), ibb1);
                    ic1 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 2), ibb1);
                    ia2 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 3), ibb2);
                    ib2 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 4), ibb2);
                    ic2 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 5), ibb2);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax1 = String.valueOf((DtuUtil.getIntegerValue(dataMsgs, 0) * ibb1)/1000.f);
                    imax2 = String.valueOf((DtuUtil.getIntegerValue(dataMsgs, 1) * ibb2) /1000.f);
                    imax3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    imax4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    imax5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    imax6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82) {
                    ptmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                }
            } else if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0404) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_8B) {
                    ibb1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    ibb2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {
                    ia1 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 0), ibb1);
                    ib1 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 1), ibb1);
                    ic1 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 2), ibb1);
                    ia2 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 3), ibb2);
                    ib2 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 4), ibb2);
                    ic2 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 5), ibb2);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    ua1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    ub1 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    uc1 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    ua2 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    ub2 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    uc2 = DtuUtil.getIntegerValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83) {
                    umax = DtuUtil.getIntegerValue(dataMsgs, 0);
                    umin = DtuUtil.getIntegerValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax1 = String.valueOf((DtuUtil.getIntegerValue(dataMsgs, 0) *ibb1) / 1000.f);
                    imax2 = String.valueOf((DtuUtil.getIntegerValue(dataMsgs, 1) *ibb2 /1000.f));
                    imax3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    imax4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    imax5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    imax6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82) {
                    ptmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                }
            } else if (message.parseDeviceModelEnum() == DeviceTypeName.FIRE_CONTROL_POWER_MONITOR_0405) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_8B) {
                    ibb1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    ibb2 = DtuUtil.getIntegerValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {
                    ia1 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 0), ibb1);
                    ib1 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 1), ibb1);
                    ic1 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 2), ibb1);
                    ia2 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 3), ibb2);
                    ib2 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 4), ibb2);
                    ic2 = getValidIValue(DtuUtil.getIntegerValue(dataMsgs, 5), ibb2);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    ua1 = DtuUtil.getIntegerValue(dataMsgs, 0);
                    ub1 = DtuUtil.getIntegerValue(dataMsgs, 1);
                    uc1 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    ua2 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    ub2 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    uc2 = DtuUtil.getIntegerValue(dataMsgs, 5);
                    ua3 = DtuUtil.getIntegerValue(dataMsgs, 6);
                    ub3 = DtuUtil.getIntegerValue(dataMsgs, 7);
                    uc3 = DtuUtil.getIntegerValue(dataMsgs, 8);
                    ua4 = DtuUtil.getIntegerValue(dataMsgs, 9);
                    ub4 = DtuUtil.getIntegerValue(dataMsgs, 10);
                    ub4 = DtuUtil.getIntegerValue(dataMsgs, 11);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83) {
                    umax = DtuUtil.getIntegerValue(dataMsgs, 0);
                    umin = DtuUtil.getIntegerValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax1 = String.valueOf((DtuUtil.getIntegerValue(dataMsgs, 0) * ibb1) /1000.f);
                    imax2 = String.valueOf((DtuUtil.getIntegerValue(dataMsgs, 1) * ibb2) /1000.f);
                    imax3 = DtuUtil.getIntegerValue(dataMsgs, 2);
                    imax4 = DtuUtil.getIntegerValue(dataMsgs, 3);
                    imax5 = DtuUtil.getIntegerValue(dataMsgs, 4);
                    imax6 = DtuUtil.getIntegerValue(dataMsgs, 5);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82) {
                    ptmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                }
            }
        }
        return this;
    }


    @Override
    public boolean execute() {
        try {
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)) {
                return false;
            }
            ServiceItem.fireControlPowerService.updateOldDataStatus(getMessageId());
            ServiceItem.fireControlPowerService.save(deviceDataDeal);
        } catch (Throwable e) {
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


    public String getFloatString(float value, int n) {
        BigDecimal b = new BigDecimal(value);
        return String.valueOf(b.setScale(n, BigDecimal.ROUND_HALF_UP).floatValue());
    }

    /**
     * 获取变比数据电流
     *
     * @param bb
     * @return
     */
    public String getValidIValue(Integer var, int bb) {
        float temI = (var * bb) / 1000f;
        String value = "0";
        if (temI < 10) {
            value = getFloatString(temI, 3);
        } else if (temI >= 10 && temI < 100) {
            value = getFloatString(temI, 2);
        } else if (temI >= 100 && temI < 1000) {
            value = getFloatString(temI, 1);
        } else {
            value = String.valueOf(temI);
        }
        return value;
    }

    public static List<DataMsg>  setFirstValue(List<DataMsg> dataMsgs, int index){
        List<DataMsg> finalDataMsgs = new ArrayList<>();
        finalDataMsgs.add(dataMsgs.get(index));
        dataMsgs.stream().forEach(dataMsg -> {
            finalDataMsgs.add(dataMsg);
        });
        return finalDataMsgs;
    }

    @Override
    public String toString() {
        return "FireControlPowerMonitorDevice{" +
                "id=" + getId() +
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
                ", ia='" + ia + '\'' +
                ", ib='" + ib + '\'' +
                ", ic='" + ic + '\'' +
                ", ibb1=" + ibb1 +
                ", ibb2=" + ibb2 +
                ", ia1='" + ia1 + '\'' +
                ", ib1='" + ib1 + '\'' +
                ", ic1='" + ic1 + '\'' +
                ", ia2='" + ia2 + '\'' +
                ", ib2='" + ib2 + '\'' +
                ", ic2='" + ic2 + '\'' +
                ", ua3=" + ua3 +
                ", ub3=" + ub3 +
                ", uc3=" + uc3 +
                ", ua4=" + ua4 +
                ", ub4=" + ub4 +
                ", uc4=" + uc4 +
                ", umax=" + umax +
                ", umin=" + umin +
                ", imax1='" + imax1 + '\'' +
                ", imax2='" + imax2 + '\'' +
                ", imax3=" + imax3 +
                ", imax4=" + imax4 +
                ", imax5=" + imax5 +
                ", imax6=" + imax6 +
                ", ptmax=" + ptmax +
                ", stmax=" + stmax +
                '}';
    }
}
