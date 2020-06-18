package wssapp.wss.com.wssapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wssapp.wss.com.wssapp.Entities.Contacto;
import wssapp.wss.com.wssapp.InfoContactActivity;
import wssapp.wss.com.wssapp.R;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    List<Contacto> listaContactos;
    Context ctx;
    //View.OnClickListener listener;

    public ContactsAdapter(List<Contacto> listaContactos,Context ctx) {
        this.listaContactos = listaContactos;
        this.ctx=ctx;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View vista =LayoutInflater.from(parent.getContext()).inflate(R.layout.contactos_row_list,parent,false);
       RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.WRAP_CONTENT);
       vista.setLayoutParams(layoutParams);



        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        holder.txtNombre.setText(listaContactos.get(position).getNombre().toString());
        holder.txtTelefono.setText(listaContactos.get(position).getTelefono().toString());
        holder.txtEmail.setText(listaContactos.get(position).getEmail().toString());

    }


    @Override
    public int getItemCount() {
        if (listaContactos != null)
            return listaContactos.size();
        return 0;

    }
    /*public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }*/

   /* @Override
    public void onClick(View v) {
        if (listener!=null){

            listener.onClick(v);
        }
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView txtNombre,txtTelefono,txtEmail;
        private Context activity;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNombre=itemView.findViewById(R.id.txt_nombre);
            txtTelefono=itemView.findViewById(R.id.txt_telefono);
            txtEmail=itemView.findViewById(R.id.txt_email);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ctx, InfoContactActivity.class);
            intent.putExtra("id", listaContactos.get(getAdapterPosition()).getId());
            ctx.startActivity(intent);
        }


    }
}
