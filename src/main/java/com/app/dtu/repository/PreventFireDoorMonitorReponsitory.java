package com.app.dtu.repository;

import com.app.dtu.bean.model.device.PrventFireDoorMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PreventFireDoorMonitorReponsitory  extends JpaRepository<PrventFireDoorMonitorDevice, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE PrventFireDoorMonitorDevice device SET device.old_flag=1 where device.messageId = :message_id")
    void updateOldDataStatus(@Param("message_id") String messageId);
}
