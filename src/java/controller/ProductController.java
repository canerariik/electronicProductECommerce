package controller;

import dao.ProductDAO;
import entity.Product;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable {

    private Product entity;
    private ProductDAO dao;
    private List<Product> list;

    private int page = 1;
    private int pageSize = 5;
    private int pageCount;

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getDao().count()/(double)pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public ProductController() {
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Product();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Product();
    }

    public void delete(Product p) {
        this.getDao().delete(p);
        entity = new Product();
    }

    public void clear() {
        entity = new Product();
    }

    public Product getEntity() {
        if (this.entity == null) {
            this.entity = new Product();
        }
        return entity;
    }

    public void setEntity(Product entity) {
        this.entity = entity;
    }

    public ProductDAO getDao() {
        if (this.dao == null) {
            this.dao = new ProductDAO();
        }
        return dao;
    }

    public void setDao(ProductDAO dao) {
        this.dao = dao;
    }

    public List<Product> getList() {
        this.list = this.getDao().getList(page, pageSize);
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

}
