package converter;

import dao.CargoDAO;
import entity.Cargo;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("cargoConverter")
public class cargoConverter implements Converter {

    private CargoDAO cargoDAO;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        Cargo c = this.getCargoDAO().findByID(id);
        return c;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Cargo c = (Cargo)t;
        return String.valueOf(c.getId());
    }

    public CargoDAO getCargoDAO() {
        if(cargoDAO == null){
            this.cargoDAO = new CargoDAO();
        }
        return cargoDAO;
    }

    public void setCargoDAO(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }
    
}
