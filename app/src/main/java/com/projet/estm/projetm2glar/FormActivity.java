package com.projet.estm.projetm2glar;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class FormActivity extends AppCompatActivity {



    //champ pour l'ajout
    EditText nom,categorie,descript,prix;
    ImageView img;
    Button btnChoose;

    final int REQUEST_CODE_GALLERY = 999;
    private static final int SELECTED_PIC = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        btnChoose = (Button) findViewById(R.id.btnChoose);
        img = (ImageView) findViewById(R.id.edImage);
    }
    public void addProduit(){

        nom = (EditText) findViewById(R.id.editName);
        categorie = (EditText) findViewById(R.id.editCat);
        descript = (EditText) findViewById(R.id.edDescript);
        prix = (EditText) findViewById(R.id.edPrix);
        img = (ImageView) findViewById(R.id.edImage);


//        dbHelper.addProduit(new Produit(nom.getText().toString(),categorie.getText().toString(),descript.getText().toString(),true,Integer.parseInt(prix.getText().toString()),imageViewToByte(img)));
    }
//    public static byte[] imageViewToByte(ImageView image) {
//        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//        return byteArray;
//    }
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    switch (requestCode) {
        case SELECTED_PIC:
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                String i=uri.getLastPathSegment();
                String[] projection = {MediaStore.Images.Media.DATA};

                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    img.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            break;
        default:
            break;
    }

    super.onActivityResult(requestCode, resultCode, data);
}
public void choisirImage(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECTED_PIC);


//        btnChoose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get the image from drawable resource as drawable object
//                Drawable drawable = getDrawable(R.drawable.apple_pie);
//                // Get the bitmap from drawable object
//                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
//                // Initializing a new file
//                File file;
//                // Get the external storage directory path
//                String path = Environment.getExternalStorageDirectory().toString();
//                // Create a file to save the image
//                file = new File(path, "UniqueFileName"+".jpg");
//
//                try{
//                    OutputStream stream = null;
//
//                    stream = new FileOutputStream(file);
//                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
//
//                    stream.flush();
//
//                    stream.close();
//
//                }catch (IOException e) // Catch the exception
//                {
//                    e.printStackTrace();
//                }
//
//                // Parse the saved image path to uri
//                Uri savedImageURI = Uri.parse(file.getAbsolutePath());
//
//                // Display the saved image to ImageView
//                iv_saved.setImageURI(savedImageURI);
//
//                // Display saved image uri to TextView
//                tv_saved.setText("Image saved in external storage.\n" + savedImageURI);
//            }
//        });
    }
}
