package ru.netology.javaqa;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderACardTest {
    private WebDriver driver;

    // Автоматическая настройка хромдрайвера с
    // помощью Webdriver Manager
    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);


    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldCardOrder() {
        // загрузить страницу
        driver.get("http://localhost:9999");
        // Поиск элементов
        WebElement name = driver.findElement(By.cssSelector("[data-test-id='name'].input"));
        WebElement phone = driver.findElement(By.cssSelector("[data-test-id='phone'].input"));
        WebElement checkbox = driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box"));
        WebElement button = driver.findElement(By.cssSelector("[data-test-id='submit'] .button"));

        // Данные
        name.sendKeys("Самохин Сергей");
        phone.sendKeys("+79890000000");
        checkbox.click();
        button.click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        // Сравниение
        assertEquals(expected, actual);


    }
}