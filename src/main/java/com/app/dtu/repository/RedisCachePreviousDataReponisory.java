package com.app.dtu.repository;

import com.app.dtu.bean.model.PreviousData;
import org.springframework.data.jpa.repository.JpaRepository;

// 实现同步缓存
public interface RedisCachePreviousDataReponisory extends JpaRepository<PreviousData, String> {
}
