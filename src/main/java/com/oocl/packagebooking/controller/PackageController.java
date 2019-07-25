package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oocl.packagebooking.Entity.Package;

import java.util.stream.Collectors;

@RestController
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
    public ResponseEntity updatePackageOnStatusByID(@PathVariable Long id, @RequestBody Package aPackage) throws Exception {
        Package newPackage = packageRepository.findById(id).orElseThrow(Exception::new);
        newPackage.setStatus(aPackage.getStatus());
        return ResponseEntity.ok(packageRepository.save(newPackage));
    }

}
