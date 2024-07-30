package com.example.phonemanagementajaxwebservice.service;

import com.example.phonemanagementajaxwebservice.model.SmartPhone;

import java.util.Optional;

public interface ISmartPhoneService {
    Iterable<SmartPhone> findAll();

    SmartPhone save(SmartPhone smartPhone);

    Optional<SmartPhone> findById(Long id);

    void remove(Long id);
}
