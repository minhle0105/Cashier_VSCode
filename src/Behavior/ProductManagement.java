package Behavior;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Customer;
import Entity.Product;

public class ProductManagement {
      ArrayList<Product> productStorage;
      Scanner sc = new Scanner(System.in);
      final String filePath = "product.txt";

      public void createFile() {
            File file = new File(filePath);
            try {
                  file.createNewFile();
            } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
            }
      }

      public void add(Product p) {
            productStorage = (ArrayList<Product>) this.ReadFile() == null ? new ArrayList<>()
                        : (ArrayList<Product>) this.ReadFile();
            productStorage.add(p);
            try {
                  UpdateFile();
                  System.out.println("Added successfully");
            } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                  System.out.println("Cannot add");
            }
      }

      public void update(String productID) {
            int count = 0;
            productStorage = (ArrayList<Product>) this.ReadFile();
            if (productStorage.size() == 0) {
                  System.out.println("No product to update");
            }
            for (Product p : productStorage) {
                  if (p.getId().equals(productID)) {
                        System.out.println("Enter new name");
                        String newName = sc.nextLine();
                        System.out.println("Enter new id");
                        String newID = sc.nextLine();
                        System.out.println("Enter new quantity");
                        int newQuantity = Integer.parseInt(sc.nextLine());
                        System.out.println("Enter new price");
                        double newPrice = Double.parseDouble(sc.nextLine());
                        p.setName(newName);
                        p.setId(newID);
                        p.setQuantity(newQuantity);
                        p.setPrice(newPrice);
                        System.out.println("Product Information Is Updated Successfully");
                        count++;
                        break;
                  }
            }
            if (count == 0) {
                  System.out.println("No product found");
            }
            try {
                  UpdateFile();
            } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                  System.out.println("Cannot update");
            }

      }

      public void delete(String productID) {
            int count = 0;
            try {
                  productStorage = (ArrayList<Product>) this.ReadFile();
                  if (productStorage.size() == 0) {
                        System.out.println("No product to delete");
                  }
                  for (Product p : productStorage) {
                        if (p.getId().equals(productID)) {
                              productStorage.remove(p);
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
                  e.printStackTrace();
                  System.out.println("Cannot delete");
            }
      }

      public void showAll(ArrayList<Product> productListToPrint) {
            for (Product p : productListToPrint) {
                  System.out.println(p.toString());
            }
      }

      public void searchByID(String productID) {
            productStorage = (ArrayList<Product>) this.ReadFile();
            for (Product p : productStorage) {
                  if (p.getId().equals(productID)) {
                        System.out.println(p.toString());
                  }
            }
      }

      public void searchByName(String productName) {
            productStorage = (ArrayList<Product>) this.ReadFile();
            for (Product p : productStorage) {
                  if (p.getName().toLowerCase().equals(productName.toLowerCase())) {
                        System.out.println(p.toString());
                  }
            }
      }

      public void UpdateFile() throws IOException {
            File file = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(productStorage);

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
                  e.printStackTrace();
                  System.out.println("Cannot read file");
                  return null;
            }
      }

}
