package createdao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;
public class ExampleDaoGenerator {  
	  
    public static void main(String[] args) throws Exception {  
          
        Schema schema = new Schema(3, "com.comtop.mynote.db");  
  
        //addNote(schema);  
       // addCustomerOrder(schema);   ɭ  
  
        addMyNote(schema);  
        new DaoGenerator().generateAll(schema, "../");  
    }  
  
    private static void addMyNote(Schema schema) {  
        Entity note = schema.addEntity("NoteVO");  
        note.addIdProperty();  
        note.addStringProperty("title");  
        note.addStringProperty("content");  
        note.addIntProperty("type");
        note.addDateProperty("createDate");  
        note.addDateProperty("modifyDate"); 
        note.addIntProperty("isEdit");
    }  
    
    
    
    
    private static void addNote(Schema schema) {  
        Entity note = schema.addEntity("Note");  
        note.addIdProperty();  
        note.addStringProperty("text").notNull();  
        note.addStringProperty("comment");  
        note.addDateProperty("date");  
    }  
  
    private static void addCustomerOrder(Schema schema) {  
        Entity customer = schema.addEntity("Customer");  
        customer.addIdProperty();  
        customer.addStringProperty("name").notNull();  
  
        Entity order = schema.addEntity("Order");  
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword  
        order.addIdProperty();  
        Property orderDate = order.addDateProperty("date").getProperty();  
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();  
        order.addToOne(customer, customerId);  
  
        ToMany customerToOrders = customer.addToMany(order, customerId);  
        customerToOrders.setName("orders");  
        customerToOrders.orderAsc(orderDate);  
    }  
  
}  
