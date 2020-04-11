/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Zahin Ahmed
 */
public class LoginController implements Initializable {
    
 
    LoginModel loginModel= new LoginModel();
    
    
    private Main main;
    private Label label;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private ComboBox<option> Role;
    @FXML
    private ImageView image;
    @FXML
    private Label Status;
    
    public void init() {
        Image img = new Image(Main.class.getResourceAsStream("8766 [Converted] copy-01.jpg"));
        image.setImage(img);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(this.loginModel.isDatabaseConnected()){
            this.Status.setText("Connected");
        }
        else {
            this.Status.setText("Not Connected.");
        }
        this.Role.setItems(FXCollections.observableArrayList(option.values()));
    }    

    
     void setMain(Main main) {
        this.main = main;
    }

    @FXML
    public void LoginCheck(ActionEvent event) {
        try {
                if(this.loginModel.isLogin(this.Username.getText(),this.Password.getText(),((option)this.Role.getValue()).toString())){
                switch (((option)this.Role.getValue()).toString()) {
                    case "Customer":
                        customerLogin(this.Username.getText());
                        break;
                    case "Employee":
                        employeeLogin(this.Username.getText());
                        break;
                }
            }
            else {
            // failed login
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("The username and password you provided is not correct.");
            alert.showAndWait();
        }
            
        } catch (Exception localException){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please choose your user type.");
                alert.showAndWait();   
        }
        /**String validUserName1 = "customer";
        String validPassword1 = "123";
        String validUserName2 = "employee";
        String validPassword2 = "123";
       
        String userName = Username.getText();
        String password = Password.getText();
        if (userName.equals(validUserName1) && password.equals(validPassword1)) {
            // successful login
            try {
                main.showCustomerHomePage(userName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (userName.equals(validUserName2) && password.equals(validPassword2)){
            try {
                main.showEmployeeHomePage(userName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else {
            // failed login
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("The username and password you provided is not correct.");
            alert.showAndWait();
        }**/
    }
    
    public void customerLogin(String userName){
       try {
                main.showCustomerHomePage(userName);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    public void employeeLogin(String userName) {
        try {
                main.showEmployeeHomePage(userName);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    @FXML
    private void CreateCustomer(ActionEvent event) {
        try {
            main.showNewCustomer();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
}
