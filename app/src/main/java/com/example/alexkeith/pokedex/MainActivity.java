package com.example.alexkeith.pokedex;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String POKEMON_NAME = "pokemon_name";

    @BindView(R.id.input_pokemon_edittext)
    protected TextInputEditText pokemonNameEditText;
    private PokestatsFragment pokestatsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @Override
    public void onBackPressed() {
        if(pokestatsFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(pokestatsFragment).commit();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.submit_button)
    protected void submitClicked() {
        if (pokemonNameEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Both fields are required. Try again!", Toast.LENGTH_LONG).show();
        } else {
            pokestatsFragment = PokestatsFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString(POKEMON_NAME, pokemonNameEditText.getText().toString());
            PokestatsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, pokestatsFragment).commit();
        }

    }
}
