import java.time.LocalDate;

public class Node {
	
	// Attributes
	private LocalDate date ;
	private String products;
	private Node link;
	private Node prev;
	private Node next;

	  
	// Constructor
	public Node(LocalDate dateToAdd, String productsToAdd) {
		date = dateToAdd;
    	products = productsToAdd;		
    	link = null;
		prev = null;
		next = null;
	}
 
	// Getters
	public LocalDate getDate() { 
		return date; 
	}
	
	public String getProducts() { 
		return products;
	}
	
	public Object getDate_Products() {
		Object all = date + " ,  "+ products;
		return all;
	}
	
	public Node getLink() {
        return link;
    }
	
	public Node getPrev() {
		return prev;
	}
	
	public Node getNext() {
		return next;
	}
	
	// Setters
	public void setDate(LocalDate date) { 
		this.date = date; 
	}
 
	public void setProduct(String product) {
	    this.products = products + " / " + product;
	}
	
	public void setProducts_NR(String Products) { 
		this.products = Products; 
	}
	
	
	public void setLink(Node link) {
        this.link = link;
    }
	
	public void setPrev(Node prev) {
		this.prev = prev;
	}
    
	public void setNext(Node next) {
		this.next = next;
	}
		  
}