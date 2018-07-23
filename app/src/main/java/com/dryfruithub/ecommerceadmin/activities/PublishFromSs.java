package com.dryfruithub.ecommerceadmin.activities;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dryfruithub.ecommerceadmin.R;
import com.dryfruithub.ecommerceadmin.adapters.ProductsAdapter;
import com.dryfruithub.ecommerceadmin.adapters.UnpublishedFbAdapter;
import com.dryfruithub.ecommerceadmin.fragment.FirebaseOneItemEditAndPublish;
import com.dryfruithub.ecommerceadmin.fragment.SingleItemEditPage;
import com.dryfruithub.ecommerceadmin.helperclass.RecyclerItemClickListener;
import com.dryfruithub.ecommerceadmin.modelclass.ProductDetails;
import com.dryfruithub.ecommerceadmin.modelclass.ProductsOnFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.dryfruithub.ecommerceadmin.adapters.ProductsAdapter.prodList;

public class PublishFromSs extends AppCompatActivity implements SingleItemEditPage.OnFragmentInteractionListener,
FirebaseOneItemEditAndPublish.OnFragmentInteractionListener{

    FirebaseDatabase database;
    public static DatabaseReference myRef;
    ProductsOnFirebase productsOnFirebase;
    ArrayList<ProductsOnFirebase> unPublishedList = new ArrayList<>();
    UnpublishedFbAdapter unpublishedFbAdapter;

    RecyclerView rvUnpublished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_from_ss);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("all_products");

        rvUnpublished = findViewById(R.id.rvUnpublished);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvUnpublished.setLayoutManager(layoutManager);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                unPublishedList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    productsOnFirebase = new ProductsOnFirebase();
                    if(!(Boolean) postSnapshot.child("p_isPublished").getValue()){
                        productsOnFirebase = postSnapshot.getValue(ProductsOnFirebase.class);
                        unPublishedList.add(productsOnFirebase);
                    }
                }
                unpublishedFbAdapter = new UnpublishedFbAdapter(getApplicationContext(),
                        unPublishedList);
                rvUnpublished.setAdapter(unpublishedFbAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rvUnpublished.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(), rvUnpublished, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    Fragment fragment = FirebaseOneItemEditAndPublish.
                            newInstance(String.valueOf(unPublishedList.get(position).getP_id()));
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
