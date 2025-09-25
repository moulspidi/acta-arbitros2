package com.example.volleyref;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SimpleSignatureDialogFragment extends DialogFragment {

    public interface OnSignatureCaptured { void onSignature(android.graphics.Bitmap bitmap, String name); }
    public static SimpleSignatureDialogFragment newInstance() { return new SimpleSignatureDialogFragment(); }

    @NonNull @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getLayoutInflater().inflate(R.layout.dialog_simple_signature, null);
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.signature)
                .setView(v)
                .setPositiveButton(android.R.string.ok, (d,w) -> {
                    SignatureView view = v.findViewById(R.id.signature_view);
                    if (getParentFragment() instanceof OnSignatureCaptured cb) {
                        cb.onSignature(view.getBitmap(), "");
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }
}
