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
import java.util.ArrayList;
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

    // cmd = 11
    //
    private String ua;
    private String dw_ua;

    private String ub;
    private String dw_ub;

    private String uc;
    private String ia;
    private String ib;
    private String ic;
    private Integer st;
    private Integer pt;
    private Integer umax;
    private Integer umin;
    private String usum;
    private String isum;

    private Integer imax;
    private Integer ptmax;
    private Integer ibh;
    private Integer stmax;
    // 频率
    private String pl;
    //    ③包含有功功率P,无功功率Q,视在功率S,字节数4*3=12B;
    private String yggl;
    private String wggl;
    private String szgl;

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
    private String pb;
    private String pc;

    // 各项无功功率
    private String qa;
    private String gb;
    private String qc;

    // 各项的功率因素
    private String pfa;
    private String pfb;
    private String pfc;

    // 电压谐波
    private String uafft;
    private String ubfft;
    private String ucfft;

    // 电流谐波
    private String iafft;
    private String ibfft;
    private String icfft;



    @Override
    public boolean execute() {
        try {
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
        List<String> udws = new ArrayList<>();
        List<String> uvalues = new ArrayList<>();
        List<String> idws = new ArrayList<>();
        List<String> iList = new ArrayList<>();
        List<String> gldws = new ArrayList<>();
        List<String> glvalues = new ArrayList<>();
        List<String> fxs = new ArrayList<>();
        buildRedunancyDeviceInfo();
        if (CollectionUtils.isEmpty(message.getDataMsgs())) {
            return this;
        }

        // TODO:
        for (int i = 0; i < message.getDataMsgs().size(); i++) {
            DataMsg dataMsg = message.getDataMsgs().get(i);
            List<Integer> dataMsgs = dataMsg.getDatas();
            if (message.parseDeviceModelEnum() == DeviceTypeName.INTELLIGENT_POWER_MONITOR_0201) {
                if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_03) {
                    for (int k=0; k < 4; k++){
                        Integer uaInt = DtuUtil.getIntegerValue(dataMsgs, k * 2);
                        byte[] bytes = intToByte(uaInt);
                        int fxdw = bytes[0]; // 1个单位
                        int xsw = bytes[1] / 16; // 小数位
                        int wa = (bytes[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
                        dw_ua = fxdw == 0 ? "V" : "KV";
                        int aa = DtuUtil.getIntegerValue(dataMsgs, 1 + k*2) + wa;
                        uvalues.add(toFloatString(aa, xsw));// 电压的完整数据值
                        udws.add(dw_ua);
                    }
//                    // TODO: 分成各1个byte位
//                    byte[] bytes = intToByte(uaInt);
//                    int fxdw = bytes[0]; // 1个单位
//                    int xsw = bytes[1] / 16; // 小数位
//                    int wa = (bytes[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
//                    dw_ua = fxdw == 0 ? "V" : "KV";
//                    int aa = DtuUtil.getIntegerValue(dataMsgs, 1) + wa;
//                    ua = toFloatString(aa, xsw);// 电压的完整数据值
//
//                    Integer ubInt = DtuUtil.getIntegerValue(dataMsgs, 2);
//                    // TODO: 分成各1个byte位
//                    byte[] bytes1 = intToByte(ubInt);
//                    int fxdw1 = bytes1[0]; // 1个单位
//                    int xsw1 = bytes1[1] >> 4; // 小数位
//                    int wa1 = (bytes1[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
//                    dw_ub = fxdw1 == 0 ? "V" : "KV";
//                    int aa1 = DtuUtil.getIntegerValue(dataMsgs, 3) + wa1;
//                    ub = toFloatString(aa1, xsw1);// 电压的完整数据值
//
//                    ua = DtuUtil.getIntegerValue(dataMsgs, 1);
//                    uc = DtuUtil.getIntegerValue(dataMsgs, 2);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_04) {
                    for (int j=0; j < 4; j++){
                        Integer uaInt = DtuUtil.getIntegerValue(dataMsgs, j * 2);
                        byte[] bytes = intToByte(uaInt);
                        int fxdw = bytes[0]; // 1个单位
                        int xsw = bytes[1] / 16; // 小数位
                        int wa = (bytes[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
                        dw_ua = fxdw == 0 ? "A" : "KA";
                        int aa = DtuUtil.getIntegerValue(dataMsgs, 1 + j*2) + wa;
                        iList.add(toFloatString(aa, xsw));// 电压的完整数据值
                        idws.add(dw_ua);
                    }
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_01) {
                    st = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_02) {
                    pt = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_83) {
                    umax = DtuUtil.getIntegerValue(dataMsgs, 0);
                    umin = DtuUtil.getIntegerValue(dataMsgs, 1);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_84) {
                    imax = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_82) {
                    ptmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_8B) {
                    ibh = DtuUtil.getIntegerValue(dataMsgs, 0);
                }  else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_81) {
                    stmax = DtuUtil.getIntegerValue(dataMsgs, 0);
                } else if (DataType.getValue(dataMsg.getType()) == DataType.DATA_TYPE_0D) {
                    if (message.getControCmd() == 0x11) {
                        pl = String.valueOf((float)DtuUtil.getIntegerValue(dataMsgs, 0) / 100.00f);
                        for (int m=0; m < 3; m++){
                            Integer uaInt = DtuUtil.getIntegerValue(dataMsgs, m * 2 + 1);
                            byte[] bytes = intToByte(uaInt);
                            int fxdw = bytes[0] / 16; // 1个单位
                            int dw = bytes[0] & 0x0F; // 1个单位
                            if (fxdw == 0) {
                                gldws.add("");
                            }else{
                                gldws.add("-");
                            }

                            int xsw = bytes[1] / 16; // 小数位
                            int wa = (bytes[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
                            if (m == 0){
                                dw_ua = dw == 0 ? "W" : dw == 1 ? "KW" : "MW";
                            }else if (m == 1){
                                dw_ua = dw == 0 ? "var" : dw == 1 ? "Kvar" : "Mvar";

                            }else if (m == 2){
                                dw_ua = dw == 0 ? "VA" : dw == 1 ? "KVA" : "MVA";
                            }
                            int aa = DtuUtil.getIntegerValue(dataMsgs, 2 + m*2) + wa;
                            glvalues.add(toFloatString(aa, xsw));// 电压的完整数据值
                            gldws.add(dw_ua);
                        }
//                        yggl = String.valueOf((float)DtuUtil.getIntegerValue(dataMsgs, 1) / 100.00f);
//                        wggl = String.valueOf((float)DtuUtil.getIntegerValue(dataMsgs, 2) / 100.00f);
//                        szgl = String.valueOf((float)DtuUtil.getIntegerValue(dataMsgs, 3) / 100.00f);
                        zglys = String.valueOf((float)DtuUtil.getIntegerValue(dataMsgs, 7) / 1000.00f);
                        double zx = DtuUtil.getIntegerValue(dataMsgs, 8) * 65536 + DtuUtil.getIntegerValue(dataMsgs, 9);
                        zxygdn = String.valueOf(zx / 10.00f);
                        zx = DtuUtil.getIntegerValue(dataMsgs, 10) * 65536 + DtuUtil.getIntegerValue(dataMsgs, 11);
                        zxwgdn = String.valueOf(zx/ 10.00f);

                    }
                }
            }
        }
        return this;
    }

    /**
     * Int 类型数据加上小数位
     * @param byteValue
     * @param xsw
     * @return
     */
    private String toFloatString(Integer byteValue, Integer xsw) {
        return String.valueOf(byteValue/(Math.pow(10, xsw)));
    }

    /**
     * int 转 byte位
     * @param uaInt
     * @return
     */
    private byte[] intToByte(Integer uaInt) {

        byte[] byteNum = new byte[2];
        byteNum[0] = (byte) (uaInt / 256);
        byteNum[1] = (byte) (uaInt % 256);

//        for (int ix = 0; ix < 2; ++ix) {
//            int offset = 32 - (ix + 1) * 8;
//            byteNum[ix] = (byte) ((uaInt >> offset) & 0xff);
//        }
        return byteNum;
    }



//    String getDwValue(List<Integer> dataMsgs, int indexOf){
//        Integer uaInt = DtuUtil.getIntegerValue(dataMsgs, indexOf);
//        // TODO: 分成各1个byte位
//        byte[] bytes = intToByte(uaInt);
//        int fxdw = bytes[0]; // 1个单位
//        int xsw = bytes[1] >> 4; // 小数位
//        int wa = (bytes[1] & 0X0F) * 65536; // 数据冗余，当数据比较大的时候
//        dw_ua = fxdw == 0 ? "V" : "KV";
//        int aa = DtuUtil.getIntegerValue(dataMsgs, indexOf + 1) + wa;
//        ua = toFloatString(aa, xsw);// 电压的完整数据值
//    }
}




