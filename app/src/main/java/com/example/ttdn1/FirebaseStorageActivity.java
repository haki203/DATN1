package com.example.ttdn1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class FirebaseStorageActivity extends AppCompatActivity {
    private Button btnChooseImage;
    private Button btnUploadImage;
    private Button btnDownloadImage;
    private ImageView selectedImage; // Thêm ImageView
    private ImageView downloadImage; // Thêm ImageView
    private Uri imageUri;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);


        setContentView(R.layout.activity_firebase_storage);

        btnChooseImage = findViewById(R.id.btnChooseImage);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        selectedImage = findViewById(R.id.selectedImage); // Liên kết với ImageView trong XML
        btnDownloadImage = findViewById(R.id.btnDownload);
        downloadImage = findViewById(R.id.downloadImage); // Liên kết với ImageView trong XML
        storageReference = FirebaseStorage.getInstance().getReference();

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mở thư viện ảnh để chọn ảnh
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
        btnDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tham chiếu đến tệp ảnh trên Firebase Storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imageRef = storageReference.child("images/image:62"); // Thay đổi đường dẫn tương ứng với tệp ảnh bạn muốn lấy

                // Tải ảnh từ Firebase Storage và hiển thị nó trong ImageView
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(downloadImage);
                        Toast.makeText(FirebaseStorageActivity.this, "Tải ảnh xuống thành công", Toast.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xử lý lỗi và hiển thị Toast tương ứng
                        if (e instanceof StorageException && ((StorageException) e).getErrorCode() == StorageException.ERROR_OBJECT_NOT_FOUND) {
                            // Lỗi: Tệp ảnh không tồn tại
                            Toast.makeText(FirebaseStorageActivity.this, "Không tìm thấy ảnh", Toast.LENGTH_SHORT).show();
                        } else {
                            // Lỗi khác
                            Toast.makeText(FirebaseStorageActivity.this, "Lỗi khi tải ảnh", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tải lên ảnh đã chọn lên Firebase Storage
                if (imageUri != null) {
                    StorageReference imageRef = storageReference.child("images/" + imageUri.getLastPathSegment());
                    imageRef.putFile(imageUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Xử lý thành công
                                    // Lấy URL của ảnh đã tải lên
                                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imageURL = uri.toString();
                                            // Hiển thị ảnh đã chọn trong ImageView
                                            selectedImage.setImageURI(imageUri);
                                            selectedImage.setVisibility(View.VISIBLE); // Hiển thị ImageView
                                            Toast.makeText(FirebaseStorageActivity.this, "Tai anh len thanh cong", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Xử lý lỗi
                                }
                            });
                }
                else {
                    Toast.makeText(FirebaseStorageActivity.this, "Bạn chưa chọn  ảnh", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
        }
    }
}