package ie.gasgit.remote;



/**
 * Created by ubuntu on 29/01/18.
 */

class MySessionObjectDetails {



    private String username;

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    private String password;
    private String hostname;
    private int  port;

    MySessionObjectDetails() {
    }

    String getHostname() {
        return hostname;
    }

    void setHostname(String hostname) {
        this.hostname = hostname;
    }

    int getPort() {
        return port;
    }

    void setPort(int port) {
        this.port = port;
    }



    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }
}
