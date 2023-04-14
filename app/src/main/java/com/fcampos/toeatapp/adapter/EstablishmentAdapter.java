package com.fcampos.toeatapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.domain.Establishment;

import java.util.List;

public class EstablishmentAdapter extends RecyclerView.Adapter<EstablishmentAdapter.EstablishmentHolder>{

    private Context context;
    private List<Establishment> establishmentList;

    public EstablishmentAdapter(Context context, List<Establishment> dataList) {
        this.context = context;
        this.establishmentList = dataList;
    }

    @Override
    public EstablishmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_establishment_list_item, parent, false);
        return new EstablishmentHolder(view);
    }

    @Override
    public void onBindViewHolder(EstablishmentHolder holder, int position) {
        holder.tv_Name_EstablishmentList.setText(establishmentList.get(position).getName());
        holder.tv_Description_EstablishmentList.setText(establishmentList.get(position).getDescription());
        holder.tv_Address_EstablishmentList.setText(establishmentList.get(position).getAdress());
    }

    @Override
    public int getItemCount() {
        return establishmentList.size();
    }

    public class EstablishmentHolder extends RecyclerView.ViewHolder {

        public TextView tv_Name_EstablishmentList;
        public TextView tv_Description_EstablishmentList;
        public TextView tv_Address_EstablishmentList;
        public View parentView;

        public EstablishmentHolder(View itemView) {
            super(itemView);
            parentView = itemView;

            tv_Name_EstablishmentList = itemView.findViewById(R.id.tv_Name_EstablishmentList);
            tv_Description_EstablishmentList = itemView.findViewById(R.id.tv_Description_EstablishmentList);
            tv_Address_EstablishmentList = itemView.findViewById(R.id.tv_Adress_EstablishmentList);
        }
    }
}
