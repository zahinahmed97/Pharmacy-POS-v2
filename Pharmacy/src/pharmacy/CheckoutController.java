/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.net.URL;
import java.sql.Connection;
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
public class CheckoutController implements Initializable {

    @FXML
    private ImageView image;
    private Main main;
    @FXML
    private Label message;
    
    private SQLiteConnect dbcon;
    private ObservableList<MedicineData> data1;
    
    private String sql2= "SELECT * FROM Receipt";
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
    @FXML
    private TextField FullTotalprice;
    
    
    
    
     public void init() {
        message.setText("Thank you for shopping with us!");
        Image img = new Image(Main.class.getResourceAsStream("8766 [Converted] copy 2-01.jpg"));
        image.setImage(img);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dbcon=new SQLiteConnect();
        try {
            Connection conn= SQLiteConnect.getConnection();
            this.data1=FXCollections.observableArrayList();
           
            ResultSet rs2= conn.createStatement().executeQuery(sql2);
                        
            while (rs2.next()){
                this.data1.add(new MedicineData(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5), rs2.getString(6)));
            }
            
        } catch (SQLException e){
            System.err.println("Error "+e);
        }
        
        this.receiptIDcolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer> ("ID"));
        this.receiptNamecolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, String> ("Name"));
        this.receiptManufacturercolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, String> ("Manufacturer"));
        this.receiptQuantitycolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer> ("Quantity"));
        this.receiptPricecolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer>("Price"));
        this.receiptTotalPricecolumn.setCellValueFactory(new PropertyValueFactory<MedicineData, Integer> ("Total_price"));
    
        this.receipttable.setItems(null);
        this.receipttable.setItems(this.data1);
        
        try {
        Connection conn= SQLiteConnect.getConnection();
        String sqlCalculateprice= "SELECT SUM(Total_price) FROM Receipt";
        ResultSet rs= conn.createStatement().executeQuery(sqlCalculateprice);
        
        
        this.FullTotalprice.setText(rs.getString(1));
        conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }    

   
   void setMain(Main main) {
        this.main = main;
    }
   

    @FXML
    private void Exit(ActionEvent event) {
        String sqlDelete= "DELETE FROM Receipt";
        
        try {
            Connection conn= SQLiteConnect.getConnection();
            conn.createStatement().executeUpdate(sqlDelete);
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.exit(1);
    }

    
    
}
