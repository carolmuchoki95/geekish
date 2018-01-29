package googlemaps.com.searchrecyclerview;

import com.orm.SugarRecord;



public class Lecturers_Auth_Model extends SugarRecord {

    public String email;
    public String password;
    public String confirm_password;

    public Lecturers_Auth_Model() {
    }


    public Lecturers_Auth_Model(String email, String password, String confirm_password) {
        this.email = email;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}
