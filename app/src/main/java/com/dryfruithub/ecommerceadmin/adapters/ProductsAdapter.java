package com.dryfruithub.ecommerceadmin.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dryfruithub.ecommerceadmin.R;
import com.dryfruithub.ecommerceadmin.modelclass.ProductDetails;
import com.dryfruithub.ecommerceadmin.modelclass.ProductsOnFirebase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    public static ArrayList<ProductDetails> prodList = new ArrayList<>();

    public ProductsAdapter(Context applicationContext, ArrayList<ProductDetails> prodList) {
        this.context = applicationContext;
        this.prodList = prodList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_product_card, parent, false);

        return new ProductsAdapter.ProductViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final ProductViewHolder productViewHolder = (ProductViewHolder)holder;
        ProductDetails productDetails = prodList.get(position);

        final int discount,max_price;
        final String p_id;
        float discounted_price;
        discount = productDetails.getP_discount();
        max_price = productDetails.getP_price();

        if (discount!=0){
            discounted_price = (float) (max_price*(1.0-(discount/100.0)));
            productViewHolder.price_after_disc.setText("₹"+String.valueOf(((int) discounted_price)));
            productViewHolder.prod_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            productViewHolder.prod_price.setGravity(Gravity.RIGHT);
        }

        productViewHolder.prod_name.setText(productDetails.getP_name());
        productViewHolder.prod_discount.setText(String.valueOf(discount));
        productViewHolder.prod_discount.append("% OFF");
        productViewHolder.prod_price.setText(String.valueOf("₹"+max_price));
        if (productDetails.isP_isavailable()){
        productViewHolder.isAvailable.setText("Available");
        }
        else productViewHolder.isAvailable.setText("Unavailable");
        Picasso.get()
                .load(productDetails.getP_images().get(0))
                .into(productViewHolder.productImg);
        productViewHolder.edit_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Add Item Clicked.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return prodList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        TextView prod_name,prod_price,prod_discount,price_after_disc,isAvailable;
        LinearLayout discount_lin_lay,prod_layout;
        Button edit_item;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            prod_name = itemView.findViewById(R.id.prod_name);
            productImg = itemView.findViewById(R.id.product_img);
            prod_price = itemView.findViewById(R.id.prod_price);
            prod_discount = itemView.findViewById(R.id.prod_discount);
            discount_lin_lay = itemView.findViewById(R.id.disc_lin_lay);
            price_after_disc = itemView.findViewById(R.id.price_after_disc);
            isAvailable = itemView.findViewById(R.id.isAvailable);
            edit_item = itemView.findViewById(R.id.edit_item);
            prod_layout = itemView.findViewById(R.id.prod_layout);

            LinearLayout prod_lin_layout = itemView.findViewById(R.id.prod_lin_layout);

            discount_lin_lay.bringToFront();
        }
    }
}
