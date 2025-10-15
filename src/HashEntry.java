import java.time.LocalDate;

public class HashEntry {
	
	// Attributes 
    private int key;
    private Customer customer;
    
    // Constructor
    public HashEntry(int key,String ID, String name, LocalDate date, String product) {
        this.key = key;
        this.customer = new Customer(ID, name, date, product);
    }
      
    // Getters
    public int getKey() {
          return key;
    }
    
    public String getValue(LocalDate date) {
        return customer.getProduct(date);
    }
    
    public String getcustomerID() {
    	return customer.getID();
    }
    
    public Customer getvalue() {
      	 return customer;
    }
    
    // Setters
    public void setkey(int key) {
    	this.key = key;
    }
    
    public void setkey_AV(int key) { //AV : available
    	this.key = key;
    	customer = null;
    }
    
    // Additional Methods
    public String Transactions() {
    	String aString = "transaction found for ";
    	if(customer.array_size() > 1) {
    		aString = "transactions found for ";
    	}
        return (customer.array_size() + " " + aString + customer.getname());
    }
    
    public void alltransactions() {
   	 System.out.println(customer.getID() + " " + customer.getname() + " : ");
   	 customer.all_Transactions();
    }
    
    public void add(LocalDate date, String Product) {
    	customer.setList_ProductNR(date, Product);
    }
    
    public void add_NE(int index, String key, String name, LocalDate date , String Product) { //NE : Not Exists
    	Customer customer = new Customer(key, name, date, Product);
    	this.key= index;
    }
    
    public void add_E(LocalDate date, String Product) { //E : Exists
    	if(customer.checkDate(date) == true) {
    		customer.setList_ProductR(date, Product);
    	}else { //There is not any repeat in date which means the purchase time is not exists in the customer list.
    		customer.setList_ProductNR(date, Product);
    	}
    	
    }

}
