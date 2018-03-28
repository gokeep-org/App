package com.app.dtu.repository;

import com.app.dtu.bean.model.device.FaultArcMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaultArcMonitorReponsitory  extends JpaRepository<FaultArcMonitorDevice, Long> {
}
