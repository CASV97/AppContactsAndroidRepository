package wssapp.wss.com.wssapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wssapp.wss.com.wssapp.Utilities.VolleySingleton;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    EditText editTextUser, editTextPass,editTextIPServer;
    JsonObjectRequest jsonObjectRequest=null;
    TextView txtError;

    private String user,password,ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.editTextUser =findViewById(R.id.user);
        this.editTextPass =findViewById(R.id.pass);
        this.txtError =findViewById(R.id.vistaid);
        this.editTextIPServer=findViewById(R.id.edit_text_ip_id);

    }
   public void login(View view){

       this.user = editTextUser.getText().toString().trim();
       this.password= editTextPass.getText().toString().trim();
       this.ip=editTextIPServer.getText().toString().trim();
       VolleySingleton.setIp(ip);
       cargarWebService();
      // txtError.setText(""+user+",  "+password);
      // finish();


   }

    private void cargarWebService() {
        String url = "http://"+ip+"/ejemploBDRemota/JSONValidarLoginUser.php?user="+user+"&pass="+password;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        VolleySingleton.getInstanciaVolley(getApplication()).addToRequestQueue(jsonObjectRequest);
    }
    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray=response.optJSONArray("usuario");

            try {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                if (user.equals(jsonObject.getString("user")) && password.equals(jsonObject.getString("password"))) {
                    Intent intent = new Intent(this, ActivityManager.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplication(), "El Usuario o la contraseña son incorrectos " + e.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println(e.getMessage());
            }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        txtError.setText("Error Usuario o contraseña :"+error);
        if (ip.equals("") || ip==null){
            txtError.setText("No ha introducido el servidor");
        }

    }


}
