/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Zahin Ahmed
 */
public class Main extends Application {
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showLoginPage();
    }
    
    public void showLoginPage() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        
        LoginController controller = loader.getController();
        controller.init();
        controller.setMain(this);
        
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 700, 503));
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }

    void showCustomerHomePage(String userName) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerHome.fxml"));
        Parent root = loader.load();

        // Loading the controller
        CustomerHomeController controller = loader.getController();
        controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Customer Homepage");
        stage.setScene(new Scene(root, 800, 800));
        stage.setResizable(false);
        stage.show();
    }

    void showEmployeeHomePage(String userName) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EmployeeHome.fxml"));
        Parent root = loader.load();

        // Loading the controller
        EmployeeHomeController controller = loader.getController();
        controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Employee Homepage");
        stage.setScene(new Scene(root, 800, 800));
        stage.setResizable(false);
        stage.show();
    }

    void showNewCustomer() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewCustomer.fxml"));
        Parent root = loader.load();

        // Loading the controller
        NewCustomerController controller = loader.getController();
        controller.init();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("New Customer");
        stage.setScene(new Scene(root, 800, 800));
        stage.setResizable(false);
        stage.show();
    }
    
    void showCheckout() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Checkout.fxml"));
        Parent root = loader.load();

        // Loading the controller
        CheckoutController controller = loader.getController();
        controller.init();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Checkout");
        stage.setScene(new Scene(root, 800, 800));
        stage.setResizable(false);
        stage.show();
    }

    void LogoutandRestart() throws Exception{
        start(stage);
    }
    }
    
