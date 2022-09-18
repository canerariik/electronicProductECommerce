package dao;

import entity.Stock;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class StockDAO extends DBConnection {

    public Stock findByID(int id) {
        Stock s = null;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from stock where id=" + id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                s = new Stock(rs.getInt("id"), rs.getInt("piece"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }

    public void create(Stock s) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into stock(piece) values(" + s.getPiece() + ")";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Stock s) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "update stock set piece=" + s.getPiece() + " where id=" + s.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Stock s) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "delete from stock where id=" + s.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Stock> getList() {
        List<Stock> list = new ArrayList<>();
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from stock order by id asc";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new Stock(rs.getInt("id"), rs.getInt("piece")));
            }

            st.isClosed();
            rs.isClosed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}
