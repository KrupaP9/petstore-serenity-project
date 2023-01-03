package io.swagger.petstore.testsuite;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.model.PetBodyDataPojo;
import io.swagger.petstore.steps.PetStorePetSteps;
import io.swagger.petstore.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
@RunWith(SerenityRunner.class)
public class CRUDTestPet extends TestBase {
    static int id = 1234;
    static String name = "Kay";
    static String status = "active";
    @Steps
    PetStorePetSteps petStorePetSteps;

    @Title("This will create a new Pet")
    @Test
    public void test001() {
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add("www.google.com");
        ArrayList<PetBodyDataPojo.TagData> tags  = new ArrayList<>();
        PetBodyDataPojo.TagData tagData = new PetBodyDataPojo.TagData(1,"Cat");
        tags.add(tagData);
        PetBodyDataPojo.CategoryData category = new PetBodyDataPojo.CategoryData(2,"Pug");
        ValidatableResponse response = petStorePetSteps.createPet(id, category, name, photoUrls, tags, status);
        response.log().all().statusCode(200);
    }

    @Title("This will get a Pet by id")
    @Test
    public void test002() {
        ValidatableResponse response = petStorePetSteps.getPet(id);
        response.log().all().statusCode(200);
    }
    @Title("This will update a new Pet")
    @Test
    public void test003() {
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add("www.google.com/updated");
        ArrayList<PetBodyDataPojo.TagData> tags  = new ArrayList<>();
        PetBodyDataPojo.TagData tagData = new PetBodyDataPojo.TagData(1,"Cat_updated");
        tags.add(tagData);
        PetBodyDataPojo.CategoryData category = new PetBodyDataPojo.CategoryData(2,"Pug_updated");
        ValidatableResponse response = petStorePetSteps.createPet(id, category, name, photoUrls, tags, status);
        response.log().all().statusCode(200);
    }
    @Title("This will delete a Pet")
    @Test
    public void test004() {
        petStorePetSteps.deletePet(id).statusCode(200);
        petStorePetSteps.getPet(id).statusCode(404);
    }
}
