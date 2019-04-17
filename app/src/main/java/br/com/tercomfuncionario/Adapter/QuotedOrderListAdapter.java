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

import br.com.tercomfuncionario.Entity.OrderAcceptanceProduct;
import br.com.tercomfuncionario.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercomfuncionario.Interface.iQuotedOrderItem;
import br.com.tercomfuncionario.R;

public class QuotedOrderListAdapter extends RecyclerView.Adapter<QuotedOrderListAdapter.ViewHolder>{

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<? extends iQuotedOrderItem> quotedOrderItems;

    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public void setmRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack) {
        this.mRecyclerViewOnClickListenerHack = mRecyclerViewOnClickListenerHack;
    }

    public QuotedOrderListAdapter (Context c, ArrayList<? extends iQuotedOrderItem> quotedOrderItems){
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = c;
        this.quotedOrderItems = quotedOrderItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_quoted_order_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtxQuotedOrderListItemName.setText(quotedOrderItems.get(position).getName());
        holder.txtxQuotedOrderListItemPrice.setText(String.valueOf(quotedOrderItems.get(position).getPrice()));
        holder.txtQuotedOrderListItemProvider.setText(quotedOrderItems.get(position).getProviderName());
        if (quotedOrderItems.get(position).isProduct()){
            holder.txtxQuotedOrderListItemManufacturer.setText(quotedOrderItems.get(position).getManufacturerName());
        } else {
            holder.txtxQuotedOrderListItemManufacturer.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return quotedOrderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtxQuotedOrderListItemName;
        public TextView txtxQuotedOrderListItemPrice;
        public TextView txtxQuotedOrderListItemManufacturer;
        public TextView txtQuotedOrderListItemProvider;

        public ViewHolder(View itemView) {
            super(itemView);
            txtxQuotedOrderListItemName = itemView.findViewById(R.id.txtxQuotedOrderListItemName);
            txtxQuotedOrderListItemPrice = itemView.findViewById(R.id.txtxQuotedOrderListItemPrice);
            txtxQuotedOrderListItemManufacturer = itemView.findViewById(R.id.txtxQuotedOrderListItemManufacturer);
            txtQuotedOrderListItemProvider = itemView.findViewById(R.id.txtQuotedOrderListItemProvider);
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
