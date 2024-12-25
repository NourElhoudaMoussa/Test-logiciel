package com.example.setpDefinition;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.example.base.TestBase;
import com.example.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep extends TestBase{
    private  LoginPage loginPage;
    private ExtentTest _scenario;
     public LoginStep() {
        loginPage = new LoginPage(getDriver());
        _scenario = Hooks.getExtentTest(); 
    }
   
    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        try{
            loginPage.openLoginPage();
            _scenario.log(Status.PASS,"the user is on the login page");
        }catch(Exception e){
            _scenario.log(Status.FAIL,"Failed to open the login page");
            throw e;
        }
        
    }

    @When("the user enters a username as {string}")
    public void enterUserName(String username){
        try{
            loginPage.enterUsername(username);
            _scenario.log(Status.PASS,"the user enters a username");
        }catch(Exception e){
            _scenario.log(Status.FAIL,"Failed to enter the username");
            throw e;
        }
        
    }

    @And("the user enters a password as {string}")
    public void enterPassword(String password){
        try{
            loginPage.enterPassword(password);
            _scenario.log(Status.PASS,"the user enters a password");
        }catch(Exception e){
            _scenario.log(Status.FAIL,"Failed to enter the password");
            throw e;
        }
        
    }

    @And("clicks on the login button")
    public void submit(){
        try{
           loginPage.submitLogin();
            _scenario.log(Status.PASS,"user clicks on the login button");
        }catch(Exception e){
            _scenario.log(Status.FAIL,"Failed to clicks on the login button");
            throw e;
        }
         
    }

    @Then("the user should see a successful login message")
    public void validresult(){
        try{
           String Result1 = loginPage.getSuccessMessage();
        assertTrue(Result1.contains("You logged into a secure area!"));
            _scenario.log(Status.PASS,"user see a successful login message");
        }catch(Exception e){
            _scenario.log(Status.FAIL,"user can not see a successful login message");
            throw e;
        }
        
    }

    @Then("the user should see an error login message")
    public void invalidresult(){
        try{
            String Result2 = loginPage.getErrorMessage();
        assertTrue(Result2.contains("Your username is invalid!")||Result2.contains("Your password is invalid!"));
            _scenario.log(Status.PASS,"user see an error login messagee");
        }catch(Exception e){
            _scenario.log(Status.FAIL,"user can not see an error login message");
            throw e;
        }
       
    }

    public static void main( String[] args )
    {

        
    }

}
