package com.projet.estm.projetm2glar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.projet.estm.projetm2glar.database.DBHelper;
import com.projet.estm.projetm2glar.model.Produit;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FormActivity extends AppCompatActivity {
    //champ pour l'ajout
    EditText nom,categorie,descript,prix;
    ImageView img;
    Button btnChoose;
    DBHelper dbHelper;

    private static final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        recupererElement();
        dbHelper = new DBHelper(this);

    }
    public void addProduit(View view){
        try{
        dbHelper.addProduit(new Produit(nom.getText().toString(),categorie.getText().toString(),descript.getText().toString(),true,Integer.parseInt(prix.getText().toString()),imageViewToByte(img)));
        Toast.makeText(this, "Added successfully!", Toast.LENGTH_SHORT).show();

        nom.setText("");
        prix.setText("");
        descript.setText("");
        categorie.setText("");
        img.setImageResource(R.drawable.ic_launcher_background);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    switch (requestCode) {
        case REQUEST_CODE_GALLERY:
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();

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
         startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }
    private void recupererElement() {

        nom = (EditText) findViewById(R.id.editName);
        categorie = (EditText) findViewById(R.id.editCat);
        descript = (EditText) findViewById(R.id.edDescript);
        prix = (EditText) findViewById(R.id.edPrix);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        img = (ImageView) findViewById(R.id.edImage);
    }
}

