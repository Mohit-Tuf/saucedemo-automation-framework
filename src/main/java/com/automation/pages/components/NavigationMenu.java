package com.automation.pages.components;

import com.automation.base.BasePage;
import com.automation.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationMenu extends BasePage {

    private final By menuButton = By.id("react-burger-menu-btn");

    private final By logoutButton = By.id("logout_sidebar_link");

    public NavigationMenu(WebDriver driver){
        super(driver);
    }

    public void openMenu(){
        click(menuButton);
    }


    public LoginPage logout(){

        openMenu();
        click(logoutButton);

        return new LoginPage(driver);
    }


}
