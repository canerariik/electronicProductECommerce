package controller;

import dao.OrdersDAO;
import entity.Orders;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "orderController")
@SessionScoped
public class OrdersController implements Serializable {

    private Orders entity;
    private OrdersDAO dao;
    private List<Orders> list;

    public OrdersController() {
    }

    public int getQuantity(int id) {
        Orders o = this.getDao().findByID(id);
        return o.getQuantity();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Orders();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Orders();
    }

    public void delete(Orders o) {
        this.getDao().delete(o);
        entity = new Orders();
    }

    public void clear() {
        entity = new Orders();
    }

    public Orders getEntity() {
        if (this.entity == null) {
            this.entity = new Orders();
        }
        return entity;
    }

    public void setEntity(Orders entity) {
        this.entity = entity;
    }

    public OrdersDAO getDao() {
        if (this.dao == null) {
            this.dao = new OrdersDAO();
        }
        return dao;
    }

    public void setDao(OrdersDAO dao) {
        this.dao = dao;
    }

    public List<Orders> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Orders> list) {
        this.list = list;
    }

}
