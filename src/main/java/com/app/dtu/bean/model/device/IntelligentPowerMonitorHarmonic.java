//package com.app.dtu.bean.model.device;
//
//import com.app.dtu.bean.DataMsg;
//import com.app.dtu.bean.Message;
//import com.app.dtu.bean.model.*;
//import com.app.dtu.config.DtuConfig;
//import com.app.dtu.service.ServiceItem;
//import com.app.dtu.util.DtuUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.CollectionUtils;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
///**
// * 谐波类型单独拿出来一张表
// */
//@Entity
//@Table(name = DtuConfig.DTU_TABLE_PRIFIX + "intelligent_power_monitor_harmonic")
//
//
//public class IntelligentPowerMonitorHarmonic  extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<IntelligentPowerMonitorDevice> {
//    private static final Logger logger = LoggerFactory.getLogger(IntelligentPowerMonitorHarmonic.class);
//
//    // 谐波类型
//    private Integer xbtype;
//    // 电压谐波, 共计32个，这个是要匹配类型的，共有6种
//    private String xb2;
//    private String xb3;
//    private String xb4;
//    private String xb5;
//    private String xb6;
//    private String xb7;
//    private String xb8;
//    private String xb9;
//    private String xb10;
//    private String xb11;
//    private String xb12;
//    private String xb13;
//    private String xb14;
//    private String xb15;
//    private String xb16;
//    private String xb17;
//    private String xb18;
//    private String xb19;
//    private String xb20;
//    private String xb21;
//    private String xb22;
//    private String xb23;
//    private String xb24;
//    private String xb25;
//    private String xb26;
//    private String xb27;
//    private String xb28;
//    private String xb29;
//    private String xb30;
//    private String xb31;
//    private String xb32;
//    private String xb33;
//
//    @Override
//    public boolean execute() {
//        try {
//            DeviceDataDeal deviceDataDeal = getStorageEntity();
//            if (Objects.isNull(deviceDataDeal)) {
//                return false;
//            }
//            ServiceItem.intelligentPowerService.updateOldDataStatus(getMessageId());
//            ServiceItem.intelligentPowerService.save(deviceDataDeal);
//        } catch (Throwable e) {
//            logger.error("Execute add data to db or generate entity is error");
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public IntelligentPowerMonitorDevice buildDevice() {
//        return this;
//    }
//
//    @Override
//    public Message buildMessage() {
//        return getMessage();
//    }
//
//    @Override
//    public IntelligentPowerMonitorHarmonic generateEntity(Message message) {
//        // 电压单位
//        List<String> udws = new ArrayList<>();
//        // 电压值
//        List<String> uValues = new ArrayList<>();
//        // 电流单位
//        List<String> idws = new ArrayList<>();
//        // 电流值
//        List<String> iValues = new ArrayList<>();
//        // 有功功率单位
//        List<String> yggldws = new ArrayList<>();
//        // 有功功率值
//        List<String> ygglvalues = new ArrayList<>();
//        // 无功功率值
//        List<String> wgglValues = new ArrayList<>();
//        // 无功功率单位
//        List<String> wggldws = new ArrayList<>();
//        // 有功功率，无功功率，视在功率
//        List<String> qps = new ArrayList<>();
//        // 有功功率，无功功率，视在功率
//        List<String> qpsdws = new ArrayList<>();
//        // 各项功率因数
//        List<String> pfs = new ArrayList<>();
//        // 谐波值
//        List<String> xbs = new ArrayList<>();
//
//        buildRedunancyDeviceInfo();
//        if (CollectionUtils.isEmpty(message.getDataMsgs())) {
//            return this;
//        }
//
//        if (getControCmd() == 0x11){
//            message.setDataMsgs(setFirstValue(message.getDataMsgs(), 4));
//        }
//        for (int i = 0; i < message.getDataMsgs().size(); i++) {
//            DataMsg dataMsg = message.getDataMsgs().get(i);
//            List<Integer> dataMsgs = dataMsg.getDatas();
//            if (message.parseDeviceModelEnum() == DeviceTypeName.INTELLIGENT_POWER_MONITOR_0201) {
//
//                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_0F) {
//                    for (int k = 0; k < 32; k++) {
//                        float value = DtuUtil.getIntegerValue(dataMsgs, k) / 100.00f;
//                        String xb = String.valueOf(+value) + "%";
//                        xbs.add(xb);
//                    }
//                    if (message.getControCmd() == 0x13) {
//                        xbtype = 0x13;
//                    } else if (message.getControCmd() == 0x14) {
//                        xbtype = 0x14;
//                    } else if (message.getControCmd() == 0x15) {
//                        xbtype = 0x15;
//                    } else if (message.getControCmd() == 0x16) {
//                        xbtype = 0x16;
//                    } else if (message.getControCmd() == 0x17) {
//                        xbtype = 0x17;
//                    } else if (message.getControCmd() == 0x18) {
//                        xbtype = 0x18;
//                    }
//                    xb2 = getValueByList(xbs, 0);
//                    xb3 = getValueByList(xbs, 1);
//                    xb4 = getValueByList(xbs, 2);
//                    xb5 = getValueByList(xbs, 3);
//                    xb6 = getValueByList(xbs, 4);
//                    xb7 = getValueByList(xbs, 5);
//                    xb8 = getValueByList(xbs, 6);
//                    xb9 = getValueByList(xbs, 7);
//                    xb10 = getValueByList(xbs, 8);
//                    xb11 = getValueByList(xbs, 9);
//                    xb12 = getValueByList(xbs, 10);
//                    xb13 = getValueByList(xbs, 11);
//                    xb14 = getValueByList(xbs, 12);
//                    xb15 = getValueByList(xbs, 13);
//                    xb16 = getValueByList(xbs, 14);
//                    xb17 = getValueByList(xbs, 15);
//                    xb18 = getValueByList(xbs, 16);
//                    xb19 = getValueByList(xbs, 17);
//                    xb20 = getValueByList(xbs, 18);
//                    xb21 = getValueByList(xbs, 19);
//                    xb22 = getValueByList(xbs, 20);
//                    xb23 = getValueByList(xbs, 21);
//                    xb24 = getValueByList(xbs, 22);
//                    xb25 = getValueByList(xbs, 23);
//                    xb26 = getValueByList(xbs, 24);
//                    xb27 = getValueByList(xbs, 25);
//                    xb28 = getValueByList(xbs, 26);
//                    xb29 = getValueByList(xbs, 27);
//                    xb30 = getValueByList(xbs, 28);
//                    xb31 = getValueByList(xbs, 29);
//                    xb32 = getValueByList(xbs, 30);
//                    xb33 = getValueByList(xbs, 31);
//                }
//            }
//        }
//        return this;
//    }
//
//    private String getValueByList(List<String> values, int i) {
//        if (CollectionUtils.isEmpty(values) || values.size() - 1 < i) {
//            return null;
//        }
//        return values.get(i);
//    }
//
//    /**
//     * Int 类型数据加上小数位
//     *
//     * @param byteValue
//     * @param xsw
//     * @return
//     */
//    private String toFloatString(Integer byteValue, Integer xsw) {
//        return String.valueOf(byteValue / (Math.pow(10, xsw)));
//    }
//
//    /**
//     * int 转 byte位
//     *
//     * @param uaInt
//     * @return
//     */
//    private byte[] intToByte(Integer uaInt) {
//        byte[] byteNum = new byte[2];
//        byteNum[0] = (byte) (uaInt / 256);
//        byteNum[1] = (byte) (uaInt % 256);
//        return byteNum;
//    }
//
//
//
//    public static List<DataMsg>  setFirstValue(List<DataMsg> dataMsgs, int index){
//        List<DataMsg> finalDataMsgs = new ArrayList<>();
//        finalDataMsgs.add(dataMsgs.get(index));
//        dataMsgs.stream().forEach(dataMsg -> {
//            finalDataMsgs.add(dataMsg);
//        });
//        return finalDataMsgs;
//    }
//}
