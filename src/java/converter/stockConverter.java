package converter;

import dao.StockDAO;
import entity.Stock;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("stockConverter")
public class stockConverter implements Converter {

    private StockDAO stockDAO;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        Stock s = this.getStockDAO().findByID(id);
        return s;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Stock s = (Stock) t;
        return String.valueOf(s.getId());
    }

    public StockDAO getStockDAO() {
        if (stockDAO == null) {
            this.stockDAO = new StockDAO();
        }
        return stockDAO;
    }

    public void setStockDAO(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

}
