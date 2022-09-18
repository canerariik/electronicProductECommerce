package entity;

public class Cargo {

    private int id;
    private Users users;
    private int cargo_tracking_no;
    private String delivery_date;

    public Cargo() {
    }

    public Cargo(int id, Users users, int cargo_tracking_no, String delivery_date) {
        this.id = id;
        this.users = users;
        this.cargo_tracking_no = cargo_tracking_no;
        this.delivery_date = delivery_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getCargo_tracking_no() {
        return cargo_tracking_no;
    }

    public void setCargo_tracking_no(int cargo_tracking_no) {
        this.cargo_tracking_no = cargo_tracking_no;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }

}
