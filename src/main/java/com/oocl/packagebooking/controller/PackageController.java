package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackageController {
    @Autowired
    private PackageRepository packageRepository;

    @GetMapping("/packages")
    public ResponseEntity getPackages() {
        return ResponseEntity.ok(packageRepository.findAll());
    }

}
