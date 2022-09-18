package converter;

import dao.UsersDAO;
import entity.Users;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("usersConverter")
public class usersConverter implements Converter {

    private UsersDAO usersDAO;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        Users u = this.getUsersDAO().findByID(id);
        return u;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Users u = (Users) t;
        return String.valueOf(u.getId());
    }

    public UsersDAO getUsersDAO() {
        if (usersDAO == null) {
            this.usersDAO = new UsersDAO();
        }
        return usersDAO;
    }

    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

}
