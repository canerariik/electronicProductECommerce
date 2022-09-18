package controller;

import entity.Users;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import java.io.Serializable;

@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private Users entity;
    private boolean loggedIn;

    public LoginController() {
    }

    public boolean validateUname(FacesContext context, UIComponent cmp, Object value) throws ValidatorException {
        String v = (String) value;
        if (v.isEmpty()) {
            throw new ValidatorException(new FacesMessage("UserName alanı bos olamaz."));
        } else if (v.length() < 10) {
            throw new ValidatorException(new FacesMessage("UserName 4 karakterden kucuk olamaz."));
        }
        return true;
    }

    public boolean validateUpasw(FacesContext context, UIComponent cmp, Object value) throws ValidatorException {
        String a = (String) value;

        if (a.isEmpty()) {
            throw new ValidatorException(new FacesMessage("Password alanı bos olamaz."));
        } else if (a.length() < 4) {
            throw new ValidatorException(new FacesMessage("Password 4 karakterden kucuk olamaz."));
        }
        return true;
    }

    public String login() {
        if (entity.getUser_name().equals("caner") && entity.getUser_password().equals("caner")) { ///!!!!!!
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("validUser", entity);
            loggedIn = true;
            return "/index?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username or Password is wrong."));
            loggedIn = false;
            return "/login/login.xhtml";
        }
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

}
