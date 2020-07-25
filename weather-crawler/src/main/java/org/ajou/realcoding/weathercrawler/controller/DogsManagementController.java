package org.ajou.realcoding.weathercrawler.controller;

import org.ajou.realcoding.weathercrawler.Service.DogManagementService;
import org.ajou.realcoding.weathercrawler.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DogsManagementController {

    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    public void createDog(@RequestBody Dog requestBody){
//     dogList.add(requestBody);
        dogManagementService.createDog(requestBody);
    }

    //http://localhost:8080/dogs?name=Ian
    @GetMapping("/dogs")
    public Dog findDog(@RequestParam String name){
        return dogManagementService.findDOg(name);
    }

    @PatchMapping("/dogs/{name}")
    public void updateDogsType(@PathVariable String name, @RequestBody String type){
        dogManagementService.updateDogsType(name,type);
    }

    @PutMapping("/dogs/{name}")
    public void updateDog(@RequestBody Dog dog){
        dogManagementService.updateDog(dog);
    }

    @DeleteMapping("/dogs")
    public void deleteDog(@RequestBody String name){
        dogManagementService.delete(name);
    }
}
