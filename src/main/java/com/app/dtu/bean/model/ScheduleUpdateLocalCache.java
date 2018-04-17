package com.app.dtu.bean.model;

import com.app.dtu.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class ScheduleUpdateLocalCache {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleUpdateLocalCache.class);

    /**
     * 每一小时执行一次更新操作，更新本地缓存
     * 获取到设备id，sn，型号码
     */
    @Scheduled(cron = "0 0 0/1 * * ?" )
    public static void updateDeviceModelCode() {
        List<DeviceRelation> deviceRelations = new ArrayList<>();
        try {
            List<Map<String, Object>> snids = JdbcUtils.findModeResult("select * from snid", null);
            List<Map<String, Object>> batchs = JdbcUtils.findModeResult("select * from batchs", null);
            if (!CollectionUtils.isEmpty(snids) && !CollectionUtils.isEmpty(batchs)){
                snids.stream().forEach(snidtMap -> {
                    String deviceSn = (String) snidtMap.get("sisn");
                    String deviceId = (String) snidtMap.get("siid");
                    long bcId = (long) snidtMap.get("bc_id");
                    if (StringUtils.isEmpty(bcId)){
                        return;
                    }
                    batchs.stream().forEach(batchMap ->{
                        long batchBcId = (long)batchMap.get("bc_id");
                        if (batchBcId != bcId){
                            return;
                        }
                        DeviceRelation relation = new DeviceRelation();
                        relation.setDevice_id(deviceId);
                        relation.setDevice_sn(deviceSn);
                        relation.setDevice_model_code((String) batchMap.get("dmcode"));
                        deviceRelations.add(relation);
                    });
                });
            }
            if (!CollectionUtils.isEmpty(deviceRelations)){
                LocalCache.updateDeviceRelation(deviceRelations);
            }
            logger.info("Update device model code local cache is success, total num is {}", LocalCache.getDeviceRelationCache().size());
        } catch (SQLException e) {
            logger.error("update local cache search is error");
        }finally {
            // 这里是一个连接，不需要关闭数据库连接
            // JdbcUtils.close();
        }
    }
}