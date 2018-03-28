package com.app.dtu.repository;

import com.app.dtu.bean.model.device.FireControlPowerMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireControlPowerMonitorReponstory  extends JpaRepository<FireControlPowerMonitorDevice, Long> {
}
