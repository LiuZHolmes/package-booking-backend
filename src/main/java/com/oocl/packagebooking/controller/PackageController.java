package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oocl.packagebooking.Entity.Package;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
public class PackageController {
    @Autowired
    private PackageRepository packageRepository;

    @GetMapping("/packages")
    public ResponseEntity getPackages(@RequestParam(name = "status", defaultValue = "all") String status) {
        if (status.equals("all"))
            return ResponseEntity.ok(packageRepository.findAll());
        else
            return ResponseEntity.ok(packageRepository.findAll().stream().filter(x -> x.getStatus().equals(status)).collect(Collectors.toList()));
    }

    @PutMapping("/packages/{id}")
    public ResponseEntity updatePackageByID(@PathVariable Long id, @RequestBody Package aPackage) throws Exception {
        Package newPackage = packageRepository.findById(id).orElseThrow(Exception::new);
        newPackage.setStatus(aPackage.getStatus());
        if (aPackage.getAppointment_time() != null) {
            if (isAppointmentTimeEligible(aPackage.getAppointment_time())) {
                newPackage.setAppointment_time(aPackage.getAppointment_time());
                newPackage.setStatus("already_appointment");
                return ResponseEntity.ok(packageRepository.save(newPackage));
            } else return ResponseEntity.badRequest().build();
        }
        else return ResponseEntity.ok(packageRepository.save(newPackage));
    }

    private boolean isAppointmentTimeEligible(Long time) {
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.HOUR_OF_DAY) <= 20 && cal.get(Calendar.HOUR_OF_DAY) >= 9) {
            return true;
        } else return false;
    }
}
