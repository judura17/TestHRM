package com.example;

import com.example.LoginPage;
import com.example.RecruitmentPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class AllTests {

    WebDriver driver;
    LoginPage loginPage;
    RecruitmentPage recruitmentPage;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\JAIRO ALONSO\\Documents\\PROGRAMACION OJDR\\TestHRM\\demo\\src\\main\\resources\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        recruitmentPage = new RecruitmentPage(driver);

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginTest() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
        assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void recruitmentTest() throws InterruptedException {

        testLoginTest();
        // Proceso de postulacion
        recruitmentPage.clickMenuRecruitment();
        assertTrue(driver.getCurrentUrl().contains("recruitment"));
        recruitmentPage.clickButtonAdd();
        recruitmentPage.fieldFirstName("Oscar");
        recruitmentPage.fieldMiddleName("Julian");
        recruitmentPage.fieldLastName("Duque Ramos");
        recruitmentPage.selectVacancy();
        recruitmentPage.fieldEmail("ojduque172@gmail.com");
        recruitmentPage.fieldContactNumber("3126983500");
        recruitmentPage.uploadFile(
                "C:\\Users\\JAIRO ALONSO\\Documents\\PROGRAMACION OJDR\\TestHRM\\demo\\src\\main\\resources\\TestCargaDocumento.txt");
        recruitmentPage.chooseCalendar();
        recruitmentPage.chooseToday();
        recruitmentPage.candidateSend();
        recruitmentPage.buttonShortList();
        recruitmentPage.buttonSave();
        // Proceso de agendamiento de entrevista
        recruitmentPage.buttonScheduleIntreview();
        recruitmentPage.fieldIntreviewTitle("Test Titulo Entrevista");
        recruitmentPage.fieldIntreviewer("Orange");
        Thread.sleep(5000);
        recruitmentPage.chooseIntreviewer();
        recruitmentPage.chooseDate("2025-04-20");
        recruitmentPage.chooseTime("08:00 AM");
        recruitmentPage.buttonSave();
        recruitmentPage.buttonPassed();
        recruitmentPage.buttonSave();
        recruitmentPage.buttonOfferJob();
        recruitmentPage.buttonSave();
        recruitmentPage.buttonHire();
        recruitmentPage.buttonSave();
        assertEquals(recruitmentPage.messageStatusget(""), "Status: Hired");
        // Proceso de validacion de postulado contratado
        recruitmentPage.clickMenuRecruitment();

    }

}
