package com.app.dtu.repository;

import com.app.dtu.bean.model.monitormanager.MonitorManagerDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MonitorManagerDeviceReponsitory extends JpaRepository<MonitorManagerDevice, Long> {
}
