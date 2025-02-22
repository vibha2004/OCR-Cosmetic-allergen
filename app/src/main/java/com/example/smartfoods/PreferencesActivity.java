package com.example.smartfoods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity {

    CheckBox paraben;
    CheckBox fragrance;
    CheckBox additive;
    CheckBox sulphate;
    CheckBox lanolin;
    CheckBox metal;
    CheckBox pore;
    CheckBox sensitive;
    CheckBox organic;
    CheckBox cruelty;
    Button apply;
    private String preferenceSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        preferenceSelection = getIntent().getExtras().getString("preferences");
        paraben = (CheckBox) findViewById(R.id.Paraben);
        fragrance = (CheckBox) findViewById(R.id.Fragrance);
        additive = (CheckBox) findViewById(R.id.Additive);
        sulphate = (CheckBox) findViewById(R.id.Sulphate);
        lanolin = (CheckBox) findViewById(R.id.Lanolin);
        metal = (CheckBox) findViewById(R.id.Metal);
        pore = (CheckBox) findViewById(R.id.Pore);
        sensitive = (CheckBox) findViewById(R.id.Sensitive);
        organic = (CheckBox) findViewById(R.id.Organic);
        cruelty = (CheckBox) findViewById(R.id.Cruelty);

        apply = (Button) findViewById(R.id.Apply);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Preferences have been saved.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                preferenceSelection = getPreferenceString();
            }
        });

        checkPreviousPreferences();
    }

    private void checkPreviousPreferences() {
        if (preferenceSelection.charAt(0) == '1') {
            paraben.setChecked(true);
        }

        if (preferenceSelection.charAt(1) == '1') {
            fragrance.setChecked(true);
        }

        if (preferenceSelection.charAt(2) == '1') {
            additive.setChecked(true);
        }

        if (preferenceSelection.charAt(3) == '1') {
            sulphate.setChecked(true);
        }

        if (preferenceSelection.charAt(4) == '1') {
            lanolin.setChecked(true);
        }

        if (preferenceSelection.charAt(5) == '1') {
            metal.setChecked(true);
        }

        if (preferenceSelection.charAt(6) == '1') {
            pore.setChecked(true);
        }

        if (preferenceSelection.charAt(7) == '1') {
            sensitive.setChecked(true);
        }

        if (preferenceSelection.charAt(8) == '1') {
            organic.setChecked(true);
        }

        if (preferenceSelection.charAt(9) == '1') {
            cruelty.setChecked(true);
        }
    }

    private String getPreferenceString() {
        StringBuilder sb = new StringBuilder();

        if (paraben.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (fragrance.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (additive.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (sulphate.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (lanolin.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (metal.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (pore.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (sensitive.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (organic.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }


        if (cruelty.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }
        return sb.toString();
    }

    public void onBackPressed() {
        Intent i = new Intent(PreferencesActivity.this, MainActivity.class);
        i.putExtra("preferences", preferenceSelection);
        startActivity(i);
        finish();
    }
}
