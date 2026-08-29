
public class ReportData {

    private String productCode;
    private String productName;
    private String productCategory;   
    private String productWarranty;   
    private double productPrice;
    private int productStockLevel;
    private String productSupplier;

    public ReportData() {
    }

    public ReportData(String productCode, String productName, String productCategory,
                       String productWarranty, double productPrice, int productStockLevel,
                       String productSupplier) {
        this.productCode = productCode;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productWarranty = productWarranty;
        this.productPrice = productPrice;
        this.productStockLevel = productStockLevel;
        this.productSupplier = productSupplier;
    }

    

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductWarranty() {
        return productWarranty;
    }

    public void setProductWarranty(String productWarranty) {
        this.productWarranty = productWarranty;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStockLevel() {
        return productStockLevel;
    }

    public void setProductStockLevel(int productStockLevel) {
        this.productStockLevel = productStockLevel;
    }

    public String getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }
}
