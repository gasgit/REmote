package ie.gasgit.remote;



/**
 * Created by ubuntu on 29/01/18.
 */

public class MySessionObjectDetails {



    String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;
    String hostname;
    int  port;

    public MySessionObjectDetails() {
    }

    public MySessionObjectDetails(String username, String password, String hostname, int port) {

        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
