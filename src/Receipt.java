/**
 * A Receipt is a list of ReceiptItems that keeps track of the total tax associated with items,
 * the total cost of the the items, and also the basic sales and import taxes associated
 * with the place where the receipt is made (10% and 5% respectively for this assignment, but
 * it is assumed that they may change depending on location).
 * 
 * @author kino
 *
 */
public class Receipt{
  LinkedList<ReceiptItem> receiptItems;
  float basicSalesTax;
  float importTax;
    float salesTax;
  float total;
  
  /**
   * Although there are default values provided for basicSalesTax and for importTax,
   * these values may change depending on location of the receipt, and as such,
   * the tax associated with the different items in the receipt may also change. So,
   * for this reason, it is important for Receipt to keep track of taxes.
   * 
   * @param basicSalesTax A basic sales tax whose default is 10% within the current constraints
   * of the assignment, and is only added for non-exempt items.
   * @param importTax An import duty tax whose default is 5% within the current constraints of the assignment, and
   * is added to the basic sales tax if items are imported.
   */
  public Receipt(float basicSalesTax, float importTax){
    receiptItems = new LinkedList<ReceiptItem>();
    this.basicSalesTax = basicSalesTax;
    this.importTax = importTax;
    salesTax = 0;
    total = 0;
  }
  
  /**
   * Adds a ReceiptItem to Receive. In addition, it calculates taxes for the receipt item
   * according to the tax rules where the receipt is made, and uses these taxes and total
   * cost for the ReceiptItem to update the Receipt sales tax and total cost.
   * @param ri ReceiptItem to be added to Receipt.
   */
  public void addItem(ReceiptItem ri){
    receiptItems.addLast(ri);
    ri.calculateTax(basicSalesTax, importTax);
    salesTax += ri.getReceiptItemTotalTax();
    total += ri.getReceiptItemTotalCost();
  }
  
  public int size(){
    return receiptItems.size();
  }
  
  /**
   * This method finds a ReceiptItem at a specified location in the Receipt list, without
   * removing the item from the list.
   * 
   * @param index Index of the ReceiptItem to be found. First item is at index 0. 
   * @return ReceiptItem
   */
  public ReceiptItem peekItem(int index){
    return (ReceiptItem) receiptItems.peek(index);
  }
  
  public float getSalesTax(){
    return salesTax;
  }

  public float getTotal(){
    return total;
  }
  
  public void print(){
    int receiptSize = this.size();
    for(int x=0; x<receiptSize; x++){
      ReceiptItem ri = this.peekItem(x);
      String outputLine = "" + ri.getQuantity();
      if(ri.isImported())
        outputLine += " imported";
      outputLine += " " + ri.getName() + ": ";
      System.out.print(outputLine);
      System.out.printf("%.2f", ri.getReceiptItemTotalCost());
      System.out.println();
    }
    System.out.print("Sales Taxes: ");
    System.out.printf("%.2f", this.getSalesTax());
    System.out.println();
    System.out.print("Total: ");
    System.out.printf("%.2f", this.getTotal());
    System.out.println();
  }
}