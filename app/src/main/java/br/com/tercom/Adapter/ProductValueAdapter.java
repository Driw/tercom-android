package br.com.tercom.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.tercom.Entity.ProductValue;
import br.com.tercom.R;

public class ProductValueAdapter extends RecyclerView.Adapter<ProductValueAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private ArrayList<ProductValue> productValues;
    private Context context;

    public ProductValueAdapter(Context context, ArrayList<ProductValue> productValues)
    {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.productValues = productValues;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_productvalue, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductValueAdapter.ViewHolder holder, int position) {
        holder.txtNome.setText(productValues.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return productValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtNome;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome_ProductValue);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
