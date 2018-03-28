package com.app.dtu.repository;

import com.app.dtu.bean.model.device.ElectricalFireMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricalFireMonitorReponsitory  extends JpaRepository<ElectricalFireMonitorDevice, Long> {
}
