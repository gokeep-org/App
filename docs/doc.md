[TOC]

### 数据库字段说明

#### 通用字段（所有的设备表均含有以下字段）
    1：modelCode -> 设备型号
    2：createDate -> 创建时间
    3：messageId -> 唯一16位消息ID
    4：warnList ->  报警列表
    5：controCmd -> 控制命令
    6：dataLen -> 数据长度
    7: id -> 表的自增ID
    
#### combustible_gas_monitor_device（可燃气体监控）
    trq1 -> 天然气
    yhq1 -> 液化气
    mzq1 -> 煤制气

### electrical_fire_monitor_device（电器火灾监监控设备）
     ua ->  第1路电压报警
     ub ->  第2路电压报警
     uc ->  第3路电压报警
     ia ->  第1路电流报警
     ib ->  第2路电流报警
     ic ->  第3路电流报警
     st ->  只有1路漏电报警报警
     pta -> 第1路温度报警（与pt——x相同）
     ptb -> 第2路温度报警
     ptc -> 第3路温度报警
     ptn -> 第4路温度报警

     st1 -> 第1路漏电报警报警
     st2 -> 第2路漏电报警报警
     st3 -> 第3路漏电报警报警
     st4 -> 第4路漏电报警报警
     st5 -> 第5路漏电报警报警
     st6 -> 第6路漏电报警报警
     st7 -> 第7路漏电报警报警
     st8 -> 第8路漏电报警报警


     pt1 -> 第1路温度报警
     pt2 -> 第2路温度报警
     pt3 -> 第3路温度报警
     pt4 -> 第4路温度报警
     pt5 -> 第5路温度报警
     pt6 -> 第6路温度报警
     pt7 -> 第7路温度报警
     pt8 -> 第8路温度报警

     umax1 -> 第1路路电压报警阈值
     umax2 -> 第2路路电压报警阈值
     umax3 -> 第3路路电压报警阈值
     umax4 -> 第4路路电压报警阈值
     umax5 -> 第5路路电压报警阈值
     umax6 -> 第6路路电压报警阈值
     umax7 -> 第7路路电压报警阈值
     umax8 -> 第8路路电压报警阈值

     imax1 -> 第1路路电流报警阈值
     imax2 -> 第2路路电流报警阈值
     imax3 -> 第3路路电流报警阈值
     imax4 -> 第4路路电流报警阈值
     imax5 -> 第5路路电流报警阈值
     imax6 -> 第6路路电流报警阈值
     imax7 -> 第7路路电流报警阈值
     imax8 -> 第8路路电流报警阈值

     ptmax1 -> 第1路温度报警阈值
     ptmax2 -> 第1路温度报警阈值
     ptmax3 -> 第1路温度报警阈值
     ptmax4 -> 第1路温度报警阈值
     ptmax5 -> 第1路温度报警阈值
     ptmax6 -> 第1路温度报警阈值
     ptmax7 -> 第1路温度报警阈值
     ptmax8 -> 第1路温度报警阈值

     stmax1 -> 第1路漏电报警阈值
     stmax2 -> 第2路漏电报警阈值
     stmax3 -> 第3路漏电报警阈值
     stmax4 -> 第4路漏电报警阈值
     stmax5 -> 第5路漏电报警阈值
     stmax6 -> 第6路漏电报警阈值
     stmax7 -> 第7路漏电报警阈值
     stmax8 -> 第8路漏电报警阈值

### fault_arc_monitor_device  （故障电弧监控）
    ua  -> 第1路电压报警
    ia  -> 第1路电流报警
    h1  -> 第1路电弧报警
    umax1 -> 第1路电压报警阈值
    imax1 -> 第1路电流报警阈值
    hmax  -> 第1路电弧报警阈值

### fire_control_power_monitor_device （消防设备电源监控）
    ua1 -> 第1路电压报警
    ub1 -> 第1路电压报警
    uc1 -> 
    ua2 ->
    ub2 ->
    uc2 ->
    st ->
    pt ->
    ua ->
    ub ->
    uc ->
    ia ->
    ib ->
    ic ->
    ia1 ->
    ib1 ->
    ic1 ->
    ia2 ->
    ib2 ->
    ic2 ->
    ua3 ->
    ub3 ->
    uc3 ->
    ua4 ->
    ub4 ->
    uc4 ->
    
