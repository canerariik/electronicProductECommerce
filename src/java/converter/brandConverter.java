package converter;

import dao.BrandDAO;
import entity.Brand;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("brandConverter")
public class brandConverter implements Converter {

    private BrandDAO brandDAO;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        Brand b = this.getBrandDAO().findByID(id);
        return b;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Brand b = (Brand) t;
        return String.valueOf(b.getId());
    }

    public BrandDAO getBrandDAO() {
        if (brandDAO == null) {
            this.brandDAO = new BrandDAO();
        }
        return brandDAO;
    }

    public void setBrandDAO(BrandDAO brandDAO) {
        this.brandDAO = brandDAO;
    }

}
