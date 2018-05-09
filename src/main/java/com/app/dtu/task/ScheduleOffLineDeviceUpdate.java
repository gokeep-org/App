package com.app.dtu.task;

import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceBeanNames;
import com.app.dtu.service.ServiceItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 一定时间内没有更新我这边加一条离线数据
 * 每15分钟判断按照设备id为维度非离线的数据如果大于15分钟，我这里人工新加一条离线数据
 */

@Configuration
public class ScheduleOffLineDeviceUpdate {
    @Qualifier(ServiceBeanNames.INTELLIGENT_POWER_SERVICE)

//    @Scheduled(cron = DtuConfig.LOCAL_OFF_LINE_UPDATE_CRON)
    public void updateOffLineData() {
        if (CollectionUtils.isEmpty(getDataServiceLists())) {
            return;
        }
        getDataServiceLists().stream().forEach(dataService -> {
//            dataService.updateOffLineData();
        });
    }

    public List<DataService> getDataServiceLists() {
        return Arrays.asList(
                ServiceItem.combustibleGasMonitorService,
                ServiceItem.electricalFireMonitorService,
                ServiceItem.faultArcMonitorService,
                ServiceItem.fireControlPowerService,
                ServiceItem.hydraulicPressureService,
                ServiceItem.intelligentPowerService,
                ServiceItem.monitorManagerService,
                ServiceItem.preventFireDoorService,
                ServiceItem.screenMonitorService,
                ServiceItem.somkeFeeService
        );
    }
}
