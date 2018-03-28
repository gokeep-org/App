package com.app.dtu.repository;

import com.app.dtu.bean.model.device.HydraulicPressureMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HydraulicPressureMonitorReponsitory  extends JpaRepository<HydraulicPressureMonitorDevice, Long> {
}
