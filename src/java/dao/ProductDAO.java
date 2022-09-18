package dao;

import entity.Brand;
import entity.Category;
import entity.Documents;
import entity.Product;
import entity.Stock;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class ProductDAO extends DBConnection {

    private BrandDAO brandDAO;
    private DocumentsDAO filesDAO;
    private StockDAO stockDAO;
    private CategoryDAO categoryDAO;

    public Product findByID(int id) {
        Product p = null;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from product where id=" + id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Brand b = this.getBrandDAO().findByID(rs.getInt("brand_id"));
                Documents f = this.getFilesDAO().findByID(rs.getInt("files_id"));
                Stock s = this.getStockDAO().findByID(rs.getInt("stock_id"));
                p = new Product(rs.getInt("id"), this.getProductCategories(rs.getInt("id")), b, f, s, rs.getString("description"), rs.getInt("price"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    public void create(Product p) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into product(brand_id,files_id,stock_id,description,price) values(" + p.getBrand().getId() + "," + p.getFiles().getId() + "," + p.getStock().getId() + ", '" + p.getDescription() + "', " + p.getPrice() + " )";
            st.executeUpdate(query);

            ResultSet rs = st.executeQuery("select max(id) as mid from product");
            rs.next();
            int product_id = rs.getInt("mid");

            for (Category cat : p.getCategories()) {
                query = "insert into product_category(product_id,category_id) values(" + product_id + "," + cat.getId() + ") ";
                st.executeUpdate(query);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Product p) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "update product set brand_id=" + p.getBrand().getId() + ", files_id=" + p.getFiles().getId() + " ,stock_id=" + p.getStock().getId() + " ,description='" + p.getDescription() + "', price=" + p.getPrice() + " where id=" + p.getId();
            st.executeUpdate(query);

            st.executeUpdate("delete from product_category where product_id=" + p.getId());
            for (Category cat : p.getCategories()) {
                query = "insert into product_category(product_id,category_id) values(" + p.getId() + "," + cat.getId() + ") ";
                st.executeUpdate(query);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Product p) {
        try {
            Statement st = this.getConnection().createStatement();
            st.executeUpdate("delete from product_category where product_id=" + p.getId());
            String query = "delete from product where id=" + p.getId();
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int count() {
        int count = 0;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select count(id) as product_count from product";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                count = rs.getInt("product_count");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public List<Product> getList(int page, int pageSize) {
        List<Product> list = new ArrayList<>();
        int start = (page - 1) * pageSize;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from product order by id asc limit " + pageSize + " offset " + start;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Brand b = this.getBrandDAO().findByID(rs.getInt("brand_id"));
                Documents f = this.getFilesDAO().findByID(rs.getInt("files_id"));
                Stock s = this.getStockDAO().findByID(rs.getInt("stock_id"));
                list.add(new Product(rs.getInt("id"), this.getProductCategories(rs.getInt("id")), b, f, s, rs.getString("description"), rs.getInt("price")));
            }

            st.isClosed();
            rs.isClosed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public List<Category> getProductCategories(int product_id) {
        List<Category> list = new ArrayList<>();
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from category where id in(select category_id from product_category where product_id = " + product_id + ")";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("category_name")));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public BrandDAO getBrandDAO() {
        if (brandDAO == null) {
            this.brandDAO = new BrandDAO();
        }
        return brandDAO;
    }

    public void setBrandDAO(BrandDAO brandDAO) {
        this.brandDAO = brandDAO;
    }

    public DocumentsDAO getFilesDAO() {
        if (filesDAO == null) {
            this.filesDAO = new DocumentsDAO();
        }
        return filesDAO;
    }

    public void setFilesDAO(DocumentsDAO filesDAO) {
        this.filesDAO = filesDAO;
    }

    public StockDAO getStockDAO() {
        if (stockDAO == null) {
            this.stockDAO = new StockDAO();
        }
        return stockDAO;
    }

    public void setStockDAO(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    public CategoryDAO getCategoryDAO() {
        if (categoryDAO == null) {
            this.categoryDAO = new CategoryDAO();
        }
        return categoryDAO;
    }

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

}
