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
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    public void loginToWebsite() {
        WebElement emailField = prohlizec.findElement(By.id("email"));
        WebElement passwordField = prohlizec.findElement(By.xpath("//input[@id = 'password']"));
        WebElement loginButton = prohlizec.findElement(By.xpath("//button[contains(text(),'Přihlásit')]"));

        emailField.sendKeys("test@katka.cz");
        passwordField.sendKeys("Lalala1");
        loginButton.click();

    }

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
        loginToWebsite();

        System.out.println(prohlizec.getCurrentUrl());
        Assertions.assertEquals(prohlizec.getCurrentUrl(), "https://cz-test-jedna.herokuapp.com/zaci");

    }

//    Rodič s existujícím účtem musí být schopen přihlásit svoje dítě na kurz.
//    Varianta, že rodič nejprve vybere kurz a potom se přihlásí ke svému účtu, vyplní přihlášku, odešle ji a nakonec ve svém seznamu přihlášek zkontroluje, že ji systém eviduje.

    @Test
    public void parentChoosesCourseLogsInFillsApplicationChecksApplicationWasCreated() {

        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");

        //create random forename and surname

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int forenameLength = 5;

        for (int i = 0; i < forenameLength; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        String randomForename = sb.toString();
        System.out.println(randomForename);

        int surenameLength = 8;

        sb = new StringBuilder();
        for (int i = 0; i < surenameLength; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        String randomSurname = sb.toString();
        System.out.println(randomSurname);

        String fullName = randomForename + " " + randomSurname;
        System.out.println(fullName);

        WebElement buttonThreeMonthCourseMoreInfo = prohlizec.findElement(By.xpath("//a [@href ='https://cz-test-jedna.herokuapp.com/31-trimesicni-kurzy-programova']"));
        buttonThreeMonthCourseMoreInfo.click();
        WebElement buttonJava1CourseMoreInfo = prohlizec.findElement(By.xpath("//a [@href ='https://cz-test-jedna.herokuapp.com/zaci/pridat/71-java-1']"));
        buttonJava1CourseMoreInfo.click();

        //login part
        loginToWebsite();

        //fill in application part
        WebElement selectDates = prohlizec.findElement(By.xpath("//div[contains(text(),'Vyberte termín')]"));
        selectDates.click();
        WebElement inputDate = prohlizec.findElement(By.xpath("//input[@type = 'search']"));
        inputDate.sendKeys("28.");
        inputDate.sendKeys(Keys.ENTER);

        WebElement inputForename = prohlizec.findElement(By.id("forename"));
        inputForename.sendKeys(randomForename);
        WebElement inputSurname = prohlizec.findElement(By.id("surname"));
        inputSurname.sendKeys(randomSurname);
        WebElement inputBirthday = prohlizec.findElement(By.id("birthday"));
        inputBirthday.sendKeys("20.02.2010");

        WebElement selectPaymentMethod = prohlizec.findElement(By.xpath("//label[@for='payment_transfer']"));
        selectPaymentMethod.click();
        WebElement selectAgreeWithTermsConditions = prohlizec.findElement(By.xpath("//label[@for ='terms_conditions']"));
        selectAgreeWithTermsConditions.click();
        WebElement buttonCreateNewApplication = prohlizec.findElement(By.xpath("//input[@value = 'Vytvořit přihlášku']"));
        buttonCreateNewApplication.click();
        WebElement navigationItemApplications = prohlizec.findElement(By.xpath("//a[@href = 'https://cz-test-jedna.herokuapp.com/zaci']"));
        navigationItemApplications.click();

        List<WebElement> listOfKidsNames = prohlizec.findElements(By.xpath("//td[@class = 'dtr-control sorting_1']"));
        WebElement oneKidName = listOfKidsNames.get(4);
        System.out.println(oneKidName);
//        String childNameOnTheListTurnedToString = checkChildNameInTheList.getText();
//
//        String expectedValueInTableOfApplicationsChildName = "Hanibal Lector";
//        Assertions.assertEquals(expectedValueInTableOfApplicationsChildName,childNameOnTheListTurnedToString);

    }

    //Rodič s existujícím účtem musí být schopen přihlásit svoje dítě na kurz.
    //Varianta, že se rodič nejprve přihlásí ke svému účtu a potom vybere kurz, vyplní, odešle, zkontroluje v seznamu.

    @Test
    public void parentLogsInChoosesCourseFillsApplicationChecksApplicationWasCreated() {

        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        loginToWebsite();

        //navigate to applications
        WebElement buttonMoreInfoForParents = prohlizec.findElement(By.xpath("//*[@href = 'https://cz-test-jedna.herokuapp.com/pro-rodice']"));
        buttonMoreInfoForParents.click();

        WebElement buttonCreateApplication = prohlizec.findElement(By.xpath("//*[@href = 'https://cz-test-jedna.herokuapp.com/zaci/pridat']"));
        buttonCreateApplication.click();

        //create random forename and surname

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int forenameLength = 5;

        for (int i = 0; i < forenameLength; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        String randomForename = sb.toString();
        System.out.println(randomForename);

        int surenameLength = 8;

        sb = new StringBuilder();
        for (int i = 0; i < surenameLength; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        String randomSurname = sb.toString();
        System.out.println(randomSurname);

        String fullName = randomForename + " " + randomSurname;
        System.out.println(fullName);

        WebElement buttonThreeMonthCourseMoreInfo = prohlizec.findElement(By.xpath("//a [@href ='https://cz-test-jedna.herokuapp.com/31-trimesicni-kurzy-programova']"));
        buttonThreeMonthCourseMoreInfo.click();
        WebElement buttonJava1CourseMoreInfo = prohlizec.findElement(By.xpath("//a [@href ='https://cz-test-jedna.herokuapp.com/zaci/pridat/71-java-1']"));
        buttonJava1CourseMoreInfo.click();


        //fill in application part
        WebElement selectDates = prohlizec.findElement(By.xpath("//div[contains(text(),'Vyberte termín')]"));
        selectDates.click();
        WebElement inputDate = prohlizec.findElement(By.xpath("//input[@type = 'search']"));
        inputDate.sendKeys("28.");
        inputDate.sendKeys(Keys.ENTER);
        WebElement inputForename = prohlizec.findElement(By.id("forename"));
        inputForename.sendKeys(randomForename);
        WebElement inputSurname = prohlizec.findElement(By.id("surname"));
        inputSurname.sendKeys(randomSurname);
        WebElement inputBirthday = prohlizec.findElement(By.id("birthday"));
        inputBirthday.sendKeys("20.02.2010");
        WebElement selectPaymentMethod = prohlizec.findElement(By.xpath("//label[@for='payment_transfer']"));

        selectPaymentMethod.click();
        WebElement selectAgreeWithTermsConditions = prohlizec.findElement(By.xpath("//label[@for ='terms_conditions']"));
        selectAgreeWithTermsConditions.click();
        WebElement buttonCreateNewApplication = prohlizec.findElement(By.xpath("//input[@value = 'Vytvořit přihlášku']"));
        buttonCreateNewApplication.click();

        WebElement navigationItemApplications = prohlizec.findElement(By.xpath("//a[@href = 'https://cz-test-jedna.herokuapp.com/zaci']"));
        navigationItemApplications.click();
    }


    ///vymysli si test

    //nejkratší test, protože čas tlačil :)

    @Test
    public void loggedInParentCanNavigateToContactDetails() {

        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        loginToWebsite();

        WebElement navigationItemContactDetails = prohlizec.findElement(By.xpath("//a[@href = 'https://cz-test-jedna.herokuapp.com/kontakt']"));
        navigationItemContactDetails.click();

        Assertions.assertEquals(prohlizec.getCurrentUrl(), "https://cz-test-jedna.herokuapp.com/kontakt");
    }


    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
