package com.tonkar.volleyballreferee.ui.setup;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.*;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.tonkar.volleyballreferee.R;
import com.tonkar.volleyballreferee.engine.service.StoredGame;
import com.tonkar.volleyballreferee.engine.service.StoredGamesManager;
import com.tonkar.volleyballreferee.engine.service.StoredGamesService;
import java.io.ByteArrayOutputStream;

public class PreMatchSignaturesFragment extends Fragment implements SimpleSignatureDialogFragment.OnSignatureCaptured {

    private StoredGame game;
    private int target; // 0..3

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_prematch_signatures, container, false);
        StoredGamesService s = new StoredGamesManager(requireContext());
        game = s.loadSetupGame();
        int[] btnIds = {R.id.btn_home_captain, R.id.btn_home_coach, R.id.btn_guest_captain, R.id.btn_guest_coach};
        for (int i=0;i<btnIds.length;i++) {
            final int idx=i;
            Button b = v.findViewById(btnIds[i]);
            b.setOnClickListener(view -> { target = idx; SimpleSignatureDialogFragment.newInstance().show(getChildFragmentManager(), "sig");});
        }
        return v;
    }

    @Override public void onSignature(Bitmap bmp, String name) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        String base64 = Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP);
        switch (target) {
            case 0 -> game.setHomeCaptainSignature(base64);
            case 1 -> game.setHomeCoachSignature(base64);
            case 2 -> game.setGuestCaptainSignature(base64);
            case 3 -> game.setGuestCoachSignature(base64);
        }
    }
}
