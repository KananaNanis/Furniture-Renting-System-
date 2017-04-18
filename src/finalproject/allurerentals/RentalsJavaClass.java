/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;

//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Study
 */
public class RentalsJavaClass {

    private final StringProperty getClientID= new SimpleStringProperty();

    public String getGetClientID() {
        return getClientID.get();
    }

    public void setGetClientID(String value) {
        getClientID.set(value);
    }

    public StringProperty getClientIDProperty() {
        return getClientID;
    }

    private final StringProperty pNum = new SimpleStringProperty();

    public String getpNum() {
        return pNum.get();
    }

    public void setpNum(String value) {
        pNum.set(value);
    }

    public StringProperty pNumProperty() {
        return pNum;
    }

    private final StringProperty address= new SimpleStringProperty();

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

    private final StringProperty date= new SimpleStringProperty();

    public String getDate() {
        return date.get();
    }

    public void setDate(String value) {
        date.set(value);
    }

    public StringProperty dateProperty() {
        return date;
    }

    private final StringProperty quantity= new SimpleStringProperty();

    public String getQuantity() {
        return quantity.get();
    }

    public void setQuantity(String value) {
        quantity.set(value);
    }

    public StringProperty quantityProperty() {
        return quantity;
    }

    private final StringProperty items = new SimpleStringProperty();

    public String getItems() {
        return items.get();
    }

    public void setItems(String value) {
        items.set(value);
    }

    public StringProperty itemsProperty() {
        return items;
    }

    

    

    private final StringProperty name = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    public RentalsJavaClass() {
        this(null,null,null,null,null,null);
    }
    public RentalsJavaClass(String id,String nm, String phoneN, String add, String item, String quant, String dt) {
//    this.name = new SimpleStringProperty(name);
//    this.pNum= new SimpleStringProperty(phoneN);
//    this.address= new SimpleStringProperty(add);
//    this.items= new SimpleStringProperty(item);
//    this.quantity= new SimpleStringProperty(quantity);
//    this.date= new SimpleStringProperty(date);
//    this.getClientID= new SimpleStringProperty(id);
        getClientID.set(id);
        name.set(nm);
        pNum.set(phoneN);
        address.set(add);
        items.set(item);
        quantity.set(quant);
        date.set(dt);
        
    
    }
    
     public RentalsJavaClass(String nm, String phoneN, String add, String item, String quant, String dt) {
    //this.name = new SimpleStringProperty(nm);
    //this.pNum= new SimpleStringProperty(phoneN);
    //this.address= new SimpleStringProperty(add);
    //this.items= new SimpleStringProperty(item);
   //this.quantity= new SimpleStringProperty(quant);
    //this.date= new SimpleStringProperty(dt);
    //this.getClientID= new SimpleStringProperty(id);
        name.set(nm);
        pNum.set(phoneN);
        address.set(add);
        items.set(item);
        quantity.set(quant);
        date.set(dt);
    
    }
    
    
    
}
