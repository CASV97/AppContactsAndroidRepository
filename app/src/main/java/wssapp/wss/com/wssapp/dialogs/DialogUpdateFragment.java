package wssapp.wss.com.wssapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import wssapp.wss.com.wssapp.R;
import wssapp.wss.com.wssapp.Utilities.VolleySingleton;

public class DialogUpdateFragment extends DialogFragment implements Response.Listener,Response.ErrorListener{
    private EditText nombre, telefono, email,direccion,especialidad;
    JsonObjectRequest jsonObjectRequest;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.update_dialog_layout,null)).setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nombre=((AlertDialog) dialog ).findViewById(R.id.edit_name);
                telefono=((AlertDialog) dialog ).findViewById(R.id.edit_phone);
                email=((AlertDialog) dialog ).findViewById(R.id.edit_email);
                direccion=((AlertDialog) dialog ).findViewById(R.id.edit_address);
                especialidad=((AlertDialog) dialog ).findViewById(R.id.edit_specialties);
                cargarWebService();


            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DialogUpdateFragment.this.getDialog().cancel();
            }
        });
        return builder.create();

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void cargarWebService(){
        String url = "http://83.61.21.211/ejemploBDRemota/JSONUpdateRegistro.php";
        url=url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, this, this);
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResponse(Object response) {
        Toast.makeText(getContext(),"Conecta",Toast.LENGTH_LONG).show();
    }
}
