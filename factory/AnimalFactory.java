package Homework.factory;

import Homework.animals.Animal;
import Homework.animals.Cat;
import Homework.animals.Dog;
import Homework.birds.Duck;


public class AnimalFactory {

    public Animal create(AnimalType type){
        if (type == null){
            return  null;
        }
        if(type == AnimalType.CAT) {
            return  new Cat();
        }
        if(type == AnimalType.DOG) {
            return  new Dog();
        }
        if(type == AnimalType.DUCK) {
            return  new Duck();
        }
        throw new  IllegalArgumentException("Неожидаемый тип животного: " + type);
    }
}
