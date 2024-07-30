package com.example.phonemanagementajaxwebservice.repository;

import com.example.phonemanagementajaxwebservice.model.SmartPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISmartPhoneRepository extends JpaRepository<SmartPhone,Long> {
}
