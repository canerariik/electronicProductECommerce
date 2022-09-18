package controller;

import dao.PrivilegeDAO;
import dao.RegisterDAO;
import dao.UsersDAO;
import entity.Privilege;
import entity.Users;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.List;

@Named(value = "registerController")
@SessionScoped
public class RegisterController implements Serializable {

    private Users entity;
    private UsersDAO dao;
    private List<Users> list;
    private RegisterDAO Rdao;
    private Privilege privilege;
    private PrivilegeDAO Pdao;

    public RegisterController() {
    }

    public boolean validatePriv(FacesContext context, UIComponent cmp, Object value) throws ValidatorException {
        int a = (int) value;

        if (equals(getPdao().findByID(a))) {

        } else {
            System.out.println("Yetkiniz yok.");
        }
        return true;
    }

    public boolean validateUname(FacesContext context, UIComponent cmp, Object value) throws ValidatorException {
        String a = (String) value;
        if (a.isEmpty()) {
            throw new ValidatorException(new FacesMessage("Username alanı bos olamaz."));
        } else if (a.length() < 4) {
            throw new ValidatorException(new FacesMessage("Username 4 karakterden kucuk olamaz."));
        }
        return true;
    }

    public boolean validateUpass(FacesContext context, UIComponent cmp, Object value) throws ValidatorException {
        String a = (String) value;

        if (a.isEmpty()) {
            throw new ValidatorException(new FacesMessage("Password alanı bos olamaz."));
        } else if (a.length() < 4) {
            throw new ValidatorException(new FacesMessage("Password 3 karakterden kucuk olamaz."));
        }
        return true;
    }

    public boolean validateUmail(FacesContext context, UIComponent cmp, Object value) throws ValidatorException {
        String a = (String) value;

        if (a.isEmpty()) {
            throw new ValidatorException(new FacesMessage("Mail alanı bos olamaz."));
        } else if (a.length() < 4) {
            throw new ValidatorException(new FacesMessage("Hatalı mail."));
        }
        return true;
    }

    public boolean validateUadr(FacesContext context, UIComponent cmp, Object value) throws ValidatorException {
        String a = (String) value;

        if (a.isEmpty()) {

        } else {

        }
        return true;
    }

    public boolean validateUphone(FacesContext context, UIComponent cmp, Object value) throws ValidatorException {
        String a = (String) value;

        if (a.isEmpty()) {
            throw new ValidatorException(new FacesMessage("Telefon alanı bos olamaz."));
        } else if (a.length() < 4) {
            throw new ValidatorException(new FacesMessage("Eksik numara girdiniz."));
        }
        return true;
    }

    public String register() {

        if (entity.getPrivilege().equals("admin")
                && entity.getUser_name().equals("caner")
                && entity.getUser_password().equals("2121")
                && entity.getUser_mail().equals("canee")
                && entity.getUser_address().equals("aa")
                && entity.getUser_phone().equals("0000")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("validUser", entity);

            return "/HomePage?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong!!!."));
            return "/login/register.xhtml";
        }

    }

    public String getRole(int id) {
        Users u = this.getRdao().findByID(id);
        return u.getPrivilege().getRole();
    }

    public void create() {
        this.getRdao().register(entity);
        entity = new Users();
    }

    public Users getEntity() {
        if (entity == null) {
            entity = new Users();
        }
        return entity;
    }

    public void setEntity(Users entity) {
        this.entity = entity;
    }

    public UsersDAO getDao() {
        if (dao == null) {
            dao = new UsersDAO();
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

    public RegisterDAO getRdao() {
        if (Rdao == null) {
            Rdao = new RegisterDAO();
        }
        return Rdao;
    }

    public void setRdao(RegisterDAO Rdao) {
        this.Rdao = Rdao;
    }

    public Privilege getPrivilege() {
        if (privilege == null) {
            privilege = new Privilege();
        }
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public PrivilegeDAO getPdao() {
        if (Pdao == null) {
            Pdao = new PrivilegeDAO();
        }
        return Pdao;
    }

    public void setPdao(PrivilegeDAO Pdao) {
        this.Pdao = Pdao;
    }

}
