package com.oocl.packagebooking.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.oocl.packagebooking.Entity.Package;

@Component
public interface PackageRepository  extends JpaRepository<Package,Long> {
}
