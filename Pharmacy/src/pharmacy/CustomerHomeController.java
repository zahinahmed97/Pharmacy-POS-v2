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
public class CustomerHomeController implements Initializable {

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
    private ObservableList<MedicineData> data1;
    
    private String sql= "SELECT * FROM Medicines";
    private String sql2= "SELECT * FROM Receipt";
    @FXML
    private TextField id;
    @FXML
    private TextField quantity;
    @FXML
    private TextField id2;
    @FXML
    private TableView<MedicineData> receipttable;
    @FXML
    private TableColumn<MedicineData, Integer> receiptIDcolumn;
    @FXML
    private TableColumn<MedicineData, String> receiptNamecolumn;
    @FXML
    private TableColumn<MedicineData, String> receiptManufacturercolumn;
    @FXML
    private TableColumn<MedicineData, Integer> receiptQuantitycolumn;
    @FXML
    private TableColumn<MedicineData, Integer> receiptPricecolumn;
    @FXML
    private TableColumn<MedicineData, Integer> receiptTotalPricecolumn;
    
    
    
    
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
            this.data1=FXCollections.observableArrayList();
            
            ResultSet rs= conn.createStatement().executeQuery(sql);
            ResultSet rs2= conn.createStatement().executeQuery(sql2);
            while (rs.next()){
                this.data.add(new MedicineData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            
            while (rs2.next()){
                this.data1.add(new MedicineData(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6)));
            }
            
        } catch (SQLException e){
            System.err.println("Error "+e);
        }
        
        this.IDcolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer> ("ID"));
        this.Namecolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, String> ("Name"));
        this.Manufacturercolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, String> ("Manufacturer"));
        this.Quantitycolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer> ("Quantity"));
        this.Pricecolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer>("Price"));
        
        this.receiptIDcolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer> ("ID"));
        this.receiptNamecolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, String> ("Name"));
        this.receiptManufacturercolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, String> ("Manufacturer"));
        this.receiptQuantitycolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer> ("Quantity"));
        this.receiptPricecolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer>("Price"));
        this.receiptTotalPricecolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer> ("Total_price"));
    
        this.medicinetable.setItems(null);
        this.medicinetable.setItems(this.data);
        this.receipttable.setItems(null);
        this.receipttable.setItems(this.data1);
    }
    
   
   void setMain(Main main) {
        this.main = main;
    }
   
   @FXML
    private void Logout(ActionEvent event) {
        try {
            String sqlDelete= "DELETE FROM Receipt";
            Connection conn= SQLiteConnect.getConnection();
            conn.createStatement().executeUpdate(sqlDelete);
            conn.close();

            main.LogoutandRestart();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void AddMedicine(ActionEvent event) {
        String sqlRetrieve= "INSERT INTO Receipt (ID,Name,Manufacturer,Quantity,Price,Total_price) SELECT ID,Name,Manufacturer,Quantity,Price,Total_price FROM Medicines WHERE ID=?";
        String sqlUpdateReceipt= "UPDATE Receipt SET Quantity=?, Total_price=? WHERE ID=?";
        String sqlSelectquantity= "SELECT Quantity FROM Medicines WHERE ID=?";
        String sqlSelectprice= "SELECT Price FROM Medicines WHERE ID=?";
        String sqlUpdatemedicine= "UPDATE Medicines SET Quantity=? WHERE ID=?";
                
        
        
        try {
            Connection conn= SQLiteConnect.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sqlRetrieve);
            stmt.setString(1, this.id.getText());
            stmt.execute();
            
            PreparedStatement stmt1 =null;
            ResultSet rs= null;
        
            stmt1=conn.prepareStatement(sqlSelectquantity);
            stmt1.setString(1, this.id.getText());
            
            rs= stmt1.executeQuery();
            
            PreparedStatement stmt4 =null;
            ResultSet rs1= null;
        
            stmt4=conn.prepareStatement(sqlSelectprice);
            stmt4.setString(1, this.id.getText());
            
            rs1= stmt4.executeQuery();
            
            int x=rs.getInt(1)-Integer.parseInt(this.quantity.getText());
            String s=x+"";
            
            int y=rs1.getInt(1)*Integer.parseInt(this.quantity.getText());
            String s1=y+"";
            
            PreparedStatement stmt2= conn.prepareStatement(sqlUpdateReceipt);
            stmt2.setString(1, this.quantity.getText());
            stmt2.setString(2, s1);
            stmt2.setString(3, this.id.getText());
            stmt2.execute();
            
            PreparedStatement stmt3= conn.prepareStatement(sqlUpdatemedicine);
            stmt3.setString(1, s);        
            stmt3.setString(2, this.id.getText());
            stmt3.execute();
            
            /**Prepared Statement stmt3= conn.prepareStatement(sqlSelectdata);
            stmt3.setString(1, this.id.getText());
            
            ResultSet rs= stmt3.execute();**/
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Clearfields(ActionEvent event) {
        this.id.setText("");
        this.quantity.setText("");
        this.id2.setText("");
    }

    @FXML
    private void DeleteMedicine(ActionEvent event) {
        String sqlDelete= "DELETE FROM Receipt WHERE ID=?";
        
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
    private void Checkout(ActionEvent event) {
        try {
            main.showCheckout();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    
    
}
