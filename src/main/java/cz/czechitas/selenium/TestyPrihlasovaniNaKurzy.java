package cz.czechitas.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    // Rodič s existujícím účtem se musí být schopen přihlásit do webové aplikace.
    @Test
    public void parentLogsIntoAppWithExistingAccount() {


        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        WebElement emailField = prohlizec.findElement(By.id("email"));
        WebElement passwordField = prohlizec.findElement(By.xpath("//input[@id = 'password']"));
        WebElement loginButton = prohlizec.findElement(By.xpath("//button[contains(text(),'Přihlásit')]"));

        emailField.sendKeys("test@katka.cz");
        passwordField.sendKeys("Lalala1");
        loginButton.click();

        System.out.println(prohlizec.getCurrentUrl());
        Assertions.assertEquals(prohlizec.getCurrentUrl(), "https://cz-test-jedna.herokuapp.com/zaci");


        //WebElement LoginButton = prohlizec.findElement(By.class(("btn-primary"));
        //LoginButton.click();

        //windowAfterLogin = "https//cz-test-jedna.herokuapp.com/zaci".

        //assert
    }

//        WebElement spanPocetLajku = prohlizec.findElement(By.id("lvlAwesome"));
//        int skutecnyPocetLajku = Integer.parseInt(spanPocetLajku.getText());
//        Assertions.assertEquals(zadanyPocetLajku, skutecnyPocetLajku);


//    Rodič s existujícím účtem musí být schopen přihlásit svoje dítě na kurz.
//    Varianta, že rodič nejprve vybere kurz a potom se přihlásí ke svému účtu, vyplní přihlášku, odešle ji a nakonec ve svém seznamu přihlášek zkontroluje, že ji systém eviduje.

    @Test
    public void parentChoosesCourseLogsInFillsApplicationChecksApplicationWasCreated() {
        //select course part
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");

        WebElement buttonThreeMonthCourseMoreInfo = prohlizec.findElement(By.xpath("//a [@href ='https://cz-test-jedna.herokuapp.com/31-trimesicni-kurzy-programova']"));
        buttonThreeMonthCourseMoreInfo.click();
        WebElement buttonJava1CourseMoreInfo = prohlizec.findElement(By.xpath("//a [@href ='https://cz-test-jedna.herokuapp.com/zaci/pridat/71-java-1']"));
        buttonJava1CourseMoreInfo.click();
//login part
        WebElement emailField = prohlizec.findElement(By.id("email"));
        WebElement passwordField = prohlizec.findElement(By.xpath("//input[@id = 'password']"));
        WebElement loginButton = prohlizec.findElement(By.xpath("//button[contains(text(),'Přihlásit')]"));

        emailField.sendKeys("test@katka.cz");
        passwordField.sendKeys("Lalala1");
        loginButton.click();
//fill in application part

        WebElement selectDates = prohlizec.findElement(By.xpath("//div[contains(text(),'Vyberte termín')]"));
        selectDates.click();
        WebElement inputDate = prohlizec.findElement(By.xpath("//input[@type = 'search']"));
        inputDate.sendKeys("28.");
        inputDate.sendKeys(Keys.ENTER);

        WebElement inputForename = prohlizec.findElement(By.id("forename"));
        inputForename.sendKeys("Testovaci");
        WebElement inputSurname = prohlizec.findElement(By.id("surname"));
        inputSurname.sendKeys("Dítě");
        WebElement inputBirthday = prohlizec.findElement(By.id("birthday"));
        inputBirthday.sendKeys("20.02.2010");
//        WebElement selectPaymentMethod = prohlizec.findElement(By.xpath("//input[@id='payment_transfer' and @type='radio']"));
//        //WebElement selectPaymentMethod = prohlizec.findElement(By.id("payment_transfer"));
//        selectPaymentMethod.click();
        WebElement selectAgreeWithTermsConditions = prohlizec.findElement(By.xpath("//input[@id = 'terms_conditions']"));
        selectAgreeWithTermsConditions.click();
        WebElement buttonCreateNewApplication = prohlizec.findElement(By.xpath("//input[@value = 'Vytvořit přihlášku']"));
        buttonCreateNewApplication.click();



        //




    }


    //Rodič s existujícím účtem musí být schopen přihlásit svoje dítě na kurz.
    //Varianta, že se rodič nejprve přihlásí ke svému účtu a potom vybere kurz, vyplní, odešle, zkontroluje v seznamu.

    @Test
    public void poStiskuPridejAOdeberKockuMusiBytSpravnyPocetObrazkuKocek() {
        prohlizec.navigate().to("https://automation3.shinekamil.repl.co/adding.html");
        WebElement tlacitkoPridejKocku = prohlizec.findElement(By.id("addItem"));
        for (int i = 0; i < 10; i++) {
            tlacitkoPridejKocku.click();
        }
        assertujSpravnyPocetKocek(10);

        WebElement tlacitkoOdeberKocku = prohlizec.findElement(By.id("removeItem"));
        tlacitkoOdeberKocku.click();

        assertujSpravnyPocetKocek(9);
    }

    @Test
    public void poStiskuPridejAOdeberKockuVicekratNezBylaPridanaMusiBytPocetKocek0() {
        prohlizec.navigate().to("https://automation3.shinekamil.repl.co/adding.html");
        WebElement tlacitkoPridejKocku = prohlizec.findElement(By.id("addItem"));
        for (int i = 0; i < 10; i++) {
            tlacitkoPridejKocku.click();
        }
        assertujSpravnyPocetKocek(10);

        WebElement tlacitkoOdeberKocku = prohlizec.findElement(By.id("removeItem"));
        for (int i = 0; i < 15; i++) {
            tlacitkoOdeberKocku.click();
        }

        assertujSpravnyPocetKocek(0);
    }

    private void assertujSpravnyPocetKocek(int pocetKocek) {
        List<WebElement> obdelnikyKocek = prohlizec.findElements(By.xpath("//div[@class = 'card cat']"));
        WebElement spanPocetKocek = prohlizec.findElement(By.id("counter"));
        Assertions.assertEquals(pocetKocek, obdelnikyKocek.size());
        Assertions.assertEquals(pocetKocek, Integer.parseInt(spanPocetKocek.getText()));
    }

    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
