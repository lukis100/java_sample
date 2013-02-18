import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * Transaction reads the input file, parses it, and outputs
 * relevant information.
 * 
 * @author kino
 *
 */
public class Transaction {
  
  // Names of exempt items
  static String[] exemptItems = {"book", "books", "chocolate bar", "box of chocolates",
      "packet of headache pills"};
  
  /**
   * This function searches an array with list of exempted items to find out whether
   * an item is exempted.
   * 
   * @param name Name of the item that may or may not be in the list of exempted items
   * @return true if exempted item, false otherwise
   */
  public static boolean isExempt(String name){
    for(int x=0; x<exemptItems.length;x++)
      if(name.compareTo(exemptItems[x])==0)
        return true;
    return false;
  }
  
  /**
   * This function parses a line and creates a ReceiptItem based on parsed information.
   * Because lines may have different formatting based on a number of factors, 
   * including location, it is understood that this Transaction function may need
   * to be re-implemented if the formatting changes. 
   * 
   * @param line Line to be parsed according to rules that can be derived from problem
   * statement, input, and output. 
   * @return A ReceiptItem with information parsed from line.
   */
  public static ReceiptItem parse(String line){
    String name = "";
    float price;
    int quantity;
    boolean imported = false;
    boolean exempt = false;
    
    String[] tokens = line.split(" ");
    int curToken = 0;
    quantity = Integer.parseInt(tokens[curToken++]);
    
    for(;!(tokens[curToken].compareTo("at")==0); curToken++){
      if(tokens[curToken].compareTo("imported")==0)
        imported = true;
      else
        name += tokens[curToken] + " ";
    }
    // Add one to curToken to represent having found "at"
    curToken++;
    // Delete extra last space in name 
    name = name.substring(0, name.length()-1);
    
    exempt = isExempt(name);
    
    price = Float.parseFloat(tokens[curToken]);
    return new ReceiptItem(name, price, quantity, imported, exempt);
  }
  
  /**
   * The main function handles input and building receipt.
   * 
   * @param args
   */
  public static void main(String[] args){
    // Default values for basic sales and import tax.
    float basicSalesTax = 10;
    float importTax = 5;
    Receipt r = new Receipt(basicSalesTax, importTax); 
    
    // Read input and build Receipt
    try{
      BufferedReader inConsole = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Please enter the absolute address of the input file. ");
      System.out.println("For instance, C:/ThoughtWorks/input1.txt");
      String s = inConsole.readLine();
      BufferedReader inFile = new BufferedReader(new FileReader(s));
      String line;
      
      while ((line = inFile.readLine()) != null){
        ReceiptItem ri = parse(line);
        r.addItem(ri);
      }
      inConsole.close();
      inFile.close();
    }
    catch (FileNotFoundException e){
      System.err.println("The file was not found. Please make sure to input a valid file next time.");
      System.err.println("Software will now exit.");
      e.printStackTrace();
      System.exit(-1);
    } 
    catch(IOException e){
      System.err.println("An InputOutput Error occurred: " + e.getMessage());
      System.err.println("Please make sure to address it.");
      System.err.println("Software will now exit.");
      e.printStackTrace();
      System.exit(-2);
    }
    
    // Print Receipt
    r.print();
  }
}
