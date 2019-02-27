package br.com.tercom.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.tercom.Entity.OrderItemProduct;
import br.com.tercom.Entity.OrderRequest;
import br.com.tercom.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercom.Interface.iNewOrderItem;
import br.com.tercom.R;

public class DetailOrderListAdapter extends RecyclerView.Adapter<DetailOrderListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<iNewOrderItem> orderItemList;
    private Context context;

    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public void setmRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack) {
        this.mRecyclerViewOnClickListenerHack = mRecyclerViewOnClickListenerHack;
    }

    public DetailOrderListAdapter(Context c, ArrayList<iNewOrderItem> orderItemProducts){
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = c;
        this.orderItemList = orderItemProducts;
    }

    @NonNull
    @Override
    public DetailOrderListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_detail_order_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailOrderListAdapter.ViewHolder holder, int position) {
        holder.txtOrderDetailProductName.setText(orderItemList.get(position).getName());
        holder.txtOrderDetailProviderName.setText(orderItemList.get(position).getProvider().getFantasyName());
        holder.txtOrderDetailAddInfo.setText(orderItemList.get(position).getObservations());
        if (isProduct(position)){
            holder.txtOrderDetailManufacturerName.setText(orderItemList.get(position).getManufacturer().getFantasyName());
        } else {
            holder.txtOrderDetailManufacturerName.setVisibility(View.GONE);
        }

    }

    private boolean isProduct(int position){
        if (orderItemList.get(position).getManufacturer() != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtOrderDetailProviderName;
        private TextView txtOrderDetailProductName;
        private TextView txtOrderDetailManufacturerName;
        private TextView txtOrderDetailAddInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtOrderDetailProviderName = itemView.findViewById(R.id.txtOrderDetailProviderName);
            txtOrderDetailProductName = itemView.findViewById(R.id.txtOrderDetailProductName);
            txtOrderDetailManufacturerName = itemView.findViewById(R.id.txtOrderDetailManufacturerName);
            txtOrderDetailAddInfo = itemView.findViewById(R.id.txtOrderDetailAddInfo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v,getPosition());
            }
        }
    }

}
