package org.ajou.realcoding.weathercrawler.Service;

import org.ajou.realcoding.weathercrawler.domain.Dog;
import org.ajou.realcoding.weathercrawler.repository.DogManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogManagementService {

    @Autowired
    private DogManagementRepository dogManagementRepository;

    public void createDog(Dog dog) {
        //강아이 이름이 너무 길진 않은지, 타입이 정상적인지, 강아지 나이가 적절한지 등등 처리(비즈니스 로직 처리)
        dogManagementRepository.insertDog(dog);
    }

    public Dog findDOg(String name) {
        return dogManagementRepository.findDog(name);
    }

    public void updateDogsType(String name, String type) {
        dogManagementRepository.updateDogsType(name,type);
    }

    public void updateDog(Dog dog) {
        dogManagementRepository.updateDog(dog);
    }

    public void delete(String name) {
        dogManagementRepository.deleteDog(name);
    }
}
