package converter;

import dao.CategoryDAO;
import entity.Category;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("categoryConverter")
public class categoryConverter implements Converter {

    private CategoryDAO categoryDAO;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        Category c = this.getCategoryDAO().findByID(id);
        return c;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Category c = (Category) t;
        return String.valueOf(c.getId());
    }

    public CategoryDAO getCategoryDAO() {
        if (categoryDAO == null) {
            this.categoryDAO = new CategoryDAO();
        }
        return categoryDAO;
    }

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

}
