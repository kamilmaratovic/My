package PetStoreAPITests;

import DataGenerators.DataGenerator;
import Objects.Pet;
import Objects.Status;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Tests {
    private int petId;
    String status;
    Pet pet;
    String baseUri = "https://petstore.swagger.io/v2";

    @BeforeMethod
    public void createPet() {
        pet = DataGenerator.generateNewPet();
        Response response =
                given()
                        .baseUri(baseUri)
                        .basePath("pet")
                        .header("Content-Type", "application/json")
                        .body(pet)
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .extract().response();
    JsonPath jsonPath = response.jsonPath();
    petId = jsonPath.getInt("id");
    status = jsonPath.getString("status");
        System.out.println(status);
    System.out.println(petId);

    }

    @AfterMethod
    public void deletePet(){
//        Response response =
        given()
                .baseUri(baseUri)
                .basePath("pet/"+petId)
                .header("Content-Type","application/json")
                .when()
                .delete()
                .then()
                .statusCode(404);
//                .extract().response();

//        JsonPath jsonPath = response.jsonPath();
//        Pet [] petList = jsonPath.getObject("$", Pet[].class);
//        boolean found = false;
//        for (Pet v: petList){
//            if(v.getId()==petId){
//                found = true;
//                break;
//            }
//        }
//        Assert.assertFalse(found);
    }

    @Test
    public void getPedById(){
        Response response = given()
                .baseUri(baseUri)
                .basePath("pet/"+petId)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Pet petResponse = jsonPath.getObject("$", Pet.class);
        Assert.assertEquals(petResponse.getName(), pet.getName());
        Assert.assertEquals(petResponse.getId(), pet.getId());
        Assert.assertEquals(petResponse.getCategory().getName(), pet.getCategory().getName());

    }
}
