package ie.gasgit.remote;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {


      private Button btnSend= null;
      private EditText etIp;
      private EditText etPort;
      private EditText etUser;
      private EditText etPass;


    //TODO
    // fix async //
    // fix getters //
    // pgrep command to get pid
    // pkill process name
    // validation on edit texts
    // exceptions and messages


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

        try {
            addListenerOnButton();
        } catch (JSchException e) {
            e.printStackTrace();
        }

    }

    private void addListenerOnButton() throws JSchException {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    getFields();
                } catch (Exception e) {

                    e.printStackTrace();
                    System.out.println("MyMessage: " +  e.getLocalizedMessage());
                    Context context = getApplicationContext();

                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, e.toString() , duration);
                    toast.show();

                }
                new MyTask().execute();
            }
        });
    }



    private void getFields() {

        // get values from fields editText

        String ipaddress = etIp.getText().toString();

        if(TextUtils.isEmpty(ipaddress)) {
            etIp.setError("IP Address Required");
            return;
        }else{
            mso.setHostname(etIp.getText().toString());

        }


        Integer i = Integer.valueOf(etPort.getText().toString());
        mso.setPort(i);
        mso.setUsername(etUser.getText().toString());
        mso.setPassword(etPass.getText().toString());

    }


    private static void executeRemoteCommand(String username, String password, String hostname, int port)
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




