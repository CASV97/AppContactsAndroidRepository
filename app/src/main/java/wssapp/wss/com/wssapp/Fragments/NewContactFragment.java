package wssapp.wss.com.wssapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import wssapp.wss.com.wssapp.R;
import wssapp.wss.com.wssapp.Utilities.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewContactFragment extends Fragment  implements Response.Listener<JSONObject>, Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText campoId,campoNombre,campoTelefono,campoEmail,campoDireccion,campoEspecalidad;
    Button btnAgregarNuevoContacto;

    ProgressDialog progressDialog;

   // RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    private OnFragmentInteractionListener mListener;

    public NewContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewContactFragment newInstance(String param1, String param2) {
        NewContactFragment fragment = new NewContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista =inflater.inflate(R.layout.fragment_new_contact, container, false);
       // campoId=vista.findViewById(R.id.edit_text_id);
        campoNombre=vista.findViewById(R.id.edit_text_nombre);
        campoTelefono=vista.findViewById(R.id.edit_text_telefono);
        campoEmail=vista.findViewById(R.id.edit_text_email);
        campoDireccion=vista.findViewById(R.id.edit_text_direccion);
        campoEspecalidad=vista.findViewById(R.id.edit_text_especialidad);
        btnAgregarNuevoContacto=vista.findViewById(R.id.btn_agregar);

        //requestQueue= Volley.newRequestQueue(getContext());
        btnAgregarNuevoContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });
        return vista;
    }

    private void cargarWebService() {
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        String url="http://"+VolleySingleton.getIp()+"/ejemploBDRemota/JSONRegistro.php?nombre="+campoNombre.getText().toString()
                +"&telefono="+campoTelefono.getText().toString()+"&email="+campoEmail.getText().toString()+
                "&direccion="+campoDireccion.getText().toString()+"&especialidad="+campoEspecalidad.getText().toString();
        url=url.replace(" ","%20");


        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        //requestQueue.add(jsonObjectRequest);
        VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Se ha registrado exitosamente, verificar en la base de datos ",Toast.LENGTH_SHORT).show();
        progressDialog.hide();

        //campoId.setText("");
        campoNombre.setText("");
        campoTelefono.setText("");
        campoEmail.setText("");
        campoDireccion.setText("");
        campoEspecalidad.setText("");

        Toast.makeText(getContext(),"El ID se autogenera asi que de nada sirve poner algún caracter  ",Toast.LENGTH_SHORT).show();


    }
    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        Toast.makeText(getContext(),"No se pudo registrar -->"+error.toString(),Toast.LENGTH_LONG).show();
        Log.i("ERROR",error.toString());

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
