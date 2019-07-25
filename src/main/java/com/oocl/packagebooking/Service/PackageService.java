package com.oocl.packagebooking.Service;

import com.oocl.packagebooking.Entity.Package;
import com.oocl.packagebooking.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public List<Package> getPackages(String status) {
        if (status.equals("all"))
            return packageRepository.findAll();
        else
            return packageRepository.findAll().stream().filter(x -> x.getStatus().equals(status)).collect(Collectors.toList());
    }

    public Package updatePackageByID(Long id, Package aPackage) throws Exception {

        Package newPackage = packageRepository.findById(id).orElseThrow(Exception::new);
        newPackage.setStatus(aPackage.getStatus());
        if (aPackage.getAppointment_time() != null) {
            if (isAppointmentTimeEligible(aPackage.getAppointment_time())) {
                newPackage.setAppointment_time(aPackage.getAppointment_time());
                newPackage.setStatus("already_appointment");
                return packageRepository.save(newPackage);
            } else throw new Exception();
        }
        else return packageRepository.save(newPackage);
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
