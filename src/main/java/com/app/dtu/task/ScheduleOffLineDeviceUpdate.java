package com.app.dtu.task;

import com.app.dtu.bean.model.DeviceRelation;
import com.app.dtu.bean.model.DeviceTypeName;
import com.app.dtu.config.DtuConfig;
import com.app.dtu.service.DataService;
import com.app.dtu.service.ServiceItem;
import com.app.dtu.util.DtuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 一定时间内没有更新我这边加一条离线数据
 * 每15分钟判断按照设备id为维度非离线的数据如果大于15分钟，我这里人工新加一条离线数据
 */

@Configuration
public class ScheduleOffLineDeviceUpdate {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleOffLineDeviceUpdate.class);

    @Scheduled(cron = DtuConfig.LOCAL_OFF_LINE_UPDATE_CRON)
    public void updateOffLineData() {
        logger.info("Start execute update offline data process schedule");
        long startTime = DtuUtil.getCurrentTimestrap();
        List<DeviceRelation> relations = LocalCache.getDeviceRelationCache();
        if (CollectionUtils.isEmpty(relations)) {
            return;
        }

        relations.stream().forEach(relation -> {
            DeviceTypeName deviceTypeName = DeviceTypeName.getDeviceTypeInfoByModelCode(relation.getDevice_model_code());
            if (Objects.isNull(deviceTypeName)) {
                return;
            }
            DataService dataService = getDataService(deviceTypeName);
            if (Objects.isNull(dataService)) {
                return;
            }
//            dataService.updateOldDataStatus(relation.getDevice_id());
            dataService.updateOffLineData(relation.getDevice_id());
        });
        long endTime = DtuUtil.getCurrentTimestrap();
        logger.info("Update off line data schedule sum time is {}s, data num is {}", (endTime - startTime)/1000, relations.size());
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

    public DataService getDataService(DeviceTypeName deviceTypeName) {
        if (deviceTypeName.getTypeCode().equals("00")) {
            return ServiceItem.monitorManagerService;
        } else if (deviceTypeName.getTypeCode().equals("01")) {
            return ServiceItem.electricalFireMonitorService;
        } else if (deviceTypeName.getTypeCode().equals("02")) {
            return ServiceItem.intelligentPowerService;
        } else if (deviceTypeName.getTypeCode().equals("03")) {
            return ServiceItem.faultArcMonitorService;
        } else if (deviceTypeName.getTypeCode().equals("04")) {
            return ServiceItem.fireControlPowerService;
        } else if (deviceTypeName.getTypeCode().equals("05")) {
            return ServiceItem.somkeFeeService;
        } else if (deviceTypeName.getTypeCode().equals("06")) {
            return ServiceItem.combustibleGasMonitorService;
        } else if (deviceTypeName.getTypeCode().equals("07")) {
            return ServiceItem.preventFireDoorService;
        } else if (deviceTypeName.getTypeCode().equals("08")) {
            return ServiceItem.screenMonitorService;
        } else if (deviceTypeName.getTypeCode().equals("12")) {
            return ServiceItem.hydraulicPressureService;
        }
        return null;
    }

}
