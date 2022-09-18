package converter;

import dao.ProductDAO;
import entity.Product;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("productConverter")
public class productConverter implements Converter {

    private ProductDAO productDAO;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        Product p = this.getProductDAO().findByID(id);
        return p;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Product p = (Product) t;
        return String.valueOf(p.getId());
    }

    public ProductDAO getProductDAO() {
        if (productDAO == null) {
            this.productDAO = new ProductDAO();
        }
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

}
