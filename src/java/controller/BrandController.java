package controller;

import dao.BrandDAO;
import entity.Brand;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "brandController")
@SessionScoped
public class BrandController implements Serializable {

    private Brand entity;
    private BrandDAO dao;
    private List<Brand> list;

    public BrandController() {
    }

    public String getBrand_name(int id) {
        Brand b = this.getDao().findByID(id);
        return b.getBrand_name();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Brand();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Brand();
    }

    public void delete(Brand b) {
        this.getDao().delete(b);
        entity = new Brand();
    }

    public void clear() {
        entity = new Brand();
    }

    public Brand getEntity() {
        if (entity == null) {
            entity = new Brand();
        }
        return entity;
    }

    public void setEntity(Brand entity) {
        this.entity = entity;
    }

    public BrandDAO getDao() {
        if (dao == null) {
            dao = new BrandDAO();
        }
        return dao;
    }

    public void setDao(BrandDAO dao) {
        this.dao = dao;
    }

    public List<Brand> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Brand> list) {
        this.list = list;
    }

}
