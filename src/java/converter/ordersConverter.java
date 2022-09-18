package converter;

import dao.OrdersDAO;
import entity.Orders;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("ordersConverter")
public class ordersConverter implements Converter {

    private OrdersDAO ordersDAO;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        Orders o = this.getOrdersDAO().findByID(id);
        return o;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Orders o = (Orders)t;
        return String.valueOf(o.getId());
    }

    public OrdersDAO getOrdersDAO() {
        if(ordersDAO == null){
            this.ordersDAO = new OrdersDAO();
        }
        return ordersDAO;
    }

    public void setOrdersDAO(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }
    
}
