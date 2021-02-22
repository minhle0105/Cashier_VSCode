package View;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Behavior.BillManagement;
import Behavior.CustomerManagement;
import Behavior.Password;
import Behavior.ProductManagement;
import Entity.Bill;
import Entity.Customer;
import Entity.Product;

public class Main {
      static Scanner sc = new Scanner(System.in);
      static ProductManagement pm = new ProductManagement();
      static BillManagement bm = new BillManagement();
      static CustomerManagement cm = new CustomerManagement();
      static Console console = System.console();
      static Password p = new Password();

      public static void showProductManagementMenu() {
            System.out.println("___ Product Management ____");
            System.out.println("1 - Add new product");
            System.out.println("2 - Update a product");
            System.out.println("3 - Delete a product");
            System.out.println("4 - Search product by name");
            System.out.println("5 - Search product by ID");
            System.out.println("6 - Show all products");
            System.out.println("7 - Back to main menu");
      }

      public static void showCustomerManagementMenu() {
            System.out.println("___ Customer Management ___");
            System.out.println("1 - Add new customer");
            System.out.println("2 - Update a customer");
            System.out.println("3 - Delete a customer");
            System.out.println("4 - Search customer by name");
            System.out.println("5 - Search customer by phone number");
            System.out.println("6 - Show all customers");
            System.out.println("7 - Back to main menu");
      }

      public static void showMainMenu() {
            System.out.println("___Main Menu___");
            System.out.println("1 - Product Management");
            System.out.println("2 - Checking out items");
            System.out.println("3 - Customer Management");
            System.out.println("4 - Quit");
      }

      public static void showMenu() throws IOException {
            try {
                  startMenu: while (true) {
                        showMainMenu();
                        int userChoice = Integer.parseInt(sc.nextLine());
                        if (userChoice == 1) {
                              showProductManagementMenu();
                              int userChoiceProductManagement = Integer.parseInt(sc.nextLine());
                              switch (userChoiceProductManagement) {
                                    case 1:
                                          System.out.println("Enter product name");
                                          String productName = sc.nextLine();
                                          System.out.println("Enter product id");
                                          String productID = sc.nextLine();
                                          System.out.println("Enter product quantity");
                                          int productQuantity = Integer.parseInt(sc.nextLine());
                                          System.out.println("Enter product price");
                                          double productPrice = Double.parseDouble(sc.nextLine());
                                          Product newProduct = new Product(productName, productID, productQuantity,
                                                      productPrice);
                                          pm.add(newProduct);
                                          break;
                                    case 2:
                                          System.out.println("Enter product ID to update");
                                          String productIDToUpDate = sc.nextLine();
                                          pm.update(productIDToUpDate);
                                          break;
                                    case 3:
                                          System.out.println("Enter product ID to remove");
                                          String productIDToRemove = sc.nextLine();
                                          pm.delete(productIDToRemove);
                                          break;
                                    case 4:
                                          System.out.println("Enter product name to search");
                                          String productNameToSearch = sc.nextLine();
                                          pm.searchByName(productNameToSearch);
                                          break;
                                    case 5:
                                          System.out.println("Enter product ID to search");
                                          String productIDToSearch = sc.nextLine();
                                          pm.searchByID(productIDToSearch);
                                          break;
                                    case 6:
                                          ArrayList<Product> productListToShow = (ArrayList<Product>) pm.ReadFile();
                                          pm.showAll(productListToShow);
                                          break;
                                    case 7:
                                          continue startMenu;
                                    default:
                                          System.out.println("Invalid option");
                                          continue startMenu;
                              }

                        } else if (userChoice == 2) {
                              System.out.println("___ Check Out ____");
                              Bill newBill = bm.createBill();

                              System.out.println("Enter customer phone number");
                              String customerPhoneNumber = sc.nextLine();
                              boolean isCustomerInDatabase = cm.checkIfCustomerIsInDatabase(customerPhoneNumber);
                              if (isCustomerInDatabase) {
                                    Customer thisCustomer = cm.getCustomerByPhoneNumber(customerPhoneNumber);
                                    thisCustomer.setPoint(thisCustomer.getPoint() + 10);
                                    if (thisCustomer.getPoint() >= 50) {
                                          thisCustomer.setPoint(0);
                                          newBill.setTotalAmount(
                                                      newBill.getTotalAmount() - (0.1 * newBill.getTotalAmount()));
                                    }
                                    cm.UpdateFile();

                              } else {
                                    System.out.println("Enter customer name");
                                    String newCustomerName = sc.nextLine();
                                    Customer newCustomer = new Customer(newCustomerName, customerPhoneNumber);
                                    newCustomer.setPoint(newCustomer.getPoint() + 10);
                                    cm.add(newCustomer);
                              }
                              System.out.println(newBill.toString());
                        } else if (userChoice == 3) {
                              showCustomerManagementMenu();
                              int userChoiceCustomerManagement = Integer.parseInt(sc.nextLine());
                              switch (userChoiceCustomerManagement) {
                                    case 1:
                                          System.out.println("Enter customer name");
                                          String customerName = sc.nextLine();
                                          System.out.println("Enter customer phone number");
                                          String customerPhoneNumber = sc.nextLine();
                                          Customer newCustomer = new Customer(customerName, customerPhoneNumber);
                                          cm.add(newCustomer);
                                          break;
                                    case 2:
                                          System.out.println("Enter customer phone number to update");
                                          String customerPhoneNumberToUpdate = sc.nextLine();
                                          cm.update(customerPhoneNumberToUpdate);
                                          break;
                                    case 3:
                                          System.out.println("Enter customer phone number to remove");
                                          String customerPhoneNumberToRemove = sc.nextLine();
                                          cm.delete(customerPhoneNumberToRemove);
                                          break;
                                    case 4:
                                          System.out.println("Enter customer name to search");
                                          String customerNameToSearch = sc.nextLine();
                                          cm.searchByName(customerNameToSearch);
                                          break;
                                    case 5:
                                          System.out.println("Enter customer phone number to search");
                                          String customerPhoneNumberToSearch = sc.nextLine();
                                          cm.searchByPhoneNumber(customerPhoneNumberToSearch);
                                          break;
                                    case 6:
                                          ArrayList<Customer> customerListToShow = (ArrayList<Customer>) cm.ReadFile();
                                          cm.showAll(customerListToShow);
                                          break;
                                    case 7:
                                          continue startMenu;
                                    default:
                                          System.out.println("Invalid option");
                                          continue startMenu;
                              }
                        } else if (userChoice == 4) {
                              System.out.println("Logged out successfully");
                              break;
                        }
                  }
            } catch (Exception e) {
                  System.out.println("Invalid option");
                  showMenu();
            }

      }

      public static void logIn() throws IOException {
            boolean notRegistered = true;
            while (notRegistered) {
                  System.out.println("Enter username");
                  String userName = sc.nextLine();
                  System.out.println("Enter password");
                  String userPassword = new String(console.readPassword());
                  if (userName.equals("admin")) {
                        if (p.validatePassword(userPassword)) {
                              notRegistered = false;
                              System.out.println("Successfully Logged In");
                              showMenu();
                        }
                  } else {
                        System.out.println("Incorrect UserName or Password");
                  }
            }
      }

      public static void main(String[] args) throws IOException {
            pm.createFile();
            cm.createFile();
            logIn();
      }
}
