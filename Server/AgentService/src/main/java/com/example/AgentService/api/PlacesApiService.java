package com.example.AgentService.api;

import com.example.AgentService.model.PlaceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlacesApiService {

    @GET("nearbysearch/json")
    Call<PlaceResponse> getNearbyPlaces(
            @Query("location") String location,     // e.g. "39.105,-94.586"
            @Query("radius") int radius,            // e.g. 2000
            @Query("keyword") String keyword,       // e.g. "cafe with a scenic view"
            @Query("key") String apiKey             // your API key
    );
}

