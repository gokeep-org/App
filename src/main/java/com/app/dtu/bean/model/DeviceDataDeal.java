package com.app.dtu.bean.model;

// 设备处理的处理接口, 用于抽象化的业务操作
public interface DeviceDataDeal{
    /**
     * 这里执行数据持久化操作
     * 所有的设备类均实现这里个接口，重写该方法实现数据持久化
     * @return
     */
    boolean execute();


}
