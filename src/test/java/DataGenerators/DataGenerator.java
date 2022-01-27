package DataGenerators;

import Objects.Category;
import Objects.Pet;
import Objects.Status;
import com.github.javafaker.Faker;

import java.util.Random;

public class DataGenerator {
    public static Status randomStatus() {
        int pick = new Random().nextInt(Status.values().length);
        return Status.values()[pick];
    }
public static Pet generateNewPet(){
    Faker faker = new Faker();
    Random random = new Random();
    int petId = Math.abs(random.nextInt());
    long categoryId = Math.abs(random.nextLong());
    String name = faker.cat().name();
    Category category = new Category(categoryId, faker.cat().breed());
    String[] photoUrls = {name+" : "+faker.internet().image()};
//    Status status = Status.available;
    Pet pet = new Pet(petId, category, name, photoUrls, DataGenerator.randomStatus());
    return pet;
}

    public static void main(String[] args) {
        Pet pet = DataGenerator.generateNewPet();
        System.out.println(pet.toString());
    }


}
