package com.tonkar.volleyballreferee.ui.team;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.tonkar.volleyballreferee.R;
import com.tonkar.volleyballreferee.engine.api.model.*;
import com.tonkar.volleyballreferee.engine.service.IStoredGame;
import com.tonkar.volleyballreferee.engine.service.StoredGamesManager;
import com.tonkar.volleyballreferee.engine.service.StoredGamesService;
import com.tonkar.volleyballreferee.engine.team.TeamType;

import java.util.*;

public class StaffSetupFragment extends Fragment {

    private TeamType teamType;
    private IStoredGame game;
    private ArrayAdapter<String> adapter;
    private List<StaffMemberDto> staff;

    public static StaffSetupFragment newInstance(TeamType teamType) {
        StaffSetupFragment f = new StaffSetupFragment();
        Bundle b = new Bundle();
        b.putString(TeamType.class.getName(), teamType.toString());
        f.setArguments(b);
        return f;
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_staff_setup, container, false);
        teamType = TeamType.valueOf(requireArguments().getString(TeamType.class.getName()));
        StoredGamesService s = new StoredGamesManager(requireContext());
        game = s.loadSetupGame();
        TeamDto team = teamType == TeamType.HOME ? game.getHomeTeam() : game.getGuestTeam();
        if (team.getStaff() == null) team.setStaff(new ArrayList<>());
        staff = team.getStaff();
        ListView list = v.findViewById(R.id.staff_list);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        refresh();
        FloatingActionButton add = v.findViewById(R.id.add_staff_btn);
        add.setOnClickListener(view -> showAddDialog());
        list.setOnItemLongClickListener((parent, view1, position, id) -> { staff.remove(position); refresh(); return true; });
        return v;
    }

    private void refresh() {
        adapter.clear();
        for (StaffMemberDto s : staff) adapter.add(s.getRole()+": "+s.getName()+(s.getLicense()==null||s.getLicense().isEmpty()?"":" (#"+s.getLicense()+")"));
        adapter.notifyDataSetChanged();
    }

    private void showAddDialog() {
        View d = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_staff, null);
        TextInputEditText role = d.findViewById(R.id.input_role);
        TextInputEditText name = d.findViewById(R.id.input_name);
        TextInputEditText lic  = d.findViewById(R.id.input_license);
        new androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle(R.string.add_staff_member)
            .setView(d)
            .setPositiveButton(android.R.string.ok, (di, w) -> {
                StaffMemberDto sm = new StaffMemberDto();
                sm.setRole(role.getText()==null?"":role.getText().toString());
                sm.setName(name.getText()==null?"":name.getText().toString());
                sm.setLicense(lic.getText()==null?"":lic.getText().toString());
                staff.add(sm);
                refresh();
            })
            .setNegativeButton(android.R.string.cancel, null)
            .show();
    }
}
