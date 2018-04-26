package com.example.alexkeith.pokedex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.alexkeith.pokedex.MainActivity.POKEMON_NAME;

public class PokestatsFragment extends Fragment {
    private String baseUrl = "https://pokeapi.co/docsv2/#pokemon-section";
    private Retrofit retrofit;
    private RetrofitPokemonApiCalls retrofitPokemonApiCalls;
    @BindView(R.id.pokestats_text)
    protected TextView pokestatsText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle SavedInstance) {
        View view = inflater.inflate(R.layout.fragment_pokestats, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        String pokemonName = getArguments().getString(POKEMON_NAME);
        buildRetrofit();
        makeApiCall(pokemonName);
    }
    private void makeApiCall(String pokemonName) {
        retrofitPokemonApiCalls.getPokemonStats(pokemonName).enqueue(new Callback<RetrofitPokemonApiCalls.PokemonStats>() {
            @Override
            public void onResponse(Call<RetrofitPokemonApiCalls.PokemonStats> call, Response<RetrofitPokemonApiCalls.PokemonStats> response) {
                if(response.isSuccessful()) {
                    pokestatsText.setText(response.body().getPokemonStats);
                } else {
                    Toast.makeText(getContext(), "Try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RetrofitPokemonApiCalls.PokemonStats> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void buildRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitPokemonApiCalls = retrofit.create(RetrofitPokemonApiCalls.class);
    }
}
