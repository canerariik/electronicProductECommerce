package converter;

import dao.PrivilegeDAO;
import entity.Privilege;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("privilegeConverter")
public class privilegeConverter implements Converter {

    private PrivilegeDAO privilegeDAO;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        Privilege p = this.getPrivilegeDAO().findByID(id);
        return p;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Privilege p = (Privilege) t;
        return String.valueOf(p.getId());
    }

    public PrivilegeDAO getPrivilegeDAO() {
        if (privilegeDAO == null) {
            this.privilegeDAO = new PrivilegeDAO();
        }
        return privilegeDAO;
    }

    public void setPrivilegeDAO(PrivilegeDAO privilegeDAO) {
        this.privilegeDAO = privilegeDAO;
    }

}
