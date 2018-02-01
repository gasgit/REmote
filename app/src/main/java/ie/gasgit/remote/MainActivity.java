package ie.gasgit.remote;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {


      private Button btnSend= null;
      private EditText etIp;
      private EditText etPort;
      private EditText etUser;
      private EditText etPass;


    //TODO
    // fix async
    // fix getters
    // pgrep command to get pid
    // pkill process name


      private static MySessionObjectDetails mso = new MySessionObjectDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etIp = findViewById(R.id.etIP);
        etPort = findViewById(R.id.etPort);
        etUser = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPassword);
        btnSend = findViewById(R.id.btnSend);

        addListenerOnButton();

    }

    public void addListenerOnButton() {


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFields();
                new MyTask().execute();


            }
        });


    }



    public void getFields() {
        // get values from fields editText
        mso.setHostname(etIp.getText().toString());
        Integer i = Integer.valueOf(etPort.getText().toString());
        mso.setPort(i);
        mso.setUsername(etUser.getText().toString());
        mso.setPassword(etPass.getText().toString());

    }


    public static String executeRemoteCommand(String username, String password, String hostname, int port)
            throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, hostname, port);
        session.setPassword(password);

        // Avoid asking for key confirmation
        Properties prop = new Properties();
        prop.put("StrictHostKeyChecking", "no");
        session.setConfig(prop);
        session.connect();
        System.out.println(session.isConnected());

        // SSH Channel
        ChannelExec sshCH = (ChannelExec) session.openChannel("exec");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        sshCH.setOutputStream(baos);

        // Execute command
        sshCH.setCommand("bash spot.sh");

        sshCH.connect();

        sshCH.disconnect();

        return baos.toString();
    }


    private static class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                executeRemoteCommand(mso.getUsername(), mso.getPassword(), mso.getHostname(), mso.getPort());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}




