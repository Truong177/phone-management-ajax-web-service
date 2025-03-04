package com.example.phonemanagementajaxwebservice.service.impl;

import com.example.phonemanagementajaxwebservice.model.SmartPhone;
import com.example.phonemanagementajaxwebservice.repository.ISmartPhoneRepository;
import com.example.phonemanagementajaxwebservice.service.ISmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmartPhoneService implements ISmartPhoneService {
    @Autowired
    private ISmartPhoneRepository smartPhoneRepository;
    @Override
    public Iterable<SmartPhone> findAll() {
        return smartPhoneRepository.findAll();
    }

    @Override
    public SmartPhone save(SmartPhone smartPhone) {
        return smartPhoneRepository.save(smartPhone);
    }

    @Override
    public Optional<SmartPhone> findById(Long id) {
        return smartPhoneRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        smartPhoneRepository.deleteById(id);
    }
}
