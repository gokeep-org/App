package com.app.dtu.repository;

import com.app.dtu.bean.model.device.SmokeFeelMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmokeFeeMonitorReponsitory extends JpaRepository<SmokeFeelMonitorDevice, Long> {
}
