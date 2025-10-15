
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Test {

  public static void main(String[] args) throws FileNotFoundException {
    
    Long startLongTimeLong = System.nanoTime();
    String csvFile = "supermarket_dataset_5.csv";
    
    // the user gives SSF or PAF and LP or DH, then add to the costructer of hash table .
    Scanner input = new Scanner(System.in);
    System.out.println("WELLCOME TO SUPERMARKET");
    System.out.println("\nPlese choose a SSF or PAF ? ");
    String SSF_PAF = input.next().toUpperCase(); // Convert to uppercase
    System.out.println("Please choose with writing LP or DH ? ");
    String LP_DH = input.next().toUpperCase(); // Convert to uppercase
    System.out.println("Please enter Landa : ");
    double landa = -1.0; // Initialize to an invalid value
    while (landa < 0 || landa > 1) {
        
        while (!input.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid number for Landa: ");
            input.nextDouble(); // Consume the invalid input
        }
        landa = input.nextDouble();
    }
    
    @SuppressWarnings("unchecked")
    HashTable<Integer, Customer> supermarket = new HashTable(SSF_PAF,LP_DH,landa);

        CSVReader(csvFile,supermarket);
                
        supermarket.display2();
        
        
        Long startcollision = System.nanoTime();        
        System.out.println("Number of Collisions: "+ supermarket.collisionfound()); 
        Long EndTimecollision = System.nanoTime();
        double avrage2 =  (EndTimecollision - startcollision)/1000000000;
        System.out.println(avrage2);
        
        System.out.println("For delete please enter ID: ");
        String inputID = input.next();
        
        Long startdelete = System.nanoTime(); 
        supermarket.remove(inputID);
        supermarket.display2();
        Long EndTimedelete = System.nanoTime();

        double avrage3 =(EndTimedelete - startdelete)/1000000000; ;
        System.out.println(avrage3);
        
        //System.out.print(">Search: ");
        //String serchID= input.next();
        //supermarket.srearch(serchID);*/
        
        Long EndTime = System.nanoTime();
        System.out.println("--------------------------------------------------------");
        double avrage =  (EndTime - startLongTimeLong)/1000000000;
        System.out.println(avrage);

  }
  
  public static void CSVReader(String CSVFILE , HashTable<Integer, Customer> supermarket) {
    String csvFile = CSVFILE;
    String line;
        String csvSplitBy = ",";

        

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                
                String[] sentence = line.split(csvSplitBy); // Split the line into values
                
                // Parse UUID
                String customerId = sentence[0];
                //get name
                String name = sentence[1]; 
                // Parse LocalDate
                LocalDate date = LocalDate.parse(sentence[2]); 
                // Extract year, month, and day from the date string
                /*
                String[] dateParts = sentence[2].split("/");
                int month = Integer.parseInt(dateParts[0]);
                int day = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);

                // Create a LocalDate object
                LocalDate date = LocalDate.of(year, month, day);
                */
                //get product
                String product = sentence[3];
                
                supermarket.put(customerId, name, date, product);
            }

        } catch (IOException e  ) {
            e.printStackTrace();
        }
    
  }
 
}