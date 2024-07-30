package com.example.phonemanagementajaxwebservice.controller;

import com.example.phonemanagementajaxwebservice.model.SmartPhone;
import com.example.phonemanagementajaxwebservice.service.ISmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/smartphones")
public class SmartPhoneController {
    @Autowired
    private ISmartPhoneService smartPhoneService;

    @GetMapping()
    public ResponseEntity<Iterable<SmartPhone>> listSmartPhone() {
        return new ResponseEntity<>(smartPhoneService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartPhone> getSmartPhone(@PathVariable Long id) {
        Optional<SmartPhone> smartPhoneOptional = smartPhoneService.findById(id);
        if (!smartPhoneOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(smartPhoneOptional.get(),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SmartPhone> createSmartPhone(@RequestBody SmartPhone smartPhone) {
        return new ResponseEntity<>(smartPhoneService.save(smartPhone), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SmartPhone> updateSmartPhone(@PathVariable Long id, @RequestBody SmartPhone smartPhone) {
        Optional<SmartPhone> smartPhoneOptional = smartPhoneService.findById(id);
        if (!smartPhoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhone.setId(smartPhoneOptional.get().getId());
        return new ResponseEntity<>(smartPhoneService.save(smartPhone), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SmartPhone> deleteSmartPhone(@PathVariable Long id) {
        Optional<SmartPhone> smartPhoneOptional = smartPhoneService.findById(id);
        if (!smartPhoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhoneService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
