package com.example.qrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.barcode.Barcode;


public class MainActivity extends AppCompatActivity {
//    ImageView imageView;
//    TextView tvOne,tvTwo;
//    Button btScane;
//    Context context;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Anhxa();
//        Control1();
//    }
//
//    private void Control1() {
//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
//        integrator .setPrompt("chao banj");
//        integrator.setCameraId(0);
//        // beep khi scan qr thành công
//        integrator.setBeepEnabled(true);
//        integrator.setBarcodeImageEnabled(false);
//        integrator.initiateScan();
//    }
//    public void onActivityResult(int requestCode, int resultcode, Intent intent) {
//        super.onActivityResult(requestCode, resultcode, intent);
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultcode, intent);
//        if (result != null) {
//            String contents = result.getContents();
//
//            tvOne.setText(contents);
//            // lấy hiệu ứng rung khi scan thành công.
//            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//            // SET RUNG 400 MILLISECONDS
//            v.vibrate(400);
//        }
//    }
//
//    private void Anhxa() {
//        imageView=findViewById(R.id.IvAnh);
//        tvOne=findViewById(R.id.tvMot);
//        tvTwo=findViewById(R.id.tvHai);
//        btScane=findViewById(R.id.buttonScan);
//    }
Button scanbtn;
    TextView result;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanbtn = (Button) findViewById(R.id.buttonScan);
        result = (TextView) findViewById(R.id.tvMot);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Scanner.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                final Barcode barcode = data.getParcelableExtra("barcode");
                result.post(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(barcode.displayValue);
                    }
                });
            }
        }
    }
}
