package com.nagappans.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Employee {

    private int employeeId;
    private String employeeName;
    private long mobileNumber;
    private Date lastModifiedDate;

    public Employee() {
        this.lastModifiedDate = new Date();
    }

    public Employee(int employeeId, String employeeName, Long mobileNumber) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.mobileNumber = mobileNumber;
        this.lastModifiedDate = new Date();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void updateDate() {
        this.lastModifiedDate = new Date();
    }

    public String toString(Object data) {
        StringBuilder str = new StringBuilder("Employee Details\n");
        str.append(String.format("Id : %d\n", employeeId));
        str.append(String.format("Name : %s\n", employeeName));
        str.append(String.format("Date : %s\n", lastModifiedDate));
        return str.toString();
    }
}
