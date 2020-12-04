package com.example.app20112020;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class activity_1_post_login extends AppCompatActivity {

    EditText edPostName, edPostAnimal;
    Button BtnPotGo;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public Uri imguri;

    Button ch, up;
    ImageView img;
    StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_post_login);
        edPostName = findViewById(R.id.edPostName);
        edPostAnimal = findViewById(R.id.edPostAnimal);
        BtnPotGo = findViewById(R.id.BtnPotGo);

        ch = findViewById(R.id.btnchoose);
        up = findViewById(R.id.btnupload);
        img = findViewById(R.id.imgview);


        mStorageRef = FirebaseStorage.getInstance().getReference("Images");

        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filechooser();
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fileuploader();
            }
        });

        onClickers();

    }

    private String getExtension(Uri uri) {

        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    public void Fileuploader() {

        StorageReference Ref = mStorageRef.child(System.currentTimeMillis() + "." + getExtension(imguri));

        Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(activity_1_post_login.this, "Image uploaded", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });


    }

    public void filechooser() {

        Intent intent = new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
            img.setImageURI(imguri);
        }
    }


    public void onClickers(){
        BtnPotGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edPostName.getText().toString().trim();
                String animalE = edPostAnimal.getText().toString().trim();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("animale");
               AnimaleHelperClass animal = new AnimaleHelperClass(name,animalE);


                reference.setValue(animal);
            }
        });


    }
}