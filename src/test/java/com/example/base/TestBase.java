package com.example.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase 
{
    
    private static WebDriver driver;
    private static WebDriverWait wait;
   
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
  
    public static void tearDown() {
        if (driver != null) {
        driver.quit();
        driver=null;
        }
    }
    public static WebDriver getDriver() {
        return driver;
    }
    public static WebDriverWait getWait() {
        return wait;
    }

   

    public static String captureScreenshot(String scenarioName) {
        // Convertir le WebDriver en TakesScreenshot
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

        // Capturer la capture d'écran en tant que tableau d'octets
        byte[] screenshotBytes = screenshotDriver.getScreenshotAs(OutputType.BYTES);

        // Créer un horodatage au format yyyyMMdd-HHmmss
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());

        // Combiner le nom du scénario et l'horodatage pour le nom du fichier
        String screenshotName = scenarioName + "-" + timestamp + ".png";

        // Définir le dossier où la capture d'écran sera enregistrée
        String targetDir = "target/screenshots/";

        // Créer le dossier s'il n'existe pas
        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();  // Crée le dossier si nécessaire
        }

        // Créer un fichier à partir du tableau d'octets
        File screenshotFile = new File(targetDir + screenshotName);

        // Écrire le tableau d'octets dans le fichier
        try (FileOutputStream fos = new FileOutputStream(screenshotFile)) {
            fos.write(screenshotBytes);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement de la capture d'écran : " + e.getMessage());
        }

        // Afficher le chemin absolu de l'image enregistrée
        String absolutePath = screenshotFile.getAbsolutePath();
        System.out.println("Capture d'écran enregistrée à : " + absolutePath);

        // Retourner le chemin absolu
        return absolutePath;
    }
 

}
