package dao;

import entity.Category;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class CategoryDAO extends DBConnection {

    public Category findByID(int id) {
        Category c = null;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from category where id=" + id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                c = new Category(rs.getInt("id"), rs.getString("category_name"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
    public void create(Category c) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into category(category_name) values('" + c.getCategory_name() + "')";
            st.executeUpdate(query);
            st.isClosed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Category c) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "update category set category_name='" + c.getCategory_name() + "' where id=" + c.getId();
            st.executeUpdate(query);
            st.isClosed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Category c) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "delete from category where id=" + c.getId();
            st.executeUpdate(query);
            st.isClosed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Category> getList() {
        List<Category> list = new ArrayList<>();
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from category order by id asc";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("category_name")));
            }
            st.isClosed();
            rs.isClosed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}
