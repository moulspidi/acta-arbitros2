package com.example.volleyref;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import java.io.ByteArrayOutputStream;

public class PreMatchSignaturesActivity extends AppCompatActivity implements SimpleSignatureDialogFragment.OnSignatureCaptured {
    private int target=0;
    private String homeCaptain, homeCoach, guestCaptain, guestCoach;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prematch_signatures);
        int[] ids = {R.id.btn_home_captain, R.id.btn_home_coach, R.id.btn_guest_captain, R.id.btn_guest_coach};
        for (int i=0;i<ids.length;i++) {
            final int idx = i;
            Button b = findViewById(ids[i]);
            b.setOnClickListener(v -> { target = idx; SimpleSignatureDialogFragment.newInstance().show(getSupportFragmentManager(), "sig"); });
        }
    }

    @Override public void onSignature(Bitmap bmp, String name) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        String base64 = Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP);
        switch (target) {
            case 0 -> homeCaptain = base64;
            case 1 -> homeCoach   = base64;
            case 2 -> guestCaptain= base64;
            case 3 -> guestCoach  = base64;
        }
    }
}
