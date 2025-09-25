package com.tonkar.volleyballreferee.ui.setup;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import com.tonkar.volleyballreferee.R;
import com.tonkar.volleyballreferee.engine.service.StoredGamesManager;
import com.tonkar.volleyballreferee.engine.service.StoredGamesService;
import com.tonkar.volleyballreferee.engine.service.StoredGame;

public class OfficialsSetupFragment extends Fragment {

    private StoredGame game;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_officials_setup, container, false);
        StoredGamesService s = new StoredGamesManager(requireContext());
        game = s.loadSetupGame();
        TextInputEditText lic1 = v.findViewById(R.id.referee1_license_input);
        TextInputEditText lic2 = v.findViewById(R.id.referee2_license_input);
        if (game != null) {
            lic1.setText(game.getReferee1License());
            lic2.setText(game.getReferee2License());
        }
        TextWatcher w = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                if (game != null) {
                    game.setReferee1License(lic1.getText() == null ? "" : lic1.getText().toString());
                    game.setReferee2License(lic2.getText() == null ? "" : lic2.getText().toString());
                }
            }
        };
        lic1.addTextChangedListener(w);
        lic2.addTextChangedListener(w);
        return v;
    }
}
