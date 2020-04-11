/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Zahin Ahmed
 */
public class NewCustomerController implements Initializable {
    
    private SQLiteConnect dbcon;

    @FXML
    private ImageView image;
    private Main main;
    @FXML
    private Button Cancel;
    @FXML
    private TextField Name;
    @FXML
    private TextField ID;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField CfmPassword;
     public void init() {
        Image img = new Image(Main.class.getResourceAsStream("8766 [Converted] copy 2-01.jpg"));
        image.setImage(img);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dbcon=new SQLiteConnect();
    }    

   void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void Logout(ActionEvent event) {
        try {
            main.LogoutandRestart();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void CreateCustomer(ActionEvent event) {
        String sqlInsertinCustomer= "INSERT INTO Customers(FName, username, password) VALUES (?,?,?)";
        String sqlInsertinLogin= "INSERT INTO Login(username, password, division) VALUES (?,?,?)";
        String sqlSearch="SELECT username FROM Login WHERE username=?";
        try {
            Connection conn= SQLiteConnect.getConnection();
            PreparedStatement stmt1= conn.prepareStatement(sqlInsertinCustomer);
            PreparedStatement stmt2= conn.prepareStatement(sqlInsertinLogin);
            PreparedStatement stmt3= conn.prepareStatement(sqlSearch);
            
            stmt1.setString(1, this.Name.getText());
            stmt1.setString(2, this.ID.getText());
            stmt1.setString(3, this.Password.getText());
            
            stmt2.setString(1, this.ID.getText());
            stmt2.setString(2, this.Password.getText());
            stmt2.setString(3, "Customer");
            
            stmt3.setString(1, this.ID.getText());
            ResultSet rs=stmt3.executeQuery();
            
            String s1=this.Password.getText();
            String s2=this.CfmPassword.getText();
            boolean b1= s1.equals(s2);
            
            if(rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("UserID unavailable");
                alert.setHeaderText("UserID is taken");
                alert.setContentText("The UserID you want has already been taken. Please use a different one.");
                alert.showAndWait();
            }
            else if(b1){
            stmt1.execute();
            stmt2.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New Account");
            alert.setHeaderText("Sign-up Succesful");
            alert.setContentText("You have been succesfully registered as a new customer. You will now be taken back to the login screen.");
            alert.showAndWait();
            try {
            main.LogoutandRestart();
            } catch (Exception ex) {
            ex.printStackTrace();
            }
            }
            else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Passwords don't match");
            alert.setHeaderText("Please try again");
            alert.setContentText("The Password you provided does not match the check.");
            alert.showAndWait();
            }
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
}
