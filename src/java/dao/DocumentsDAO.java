package dao;

import entity.Documents;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class DocumentsDAO extends DBConnection {

    public Documents findByID(int id) {
        Documents d = null;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from files where id=" + id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                d = new Documents(rs.getInt("id"), rs.getString("file_name"), rs.getString("file_type"), rs.getString("file_path"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return d;
    }

    public void create(Documents d) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into files(file_name,file_type,file_path) values('" + d.getFile_name() + "','" + d.getFile_type() + "', '" + d.getFile_path() + "')";
            st.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Documents d) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "update files set file_name='" + d.getFile_name() + "' where id=" + d.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Documents d) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "delete from files where id =" + d.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Documents> getList() {
        List<Documents> list = new ArrayList<>();
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from files order by id asc";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new Documents(rs.getInt("id"), rs.getString("file_name"), rs.getString("file_type"), rs.getString("file_path")));
            }
            st.isClosed();
            rs.isClosed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}
