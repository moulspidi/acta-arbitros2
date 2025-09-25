package com.tonkar.volleyballreferee.ui.setup;

import android.app.Dialog;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.tonkar.volleyballreferee.R;

public class SimpleSignatureDialogFragment extends DialogFragment {

    public interface OnSignatureCaptured { void onSignature(Bitmap bitmap, String name); }

    public static SimpleSignatureDialogFragment newInstance() { return new SimpleSignatureDialogFragment(); }

    @NonNull @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getLayoutInflater().inflate(R.layout.dialog_simple_signature, null);
        SignatureView view = v.findViewById(R.id.signature_view);
        return new AlertDialog.Builder(requireContext())
            .setTitle(R.string.signature)
            .setView(v)
            .setPositiveButton(android.R.string.ok, (d,w) -> {
                if (getParentFragment() instanceof OnSignatureCaptured cb) {
                    cb.onSignature(view.getBitmap(), "");
                }
            })
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    }
}
