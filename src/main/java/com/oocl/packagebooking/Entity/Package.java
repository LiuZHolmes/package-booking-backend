package com.oocl.packagebooking.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Package {

    @Id
    @GeneratedValue
    private Long id;

    private String receiver;

    private String phoneNumber;

    private String status;

    private Date appointment_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(Date appointment_time) {
        this.appointment_time = appointment_time;
    }

    public Package() {
    }

    public Package(String receiver, String phoneNumber, String status, Date appointment_time) {
        this.receiver = receiver;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.appointment_time = appointment_time;
    }
}
