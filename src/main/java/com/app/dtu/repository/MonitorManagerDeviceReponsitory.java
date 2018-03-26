package com.app.dtu.repository;

import com.app.dtu.bean.model.monitormanager.MonitorManagerDeviceV2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorManagerDeviceReponsitory extends JpaRepository<MonitorManagerDeviceV2, Long> {
}
