package com.example.alexkeith.pokedex;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitPokemonApiCalls {

    @GET("{name}")
    Call<PokemonStats> getPokemonStats(@Path("name") String pokemonNameText);



    class PokemonStats {
        @SerializedName("Stats")
        private String stats;

        public String getStats() {
            return stats;
        }
    }
}
