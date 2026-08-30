import java.util.ArrayList;
import java.util.Scanner;

/**
 * Products.java
 * Contains all the working methods for the Extreme IT Products application.
 */
public class Products {

    private ArrayList<ReportData> productList;
    private Scanner scanner;

    public Products() {
        productList = new ArrayList<ReportData>();
        scanner = new Scanner(System.in);
    }

    // ---------------------------------------------------------------
    // Q.1.1 - Main application loop / welcome screen
    // ---------------------------------------------------------------
    public void run() {

        while (true) {
            System.out.println("BRIGHT FUTURE TECHNOLOGIES APPLICATION");
            System.out.println("****************************************");
            System.out.print("Enter (1) to launch menu or any other key to exit: ");
            String start = scanner.nextLine().trim();

            if (!start.equals("1")) {
                ExitApplication();
                break;
            }

            boolean keepGoing = true;
            while (keepGoing) {
                DisplayMenu();
                System.out.print(">> ");
                String option = scanner.nextLine().trim();
                System.out.println();

                switch (option) {
                    case "1":
                        CaptureProduct();
                        break;
                    case "2":
                        SearchProduct();
                        break;
                    case "3":
                        UpdateProduct();
                        break;
                    case "4":
                        DeleteProduct();
                        break;
                    case "5":
                        PrintReport();
                        break;
                    case "6":
                        ExitApplication();
                        return;
                    default:
                        System.out.println("Invalid selection. Please choose an option between 1 and 6.\n");
                        continue;
                }

                System.out.print("Enter (1) to launch menu or any other key to exit: ");
                String again = scanner.nextLine().trim();
                System.out.println();
                if (!again.equals("1")) {
                    ExitApplication();
                    return;
                }
            }
        }
    }

    // ---------------------------------------------------------------
    // Q.1.1 - Displays the menu structure
    // ---------------------------------------------------------------
    public void DisplayMenu() {
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new product.");
        System.out.println("(2) Search for a product.");
        System.out.println("(3) Update a product.");
        System.out.println("(4) Delete a product.");
        System.out.println("(5) Print report.");
        System.out.println("(6) Exit Application.");
    }

    // ---------------------------------------------------------------
    // Q.1.2, Q.1.4, Q.1.5, Q.1.6, Q.1.7 - Capture a new product
    // ---------------------------------------------------------------
    public void CaptureProduct() {
        System.out.println("CAPTURE A NEW PRODUCT");
        System.out.println("****************************");

        String code;
        while (true) {
            System.out.print("Enter the product code: ");
            code = scanner.nextLine().trim();

            if (findProductByCode(code) != null) {
                System.out.println("A product with that code already exists. Please enter a unique product code.\n");
            } else {
                break;
            }
        }

        System.out.print("Enter the product name: ");
        String name = scanner.nextLine().trim();

        String category = getValidCategory();

        System.out.print("Indicate the product warranty. Enter (1) for 6 months or any other key for 2 years. ");
        String warrantyChoice = scanner.nextLine().trim();
        String warranty = warrantyChoice.equals("1") ? "6 months" : "2 years";

        double price = getValidPrice(name);

        int stock = getValidStock(name);

        System.out.print("Enter the supplier for " + name + " >> ");
        String supplier = scanner.nextLine().trim();

        ReportData product = new ReportData(code, name, category, warranty, price, stock, supplier);

        // Q.1.2 - store the product using arrays/array lists
        SaveProduct(product);

        // Q.1.7 - confirm successful save
        System.out.println("Product details has been saved successfully!!!");
    }

    // Helper: Q.1.4 & Q.1.5 - validate product category, re-prompt if invalid
    private String getValidCategory() {
        String[] categories = {"Desktop Computer", "Laptop", "Tablet", "Printer", "Gaming Console"};
        int choice = -1;

        while (true) {
            System.out.println("Select the product category:");
            System.out.println("Desktop Computer - 1");
            System.out.println("Laptop - 2");
            System.out.println("Tablet - 3");
            System.out.println("Printer - 4");
            System.out.println("Gaming Console - 5");
            System.out.print("Product Category >> ");
            String input = scanner.nextLine().trim();

            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 5) {
                    return categories[choice - 1];
                }
            } catch (NumberFormatException e) {
                // fall through to error message below
            }
            System.out.println("Invalid product category. Please re-enter a valid product category.\n");
        }
    }

    // Helper: validate price input
    private double getValidPrice(String name) {
        while (true) {
            System.out.print("Enter the price for " + name + " >> ");
            String input = scanner.nextLine().trim();
            try {
                double price = Double.parseDouble(input);
                if (price >= 0) {
                    return price;
                }
            } catch (NumberFormatException e) {
                // fall through
            }
            System.out.println("Invalid price. Please enter a numeric value.");
        }
    }

    // Helper: validate stock level input
    private int getValidStock(String name) {
        while (true) {
            System.out.print("Enter the stock level for " + name + " >> ");
            String input = scanner.nextLine().trim();
            try {
                int stock = Integer.parseInt(input);
                if (stock >= 0) {
                    return stock;
                }
            } catch (NumberFormatException e) {
                // fall through
            }
            System.out.println("Invalid stock level. Please enter a whole number.");
        }
    }

    // ---------------------------------------------------------------
    // Q.1.2 - Save a product into memory (array list)
    // ---------------------------------------------------------------
    public void SaveProduct(ReportData product) {
        productList.add(product);
    }

    // ---------------------------------------------------------------
    // Q.1.8 - Search for a product by product code
    // ---------------------------------------------------------------
    public void SearchProduct() {
        System.out.print("Please enter the product code to search: ");
        String code = scanner.nextLine().trim();

        ReportData found = findProductByCode(code);

        if (found != null) {
            System.out.println("****************************************************");
            System.out.println("PRODUCT SEARCH RESULTS");
            System.out.println("****************************************************");
            printProductDetails(found);
            System.out.println("****************************************************");
        } else {
            System.out.println("The product cannot be located. Invalid Product");
        }
    }

    // Helper: locate a product by its code, returns null if not found
    private ReportData findProductByCode(String code) {
        for (ReportData p : productList) {
            if (p.getProductCode().equalsIgnoreCase(code)) {
                return p;
            }
        }
        return null;
    }

    // Helper: print a single product's details in the required format
    private void printProductDetails(ReportData p) {
        System.out.println("PRODUCT CODE:          " + p.getProductCode());
        System.out.println("PRODUCT NAME:          " + p.getProductName());
        System.out.println("PRODUCT WARRANTY:      " + p.getProductWarranty());
        System.out.println("PRODUCT CATEGORY:      " + p.getProductCategory());
        System.out.println("PRODUCT PRICE:         R " + p.getProductPrice());
        System.out.println("PRODUCT STOCK LEVELS:  " + p.getProductStockLevel());
        System.out.println("PRODUCT SUPPLIER:      " + p.getProductSupplier());
    }

    // ---------------------------------------------------------------
    // Q.1.9 - Delete a product (with confirmation)
    // ---------------------------------------------------------------
    public void DeleteProduct() {
        System.out.print("Please enter the product code to delete: ");
        String code = scanner.nextLine().trim();

        ReportData found = findProductByCode(code);

        if (found == null) {
            System.out.println("The product cannot be located. Invalid Product");
            return;
        }

        System.out.print("Are you sure you want to delete " + found.getProductName() + "? (y) Yes, (n) No ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("y")) {
            productList.remove(found);
            System.out.println("Product has been deleted successfully!!!");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // ---------------------------------------------------------------
    // Q.1.10 - Update specific details of a product
    // ---------------------------------------------------------------
    public void UpdateProduct() {
        System.out.print("Please enter the product code to update: ");
        String code = scanner.nextLine().trim();

        ReportData found = findProductByCode(code);

        if (found == null) {
            System.out.println("The product cannot be located. Invalid Product");
            return;
        }

        System.out.print("Update the warranty? (y) Yes, (n) No ");
        String updateWarranty = scanner.nextLine().trim();
        if (updateWarranty.equalsIgnoreCase("y")) {
            System.out.print("Indicate the product warranty. Enter (1) for 6 months or any other key for 2 years. ");
            String warrantyChoice = scanner.nextLine().trim();
            found.setProductWarranty(warrantyChoice.equals("1") ? "6 months" : "2 years");
        }

        System.out.print("Update the product price? (y) Yes, (n) No ");
        String updatePrice = scanner.nextLine().trim();
        if (updatePrice.equalsIgnoreCase("y")) {
            double newPrice = getValidPrice(found.getProductName());
            found.setProductPrice(newPrice);
        }

        System.out.print("Update the stock level? (y) Yes, (n) No ");
        String updateStock = scanner.nextLine().trim();
        if (updateStock.equalsIgnoreCase("y")) {
            int newStock = getValidStock(found.getProductName());
            found.setProductStockLevel(newStock);
        }

        System.out.println("Product details has been updated successfully!!!");
    }

    // ---------------------------------------------------------------
    // Q.1.11 - Print product report using ReportData get methods
    // ---------------------------------------------------------------
    public void PrintReport() {
        System.out.println("PRODUCT REPORT");
        System.out.println("====================================================================");

        if (productList.isEmpty()) {
            System.out.println("No products have been captured yet.");
            System.out.println("====================================================================");
            return;
        }

        double totalValue = 0.0;

        for (int i = 0; i < productList.size(); i++) {
            ReportData p = productList.get(i);

            System.out.println("PRODUCT " + (i + 1));
            System.out.println("--------------------------------------------------------------------");
            System.out.println("PRODUCT CODE >>       " + p.getProductCode());
            System.out.println("PRODUCT NAME >>       " + p.getProductName());
            System.out.println("PRODUCT CATEGORY >>   " + p.getProductCategory());
            System.out.println("PRODUCT WARRANTY >>   " + p.getProductWarranty());
            System.out.println("PRODUCT PRICE >>      " + p.getProductPrice());
            System.out.println("PRODUCT LEVEL >>      " + p.getProductStockLevel());
            System.out.println("PRODUCT SUPPLIER >>   " + p.getProductSupplier());
            System.out.println("--------------------------------------------------------------------");

            totalValue += p.getProductPrice();
        }

        double averageValue = totalValue / productList.size();

        System.out.println("====================================================================");
        System.out.println("TOTAL PRODUCT COUNT: " + productList.size());
        System.out.println("TOTAL PRODUCT VALUE: R " + totalValue);
        System.out.println("AVERAGE PRODUCT VALUE: R " + String.format("%.2f", averageValue));
        System.out.println("====================================================================");
    }

    // ---------------------------------------------------------------
    // Q.1.12 - Exit the application
    // ---------------------------------------------------------------
    public void ExitApplication() {
        System.out.println("Thank you for using the Bright Future Technologies Application. Goodbye!");
    }
}
