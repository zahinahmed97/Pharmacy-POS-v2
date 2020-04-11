/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Zahin Ahmed
 */
public class MedicineData {
    
    private final StringProperty ID;
    private final StringProperty Name;
    private final StringProperty Manufacturer;
    private final StringProperty Quantity;
    private final StringProperty Price;
    private final StringProperty Total_price;
    
    public MedicineData (String id, String name, String manufacturer, String quantity, String price, String totalprice) {
        this.ID= new SimpleStringProperty(id);
        this.Name= new SimpleStringProperty(name);
        this.Manufacturer= new SimpleStringProperty(manufacturer);
        this.Quantity= new SimpleStringProperty(quantity);
        this.Price= new SimpleStringProperty(price);
        this.Total_price=new SimpleStringProperty(totalprice);
    }

    public StringProperty IDProperty() {
        return ID;
    }
    
    public String getID(){
        return ID.get();
    }
    
    public void setID (String ID){
        this.ID.set(ID);
    }

    public StringProperty NameProperty() {
        return Name;
    }
    public String getName(){
        return Name.get();
    }
    
    public void setName (String Name){
        this.Name.set(Name);
    }

    public StringProperty ManufacturerProperty() {
        return Manufacturer;
    }
    
    public String getManufacturer(){
        return Manufacturer.get();
    }
    
    public void setManufacturer (String Manufacturer){
        this.Manufacturer.set(Manufacturer);
    }

    public StringProperty QuantityProperty() {
        return Quantity;
    }
    
    public String getQuantity(){
        return Quantity.get();
    }
    
    public void setQuantity (String Quantity){
        this.Quantity.set(Quantity);
    }
   
    public StringProperty PriceProperty() {
        return Price;
    }
    
    public String getPrice(){
        return Price.get();
    }
    
    public void setPrice (String Price){
        this.Price.set(Price);
    }
    
    public StringProperty TotalPriceProperty() {
        return Price;
    }
    
    public String getTotal_price(){
        return Total_price.get();
    }
    
    public void setTotal_price (String TotalPrice){
        this.Total_price.set(TotalPrice);
    }
    
}   

