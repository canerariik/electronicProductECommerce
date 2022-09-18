package dao;

import entity.Cargo;
import entity.Orders;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class OrdersDAO extends DBConnection {

    private CargoDAO cargoDAO;

    public Orders findByID(int id) {
        Orders o = null;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from orders where id=" + id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Cargo c = this.getCargoDAO().findByID(rs.getInt("cargo_id"));
                o = new Orders(rs.getInt("id"), c, rs.getInt("quantity"), rs.getString("order_date"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return o;
    }

    public void create(Orders o) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into orders(cargo_id, quantity, order_date) values(" + o.getCargo().getId() + ", " + o.getQuantity() + ", '" + o.getOrder_date() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Orders o) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "update orders set cargo_id=" + o.getCargo().getId() + ", quantity=" + o.getQuantity() + ", order_date='" + o.getOrder_date() + "' where id=" + o.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Orders o) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "delete from orders where id=" + o.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Orders> getList() {
        List<Orders> list = new ArrayList<>();
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from orders order by id asc";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Cargo c = this.getCargoDAO().findByID(rs.getInt("cargo_id"));
                list.add(new Orders(rs.getInt("id"), c, rs.getInt("quantity"), rs.getString("order_date")));
            }
            st.isClosed();
            rs.isClosed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public CargoDAO getCargoDAO() {
        if (cargoDAO == null) {
            this.cargoDAO = new CargoDAO();
        }
        return cargoDAO;
    }

    public void setCargoDAO(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }

}
