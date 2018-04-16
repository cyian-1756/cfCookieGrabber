package com.cookiegrabber;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Controller {
    @FXML private TextArea loggingArea;
    @FXML private TextArea urlArea;

    private void log(String line) {
        loggingArea.appendText(line + "\n");
    }

    private ArrayList<String> getValidURLs() {
        ArrayList<String> validURLS = new ArrayList<String>();
        for (String s : getUrls()) {
            try {
                URL url = new URL(s);
                url.toURI();
                validURLS.add(s);
            } catch (MalformedURLException e) {
                log(s + " is not a valid URL");
            } catch (URISyntaxException e) {
                log(s + " is not a valid URL");
            }
        }
        return validURLS;
    }

    private void getCookiesFromURL(String url) {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to(url);
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Size: " + cookies.size());


        Iterator<Cookie> itr = cookies.iterator();
        while (itr.hasNext()) {
            Cookie cookie = itr.next();
            if (cookie.getName().equals("__cfduid") || cookie.getName().equals("cf_clearance")) {
                System.out.println(cookie.getDomain() + "." + cookie.getName() + " = " + cookie.getValue());
            }
        }
    }
    private String[] getUrls() {
        String[] urls = urlArea.getText().split("\n");
        return urls;
    }
    @FXML protected void runBtnPressed(ActionEvent event) {
        for (String s : getValidURLs()) {
            getCookiesFromURL(s);
        }
    }

}
