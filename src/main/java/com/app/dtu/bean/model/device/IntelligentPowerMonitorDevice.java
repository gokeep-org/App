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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 智能电力设监控设别-02
 * 前缀说明：
 * dw_: 表示为单位
 * zfx: 表示方向
 * xsw: 表示小数位
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

    // 电压参数
    private String ua;
    private String dw_ua;
    private String ub;
    private String dw_ub;
    private String uc;
    private String dw_uc;


    // 电流参数
    private String ia;
    private String dw_ia;
    private String ib;
    private String dw_ib;
    private String ic;
    private String dw_ic;


    private Integer st;
    private Integer pt;
    private Integer umax;
    private Integer umin;
    private String usum;
    private String dw_usum;
    private String isum;
    private String dw_isum;

    private Integer imax;
    private String dw_imax;
    private Integer ptmax;
    private Integer ibh;
    private Integer stmax;
    // 频率
    private String pl;

    // 有功功率，无功功率，视在功率
    private String yggl;
    private String dw_yggl;
    private String wggl;
    private String dw_wggl;
    private String szgl;
    private String dw_szgl;

    // 总功率因数
    private String zglys;


    // 电能，前正后反
    private String zxygdn;
    private String zxwgdn;


    // cmd 为 12

    // 电压相位
    private String uaxw;
    private String ubxw;
    private String ucxw;

    // 电压不平衡度，
    private String ubphd;// 电压
    private String ibphd; // 电流


    // 电流相位
    private String iaxw;
    private String ibxw;
    private String icxw;


    // 各项有功功率
    private String pa;
    private String dw_pa;
    private String pb;
    private String dw_pb;
    private String pc;
    private String dw_pc;

    // 各项无功功率
    private String qa;
    private String dw_qa;
    private String qb;
    private String dw_qb;
    private String qc;
    private String dw_qc;

    // 各项的功率因素
    private String pfa;
    private String pfb;
    private String pfc;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String uaXb;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String ubXb;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String ucXb;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String iaXb;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String ibXb;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private String icXb;

    @Override
    public boolean execute() {
        try {
//            if (getControCmd() != 0x11 && getControCmd() != 0x12) {
//                IntelligentPowerMonitorHarmonic harmonicDevice = new IntelligentPowerMonitorHarmonic();
//            }
            DeviceDataDeal deviceDataDeal = getStorageEntity();
            if (Objects.isNull(deviceDataDeal)) {
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
        // 电压单位
        List<String> udws = new ArrayList<>();
        // 电压值
        List<String> uValues = new ArrayList<>();
        // 电流单位
        List<String> idws = new ArrayList<>();
        // 电流值
        List<String> iValues = new ArrayList<>();
        // 有功功率单位
        List<String> yggldws = new ArrayList<>();
        // 有功功率值
        List<String> ygglvalues = new ArrayList<>();
        // 无功功率值
        List<String> wgglValues = new ArrayList<>();
        // 无功功率单位
        List<String> wggldws = new ArrayList<>();
        // 有功功率，无功功率，视在功率
        List<String> qps = new ArrayList<>();
        // 有功功率，无功功率，视在功率
        List<String> qpsdws = new ArrayList<>();
        // 各项功率因数
        List<String> pfs = new ArrayList<>();
        // 谐波值
        List<String> xbs = new ArrayList<>();

        buildRedunancyDeviceInfo();
        if (CollectionUtils.isEmpty(message.getDataMsgs())) {
            return this;
        }

        if (getControCmd() == 0x11){
            message.setDataMsgs(setFirstValue(message.getDataMsgs(), 4));
        }
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.INTELLIGENT_POWER_MONITOR_0201) {

                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    // 电压
                    for (int k = 0; k < 4; k++) {
                        Integer uaInt = DtuUtil.getIntegerValue(dataMsgs, k * 2);
                        byte[] bytes = intToByte(uaInt);
                        int fxdw = bytes[0]; // 1个单位
                        int xsw = bytes[1] / 16; // 小数位
                        int wa = (bytes[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
                        String dw = fxdw == 0 ? "V" : "KV";
                        int aa = DtuUtil.getIntegerValue(dataMsgs, 1 + k * 2) + wa;
                        uValues.add(toFloatString(aa, xsw));// 电压的完整数据值
                        udws.add(dw);
                    }
                    ua = getValueByList(uValues, 0);
                    dw_ua = getValueByList(udws, 0);
                    ub = getValueByList(uValues, 1);
                    dw_ub = getValueByList(udws, 1);
                    uc = getValueByList(uValues, 2);
                    dw_uc = getValueByList(udws, 2);
                    usum = getValueByList(uValues, 3);
                    dw_usum = getValueByList(udws, 3);

                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {
                    for (int j = 0; j < 4; j++) {
                        Integer uaInt = DtuUtil.getIntegerValue(dataMsgs, j * 2);
                        byte[] bytes = intToByte(uaInt);
                        int fxdw = bytes[0]; // 1个单位
                        int xsw = bytes[1] / 16; // 小数位
                        int wa = (bytes[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
                        String dw = fxdw == 0 ? "A" : "KA";
                        int aa = (DtuUtil.getIntegerValue(dataMsgs, 1 + j * 2) + wa) * ibh;
                        iValues.add(toFloatString(aa, xsw));// 电压的完整数据值
                        idws.add(dw);
                    }
                    ia = getValueByList(iValues, 0);
                    dw_ia = getValueByList(idws, 0);
                    ib = getValueByList(iValues, 1);
                    dw_ib = getValueByList(idws, 1);
                    ic = getValueByList(iValues, 2);
                    dw_ic = getValueByList(idws, 2);
                    isum = getValueByList(iValues, 3);
                    dw_isum = getValueByList(idws, 3);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83) {
                    umax = DtuUtil.getIntegerValue(dataMsgs, 0);
                    umin = DtuUtil.getIntegerValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax = (DtuUtil.getIntegerValue(dataMsgs, 0) * ibh)/1000;
                    dw_imax = imax < 1000? "A":"KA";
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82) {
                    ptmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_8B) {
                    ibh = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_0D) {
                    // 总功率QPS
                    if (message.getControCmd() == 0x11) {
                        pl = String.valueOf((float) DtuUtil.getIntegerValue(dataMsgs, 0) / 100.00f);
                        for (int m = 0; m < 3; m++) {
                            Integer glInt = DtuUtil.getIntegerValue(dataMsgs, m * 2 + 1);
                            byte[] bytes = intToByte(glInt);
                            int fxdw = bytes[0] / 16; // 1个单位
                            int dw = bytes[0] & 0x0F; // 1个单位
                            if (fxdw == 0) {
                                yggldws.add("");
                            } else {
                                yggldws.add("-");
                            }

                            int xsw = bytes[1] / 16; // 小数位
                            int wa = (bytes[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
                            String dwValue = null;
                            if (m == 0) {
                                dwValue = dw == 0 ? "W" : dw == 1 ? "KW" : "MW";
                            } else if (m == 1) {
                                dwValue = dw == 0 ? "var" : dw == 1 ? "Kvar" : "Mvar";

                            } else if (m == 2) {
                                dwValue = dw == 0 ? "VA" : dw == 1 ? "KVA" : "MVA";
                            }
                            int aa = DtuUtil.getIntegerValue(dataMsgs, 2 + m * 2) + wa;
                            qps.add(toFloatString(aa, xsw));// 电压的完整数据值
                            qpsdws.add(dwValue);
                        }
                        yggl = getValueByList(qps, 0);
                        dw_yggl = getValueByList(qpsdws, 0);
                        wggl = getValueByList(qps, 1);
                        dw_wggl = getValueByList(qpsdws, 1);
                        szgl = getValueByList(qps, 2);
                        dw_szgl = getValueByList(qpsdws, 2);
                        zglys = String.valueOf((float) DtuUtil.getIntegerValue(dataMsgs, 7) / 1000.00f);
                        double zx = DtuUtil.getIntegerValue(dataMsgs, 8) * 65536 + DtuUtil.getIntegerValue(dataMsgs, 9);
//                        zxygdn = String.valueOf(zx / 10.00f);
                        zxygdn = new BigDecimal(zx / 10.00f).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
                        zx = DtuUtil.getIntegerValue(dataMsgs, 10) * 65536 + DtuUtil.getIntegerValue(dataMsgs, 11);
//                        zxwgdn = String.valueOf(zx / 10.00f);
                        zxwgdn = new BigDecimal(zx / 10.00f).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();

                    }
                }else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_11){
                    Xb xb = new Xb(DataType.DATA_TYPE_11.getCode());
                    for (int k = 0; k < 32; k++) {
                        float value = DtuUtil.getIntegerValue(dataMsgs, k) / 100.00f;
                        String xbValue = String.valueOf(+value) + "%";
                        xb.addXb(xbValue);
                        xb.toJson();
                        uaXb = DtuUtil.toJson(xb);
                    }
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_12){
                    Xb xb = new Xb(DataType.DATA_TYPE_12.getCode());
                    for (int k = 0; k < 32; k++) {
                        float value = DtuUtil.getIntegerValue(dataMsgs, k) / 100.00f;
                        String xbValue = String.valueOf(+value) + "%";
                        xb.addXb(xbValue);
                        xb.toJson();
                        ubXb = DtuUtil.toJson(xb);
                    }
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_13){
                    Xb xb = new Xb(DataType.DATA_TYPE_13.getCode());
                    for (int k = 0; k < 32; k++) {
                        float value = DtuUtil.getIntegerValue(dataMsgs, k) / 100.00f;
                        String xbValue = String.valueOf(+value) + "%";
                        xb.addXb(xbValue);
                        xb.toJson();
                        ucXb = DtuUtil.toJson(xb);
                    }
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_14){
                    Xb xb = new Xb(DataType.DATA_TYPE_14.getCode());
                    for (int k = 0; k < 32; k++) {
                        float value = DtuUtil.getIntegerValue(dataMsgs, k) / 100.00f;
                        String xbValue = String.valueOf(+value) + "%";
                        xb.addXb(xbValue);
                        xb.toJson();
                        iaXb = DtuUtil.toJson(xb);
                    }
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_15){
                    Xb xb = new Xb(DataType.DATA_TYPE_15.getCode());
                    for (int k = 0; k < 32; k++) {
                        float value = DtuUtil.getIntegerValue(dataMsgs, k) / 100.00f;
                        String xbValue = String.valueOf(+value) + "%";
                        xb.addXb(xbValue);
                        xb.toJson();
                        ibXb = DtuUtil.toJson(xb);
                    }
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_16){
                    Xb xb = new Xb(DataType.DATA_TYPE_16.getCode());
                    for (int k = 0; k < 32; k++) {
                        float value = DtuUtil.getIntegerValue(dataMsgs, k) / 100.00f;
                        String xbValue = String.valueOf(+value) + "%";
                        xb.addXb(xbValue);
                        xb.toJson();
                        icXb = DtuUtil.toJson(xb);
                    }
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_0F) {
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
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_0E) {
                    uaxw = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 0) / 10f);
                    ubxw = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 1) / 10f);
                    ucxw = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 2) / 10f);

                    ubphd = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 3) / 100f) + "%";
                    iaxw = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 4) / 10f);
                    ibxw = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 5) / 10f);
                    icxw = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 6) / 10f);
                    ibphd = String.valueOf(DtuUtil.getIntegerValue(dataMsgs, 7) / 100f) + "%";

                    // 各项有功功率
                    for (int m = 0; m < 3; m++) {
                        Integer ygglInt = DtuUtil.getIntegerValue(dataMsgs, m * 2 + 8);
                        byte[] bytes = intToByte(ygglInt);
                        int fxdw = bytes[0] / 16; // 1个单位
                        int dw = bytes[0] & 0x0F; // 1个单位
                        String fx = fxdw == 0 ? "" : "-";
                        int xsw = bytes[1] / 16; // 小数位
                        int wa = (bytes[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
                        String yggldw = dw == 0 ? "W" : dw == 1 ? "KW" : "MW";
                        int ygglValue = DtuUtil.getIntegerValue(dataMsgs, 9 + m * 2) + wa;
                        ygglvalues.add(fx + toFloatString(ygglValue, xsw));// 电压的完整数据值
                        yggldws.add(yggldw);
                    }
                    pa = getValueByList(ygglvalues, 0);
                    dw_pa = getValueByList(yggldws, 3);
                    pb = getValueByList(ygglvalues, 1);
                    dw_pb = getValueByList(yggldws, 4);
                    pc = getValueByList(ygglvalues, 2);
                    dw_pc = getValueByList(yggldws, 5);
                    // 各项无功功率
                    for (int m = 0; m < 3; m++) {
                        Integer uaInt = DtuUtil.getIntegerValue(dataMsgs, m * 2 + 14);
                        byte[] bytes = intToByte(uaInt);
                        int fxdw = bytes[0] / 16; // 1个单位
                        int dw = bytes[0] & 0x0F; // 1个单位
                        if (fxdw == 0) {
                            yggldws.add("");
                        } else {
                            yggldws.add("-");
                        }
                        int xsw = bytes[1] / 16; // 小数位
                        int wa = (bytes[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
                        String fx = dw == 0 ? "var" : dw == 1 ? "Kvar" : "Mvar";
                        int aa = DtuUtil.getIntegerValue(dataMsgs, 15 + m * 2) + wa;
                        wgglValues.add(toFloatString(aa, xsw));// 电压的完整数据值
                        wggldws.add(fx);
                    }
                    qa = getValueByList(wgglValues, 0);
                    dw_qa = getValueByList(wggldws, 0);
                    qb = getValueByList(wgglValues, 1);
                    dw_qb = getValueByList(wggldws, 1);
                    qc = getValueByList(wgglValues, 2);
                    dw_qc = getValueByList(wggldws, 2);
                    // 各项功率因数
                    for (int j = 0; j < 3; j++) {
                        Integer pfValue = DtuUtil.getIntegerValue(dataMsgs, j + 20);
                        byte[] bytes = intToByte(pfValue);
                        int fxdw = bytes[0] & 0x80; // 方向
                        String fx = fxdw == 0 ? "" : "-";
                        int wa = (bytes[0] & 0X7F) * 256 + (bytes[1] & 0xFF); // 数据冗余，当数据比较大的时候
                        pfs.add(fx + toFloatString(wa, 3));// 电压的完整数据值
                    }
                    pfa = getValueByList(pfs, 0);
                    pfb = getValueByList(pfs, 1);
                    pfc = getValueByList(pfs, 2);
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

    private String getValueByList(List<String> values, int i) {
        if (CollectionUtils.isEmpty(values) || values.size() - 1 < i) {
            return null;
        }
        return values.get(i);
    }

    /**
     * Int 类型数据加上小数位
     *
     * @param byteValue
     * @param xsw
     * @return
     */
    private String toFloatString(Integer byteValue, Integer xsw) {
        return String.valueOf(byteValue / (Math.pow(10, xsw)));
    }

    /**
     * int 转 byte位
     *
     * @param uaInt
     * @return
     */
    private byte[] intToByte(Integer uaInt) {
        byte[] byteNum = new byte[2];
        byteNum[0] = (byte) (uaInt / 256);
        byteNum[1] = (byte) (uaInt % 256);
        return byteNum;
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
        return "IntelligentPowerMonitorDevice{" +
                "id=" + getId() +
                "ua='" + ua + '\'' +
                ", dw_ua='" + dw_ua + '\'' +
                ", ub='" + ub + '\'' +
                ", dw_ub='" + dw_ub + '\'' +
                ", uc='" + uc + '\'' +
                ", dw_uc='" + dw_uc + '\'' +
                ", ia='" + ia + '\'' +
                ", dw_ia='" + dw_ia + '\'' +
                ", ib='" + ib + '\'' +
                ", dw_ib='" + dw_ib + '\'' +
                ", ic='" + ic + '\'' +
                ", dw_ic='" + dw_ic + '\'' +
                ", st=" + st +
                ", pt=" + pt +
                ", umax=" + umax +
                ", umin=" + umin +
                ", usum='" + usum + '\'' +
                ", dw_usum='" + dw_usum + '\'' +
                ", isum='" + isum + '\'' +
                ", dw_isum='" + dw_isum + '\'' +
                ", imax=" + imax +
                ", dw_imax='" + dw_imax + '\'' +
                ", ptmax=" + ptmax +
                ", ibh=" + ibh +
                ", stmax=" + stmax +
                ", pl='" + pl + '\'' +
                ", yggl='" + yggl + '\'' +
                ", dw_yggl='" + dw_yggl + '\'' +
                ", wggl='" + wggl + '\'' +
                ", dw_wggl='" + dw_wggl + '\'' +
                ", szgl='" + szgl + '\'' +
                ", dw_szgl='" + dw_szgl + '\'' +
                ", zglys='" + zglys + '\'' +
                ", zxygdn='" + zxygdn + '\'' +
                ", zxwgdn='" + zxwgdn + '\'' +
                ", uaxw='" + uaxw + '\'' +
                ", ubxw='" + ubxw + '\'' +
                ", ucxw='" + ucxw + '\'' +
                ", ubphd='" + ubphd + '\'' +
                ", ibphd='" + ibphd + '\'' +
                ", iaxw='" + iaxw + '\'' +
                ", ibxw='" + ibxw + '\'' +
                ", icxw='" + icxw + '\'' +
                ", pa='" + pa + '\'' +
                ", dw_pa='" + dw_pa + '\'' +
                ", pb='" + pb + '\'' +
                ", dw_pb='" + dw_pb + '\'' +
                ", pc='" + pc + '\'' +
                ", dw_pc='" + dw_pc + '\'' +
                ", qa='" + qa + '\'' +
                ", dw_qa='" + dw_qa + '\'' +
                ", qb='" + qb + '\'' +
                ", dw_qb='" + dw_qb + '\'' +
                ", qc='" + qc + '\'' +
                ", dw_qc='" + dw_qc + '\'' +
                ", pfa='" + pfa + '\'' +
                ", pfb='" + pfb + '\'' +
                ", pfc='" + pfc + '\'' +
                ", uaXb='" + uaXb + '\'' +
                ", ubXb='" + ubXb + '\'' +
                ", ucXb='" + ucXb + '\'' +
                ", iaXb='" + iaXb + '\'' +
                ", ibXb='" + ibXb + '\'' +
                ", icXb='" + icXb + '\'' +
                '}';
    }
}




