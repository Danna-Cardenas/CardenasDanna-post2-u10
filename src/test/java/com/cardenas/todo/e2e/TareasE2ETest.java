package com.cardenas.todo.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, properties = "server.port=8081")
class TareasE2ETest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(opts);
        driver.get("http://localhost:8081/tareas");
    }

    @Test
    void paginaTareas_cargaCorrectamente() {
        assertThat(driver.getTitle()).contains("Tareas");
    }

    @Test
    void crearTarea_muestraEnListado() {
        TareasPage tareasPage = new TareasPage(driver);
        tareasPage.esperarCarga();
        int inicial = tareasPage.contarTareas();

        NuevaTareaPage nuevaTarea = tareasPage.irANuevaTarea();
        nuevaTarea.esperarCarga();
        TareasPage despues = nuevaTarea.crearTarea("Tarea E2E", "Creada desde Selenium");
        despues.esperarCarga();

        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(d -> despues.contarTareas() > inicial);

        assertThat(despues.contarTareas()).isGreaterThan(inicial);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
