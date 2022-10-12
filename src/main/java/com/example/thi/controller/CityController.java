package com.example.thi.controller;

import com.example.thi.model.City;
import com.example.thi.service.city.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CityController {

    @Autowired
    ICityService cityService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<City>> findAllCity(){
        List<City> cities = (List<City>)cityService.findAll();
        if (cities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id){
        Optional<City> city =cityService.findById(id);
        if (!city.isPresent()){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(city.get(),HttpStatus.OK);
    }

    @PostMapping("/city")
    public ResponseEntity<City> save(@RequestBody City city){
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateStudent(@PathVariable Long id,@RequestBody City city){
        Optional<City> classOptional = cityService.findById(id);
        if (!classOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        city.setId(classOptional.get().getId());
        return new ResponseEntity<>(cityService.save(city),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteStudent(@PathVariable Long id){
        Optional<City> classOptional = cityService.findById(id);
        if (!classOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.remove(id);
        return new ResponseEntity<>(classOptional.get(),HttpStatus.OK);
    }
}
