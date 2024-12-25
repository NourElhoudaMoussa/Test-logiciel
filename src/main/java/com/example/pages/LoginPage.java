package com.example.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage 
{
    private WebDriver driver;
    private WebDriverWait wait;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void openLoginPage(){
        driver.get("https://the-internet.herokuapp.com/login");
    }
    public void enterUsername(String username){
        WebElement user =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        user.sendKeys(username);
    }
    public void enterPassword(String password){
        WebElement pwd =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        pwd.sendKeys(password);
    }
    public void submitLogin(){
        WebElement form =wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
        form.submit();
    }
    public String getSuccessMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash-messages")));
        return firstResult.getText();
    }
      public String getErrorMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash.error")));
        return firstResult.getText();
    }
    
}
