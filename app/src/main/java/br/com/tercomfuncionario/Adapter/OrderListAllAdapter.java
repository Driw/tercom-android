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

public class OrderListAllAdapter extends RecyclerView.Adapter<OrderListAllAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<OrderRequest> orderRequests;
    private Context context;

    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public void setmRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack){
        this.mRecyclerViewOnClickListenerHack = mRecyclerViewOnClickListenerHack;
    }

    public OrderListAllAdapter (Context c, ArrayList<OrderRequest> orderRequests){
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
        holder.txtOrderDetailListRequesterName.setText(orderRequests.get(position).getCustomerEmployee().getName());
        holder.txtOrderDetailListCompanyName.setVisibility(View.GONE);
        holder.txtOrderDetailListStatus.setText(String.valueOf(orderRequests.get(position).getStatusMessage()));
        if (isOrderOpen(position)){
            holder.txtOrderDetailListReceivedBy.setVisibility(View.GONE);
        } else {
            holder.txtOrderDetailListReceivedBy.setText(orderRequests.get(position).getTercomEmployee().getName());
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
