package Behavior;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Customer;

public class CustomerManagement {
      ArrayList<Customer> customerDataBase;
      Scanner sc = new Scanner(System.in);
      final String filePath = "customer.txt";

      public void createFile() {
            File file = new File(filePath);
            try {
                  file.createNewFile();
            } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
            }
      }

      public boolean checkIfCustomerIsInDatabase(String phoneNumber) {
            customerDataBase = (ArrayList<Customer>) this.ReadFile();
            for (Customer c : customerDataBase) {
                  if (c.getPhoneNumber().equals(phoneNumber)) {
                        return true;
                  }
            }
            return false;
      }

      public Customer getCustomerByPhoneNumber(String phoneNumber) {
            customerDataBase = (ArrayList<Customer>) this.ReadFile();
            for (Customer c : customerDataBase) {
                  if (c.getPhoneNumber().equals(phoneNumber)) {
                        return c;
                  }
            }
            return null;
      }

      public void add(Customer c) {
            try {
                  customerDataBase = (ArrayList<Customer>) this.ReadFile() == null ? new ArrayList<>()
                              : (ArrayList<Customer>) this.ReadFile();
            } catch (Exception e) {
                  e.printStackTrace();
                  System.out.println("Error");

            }
            customerDataBase.add(c);
            try {
                  UpdateFile();
                  System.out.println("Added successfully");
            } catch (IOException e) {
                  e.printStackTrace();
                  System.out.println("Cannot add");
            }

      }

      public void update(String customerPhone) {
            int count = 0;
            customerDataBase = (ArrayList<Customer>) this.ReadFile();
            if (customerDataBase.size() == 0) {
                  System.out.println("No customer to update");
            }
            for (Customer c : customerDataBase) {
                  if (c.getPhoneNumber().equals(customerPhone)) {
                        System.out.println("Enter new name");
                        String newName = sc.nextLine();
                        System.out.println("Enter new phone number");
                        String newPhoneNumber = sc.nextLine();

                        c.setFullName(newName);
                        c.setPhoneNumber(newPhoneNumber);
                        System.out.println("Product Information Is Updated Successfully");
                        count++;
                        break;
                  }
            }
            if (count == 0) {
                  System.out.println("No customer found");
            }
            try {
                  UpdateFile();
            } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                  System.out.println("Cannot update");
            }

      }

      public void delete(String phoneNumber) {
            int count = 0;
            try {
                  customerDataBase = (ArrayList<Customer>) this.ReadFile();
                  if (customerDataBase.size() == 0) {
                        System.out.println("No product to delete");
                  }
                  for (Customer c : customerDataBase) {
                        if (c.getPhoneNumber().equals(phoneNumber)) {
                              customerDataBase.remove(c);
                              count++;
                              break;
                        }
                  }
                  if (count == 0) {
                        System.out.println("No product found");
                  } else {
                        System.out.println("Delete successfully");
                  }
                  UpdateFile();
            } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                  System.out.println("Cannot delete");
            }
      }

      public void showAll(ArrayList<Customer> customerListToPrint) {
            for (Customer c : customerListToPrint) {
                  System.out.println(c.toString());
            }

      }

      public void searchByPhoneNumber(String phoneNumber) {
            customerDataBase = (ArrayList<Customer>) this.ReadFile();
            for (Customer c : customerDataBase) {
                  if (c.getPhoneNumber().equals(phoneNumber)) {
                        System.out.println(c.toString());
                  }
            }
      }

      public void searchByName(String productName) {
            customerDataBase = (ArrayList<Customer>) this.ReadFile();
            for (Customer c : customerDataBase) {
                  if (c.getFullName().toLowerCase().equals(productName.toLowerCase())) {
                        System.out.println(c.toString());
                  }
            }
      }

      public void UpdateFile() throws IOException {
            File file = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(customerDataBase);

            objectOutputStream.close();
            fileOutputStream.close();
      }

      public Object ReadFile() {
            try {
                  FileInputStream fileInputStream = new FileInputStream(filePath);
                  ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                  Object o = objectInputStream.readObject();

                  objectInputStream.close();
                  fileInputStream.close();
                  return o;

            } catch (Exception e) {
                  // e.printStackTrace();
                  System.out.println("Cannot read file");
                  return null;
            }
      }

}
