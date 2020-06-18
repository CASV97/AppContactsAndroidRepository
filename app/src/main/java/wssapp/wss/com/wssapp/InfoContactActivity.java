package wssapp.wss.com.wssapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wssapp.wss.com.wssapp.Utilities.VolleySingleton;

public class InfoContactActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    private TextView nombre,telefono,email,direccion,especialidad;
    private int id;
    private JsonObjectRequest jsonObjectRequest=null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contact);
        nombre=findViewById(R.id.tv_name_detail);
        telefono=findViewById(R.id.tv_phone_detail);    
        email=findViewById(R.id.tv_email_detail);
        direccion=findViewById(R.id.tv_address_detail);
        especialidad=findViewById(R.id.tv_specialties_detail);
        if (null == savedInstanceState){
            Bundle extras=getIntent().getExtras();
            id= extras.getInt("id");
            nombre.setText(""+id);
            cargarWebService();
        }
    }

    private void cargarWebService() {
        String url = "http://"+VolleySingleton.getIp()+"/ejemploBDRemota/JSONConsultaRegistro.php?id=" + id;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray=response.optJSONArray("contacto");
        try {
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            nombre.setText(jsonObject.getString("nombre"));
            telefono.setText(jsonObject.getString("telefono"));
            email.setText(jsonObject.getString("email"));
            direccion.setText(jsonObject.getString("direccion"));
            especialidad.setText(jsonObject.getString("especialidad"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public Context getContext() {
        return context;
    }
}
