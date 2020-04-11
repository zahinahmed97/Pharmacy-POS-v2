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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Zahin Ahmed
 */
public class EmployeeHomeController implements Initializable {

    @FXML
    private ImageView image;
    private Main main;
    @FXML
    private Label message;
    @FXML
    private TableView<MedicineData> medicinetable;
    @FXML
    private TableColumn<MedicineData, Integer> IDcolumn;
    @FXML
    private TableColumn<MedicineData, String> Namecolumn;
    @FXML
    private TableColumn<MedicineData, String> Manufacturercolumn;
    @FXML
    private TableColumn<MedicineData, Integer> Quantitycolumn;
    @FXML
    private TableColumn<MedicineData, Integer> Pricecolumn;
    
    private SQLiteConnect dbcon;
    private ObservableList<MedicineData> data;
    
    private String sql= "SELECT * FROM Medicines";
    @FXML
    private TextField id;
    @FXML
    private TextField quantity;
    @FXML
    private TextField manufacturer;
    @FXML
    private TextField name;
    @FXML
    private TextField id2;
    @FXML
    private TextField price;
    
    
    
    
     public void init(String msg) {
        message.setText("Welcome "+msg);
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

    @FXML
    private void LoadMedicinesData(ActionEvent event) {
        try {
            Connection conn= SQLiteConnect.getConnection();
            this.data=FXCollections.observableArrayList();
            
            ResultSet rs= conn.createStatement().executeQuery(sql);
            while (rs.next()){
                this.data.add(new MedicineData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            
        } catch (SQLException e){
            System.err.println("Error "+e);
        }
        
        this.IDcolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer> ("ID"));
        this.Namecolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, String> ("Name"));
        this.Manufacturercolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, String> ("Manufacturer"));
        this.Quantitycolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer> ("Quantity"));
        this.Pricecolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer>("Price"));
    
        this.medicinetable.setItems(null);
        this.medicinetable.setItems(this.data);
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
    private void AddMedicine(ActionEvent event) {
        String sqlInsert= "INSERT INTO Medicines(ID,Name,Manufacturer,Quantity,Price,Total_price) VALUES (?,?,?,?,?,?)";
        
        try {
            Connection conn= SQLiteConnect.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sqlInsert);
            
            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.name.getText());
            stmt.setString(3, this.manufacturer.getText());
            stmt.setString(4, this.quantity.getText());
            stmt.setString(5, this.price.getText());
            stmt.setString(6, "0");
            stmt.execute();
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Clearfields(ActionEvent event) {
        this.id.setText("");
        this.name.setText("");
        this.manufacturer.setText("");
        this.quantity.setText("");
        this.price.setText("");
        this.id2.setText("");
    }

    @FXML
    private void DeleteMedicine(ActionEvent event) {
        String sqlDelete= "DELETE FROM Medicines WHERE ID=?";
        
        try {
            Connection conn= SQLiteConnect.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sqlDelete);
            
            stmt.setString(1, this.id2.getText());
            stmt.execute();
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void UpdateMedicine(ActionEvent event) {
        String sqlUpdate= "UPDATE Medicines SET Name=?, Manufacturer=?, Quantity=?, Price=? WHERE ID=?";
        
        try {
            Connection conn= SQLiteConnect.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sqlUpdate);
            
            stmt.setString(1, this.name.getText());
            stmt.setString(2, this.manufacturer.getText());
            stmt.setString(3, this.quantity.getText());
            stmt.setString(4, this.price.getText());
            stmt.setString(5, this.id.getText());
            stmt.execute();
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
