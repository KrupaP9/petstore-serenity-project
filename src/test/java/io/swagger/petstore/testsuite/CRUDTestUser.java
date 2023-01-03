package io.swagger.petstore.testsuite;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.steps.PetStoreSteps;
import io.swagger.petstore.testbase.TestBase;
import io.swagger.petstore.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class CRUDTestUser extends TestBase {
    static int id = getRandomDigits();
    static String username = "Kay"+ TestUtils.getRandomValue();
    static String firstName = "Kay";
    static String lastName = "McDonald";
    static String email = "kaymcdonald@gmail.com";
    static String password = "KayPassword1";
    static String phone = "0745637382";
    static int userStatus = 0;

    @Steps
    PetStoreSteps petStoreSteps;
    @Title("This will create a new user")
    @Test
    public void test001() {
        ValidatableResponse response = petStoreSteps.createUser(id,username,firstName,lastName,email,password,phone,userStatus);
        response.log().all().statusCode(200);

    }
    @Title("Verify is the user was added to the database")
    @Test
    public void test002(){
        ValidatableResponse userMap = petStoreSteps.getUserByUsername(username);
        HashMap<String,?> map = userMap.extract().path("");
        Assert.assertTrue(map.containsValue(username));
    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        firstName = firstName + "_updated";
        petStoreSteps.updateUser(id,username,firstName,lastName,email,password,phone,userStatus).log().all().statusCode(200);
        ValidatableResponse response = petStoreSteps.getUserByUsername(username);
        HashMap<String,Object> map = response.extract().path("");
        Assert.assertTrue(map.containsValue(username));
    }
    @Title("Delete the user and verify if the user is deleted!")
    @Test
    public void test004() {
        petStoreSteps.deleteUser(username).statusCode(200);
        petStoreSteps.getUser(username).statusCode(404);
    }
}
