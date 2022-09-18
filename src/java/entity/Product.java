
package entity;

import java.util.List;

public class Product {

    private int id;
    private List<Category> categories;
    private Brand brand;
    private Documents files;
    private Stock stock;
    private String description;
    private int price;

    public Product() {
    }

    public Product(int id, List<Category> cat, Brand brand, Documents files, Stock stock, String description, int price) {
        this.id = id;
        this.categories = cat;
        this.brand = brand;
        this.files = files;
        this.stock = stock;
        this.description = description;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Documents getFiles() {
        return files;
    }

    public void setFiles(Documents files) {
        this.files = files;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
