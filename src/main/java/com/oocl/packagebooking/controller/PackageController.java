package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.Repository.PackageRepository;
import com.oocl.packagebooking.Service.PackageService;
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

    @Autowired
    private PackageService packageService;

    @GetMapping("/packages")
    public ResponseEntity getPackages(@RequestParam(name = "status", defaultValue = "all") String status) {
        return ResponseEntity.ok(packageService.getPackages(status));
    }

    @PutMapping("/packages/{id}")
    public ResponseEntity updatePackageByID(@PathVariable Long id, @RequestBody Package aPackage) throws Exception {
        Package newPackage;
        try {
            newPackage = packageService.updatePackageByID(id,aPackage);
            return ResponseEntity.ok(newPackage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
