package com.app.dtu.bean.model;

import javax.persistence.*;


/**
 * 与平台系统是公用一张表结构
 */
@Entity
@Table(name = "snid")
public class DeviceSnid {
    @Column(name = "si_id")
    private Long siId;
    @Column(name = "bc_id")
    private Long bcId;

    @Id
    @Column(name = "sisn")
    private String sisn;

    @Column(name = "siid")
    private String siid;
    @Column(name = "sistate")
    private String sistate;


    public Long getSiId() {
        return siId;
    }

    public void setSiId(Long siId) {
        this.siId = siId;
    }

    public Long getBcId() {
        return bcId;
    }

    public void setBcId(Long bcId) {
        this.bcId = bcId;
    }

    public String getSisn() {
        return sisn;
    }

    public void setSisn(String sisn) {
        this.sisn = sisn;
    }

    public String getSiid() {
        return siid;
    }

    public void setSiid(String siid) {
        this.siid = siid;
    }

    public String getSistate() {
        return sistate;
    }

    public void setSistate(String sistate) {
        this.sistate = sistate;
    }
}
