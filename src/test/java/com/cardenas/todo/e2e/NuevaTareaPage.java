package com.cardenas.todo.e2e;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NuevaTareaPage {
    private final WebDriver driver;
    private final By inputTitulo = By.id("titulo");
    private final By inputDescripcion = By.id("descripcion");
    private final By btnGuardar = By.id("btn-guardar");

    public NuevaTareaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void esperarCarga() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(inputTitulo));
    }

    public TareasPage crearTarea(String titulo, String descripcion) {
        driver.findElement(inputTitulo).clear();
        driver.findElement(inputTitulo).sendKeys(titulo);
        driver.findElement(inputDescripcion).clear();
        driver.findElement(inputDescripcion).sendKeys(descripcion);
        driver.findElement(btnGuardar).click();
        return new TareasPage(driver);
    }
}
