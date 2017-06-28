package tj.appfest.com.blooddonor;

/**
 * Created by nitin on 28/06/17.
 */

public class UserProfile {
    public String name;
    public String email;
    public String mobile;
    public String dob;
    public String address;
    public String bloodType;
    public String fcmToken;
    public boolean donateBlood;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
