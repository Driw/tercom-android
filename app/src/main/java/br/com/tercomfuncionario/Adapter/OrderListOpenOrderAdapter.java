package br.com.tercomfuncionario.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.tercomfuncionario.Entity.OrderRequest;
import br.com.tercomfuncionario.Interface.RecyclerViewOnClickListenerHack;
import br.com.tercomfuncionario.R;

public class OrderListOpenOrderAdapter extends RecyclerView.Adapter<OrderListOpenOrderAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<OrderRequest> orderRequests;
    private Context context;

    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public void setmRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack){
        this.mRecyclerViewOnClickListenerHack = mRecyclerViewOnClickListenerHack;
    }

    public OrderListOpenOrderAdapter (Context c, ArrayList<OrderRequest> orderRequests){
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = c;
        this.orderRequests = orderRequests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_order_request_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (isOrderOpen(position)){
            holder.txtOrderDetailListRequesterName.setText(orderRequests.get(position).getCustomerEmployee().getName());
            holder.txtOrderDetailListStatus.setText(String.valueOf(orderRequests.get(position).getStatus()));
            holder.txtOrderDetailListReceivedBy.setVisibility(View.GONE);
        } else {
            holder.txtOrderDetailListRequesterName.setVisibility(View.GONE);
            holder.txtOrderDetailListCompanyName.setVisibility(View.GONE);
            holder.txtOrderDetailListReceivedBy.setVisibility(View.GONE);
            holder.txtOrderDetailListStatus.setVisibility(View.GONE);
        }
    }

    private boolean isOrderOpen(int position){
        if(orderRequests.get(position).getStatus() < 4){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return orderRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtOrderDetailListRequesterName;
        private TextView txtOrderDetailListCompanyName;
        private TextView txtOrderDetailListReceivedBy;
        private TextView txtOrderDetailListStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            txtOrderDetailListRequesterName = itemView.findViewById(R.id.txtOrderDetailListRequesterName);
            txtOrderDetailListCompanyName = itemView.findViewById(R.id.txtOrderDetailListCompanyName);
            txtOrderDetailListReceivedBy = itemView.findViewById(R.id.txtOrderDetailListReceivedBy);
            txtOrderDetailListStatus = itemView.findViewById(R.id.txtOrderDetailListStatus);
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
