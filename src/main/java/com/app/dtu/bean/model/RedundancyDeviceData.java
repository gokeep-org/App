package com.app.dtu.bean.model;

import com.app.dtu.bean.Message;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 可以作为基础的数据存储被其他的设备类继承， 不是一张单独的表
 */
@MappedSuperclass
public class RedundancyDeviceData implements Serializable {
    // 设备的型号
    private String modelCode;

    // 添加日期
    private long createDate;

    // 唯一16位消息id
    private String messageId;

    // 告警列表
    private int warnList;

    // 控制指令
    private int controCmd;

    // 数据长度
    private int dataLen;

    private Long handle_date;

    /**
     * 报警列表详情
     */
    private int warn1;
    private int warn2;
    private int warn3;
    private int warn4;
    private int warn5;
    private int warn6;
    private int warn7;
    private int warn8;
    private int warn9;
    private int warn10;
    private int warn11;
    private int warn12;
    private int warn13;
    private int warn14;
    private int warn15;
    private int warn16;

    // 正常状态
    private Integer yhf_state = 1;
    // 报警状态
    private Integer bj_state = 0;
    // 故障状态
    private Integer gz_state = 0;
    // 离线状态
    private Integer lx_state = 0;
    // 是否是老数据
    private Integer old_flag = 0;

    public void buildDeviceStatus() {
        int status = message.getStatus();
        if (status == 0) {
            yhf_state = 1;
            bj_state = 0;
            gz_state = 0;
            lx_state = 0;
        } else if (status == 1) {
            yhf_state = 0;
            bj_state = 1;
            gz_state = 0;
            lx_state = 0;
        } else if (status == 2) {
            yhf_state = 0;
            bj_state = 0;
            gz_state = 1;
            lx_state = 0;
        } else if (status == 3) {
            yhf_state = 0;
            bj_state = 1;
            gz_state = 1;
            lx_state = 0;
        } else if (status == 4) {
            yhf_state = 0;
            bj_state = 0;
            gz_state = 0;
            lx_state = 1;
        }
    }

    public Integer getYhf_state() {
        return yhf_state;
    }

    public void setYhf_state(Integer yhf_state) {
        this.yhf_state = yhf_state;
    }

    public Integer getBj_state() {
        return bj_state;
    }

    public void setBj_state(Integer bj_state) {
        this.bj_state = bj_state;
    }

    public Integer getGz_state() {
        return gz_state;
    }

    public void setGz_state(Integer gz_state) {
        this.gz_state = gz_state;
    }

    public Integer getLx_state() {
        return lx_state;
    }

    public void setLx_state(Integer lx_state) {
        this.lx_state = lx_state;
    }

    public Integer getOld_flag() {
        return old_flag;
    }

    public void setOld_flag(Integer old_flag) {
        this.old_flag = old_flag;
    }

    //    /**
//     * 设备离线设置
//     */
//    private void buildDeviceNormalSttaus() {
//        if (warnList == 0 && controCmd != 28) {
//            this.yhf＿state = true;
//            return;
//        }
//        this.yhf＿state = false;
//    }
//
//
//    /**
//     * 判断是否是离线
//     *
//     * @return
//     */
//    public boolean isOneline() {
//        return controCmd != 28 ? true : false;
//    }
//
//
//    /**
//     * 判断是离线而且存在报警
//     *
//     * @return
//     */
//    public boolean isOnlineAndExistWarnOrFault() {
//        return isOneline() && (warnList != 0);
//    }
//
//
//    /**
//     * 构建设备状态消息
//     *
//     * @param statusWarn
//     * @param statusFault
//     */
//    public void buildDeviceStatus(Boolean statusWarn, Boolean statusFault) {
//        buildDeviceNormalSttaus();
//        buildDeviceStatusOnline();
//        this.bj_state = statusWarn;
//        this.gz＿state = statusFault;
//    }
//
//
//    public void buildAllIsFault() {
//        if (isOnlineAndExistWarnOrFault()) {
//            buildDeviceStatus(false, true);
//        }
//    }
//
//    public void buildAllIsWarn() {
//        if (isOnlineAndExistWarnOrFault()) {
//            buildDeviceStatus(false, true);
//        }
//    }
//
//
//    /**
//     * 判断是不是离线消息
//     */
//    private void buildDeviceStatusOnline() {
//        if (warnList == 0 && controCmd == 28) {
//            this.lx＿state = true;
//            return;
//        }
//        this.lx＿state = false;
//
//    }

    /**
     * 解析报警列表
     */
    private void buildWarnValues() {
        if (this.warnList == 0) {
            return;
        }
        warn1 = (warnList & (1)) == 0 ? 0 : 1;
        warn2 = (warnList & (2)) == 0 ? 0 : 1;
        warn3 = (warnList & (4)) == 0 ? 0 : 1;
        warn4 = (warnList & (8)) == 0 ? 0 : 1;
        warn5 = (warnList & (16)) == 0 ? 0 : 1;
        warn6 = (warnList & (32)) == 0 ? 0 : 1;
        warn7 = (warnList & (64)) == 0 ? 0 : 1;
        warn8 = (warnList & (128)) == 0 ? 0 : 1;
        warn9 = (warnList & (256)) == 0 ? 0 : 1;
        warn10 = (warnList & (512)) == 0 ? 0 : 1;
        warn11 = (warnList & (1024)) == 0 ? 0 : 1;
        warn12 = (warnList & (2018)) == 0 ? 0 : 1;
        warn13 = (warnList & (4096)) == 0 ? 0 : 1;
        warn14 = (warnList & (8192)) == 0 ? 0 : 1;
        warn15 = (warnList & (16384)) == 0 ? 0 : 1;
        warn16 = (warnList & (32768)) == 0 ? 0 : 1;

    }

    /**
     * 设置消息元数据信息
     */
    public void buildRedunancyDeviceInfo() {
        setMessageId(message.getId());
        setCreateDate(new Date().getTime());
        setModelCode(message.parseModelCode());
        setControCmd(message.getControCmd());
        setDataLen(message.getDataLen());
        setWarnList(message.getWarnList());
        buildWarnValues();
        buildDeviceStatus();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Transient
    private Message message;


    public Long getHandle_date() {
        return handle_date;
    }

    public void setHandle_date(Long handle_date) {
        this.handle_date = handle_date;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getWarnList() {
        return warnList;
    }

    public void setWarnList(int warnList) {
        this.warnList = warnList;
    }

    public int getControCmd() {
        return controCmd;
    }

    public void setControCmd(int controCmd) {
        this.controCmd = controCmd;
    }

    public int getDataLen() {
        return dataLen;
    }

    public void setDataLen(int dataLen) {
        this.dataLen = dataLen;
    }

    public int getWarn1() {
        return warn1;
    }

    public void setWarn1(int warn1) {
        this.warn1 = warn1;
    }

    public int getWarn2() {
        return warn2;
    }

    public void setWarn2(int warn2) {
        this.warn2 = warn2;
    }

    public int getWarn3() {
        return warn3;
    }

    public void setWarn3(int warn3) {
        this.warn3 = warn3;
    }

    public int getWarn4() {
        return warn4;
    }

    public void setWarn4(int warn4) {
        this.warn4 = warn4;
    }

    public int getWarn5() {
        return warn5;
    }

    public void setWarn5(int warn5) {
        this.warn5 = warn5;
    }

    public int getWarn6() {
        return warn6;
    }

    public void setWarn6(int warn6) {
        this.warn6 = warn6;
    }

    public int getWarn7() {
        return warn7;
    }

    public void setWarn7(int warn7) {
        this.warn7 = warn7;
    }

    public int getWarn8() {
        return warn8;
    }

    public void setWarn8(int warn8) {
        this.warn8 = warn8;
    }

    public int getWarn9() {
        return warn9;
    }

    public void setWarn9(int warn9) {
        this.warn9 = warn9;
    }

    public int getWarn10() {
        return warn10;
    }

    public void setWarn10(int warn10) {
        this.warn10 = warn10;
    }

    public int getWarn11() {
        return warn11;
    }

    public void setWarn11(int warn11) {
        this.warn11 = warn11;
    }

    public int getWarn12() {
        return warn12;
    }

    public void setWarn12(int warn12) {
        this.warn12 = warn12;
    }

    public int getWarn13() {
        return warn13;
    }

    public void setWarn13(int warn13) {
        this.warn13 = warn13;
    }

    public int getWarn14() {
        return warn14;
    }

    public void setWarn14(int warn14) {
        this.warn14 = warn14;
    }

    public int getWarn15() {
        return warn15;
    }

    public void setWarn15(int warn15) {
        this.warn15 = warn15;
    }

    public int getWarn16() {
        return warn16;
    }

    public void setWarn16(int warn16) {
        this.warn16 = warn16;
    }

    @Override
    public String toString() {
        return "RedundancyDeviceData{" +
                "modelCode='" + modelCode + '\'' +
                ", createDate=" + createDate +
                ", messageId='" + messageId + '\'' +
                ", warnList=" + warnList +
                ", controCmd=" + controCmd +
                ", dataLen=" + dataLen +
                ", handle_date=" + handle_date +
                ", warn1=" + warn1 +
                ", warn2=" + warn2 +
                ", warn3=" + warn3 +
                ", warn4=" + warn4 +
                ", warn5=" + warn5 +
                ", warn6=" + warn6 +
                ", warn7=" + warn7 +
                ", warn8=" + warn8 +
                ", warn9=" + warn9 +
                ", warn10=" + warn10 +
                ", warn11=" + warn11 +
                ", warn12=" + warn12 +
                ", warn13=" + warn13 +
                ", warn14=" + warn14 +
                ", warn15=" + warn15 +
                ", warn16=" + warn16 +
                ", yhf_state=" + yhf_state +
                ", bj_state=" + bj_state +
                ", gz_state=" + gz_state +
                ", lx_state=" + lx_state +
                ", old_flag=" + old_flag +
                ", message=" + message +
                '}';
    }
}
