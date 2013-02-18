Author: 
Joaquin (Kino) A. Aguilar

Design Notes and Assumptions:
1. Although using an abstract data type is not necessary, a generic LinkedList was used since future versions of this software might need additional operations commonly found in LinkedLists, including deletion of items. In addition, I wrote the LinkedList as generic because it follows good OOP to reuse code.
2. Only java.io.* external libraries were imported for input/output. In places were it would have been more convenient to use standard libraries (e.g., Math.* for rounding numbers), I instead wrote code as an additional challenge.
3. Although the assignment lists default taxes to consider, I considered that these taxes operate only in some locations, and as such, wrote the program such that different locations could have different taxes. Since receipts are localized, I had the Receipt object store the localized taxes.
4. I also considered that the format of the input file may change depending on factors, including location, and as such, should be left a separate function within Transaction to be re-implemented if the format of the input file changes.
5. After printing the receipt, I deliberately did not set it to null, which might signal to the garbage collection that memory should be released, because I expect that a future iteration of the software might want perform additional operations with the receipt, including submitting it to a database.

Instructions to run: 
In a terminal (linux) or command line (windows), enter the following -> java -jar <Absolute_address to Transaction.jar>
The software will then prompt you to enter an input file to read using an absolute address. If the file can be read correctly, it will output a receipt to the terminal or command line.

Input files:
input4.txt -> Contains no items, and as such, should output a receipt with 0 taxes and 0 cost.
input5.txt -> Contains items that are repeated, i.e., quantity more than 1.