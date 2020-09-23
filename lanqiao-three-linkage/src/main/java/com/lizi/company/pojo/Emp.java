package com.lizi.company.pojo;

import java.util.Objects;

public class Emp {
    // 员工ID
    private int empId;
    // 员工编号
    private String empNo;
    // 员工姓名
    private String empName;
    // 分公司
    private int subComId;

    public Emp() {
    }

    public Emp(int empId, String empNo, String empName, int subComId) {
        this.empId = empId;
        this.empNo = empNo;
        this.empName = empName;
        this.subComId = subComId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getSubComId() {
        return subComId;
    }

    public void setSubComId(int subComId) {
        this.subComId = subComId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return empId == emp.empId &&
                subComId == emp.subComId &&
                Objects.equals(empNo, emp.empNo) &&
                Objects.equals(empName, emp.empName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, empNo, empName, subComId);
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empId=" + empId +
                ", empNo='" + empNo + '\'' +
                ", empName='" + empName + '\'' +
                ", subComId=" + subComId +
                '}';
    }
}
