package com.dryfruithub.ecommerceadmin.activities;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.dryfruithub.ecommerceadmin.R;
import com.dryfruithub.ecommerceadmin.adapters.ProductsAdapter;
import com.dryfruithub.ecommerceadmin.fragment.SingleItemEditPage;
import com.dryfruithub.ecommerceadmin.helperclass.RecyclerItemClickListener;
import com.dryfruithub.ecommerceadmin.modelclass.BannerDetails;
import com.dryfruithub.ecommerceadmin.modelclass.ProductDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EditItems extends AppCompatActivity implements SingleItemEditPage.OnFragmentInteractionListener{

    RecyclerView rvEditItems;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference,prod_collectionRef;
    ArrayList<BannerDetails> addBannerList = new ArrayList<>();
    public static ArrayList<ProductDetails> allProdList = new ArrayList<>();
    public static int screen_width;
    ProductsAdapter productsAdapter;
    static FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        rvEditItems = findViewById(R.id.rvEditItems);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        /*LinearLayoutManager layoutManagerVert

                =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);*/

        rvEditItems.setLayoutManager(layoutManager);

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("add_banners");
        prod_collectionRef = firebaseFirestore.collection("all_products");
        screen_width = displayMetrics.widthPixels;

        prod_collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot dRef:task.getResult()){
                        ProductDetails productDetails = dRef.toObject(ProductDetails.class);
                        allProdList.add(productDetails);
                    }
                    productsAdapter = new ProductsAdapter(getApplication(), allProdList);
                    rvEditItems.setAdapter(productsAdapter);
                }
            }
        });

        rvEditItems.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(), rvEditItems, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = SingleItemEditPage.newInstance(ProductsAdapter.prodList.get(position).getP_id());
                transaction.add(R.id.container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }
        ));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
