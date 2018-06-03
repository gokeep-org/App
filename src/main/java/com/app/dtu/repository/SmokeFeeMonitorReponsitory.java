package com.app.dtu.repository;

import com.app.dtu.bean.model.device.SmokeFeelMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SmokeFeeMonitorReponsitory extends JpaRepository<SmokeFeelMonitorDevice, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE SmokeFeelMonitorDevice device SET device.old_flag=1 where device.messageId = :message_id")
    void updateOldDataStatus(@Param("message_id") String messageId);

    List<SmokeFeelMonitorDevice> findByMessageIdAndCreateDateGreaterThanEqual(String messageId, long time);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SmokeFeelMonitorDevice device SET device.old_flag=:status where device.id = :id")
    void updatePreviousDataStatus(@Param("id") String id, @Param("status") Integer status);
}
