package com.oocl.packagebooking.Service;

import com.oocl.packagebooking.Entity.Package;
import com.oocl.packagebooking.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
