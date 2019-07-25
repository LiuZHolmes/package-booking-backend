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

    private Long appointment_time;

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

    public Long getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(Long appointment_time) {
        this.appointment_time = appointment_time;
    }

    public Package() {
    }

    public Package(Long id, String receiver, String phoneNumber, String status, Long appointment_time) {
        this.id = id;
        this.receiver = receiver;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.appointment_time = appointment_time;
    }
}
