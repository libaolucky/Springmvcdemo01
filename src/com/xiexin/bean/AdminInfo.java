package com.xiexin.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdminInfo {
    private String adminName;
    private String adminPwd;
    //因为前端过来的参数是个 字符串，所以 这里要 做个类型转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date adminTime;
    private List<Lover> loverList;
    private Integer[] aihao;  // 1.写代码 2.看书 3.读报纸

    @Override
    public String toString() {
        return "AdminInfo{" +
                "adminName='" + adminName + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminTime=" + adminTime +
                ", loverList=" + loverList +
                ", aihao=" + Arrays.toString(aihao) +
                '}';
    }

    public Integer[] getAihao() {
        return aihao;
    }

    public void setAihao(Integer[] aihao) {
        this.aihao = aihao;
    }

    public List<Lover> getLoverList() {
        return loverList;
    }

    public void setLoverList(List<Lover> loverList) {
        this.loverList = loverList;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public Date getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(Date adminTime) {
        this.adminTime = adminTime;
    }
}
