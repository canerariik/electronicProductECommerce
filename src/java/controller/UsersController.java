package controller;

import dao.UsersDAO;
import entity.Users;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "usersController")
@SessionScoped
public class UsersController implements Serializable {

    private Users entity;
    private UsersDAO dao;
    private List<Users> list;

    public UsersController() {
    }

    public String getUser_name(int id){
        Users u = this.getDao().findByID(id);
        return u.getUser_name(); //bos donduruyor!!
    }
    
    public void create() {
        this.getDao().create(entity);
        entity = new Users();
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Users();
    }

    public void delete(Users u) {
        this.getDao().delete(u);
        entity = new Users();
    }

    public void clear() {
        entity = new Users();
    }

    public Users getEntity() {
        if (this.entity == null) {
            this.entity = new Users();
        }
        return entity;
    }

    public void setEntity(Users entity) {
        this.entity = entity;
    }

    public UsersDAO getDao() {
        if (this.dao == null) {
            this.dao = new UsersDAO();
        }
        return dao;
    }

    public void setDao(UsersDAO dao) {
        this.dao = dao;
    }

    public List<Users> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Users> list) {
        this.list = list;
    }

}
