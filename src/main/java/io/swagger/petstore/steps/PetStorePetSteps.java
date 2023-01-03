package io.swagger.petstore.steps;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.constants.EndPoints;
import io.swagger.petstore.model.PetBodyDataPojo;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;

public class PetStorePetSteps {
    @Step("Create Pet")
    public ValidatableResponse createPet(int id, PetBodyDataPojo.CategoryData category, String name, ArrayList<String> photoUrls, ArrayList<PetBodyDataPojo.TagData> tags, String status) {
        //photoUrls.add("www.google.com");
        PetBodyDataPojo petBodyDataPojo = new PetBodyDataPojo();
        petBodyDataPojo.setId(id);
        petBodyDataPojo.setCategory(category);
        petBodyDataPojo.setName(name);
        petBodyDataPojo.setPhotoUrls(photoUrls);
        petBodyDataPojo.setTags(tags);
        petBodyDataPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(petBodyDataPojo)
                .when()
                .post(EndPoints.CREATE_A_PET)
                .then().log().all().statusCode(200);
    }
    @Step("Get Pet")
    public ValidatableResponse getPet(int id){
return SerenityRest.given().log().all()
        .pathParam("pet_id",id)
        .when()
        .get(EndPoints.GET_A_PET)
        .then().log().all();
    }
    @Step("Update a Pet")
    public ValidatableResponse updatePet(int id, PetBodyDataPojo.CategoryData category, String name, ArrayList<String> photoUrls, ArrayList<PetBodyDataPojo.TagData> tags, String status) {
        PetBodyDataPojo petBodyDataPojo = new PetBodyDataPojo();
        petBodyDataPojo.setId(id);
        petBodyDataPojo.setCategory(category);
        petBodyDataPojo.setName(name);
        petBodyDataPojo.setPhotoUrls(photoUrls);
        petBodyDataPojo.setTags(tags);
        petBodyDataPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(petBodyDataPojo)
                .when()
                .put(EndPoints.UPDATE_A_PET)
                .then().log().all().statusCode(200);
    }
    @Step("Deleting information with id: {0}")
    public ValidatableResponse deletePet(int id) {
        return SerenityRest.given().log().all()
                .pathParam("pet_id", id)
                .when()
                .delete(EndPoints.DELETE_A_PET)
                .then().log().all();
    }

}
