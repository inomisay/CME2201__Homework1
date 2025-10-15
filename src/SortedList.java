import java.time.LocalDate;

public class SortedList<K, V> implements SortedList_Interface<LocalDate, String> {

	Node head;
	
	@Override
	public int size() {
		int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.getLink();
        }
        if (head == null) {
            System.out.println("No shopping data has been created yet!");
        }
        return count;		
	}

	@Override
	public void add(LocalDate date,String datatoadd) {
	    
	    
	    if(head == null) {
	      Node newnode = new Node(date,datatoadd); 
	      head= newnode;
	      head.setPrev(head);
	      head.setNext(head);
	      
	    }else {      
	      //add by order
	      // add beginning
	      if( date.compareTo(head.getDate()) > 0) {  
	        Node temp = head;
	        do {
	          temp = temp.getNext();
	        } while (temp.getNext()!=head);
	        
	        Node newnode= new Node(date,datatoadd);
	        newnode.setNext(head);
	        newnode.setPrev(head.getPrev());
	        head.getPrev().setNext(newnode);
	        head.setPrev(newnode);        
	        head = newnode;
	      }
	      else {//add the middle 
	        Node temp = head;
	        while (date.compareTo(temp.getDate()) > 0 && temp.getNext() != head ) {
	                temp = temp.getNext();
	            }
	        Node newnode = new Node(date,datatoadd);
	        newnode.setNext(temp.getNext());
	        newnode.setPrev(temp);
	        temp.getNext().setPrev(newnode);
	        temp.setNext(newnode);
	        
	      }  
	    }   
	  }

	/*
	@Override
	public void delete(LocalDate keyToDelete) {
		if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        Node temp = head;
        Node prev = null;

        // Search for the node with the specified date
        while (temp != null && !temp.getDate().equals(keyToDelete)) {
            prev = temp;
            temp = temp.getLink();
        }

        // If the node is found, remove it
        if (temp != null) {
            if (prev != null) {
                // Node is not the head
                prev.setLink(temp.getLink());
                if (temp.getLink() != null) {
                    temp.getLink().setPrev(prev);
                }
            } else {
                // Node is the head
                head = temp.getLink();
                if (head != null) {
                    head.setPrev(null);
                }
            }
            System.out.println("Node with date " + keyToDelete + " deleted.");
        } else {
            System.out.println("Node with date " + keyToDelete + " not found.");
        }		
	}
*/
	
	@Override
	public void display() {
		if (head == null)
			System.out.println("No shopping data has been created yet!");
		else {
			Node temp = head;
			while (temp != null) {
				System.out.println(temp.getProducts() + " ");
			    temp = temp.getLink();
			 }
			System.out.println();
		}		
	}
	
	@Override
	public void displayReverse() {
	    Node temp = head;
	    if (head == null)
			System.out.println("No shopping data has been created yet!");
		else {
			// Move to the last node
		    while (temp != null && temp.getLink() != null) {
		        temp = temp.getLink();
		    }

		    // Traverse the list in reverse order
		    while (temp != null) {
		        System.out.println(temp.getDate_Products());
		        temp = temp.getPrev();
		    }
		}	    
	}
	
	@Override
	public void setKey(LocalDate index, LocalDate object) {// sets the date of the product if the date was wrongly given.
		
		if(head ==null || index == null || object == null || size()==0 ) {
			// the list is empty
			System.out.println("Invalid index or No shopping data has been created yet!");
            
		}else {
			Node temp = head;
			while(temp!=null) {
				if(temp.getDate()== index) {
					temp.setDate(object);
					System.out.println("The list was succesfully updated.");
				}
				temp = temp.getLink();
			}
		}		
	}

	@Override
	public String getValue(LocalDate index) { // Gets the product of that time.
		
		if(head ==null || index == null ||  size()==0 ) {
			// List is empty
			System.out.println("Invalid index or No shopping data has been created yet!");
            
		}else {
			Node temp = head;
			while(temp!=null) {
				if(temp.getDate()== index) {
					return temp.getProducts();
				}
				temp = temp.getLink();
			}
		}
		return "There is no product for it";
	}

	
	@Override
	public void setValueNR(LocalDate index, String object) { // Rewrite the product and delete the last product of that time
		
		if(head ==null || index == null ||  size()==0 ) {
			// List is empty
			System.out.println("Invalid index or No shopping data has been created yet!");
            
		}else {
			Node temp = head;
			while(temp!=null) {
				if(temp.getDate()== index) {
					temp.setProducts_NR(object);
				}
				temp = temp.getLink();
			}
		}		
	}

	@Override
	public void setValueR(LocalDate index, String object) { // Adds the product of that time 
		
		if(head ==null || index == null ||  size()==0 ) {
			// List is empty
			System.out.println("Invalid index or No shopping data has been created yet!");
            
		}else {
			Node temp = head;
			while(temp!=null) {
				if(temp.getDate()== index) {
					temp.setProduct(object);
				}
				temp = temp.getLink();
			}
		}		
	}
	
	
	@Override
	public int getsize() {
		return size();
	}
	
}
