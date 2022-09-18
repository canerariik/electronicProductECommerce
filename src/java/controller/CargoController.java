package controller;

import dao.CargoDAO;
import entity.Cargo;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "cargoController")
@SessionScoped
public class CargoController implements Serializable {

    private Cargo entity;
    private CargoDAO dao;
    private List<Cargo> list;

    public CargoController() {
    }

    public int getCargo_tracking_no(int id) {
        Cargo c = this.getDao().findByID(id);
        return c.getCargo_tracking_no();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Cargo();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Cargo();
    }

    public void delete(Cargo c) {
        this.getDao().delete(c);
        entity = new Cargo();
    }

    public void clear() {
        entity = new Cargo();
    }

    public Cargo getEntity() {
        if (this.entity == null) {
            this.entity = new Cargo();
        }
        return entity;
    }

    public void setEntity(Cargo entity) {
        this.entity = entity;
    }

    public CargoDAO getDao() {
        if (this.dao == null) {
            this.dao = new CargoDAO();
        }
        return dao;
    }

    public void setDao(CargoDAO dao) {
        this.dao = dao;
    }

    public List<Cargo> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Cargo> list) {
        this.list = list;
    }

}
