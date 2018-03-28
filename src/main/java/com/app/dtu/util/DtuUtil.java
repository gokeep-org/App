package com.app.dtu.util;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.device.MonitorManagerDevice;

public class DtuUtil {
    private DtuUtil(){}

    public static DeviceDataDeal getDeviceDataDealEntity(Message message){
        String terminaTypeCode = message.parseTypeCode();
        switch (terminaTypeCode){
            case "00" : return new MonitorManagerDevice(message);
            case "01" : return new MonitorManagerDevice(message);
            case "02" : return new MonitorManagerDevice(message);
            case "03" : return new MonitorManagerDevice(message);
            case "04" : return new MonitorManagerDevice(message);
            case "05" : return new MonitorManagerDevice(message);
            case "06" : return new MonitorManagerDevice(message);
            case "07" : return new MonitorManagerDevice(message);
            case "08" : return new MonitorManagerDevice(message);
            case "12" : return new MonitorManagerDevice(message);
            default: return null;
        }
    }
}
