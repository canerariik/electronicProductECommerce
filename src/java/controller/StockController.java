package controller;

import dao.StockDAO;
import entity.Stock;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "stockController")
@SessionScoped
public class StockController implements Serializable {

    private Stock entity;
    private StockDAO dao;
    private List<Stock> list;

    public StockController() {
    }

    public int getPiece(int id) {
        Stock s = this.getDao().findByID(id);
        return s.getPiece();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Stock();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Stock();
    }

    public void delete(Stock s) {
        this.getDao().delete(s);
        entity = new Stock();
    }

    public void clear() {
        entity = new Stock();
    }

    public Stock getEntity() {
        if (this.entity == null) {
            this.entity = new Stock();
        }
        return entity;
    }

    public void setEntity(Stock entity) {
        this.entity = entity;
    }

    public StockDAO getDao() {
        if (this.dao == null) {
            this.dao = new StockDAO();
        }
        return dao;
    }

    public void setDao(StockDAO dao) {
        this.dao = dao;
    }

    public List<Stock> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Stock> list) {
        this.list = list;
    }

}
