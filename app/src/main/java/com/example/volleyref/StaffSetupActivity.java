package com.example.volleyref;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.*;

public class StaffSetupActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private ArrayList<StaffMember> staff = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_setup);
        ListView list = findViewById(R.id.staff_list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        findViewById(R.id.add_staff_btn).setOnClickListener(v -> showAddDialog());
        list.setOnItemLongClickListener((a, v, pos, id) -> { staff.remove(pos); refresh(); return true; });
    }

    private void refresh() {
        adapter.clear();
        for (StaffMember s : staff) adapter.add(s.toString());
        adapter.notifyDataSetChanged();
    }

    private void showAddDialog() {
        View d = LayoutInflater.from(this).inflate(R.layout.dialog_add_staff, null);
        TextInputEditText role = d.findViewById(R.id.input_role);
        TextInputEditText name = d.findViewById(R.id.input_name);
        TextInputEditText lic  = d.findViewById(R.id.input_license);
        new AlertDialog.Builder(this)
            .setTitle(R.string.add_staff_member)
            .setView(d)
            .setPositiveButton(android.R.string.ok, (di, w) -> {
                StaffMember sm = new StaffMember();
                sm.role = role.getText()==null?"":role.getText().toString();
                sm.name = name.getText()==null?"":name.getText().toString();
                sm.license = lic.getText()==null?"":lic.getText().toString();
                staff.add(sm);
                refresh();
            })
            .setNegativeButton(android.R.string.cancel, null)
            .show();
    }
}
