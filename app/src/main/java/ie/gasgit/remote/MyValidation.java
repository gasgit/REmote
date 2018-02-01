package ie.gasgit.remote;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ubuntu on 01/02/18.
 */

public class MyValidation {

    public boolean isIPAddress(String ipAddress){

        Pattern ptn = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        Matcher match = ptn.matcher(ipAddress);

        return match.find();

    }


}
