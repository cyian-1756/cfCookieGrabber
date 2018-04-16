package com.cookiegrabber;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        primaryStage.setTitle("CF Cookie grabber");
        primaryStage.setScene(new Scene(root, 500, 350));
        primaryStage.show();
    }

    public static void test() {
        WebDriver driver = new PhantomJSDriver();
        driver.navigate().to("http://icanhazip.com");
        System.out.println(driver.getPageSource());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
