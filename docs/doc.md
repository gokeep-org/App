[TOC]

### 数据库字段说明

#### 字段含义描述
    0：x*-> 总线故障
    1：u*-> 电压
    2：i*-> 电流
    3：pt*-> 温度
    4：st->漏电
    5：*max或*min-> 阈值， 如前缀加umax表示电压最大阈值
    6：h*-> 电弧
    7：y*->烟感
    8：trq -> 天然气
    9: yhq -> 液化气
    10:mzq -> 煤制气
    11:status->状态
    
#### 通用字段（所有的设备表均含有以下字段）
    1：modelCode -> 设备型号
    2：createDate -> 创建时间
    3：messageId -> 唯一16位消息ID
    4：warnList ->  报警列表
    5：controCmd -> 控制命令
    6：dataLen -> 数据长度
    7: id -> 表的自增ID
    8：handle_data: 处理时间
    8：warn1~warn16: 最大15种报警，顺序排列，需要参考协议文档描述
    
    
    
#### monitor_manager_device（监控管理设备）
    x1: 第一路总线故障
    x2: 第二路总线故障
    x3: 第三路总线故障
    x4: 第四路总线故障
    x5: 第五路总线故障
    x6: 第六路总线故障
    x7: 第七路总线故障
    x8: 第八路总线故障
    x8: 第一路总线故障
    zdx: 主电故障
    bdx：备电故障
    scx: 输出故障
    报警：
    {
        warn1~warn8: 第一路到第八路总线故障
        warn9: 主电故障
        warn: 备电故障
        warn: 输出故障
    }
    
    
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
    ua1 -> 第1路电压
    ub1 -> 第2路电压
    uc1 -> 第3路电压
    ua2 -> 第4路电压
    ub2 -> 第5路电压
    uc2 -> 第6路电压
    st ->  1路漏电数
    pt ->  1路温度数
    ua ->  第1路电压（这种情况下表示另一种型号, 下面同此相同）
    ub ->  第2路电压
    uc ->  第3路电压
    ia ->  第1路电流
    ib ->  第2路电流
    ic ->  第3路电流
    ia1 -> 第1路电流
    ib1 -> 第1路电流 
    ic1 -> 第2路电流
    ia2 -> 第4路电流
    ib2 -> 第5路电流
    ic2 -> 第6路电流
    ua3 -> 第7路电压
    ub3 -> 第8路电压
    uc3 -> 第9路电压
    ua4 -> 第10路电压
    ub4 -> 第11路电压
    uc4 -> 第12路电压
    
### smoke_feel_monitor_device（烟感监控）
    pt -> 1路温度
    y1 -> 1路烟感
    
### combustible_gas_monitor_device（可燃气体监控）
    trq1 -> 1路天然气
    yhq1 -> 1路液化气
    mzq1 -> 1路煤制气
    
### prvent_fire_door_monitor_device（防火门监控设备）
    status -> 状态
    
### screen_monitor_device（视屏监控）
    status -> 状态
    
### hydraulic_pressure_monitor_device（水压监控设备）
    sy1 -> 1路水压
    
