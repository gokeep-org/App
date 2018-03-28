package com.app.dtu.repository;

import com.app.dtu.bean.model.device.CombustibleGasMonitorDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CombustibleGasMonitorReponsitory extends JpaRepository<CombustibleGasMonitorDevice, Long> {
}
