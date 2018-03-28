package com.app.dtu.repository;

import com.app.dtu.bean.model.device.IntelligentPowerMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntelligentPowerMonitorReponsitory extends JpaRepository<IntelligentPowerMonitorDevice, Long> {
}
