package com.dryfruithub.ecommerceadmin.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.dryfruithub.ecommerceadmin.R;
import com.dryfruithub.ecommerceadmin.activities.PublishFromSs;
import com.dryfruithub.ecommerceadmin.modelclass.ProductDetails;
import com.dryfruithub.ecommerceadmin.modelclass.ProductsOnFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirebaseOneItemEditAndPublish.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirebaseOneItemEditAndPublish#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirebaseOneItemEditAndPublish extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    Button submit_update;

    FirebaseDatabase database;
    DatabaseReference myRef;
    DocumentReference prod_documentRef;
    FirebaseFirestore firebaseFirestore;
    ProductsOnFirebase readProductDetails;
    ProductDetails publishProduct;

    private StorageReference mStorageRef;

    EditText short_name;
    EditText price;
    EditText discount;
    EditText description;
    EditText quantity_left;
    Spinner isAvailableSp;
    ImageView image1,image2;
    Button edit_image1,edit_image2;
    ProgressBar img_1progress,img_2progress;
    FirebaseAuth mAuth;
    int REQUEST_CODE;

    private OnFragmentInteractionListener mListener;

    public FirebaseOneItemEditAndPublish() {
        // Required empty public constructor
    }

    public static FirebaseOneItemEditAndPublish newInstance(String param1) {
        FirebaseOneItemEditAndPublish fragment = new FirebaseOneItemEditAndPublish();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_item_edit_page, container, false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        myRef = database.getReference("all_products").child(mParam1);

        firebaseFirestore = FirebaseFirestore.getInstance();
        prod_documentRef = firebaseFirestore.collection("all_products").document();

        short_name = view.findViewById(R.id.short_name);
        price = view.findViewById(R.id.price);
        discount = view.findViewById(R.id.discount);
        description = view.findViewById(R.id.description);
        quantity_left = view.findViewById(R.id.quantity_left);
        isAvailableSp = view.findViewById(R.id.isAvailableSp);
        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        submit_update = view.findViewById(R.id.submit_update);
        edit_image1 = view.findViewById(R.id.edit_image1);
        edit_image2 = view.findViewById(R.id.edit_image2);
        img_1progress = view.findViewById(R.id.img_1progress);
        img_2progress = view.findViewById(R.id.img_2progress);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                readProductDetails = dataSnapshot.getValue(ProductsOnFirebase.class);
                short_name.setText(readProductDetails.getP_full_name());
                price.setText(String.valueOf(readProductDetails.getP_price()));
                discount.setText(String.valueOf(readProductDetails.getP_discount()));
                description.setText(readProductDetails.getP_description());
                if (readProductDetails.getP_isavailable()) {
                    isAvailableSp.setSelection(0);
                }
                else isAvailableSp.setSelection(1);
                //quantity_left.setText(productDetails.getP_quantity_left());
                try {
                    Picasso.get()
                            .load(readProductDetails.getP_img_url1())
                            .into(image1);
                }catch (Exception e){}

                try {
                    Picasso.get()
                            .load(readProductDetails.getP_img_url2())
                            .into(image2);
                }catch (Exception e){}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submit_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                publishProduct = new ProductDetails();
                PublishFromSs.myRef.child(mParam1).child("p_isPublished").setValue(true);
                submit_update();
                prod_documentRef
                        .set(publishProduct)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Snackbar snackbar = Snackbar.make(view, "Item Updated", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null);
                                snackbar.getView().setBackgroundColor(Color.GREEN);
                                snackbar.show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar snackbar = Snackbar.make(view, "Error"+e, Snackbar.LENGTH_LONG)
                                        .setAction("Action", null);
                                snackbar.getView().setBackgroundColor(Color.RED);
                                snackbar.show();
                            }
                        });
            }
        });

        edit_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_image1();
            }
        });

        edit_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_image2();
            }
        });

        return view;
    }

    private void edit_image1() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        REQUEST_CODE=1;
        startActivityForResult(intent, REQUEST_CODE);

    }

    private void edit_image2() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        REQUEST_CODE=2;
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            Uri selectedImageURI = data.getData();
            switch (requestCode) {
                case 1:
                    edit_image1.setVisibility(View.INVISIBLE);
                    img_1progress.setVisibility(View.VISIBLE);
                    Picasso.get().load(selectedImageURI).noPlaceholder().centerCrop().fit()
                        .into(image1);
                    uploadImage(selectedImageURI,requestCode);
                    break;

                case 2:
                    edit_image2.setVisibility(View.INVISIBLE);
                    img_2progress.setVisibility(View.VISIBLE);
                    Picasso.get().load(selectedImageURI).noPlaceholder().centerCrop().fit()
                            .into(image2);
                    uploadImage(selectedImageURI,requestCode);
                    break;
            }

        } else {
            Toast.makeText(getActivity(), "Try Again!!", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    private void uploadImage(Uri selectedImageURI, final int imgNum) {
        Uri file = selectedImageURI;
        String uid = FirebaseDatabase.getInstance().getReference().push().getKey();
        StorageReference riversRef = mStorageRef.child("p_images/"+uid);
        StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        assert downloadUrl != null;
                        switch (imgNum) {
                            case 1: {
                                try {
                                    readProductDetails.setP_img_url1(downloadUrl.toString());
                                } catch (Exception e) {
                                    Toast.makeText(getActivity(), "Error:" + e, Toast.LENGTH_SHORT)
                                            .show();
                                }
                                img_1progress.setVisibility(View.GONE);
                                break;
                            }
                            case 2: {
                                try {
                                    readProductDetails.setP_img_url2(downloadUrl.toString());
                                } catch (Exception e) {
                                    Toast.makeText(getActivity(), "Error:" + e, Toast.LENGTH_SHORT)
                                            .show();
                                }
                                img_2progress.setVisibility(View.GONE);
                                break;
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getActivity(), "Image " + REQUEST_CODE + " Upload Error: " + exception, Toast.LENGTH_SHORT)
                                .show();

                    }
                });
    }

    private void submit_update() {

        int spinnerId = isAvailableSp.getSelectedItemPosition();
        switch (spinnerId){
            case 0: publishProduct.setP_isavailable(true);break;
            case 1: publishProduct.setP_isavailable(false);break;
            default: publishProduct.setP_isavailable(false);
        }

        publishProduct.setP_id(prod_documentRef.getId());
        publishProduct.setP_name(short_name.getText().toString());
        publishProduct.setP_price(Integer.parseInt(price.getText().toString()));
        publishProduct.setP_discount(Integer.parseInt(discount.getText().toString()));
        publishProduct.setP_description(description.getText().toString());
        publishProduct.setP_qty_per_unit(readProductDetails.getP_qty_per_unit());
        publishProduct.setP_ranking(readProductDetails.getP_ranking());
        publishProduct.setP_size(readProductDetails.getP_size());
        publishProduct.setP_visit_count(readProductDetails.getP_visit_count());

        ArrayList<String> imageList = new ArrayList<>();
        if (!(readProductDetails.getP_img_url1().length() <1)){
            imageList.add(readProductDetails.getP_img_url1());
        }
        if (!(readProductDetails.getP_img_url2().length() <1)) {
            imageList.add(readProductDetails.getP_img_url2());
        }
        if (!(readProductDetails.getP_img_url3().length() <1)) {
            imageList.add(readProductDetails.getP_img_url3());
        }
        if (!(readProductDetails.getP_img_url4().length() <1)) {
            imageList.add(readProductDetails.getP_img_url4());
        }
        publishProduct.setP_images(imageList);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
