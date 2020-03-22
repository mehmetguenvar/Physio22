package com.example.mehme.physio22.fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mehme.physio22.BuildConfig;
import com.example.mehme.physio22.Database.entities.Kategorie;
import com.example.mehme.physio22.Database.entities.Uebung;
import com.example.mehme.physio22.R;
import com.example.mehme.physio22.dtos.UebungDTO;
import com.example.mehme.physio22.viewmodels.UebungViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class UebungErstellungFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 9565;
    EditText bezeichnung;
    ImageView imageView;
    UebungViewModel uebungViewModel;
    Uebung uebung = new Uebung();
    Button saveButton;
    SwipeRefreshLayout swr;
    private boolean isFromKategorie;
    private long katId = 0;

    public UebungErstellungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_uebung_erstellung, container, false);

        bezeichnung = v.findViewById(R.id.editTextNameFragmentUebungErstellung);
        imageView = v.findViewById(R.id.imageViewFragmentUebungErstellung);
        saveButton = v.findViewById(R.id.buttonAddUebungFragmentUebungErstellung);
        swr = v.findViewById(R.id.swrFragmentUebungErstellung);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
            }
        });


        swr.setOnRefreshListener(() -> {
            if(uebung.getUebungId()!=0){
                uebungViewModel.getUebungTrue(uebung.getUebungId()).observe(getViewLifecycleOwner(), new Observer<Uebung>() {
                    @Override
                    public void onChanged(Uebung uebungD) {
                        uebung = uebungD;
                        setData();
                        setBinders();
                        swr.setRefreshing(false);
                    }
                });
            }else {
                swr.setRefreshing(false);
            }

        });



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uebung!=null)
                    uebungViewModel.saveUebung(uebung).observe(getViewLifecycleOwner(), kundenDatenDTO1 -> {if(kundenDatenDTO1!=null) {
                        Toast.makeText(getContext(), "Speichern erfolgreich", Toast.LENGTH_LONG).show();
                        uebung = kundenDatenDTO1;
                        setData();
                        setBinders();
                    }
                    else
                        Toast.makeText(getContext(),"Fehler beim speichern",Toast.LENGTH_LONG).show();
                    });
            }
        });
        return v;
    }

    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:

                    Uri selectedImage =  data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    if (selectedImage != null) {
                        Cursor cursor = getContext().getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String picturePath = cursor.getString(columnIndex);

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            BitmapFactory.decodeFile(picturePath).compress(Bitmap.CompressFormat.JPEG,80,stream);
                            byte[] byteArray = stream.toByteArray();

                            uebung.setBild(byteArray);
                            setData();
                            cursor.close();
                        }
                    }

                    break;


            }
    }

    private void setData(){
        bezeichnung.setText(uebung.getBezeichnung());
        if(uebung.getBild()!=null)
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(uebung.getBild(),0,uebung.getBild().length));
    }

    private void setBinders(){
        bezeichnung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                uebung.setBezeichnung(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        uebungViewModel = new ViewModelProvider(getActivity()).get(UebungViewModel.class);

        if(getArguments() != null && getArguments().getLong("UebungId",-1) != -1){
                uebungViewModel.getUebungTrue(getArguments().getLong("UebungId",-1)).observe(getViewLifecycleOwner(), new Observer<Uebung>() {
                    @Override
                    public void onChanged(Uebung uebungD) {
                        uebung = uebungD;
                        if((uebung.getKategories()==null))uebung.setKategories(new HashSet<>());

                        setData();
                        setBinders();
                        swr.setRefreshing(false);
                    }
                });
        }else{
            if(getArguments() != null && getArguments().getLong("KategorieId",-1) != -1 && getArguments().getString("KategorieName")!=null){
                isFromKategorie = true;
                katId = getArguments().getLong("KategorieId",-1);
                HashSet<Kategorie> kats = new HashSet<>();
                Kategorie k = new Kategorie();
                k.setKategorieId(katId);
                kats.add(k);
                uebung.setKategories(kats);
            }
            setData();
            setBinders();
        }
    }
}
