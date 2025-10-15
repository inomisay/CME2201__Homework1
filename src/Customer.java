import java.time.LocalDate;

public class Customer {
	
	// Attributes 
    private String ID;
    private String name;
	private SortedList<LocalDate, String> List;

    // Constructor   
    public Customer(String ID , String name, LocalDate date, String product) {
		this.ID=ID;
		this.name= name;
		this.List =new SortedList<>();
		this.List.add(date, product);
	}

    // Getters
    public String getID() {
    	return this.ID;
    }
    
    public String getname() {
    	return this.name;
    }
    
    public String getProduct(LocalDate date) {
    	return List.getValue(date);
    }
    
    // Setters
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	public void setList_Date(LocalDate index, LocalDate date) {
		List.setKey(index, date);
	}
	
	public void setList_ProductNR(LocalDate date,String Product) {// delete the existed product and add new product.
		List.setValueNR(date, Product);
	}
	
    public void setList_ProductR(LocalDate date,String Product) {// just add to product.
		List.setValueR(date, Product);
	}
    
    // Additional Methods
    public void all_Transactions() {
    	List.displayReverse();
    }

    public int array_size() {
    	return List.getsize();
    }
    
    public boolean checkDate(LocalDate date) { // If the date has been found, it means there is repeated product. If not the product has been bought in another day.
    	String isfounddate = List.getValue(date);
    	if(isfounddate.equals("In this date there is no.")) {
    		return false;
    	}else {	// Time has found!
    		return true;
    	}
    }
    
}
