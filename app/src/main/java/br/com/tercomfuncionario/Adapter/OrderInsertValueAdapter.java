package br.com.tercomfuncionario.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.tercomfuncionario.Entity.OrderItemProduct;
import br.com.tercomfuncionario.Interface.RecyclerViewOnClickListenerHack;

public class OrderInsertValueAdapter extends RecyclerView.Adapter<OrderInsertValueAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<OrderItemProduct> orderItemProducts;
    private Context context;

    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public void setmRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack) {
        this.mRecyclerViewOnClickListenerHack = mRecyclerViewOnClickListenerHack;
    }

    @Override
    public OrderInsertValueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderInsertValueAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderItemProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }
        }

    }
}
