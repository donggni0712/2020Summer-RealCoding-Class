package org.ajou.realcoding.weathercrawler.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.weathercrawler.domain.Dog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class DogManagementRepository {
    List<Dog> dogList = new ArrayList<>();

    public void insertDog(Dog dog) {
        dogList.add(dog);
        log.info("Dog add {}",dog);
    }

    public Dog findDog(String name) {
        for(Dog dog : dogList){
            if(dog.getName().equals(name)){
                return dog;
            }
        }

        log.error("Can't find {}",name);
        throw new RuntimeException();
    }

    public void updateDogsType(String name, String type) {
        Dog dog = this.findDog(name);
        dog.setType(type);

        log.info("Dog updateType {}",dog);
    }

    public void updateDog(Dog dog) {
        Dog toBeUpdateDog = this.findDog(dog.getName());
        toBeUpdateDog.setType(dog.getType());
        toBeUpdateDog.setAge(dog.getAge());

        log.info("Dog update {}", dog);
    }

    public void deleteDog(String name) {
        Dog dog = this.findDog(name);
        dogList.remove(dog);

        log.info("Dog remove success");
    }
}
