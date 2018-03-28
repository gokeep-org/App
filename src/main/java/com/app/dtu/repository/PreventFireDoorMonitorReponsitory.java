package com.app.dtu.repository;

import com.app.dtu.bean.model.device.PrventFireDoorMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreventFireDoorMonitorReponsitory  extends JpaRepository<PrventFireDoorMonitorDevice, Long> {
}
