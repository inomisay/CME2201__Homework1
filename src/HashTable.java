import java.time.LocalDate;

public class HashTable<K,V>implements HashTable_Interface<K, V> {

	// Attributes
	private static int TABLE_SIZE = 1001; // Prime number503
	private HashEntry[] table = new HashEntry[TABLE_SIZE];
    private int doublehashinteger = 407;
    private String SSF_PSF = "SSF";  // for choosing method
    private String linearP_DoubleH; // for choosing which one the user wants to use.(LP OR DH)
    private Double Landa;
    private int size = 0;
    private int collision;
        
    // Constructor
	public HashTable(String methode, String LP_DH, double landa){ // FRom the beginning gets the method 
		SSF_PSF = methode;
		linearP_DoubleH = LP_DH;
		Landa = landa;
		collision = 0;
		table = new HashEntry[TABLE_SIZE];
	}
	
	@Override
	public void put(String key, String name, LocalDate date, String product) {
		int hash = hashFunction(key,SSF_PSF); // Calculate hash value with the chosen method
        
		//check if it is full or null or available
		if(table[hash] == null) {
			// Customer not present, create a new entry
			// No collision, create a new entry
            HashEntry newEntry = new HashEntry(hash, key, name, date, product);
            table[hash] = newEntry;
            table[hash].add(date, product);
            size++;
			
            // Check if resizing is needed
            if (((double) size / TABLE_SIZE) >= Landa) {
                resize();
            }
            
		} else if(table[hash].getvalue().getID().compareTo(key)==0){ // the given customer exists		
			table[hash].add(date, product);
			
		} else { // add by linear probing add by double hashing.
			
			if(linearP_DoubleH.equals("LP")) { // add by linear probing
				
				Linear_Probing(hash, key, name, date, product, "add");
				
			} else if(linearP_DoubleH.equals("DH"))  { //add by double hashing
				
				Double_Hashing(hash, key, name, date, product, "add");
				
			} else {
				System.out.println("Please write like this: LP or DH");
			}
		}
	}
	
	private int check(int index ,String key) { // for Double Hashing
		
		Object check1 = table[index];
		int check;
		if(check1 == null) {
			check = 0;
		} else {
			check = table[index].getKey();
		}
		 
		if(check < 0) { // it is available
			return -1;
		} else if(check == 0 || table[index] == null) { //the key is empty
			return 0;
		} else if(check > 0 && table[index].getcustomerID().compareTo(key) == 0) { // the added customer exists in the table just add it to the list
			return 2;			
		}
		else { // the place is not empty and not available and it is not that customer but it is full.
			return 1;
		}
	}
	
	private int search_key(int hash,String key) { // for Linear Probing
		int index = hash;
		boolean isfound = false;
		while(table[index] != null) {
			if(table[index].getvalue().getID().compareTo(key) == 0) { // the customer exists
				isfound = true;
				return index;
			}else {
				isfound=false;
			}
			index++;
			if(index++ > TABLE_SIZE) {
				index = 0;
			}
		}
		if(isfound == false) { // for not finding the customer
			return -2;
		} else {
			return index;
		}
		
	}
	
	public void search(String key) {
		int hash = hashFunction(key,SSF_PSF); // Calculate hash value with the chosen method

		//add by linear probing add by double hashing.
		if(linearP_DoubleH.equals("LP")) { //add by linear probing
			
			int linear_search = search_key(hash, key);
			if(linear_search != -2) {// the  customer founded
				System.out.println(table[linear_search].getvalue().array_size() + "transactions found for " + table[linear_search].getvalue().getname() + " ." );
        		table[linear_search].getvalue().all_Transactions();
			}else {
				System.out.println("there is no such a customer.");
			}
			
		}else if(linearP_DoubleH.equals("DH")) { //add by double hashing
			
			Double_Hashing(hash, key, "noNeed", null , "noNeed", "search");
			
		}else {
			System.out.println("Please write like this: LP or DH");
		}
	}
	
	@Override
	public void Linear_Probing(int hash ,String key, String name ,LocalDate date , String product , String add_re) {
		int newindex = hash+1;
		if(newindex >= TABLE_SIZE) {
			newindex = 0;
		}
		
		if(table[newindex]== null) {
			if(add_re == "add") {
        		// Found an empty slot, insert the new entry
                HashEntry newEntry = new HashEntry(newindex,key, name, date, product);
                table[newindex] = newEntry;
                table[newindex].add(date, product);
                size++;

                // Check if resizing is needed
                if (((double) size / TABLE_SIZE) >= Landa) {
                    resize();
                }
                collision++;
                return;
        	}
			else { //delete
        		System.out.println("there is no customer to delete.");
        	}	
		} else {
			int isfound = search_key(hash, key);
			if(isfound == -2) { // the customer not founded
				if(add_re == "add") {
					boolean isadded = false;
					int reindex = newindex++;
					while(isadded == false) {
						if(table[reindex]==null||table[reindex].getKey() == -1) {// it is available
							// Found an available slot, insert the new entry
			                HashEntry newEntry = new HashEntry(reindex,key, name, date, product);
			                table[reindex] = newEntry;
			                table[reindex].add(date, product);
			                size++;

			                // Check if resizing is needed
			                if (((double) size / TABLE_SIZE) >= Landa) {
			                    resize();
			                }
			                return;
						}
						else {							
							reindex++;
							if(reindex++ > TABLE_SIZE) {
								reindex = 0;
							}
						}
					}
					collision++;
				}else {
					System.out.println("There is no such a customer.");
				}				
			} else {
				//the exact customer founded
				if(add_re == "add") {
            		// add to excited 
                    table[newindex].add(date, product);
                    size++;

                    // Check if resizing is needed
                    if (((double) size / TABLE_SIZE) >= Landa) {
                        resize();
                    }
                    collision++;
                    return;
            	}else {
            		table[newindex].setkey_AV(-1); // set it AV : available      		
            		size--;
            	}	
			}
		}
	}

	@Override
	public void Double_Hashing(int hash ,String key, String name ,LocalDate date , String product, String add_re) {
		
		int A = hash % doublehashinteger;
		boolean isfound = false;
		while(isfound == false) {
			int newhash = doublehashinteger - (A);
			if(newhash < 0) {
				newhash = (newhash+TABLE_SIZE) % TABLE_SIZE ;
			}
			int index = check(newhash, key);
			if(index == 0) { //there is empty
				if(add_re == "add") {
					//--------------------------------------
					// Found an empty slot, insert the new entry
	                HashEntry newEntry = new HashEntry(newhash, key, name, date, product);
	                table[newhash] = newEntry;
	                size++;

	                // Check if resizing is needed
	                if (((double) size / TABLE_SIZE) >= Landa) {
	                    resize();
	                }	   
	                collision++;
					isfound = true;
				} else if (add_re == "search") {
	        		System.out.println("The customer not found with this ID.");
	        	} else {
					System.out.println("there is no such a customer.");
				}
				
			} else if(index == -1) { //there is available 
                if(add_re == "add") {
                	// Found an empty slot, insert the new entry
	                HashEntry newEntry = new HashEntry(newhash, key, name, date, product);
	                table[newhash] = newEntry;
	                size++;

	                // Check if resizing is needed
	                if (((double) size / TABLE_SIZE) >= Landa) {
	                    resize();
	                }
	                collision++;
    				isfound= true;
				}else {
					A = A * (hash % doublehashinteger);	
					isfound = false;
				}
				
			}else if(index ==2) { // the customer is same just add to existed customer
                if(add_re == "add") {
                	table[newhash].add_E(date, product);
                	collision++;
    				isfound = true;
				}else if (add_re == "search") {
            		System.out.println(table[newhash].getvalue().array_size() + "transactions found for " + table[newhash].getvalue().getname()+" ." );
            		table[newhash].getvalue().all_Transactions();
            	}else {
					// delete and make it available.
					table[newhash].setkey_AV(-1);
					size--;
				}
				
			}else { // just index ==1, the place is not empty and not available and it is not that customer but it is full.
				// couldn't find then multiply by n and n increase each time we couldn't find the proper index.
				A = A *(hash % doublehashinteger);	
				isfound=false;
			}
		}		
	}
	
	@Override
	public void remove(String key) {
		
		int hash = hashFunction(key,"SSF"); // Calculate hash value
       
        if(linearP_DoubleH == "LP") {
			Linear_Probing(hash, (String)key, "noneed", null, "noneed", "remove");
		}else if(linearP_DoubleH == "DH") {
			Double_Hashing(hash, (String)key, "noneed", null, "noneed", "remove");
		}else {
			System.out.println("Please write like this: LP or DH");
		}
		
		if (table[hash] != null && table[hash].getKey() == hash && table[hash].getcustomerID().compareTo(key) == 0) {
            // Customer found, remove the entry
            table[hash].setkey_AV(-1);
        } else {
            System.out.println("Customer not found!");
        }		
		
	}

	private double numberofelement() {
		double exist=0.0;
		for(int i=0; i<TABLE_SIZE; i++) {
			if(table[i].getKey()!= -1 || table[i] != null) { // first one is for available second for exactly empty.
				exist++;
			}
		}
		return exist;
	}
	
	@Override
	public void check_Capacity() { // if it is more slot needed  resize 
		
		double Achived_landa = numberofelement()/TABLE_SIZE;
		
		if(Achived_landa>=Landa) {
			resize();
		}
	}
	
	public void resize() {
		/*1. make the table size *2 then make then till the prime number
		*open new table 
		*add all in new table 
		*at the end make table size and array refresh and update
		*/
		int newtable_size= (TABLE_SIZE*2)+1;
		boolean isP= false;
		while(isP==false) {
			if(isPrime(newtable_size)!= true) {
				newtable_size++;
				isP= false;
			}else {
				// the number is prime
				isP=true;
			}				
		}
		
		// open new table.
		HashEntry[] newtable = new HashEntry[newtable_size];
		
		// Rehash all entries to the new table
		for (int i = 0; i < table.length; i++) {
		    HashEntry entry = table[i];
		    if (entry != null && entry.getKey() != -1) {
		        int newHash;
		        if (SSF_PSF.equals("SSF")) {
		            newHash = hashFunctionSSF(entry.getcustomerID());
		        } else {
		            newHash = hashFunctionPAF(entry.getcustomerID());
		        }
		        newtable[newHash] = entry;
		    }
		}

        table = newtable;		
	}
	
	private static boolean isPrime(int num) {
	    if (num <= 1) {
	        return false;
	    }
	    for (int i = 2; i < Math.sqrt(num) + 1; i++) {
	        if (num % i == 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public int collisionfound() {
		return collision;		 
	}

    // Choose which hash function to use based on a specified method
    private int hashFunction(String key, String method) {
        if ("SSF".equals(method)) {
            return hashFunctionSSF(key);
        } else if ("PAF".equals(method)) {
            return hashFunctionPAF(key);
        } else {
            // Default to SSF if an invalid method is specified
            return hashFunctionSSF(key);
        }
    }
    
    private int hashFunctionSSF(String ID) {
        int hash = 0;

        //String keyString = ID.toString();
        String keyString2=ID;
        int length = keyString2.length();

        // Simple Summation Function (SSF)
        for (int i = 0; i < length; i++) {
            char c = keyString2.charAt(i);
            hash += c;
        }

        return hash % TABLE_SIZE;
    }

    private int hashFunctionPAF(String ID) { 
        int hash = 0;
        int a = 31; // A prime number for polynomial accumulation

        // Polynomial Accumulation Function (PAF)
        for (char c : ID.toCharArray()) {
            hash = a * hash + c;
        }

        return Math.abs(hash) % TABLE_SIZE;
    }
    
    @Override
    public void display() {
    	for(int i=0; i<TABLE_SIZE; i++) {
    		
    		if(table[i]!=null&& table[i].getKey()!=-1) {
    			table[i].alltransactions();
    			System.out.println("------------------------------------------------------------------------");
    		}
    	}
    }
    @Override
    public void display2() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            HashEntry entry = table[i];
            if (entry != null) {
                System.out.println("Index " + i + ": " + entry.getKey() );
                entry.alltransactions();
            }
        }
    }

	@Override
	public V get(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
