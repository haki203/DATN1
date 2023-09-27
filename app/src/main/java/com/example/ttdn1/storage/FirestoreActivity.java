package com.example.ttdn1.storage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.os.Bundle;

import com.example.ttdn1.R;
import com.google.firebase.FirebaseApp;


public class FirestoreActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST = 1;
    private Button selectButton;
    private StorageReference storageReference;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore);
        FirebaseApp.initializeApp(this);
        storageReference = FirebaseStorage.getInstance().getReference();
        selectButton = findViewById(R.id.selectButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilePicker();
            }
        });

    }
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Tất cả các loại tệp
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri fileUri = data.getData();
            uploadFile(fileUri);
        }
    }
    private void uploadFile(Uri fileUri) {
        if (fileUri != null) {
            StorageReference fileRef = storageReference.child("uploads/" + fileUri.getLastPathSegment());

            fileRef.putFile(fileUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Xử lý khi tải lên thành công
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Xử lý khi tải lên thất bại
                        }
                    });
        }
    }

}