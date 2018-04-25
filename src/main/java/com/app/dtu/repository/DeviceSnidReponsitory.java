package com.app.dtu.repository;

import com.app.dtu.bean.model.DeviceSnid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface DeviceSnidReponsitory extends JpaRepository<DeviceSnid, Long> {

    /**
     * 这里需要实现异步的方法调用，保证中间不被阻塞
     *
     * @return
     */
    @Async
    @Override
    List<DeviceSnid> findAll();
}
