package com.example.bookdonation.DonoteABook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookdonation.MainModuleActivity;
import com.example.bookdonation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class DonoteABookActivity extends AppCompatActivity {


    private ImageView BookImage;
    private  EditText NameofStudent,MobileNumber,Email,City,NameofBook;
    private Spinner BookGenre;
    private Button Submit;
    private TextView BookDonating;

    MaterialCardView materialCardView;

    private DatabaseReference reference,dbRef;
    private StorageReference storageReference;

     final int REQ = 1;
     Bitmap bitmap;
    private ProgressDialog pd;
    private String NameofStudent1,MobileNumber1,EMail1,City1,NameofBook1,downloadUrl = "img";

    String bookGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donote_abook);
//
//        BookDonating=findViewById(R.id.BookDonating);
//        BookDonating.setSelected(true);

        pd=new ProgressDialog(this);

        BookImage=findViewById(R.id.BookImage);
        NameofStudent=findViewById(R.id.NameofStudent);
        MobileNumber=findViewById(R.id.MobileNumber);
        Email=findViewById(R.id.Email);
        City=findViewById(R.id.City);
        NameofBook=findViewById(R.id.NameofBook);
        BookGenre=findViewById(R.id.BookGenre);
        Submit=findViewById(R.id.Submit);
        materialCardView=findViewById(R.id.materialCardView);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });

        materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });

        storageReference = FirebaseStorage.getInstance().getReference();
        reference= FirebaseDatabase.getInstance().getReference("BookDonation");

        String[] itemBookGenre = new String[]{"Select Book Genre","Literary Fiction","Mystery","Thriller","Horror",
        "Historical","Romance","Western","Bildungsroman","Speculative Fiction","Fantasy","Dystopian","Realist Literature"};
        BookGenre.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,itemBookGenre));

        BookGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bookGenre=BookGenre.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void checkValidation() {

        NameofStudent1=NameofStudent.getText().toString();
        MobileNumber1=MobileNumber.getText().toString();
        EMail1=Email.getText().toString();
        City1=City.getText().toString();
        NameofBook1=NameofBook.getText().toString();


        if (bitmap==null){
            Toast.makeText(DonoteABookActivity.this,"Set BookImage",Toast.LENGTH_SHORT).show();
        }
        else if (NameofStudent1.isEmpty()){
            NameofStudent.setError("Name Empty");
            NameofStudent.requestFocus();
        }else if (MobileNumber1.isEmpty()){
            MobileNumber.setError("Mobile Number Empty");
            MobileNumber.requestFocus();
        }else if (MobileNumber.length()<10){
            Toast.makeText(DonoteABookActivity.this,"Mobile Number \nOnly 10Numbers" , Toast.LENGTH_SHORT).show();
        }else if (MobileNumber.length()>10){
            Toast.makeText(DonoteABookActivity.this,"Mobile Number \nOnly 10Numbers" , Toast.LENGTH_SHORT).show();
        }else if (EMail1.isEmpty()){
            Email.setError("Email Empty");
            Email.requestFocus();
        }else if (City1.isEmpty()){
            City.setError("City Empty");
            City.requestFocus();
        }else if (NameofBook1.isEmpty()){
            NameofBook.setError("Name of BookName Empty");
            NameofBook.requestFocus();
        }else if (bookGenre.equals("Select Book Genre")){
            Toast.makeText(DonoteABookActivity.this,"Select Book Genre",Toast.LENGTH_SHORT).show();
        }
        else{
            uploadImage();
        }

    }

    private void uploadImage() {
        pd.setMessage(NameofStudent1+"\nPlease Wait..."+"\nYour Book is Uploading");
        pd.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();

        final StorageReference filePath;
        filePath=storageReference.child("BookDonation").child(NameofStudent1+" "+finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(DonoteABookActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    insertDate();
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(DonoteABookActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertDate() {

        dbRef=reference.child(bookGenre);
        String UniqueKey = dbRef.push().getKey();

        HashMap<String, Object> hashMap1 = new HashMap<>();
        hashMap1.put("UniqueKey",UniqueKey);
        hashMap1.put("NameofStudent",NameofStudent1);
        hashMap1.put("MobileNumber",MobileNumber1);
        hashMap1.put("Email",EMail1);
        hashMap1.put("City",City1);
        hashMap1.put("NameofBook",NameofBook1);
        hashMap1.put("BookGenre",bookGenre);
        hashMap1.put("BookImage",downloadUrl);

        dbRef.child(UniqueKey).setValue(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast toast;
                toast=Toast.makeText(DonoteABookActivity.this,"Book Donated Successfully",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();

                Intent intent = new Intent(DonoteABookActivity.this, MainModuleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DonoteABookActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent picImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picImage,REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ && resultCode == RESULT_OK) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BookImage.setImageBitmap(bitmap);
        }
    }
}