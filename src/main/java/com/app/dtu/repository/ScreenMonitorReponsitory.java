package com.app.dtu.repository;

import com.app.dtu.bean.model.device.ScreenMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenMonitorReponsitory  extends JpaRepository<ScreenMonitorDevice, Long> {
}
