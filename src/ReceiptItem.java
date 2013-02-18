/**
 * 
 * @author kino
 *
 */
public class ReceiptItem{
  private String name;
  private float price;
  private int quantity;
  private boolean imported;
  private boolean exempt;
  private float tax;
  
  /**
   * Creates a ReceiptItem
   * @param name Name of item
   * @param price Price of item
   * @param quantity Repeated number of items to consider
   * @param imported Whether the item is imported
   * @param exempt Whether the item is exempt
   */
  public ReceiptItem(String name, float price, int quantity, boolean imported, boolean exempt){
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.imported = imported;
    this.exempt = exempt;
  }
  
  /**
   * This method returns the total tax of the receipt item by multiplying the 
   * tax for an individual item by the number of repeated items.
   * 
   * @return the total tax of the receipt item. 
   */
  public float getReceiptItemTotalTax(){
    return tax*quantity;
  }
  
  /**
   * This method returns the total cost of the receipt item by multiplying the
   * cost for an individual item by the number of repeated items, and adding
   * to this number the total tax of the receipt item.
   * 
   * @return the total cost of the receipt item, including taxes.
   */
  public float getReceiptItemTotalCost(){
    return price*quantity+getReceiptItemTotalTax();
  }
  
  /**
   * This method calculates the tax for an individual item.
   * 
   * @param basicSalesTax A basic sales tax whose default is 10% within the current constraints
   * of the assignment, and is only added for non-exempt items.
   * @param importTax An import duty tax whose default is 5% within the current constraints of the assignment, and
   * is added to the basic sales tax if items are imported.
   */
  public void calculateTax(float basicSalesTax, float importTax){
    tax = 0;
    if(exempt==false)
      tax = price*basicSalesTax/100;
    if(imported==true)
      tax = tax+price*importTax/100;
    tax = roundNearest05(tax);
  }
  
  /**
   * This method rounds up to the nearest 0.05 considering up to two decimal
   * levels of precision, and without using external Math.* libraries.
   * 
   * @param tax Tax to be rounded up to the nearest 0.05
   * @return tax rounded up to the nearest 0.05
   */
  private float roundNearest05(float tax){
    float roundTax = (int) (tax*100)%10;
    if(roundTax==0||roundTax==5)
      tax = (float) ((int) (tax*100))/100;
    else if(roundTax>5)
      tax = ((float) ((int) (tax*10))/10)+(float)0.1;
    else
      tax = ((float) ((int) (tax*10))/10)+(float)0.05;
    return tax;
  }
  
  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public float getPrice(){
    return price;
  }

  public void setPrice(float price){
    this.price = price;
  }

  public int getQuantity(){
    return quantity;
  }

  public void setQuantity(int quantity){
    this.quantity = quantity;
  }

  public boolean isImported(){
    return imported;
  }

  public void setImported(boolean imported){
    this.imported = imported;
  }
  
  public boolean isExempt(){
    return exempt;
  }

  public void setExempt(boolean exempt){
    this.exempt = exempt;
  }

  public float getTax(){
    return tax;
  }

  public void setTax(float tax){
    this.tax = tax;
  }  
}