/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nanis
 */
public class RentalsJavaClass {

    private final StringProperty getClientID;

    public String getGetClientID() {
        return getClientID.get();
    }

    public void setGetClientID(String value) {
        getClientID.set(value);
    }

    public StringProperty getClientIDProperty() {
        return getClientID;
    }

    private final StringProperty pNum;

    public String getpNum() {
        return pNum.get();
    }

    public void setpNum(String value) {
        pNum.set(value);
    }

    public StringProperty pNumProperty() {
        return pNum;
    }

    private final StringProperty address;

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String value) {
        address.set(value);
    }

    public StringProperty addressProperty() {
        return address;
    }
     
    public String toString(){
    return getName()+" "+getAddress()+ " " +getpNum()+ " "+getItems()+ " "+getQuantity()+ " "+getDate()+" "+getGetClientID();
    }

    private final StringProperty date;

    public String getDate() {
        return date.get();
    }

    public void setDate(String value) {
        date.set(value);
    }

    public StringProperty dateProperty() {
        return date;
    }

    private final StringProperty quantity;

    public String getQuantity() {
        return quantity.get();
    }

    public void setQuantity(String value) {
        quantity.set(value);
    }

    public StringProperty quantityProperty() {
        return quantity;
    }

    private final StringProperty items;

    public String getItems() {
        return items.get();
    }

    public void setItems(String value) {
        items.set(value);
    }

    public StringProperty itemsProperty() {
        return items;
    }  

    private final StringProperty name;

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    
    /**
     * This is the empty constructor for the java class
     */
    public RentalsJavaClass() {
        this(null,null,null,null,null,null);
    }
    /**
     * This is the constructor
     * @param id this is the unique id of the client
     * @param nm this is the name of the client
     * @param phoneN this is the phone number of the client
     * @param add this is the address of the client
     * @param item this is the item bought 
     * @param quant this is the quantity bought
     * @param dt this is the date of the transaction
     */
    public RentalsJavaClass(String id,String nm, String phoneN, String add, String item, String quant, String dt) {
        this.name = new SimpleStringProperty(nm);
        this.items = new SimpleStringProperty(item);
        this.quantity = new SimpleStringProperty(quant);
        this.date = new SimpleStringProperty(dt);
        this.address = new SimpleStringProperty(add);
        this.pNum = new SimpleStringProperty(phoneN);
        this.getClientID = new SimpleStringProperty(id);
       
    
    }
    /**
     * This is the constructor
     * @param nm this is the name of the client
     * @param phoneN this is the phone number of the client
     * @param add this is the address of the client
     * @param item this is the item bought
     * @param quant this is the quantity bought
     * @param dt this is the date of the transaction
     */
     public RentalsJavaClass(String nm, String phoneN, String add, String item, String quant, String dt) {
        this.name = new SimpleStringProperty();
        this.items = new SimpleStringProperty();
        this.quantity = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.pNum = new SimpleStringProperty();
        this.getClientID = new SimpleStringProperty();
    
    }
    
    
    
}
