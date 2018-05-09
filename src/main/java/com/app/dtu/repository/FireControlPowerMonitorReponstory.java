package com.app.dtu.repository;

import com.app.dtu.bean.model.device.FireControlPowerMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FireControlPowerMonitorReponstory  extends JpaRepository<FireControlPowerMonitorDevice, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE FireControlPowerMonitorDevice device SET device.old_flag=1 where device.messageId = :message_id")
    void updateOldDataStatus(@Param("message_id") String messageId);

    List<FireControlPowerMonitorDevice> findByCreateDateGreaterThanEqual(long time);
}
