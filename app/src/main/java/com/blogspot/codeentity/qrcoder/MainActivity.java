package com.blogspot.codeentity.qrcoder;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button generateButton , scanButton;
    private ImageView image;
    private EditText myTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateButton = findViewById(R.id.genbtn);
        scanButton = findViewById(R.id.scanbtn);
        myTXT = findViewById(R.id.mytxt);
        image= findViewById(R.id.imageview);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = myTXT.getText().toString();
                if (text.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter product name first",Toast.LENGTH_SHORT).show();
                }
                else {
                    try{


                    MultiFormatWriter multiFormatWriter=  new MultiFormatWriter();
                    BitMatrix bitMatrix =multiFormatWriter.encode(text,BarcodeFormat.QR_CODE,250,250);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }

                }
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("Scanning");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();
            }
        });
    }
}
