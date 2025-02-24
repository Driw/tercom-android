package br.com.tercomfuncionario.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import br.com.tercomfuncionario.Entity.ServicePrice;
import br.com.tercomfuncionario.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercomfuncionario.R;

public class ServicePriceAdapter extends RecyclerView.Adapter<ServicePriceAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private ArrayList<ServicePrice> servicePrice;
    private Context context;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    public void setmRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack) {
        this.mRecyclerViewOnClickListenerHack = mRecyclerViewOnClickListenerHack;
    }

    public ServicePriceAdapter(Context c, ArrayList<ServicePrice> providers) {
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.servicePrice = providers;
        this.context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.list_item_service_price,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardListServicePrice.setCardBackgroundColor(context.getResources().getColor(servicePrice.get(position).isSelected()? R.color.colorOrange: R.color.accent));
        holder.serviceName.setText("Nome:"+servicePrice.get(position).getName());
        holder.serviceValue.setText(String.format(Locale.getDefault(),"R$ %.2f", servicePrice.get(position).getPrice()).replace(".", ","));
        holder.serviceProvider.setText("Fornecedor:"+servicePrice.get(position).getProvider().getFantasyName());
    }

    @Override
    public int getItemCount() {
        return servicePrice.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView serviceName;
        public TextView serviceValue;
        public TextView serviceProvider;
        public CardView cardListServicePrice;

        public ViewHolder(View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.ServiceName);
            serviceValue = itemView.findViewById(R.id.servicePrice);
            serviceProvider = itemView.findViewById(R.id.ServiceProvider);
            cardListServicePrice = itemView.findViewById(R.id.cardListServicePrice);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v,getPosition());
            }
        }
    }
}