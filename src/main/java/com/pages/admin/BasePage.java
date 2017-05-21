package com.pages.admin;

import org.openqa.selenium.WebDriver;


public class BasePage {
    protected final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

}
