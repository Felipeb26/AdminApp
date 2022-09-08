package com.bats.admin.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bats.admin.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Settings extends Fragment {

    ImageView qrCode;
    TextView linkApk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initComponents(view);
        generateQrCode();
        activeLink();
        return view;
    }

    private void initComponents(View view) {
        qrCode = view.findViewById(R.id.qrcode);
        linkApk = view.findViewById(R.id.linkApk);
    }

    private void generateQrCode() {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = writer.encode(getString(R.string.link_apk), BarcodeFormat.QR_CODE, 400, 400);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void activeLink(){
//        linkApk.setText("https://docs.google.com/uc?export=dowload&id=15__eudQNaCGenr2nAIqeSU-4jHdjcCMn");
        linkApk.setMovementMethod(LinkMovementMethod.getInstance());
    }

}