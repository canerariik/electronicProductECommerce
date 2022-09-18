package controller;

import dao.CategoryDAO;
import entity.Category;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "categoryController")
@SessionScoped
public class CategoryController implements Serializable {

    private Category entity;
    private CategoryDAO dao;
    private List<Category> list;

    public CategoryController() {
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Category();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Category();
    }

    public void delete(Category c) {
        this.getDao().delete(c);
        entity = new Category();
    }

    public void clear() {
        entity = new Category();
    }

    public Category getEntity() {
        if (this.entity == null) {
            this.entity = new Category();
        }
        return entity;
    }

    public void setEntity(Category entity) {
        this.entity = entity;
    }

    public CategoryDAO getDao() {
        if (this.dao == null) {
            this.dao = new CategoryDAO();
        }
        return dao;
    }

    public void setDao(CategoryDAO dao) {
        this.dao = dao;
    }

    public List<Category> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }

}
