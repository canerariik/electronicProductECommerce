package controller;

import dao.PrivilegeDAO;
import entity.Privilege;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "privilegeController")
@SessionScoped
public class PrivilegeController implements Serializable {

    private Privilege entity;
    private PrivilegeDAO dao;
    private List<Privilege> list;

    public PrivilegeController() {
    }

    public String getRole(int id) {
        Privilege p = this.getDao().findByID(id);
        return p.getRole();
    }

    public void create() {
        this.getDao().create(entity);
        entity = new Privilege();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Privilege();
    }

    public void delete(Privilege p) {
        this.getDao().delete(p);
        entity = new Privilege();
    }

    public void clear() {
        entity = new Privilege();
    }

    public Privilege getEntity() {
        if (this.entity == null) {
            this.entity = new Privilege();
        }
        return entity;
    }

    public void setEntity(Privilege entity) {
        this.entity = entity;
    }

    public PrivilegeDAO getDao() {
        if (this.dao == null) {
            this.dao = new PrivilegeDAO();
        }
        return dao;
    }

    public void setDao(PrivilegeDAO dao) {
        this.dao = dao;
    }

    public List<Privilege> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Privilege> list) {
        this.list = list;
    }

}
