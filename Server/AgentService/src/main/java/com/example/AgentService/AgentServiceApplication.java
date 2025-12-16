package com.example.AgentService;

import com.example.AgentService.api.PlacesApiService;
import com.example.AgentService.model.PlaceResponse;
import com.example.AgentService.util.RetrofitClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class AgentServiceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AgentServiceApplication.class, args);
        System.out.println("Agent Service Started");
        callPlacesApi();

	}


    public static void callPlacesApi() throws IOException {
        PlacesApiService service = RetrofitClient.getService();
        String location = "39.105,-94.586";
        int radius = 2000;
        String keyword = "cafe with a scenic view";
        String apiKey = "AIzaSyD6nuU_yZkx0gnmhlNW_9A6muRIIUwBOVQ";
        Call<PlaceResponse> call = service.
                getNearbyPlaces(location, radius, keyword, apiKey);
        Response<PlaceResponse> res = call.execute();
        if (!res.isSuccessful()) {
            System.out.println("HTTP error: " + res.code());
            System.out.println(res.errorBody().string());
            return;
        }
        res.body().results.forEach(System.out::println);
        if (!res.isSuccessful()) {
            System.out.println("error body = " + (res.errorBody() != null ? res.errorBody().string() : "null"));
            return;
        }

        // 2) check body
        PlaceResponse body = res.body();
        if (body == null) {
            System.out.println("body is null");
            return;
        }

        // 3) check google status
        System.out.println("google status = " + body.status);
        System.out.println("next_page_token = " + body.next_page_token);

        if (body.results == null) {
            System.out.println("results is null");
            return;
        }
        if (body.results.isEmpty()) {
            System.out.println("results is empty");
            return;
        }

        // 4) finally print
        body.results.forEach(r -> {
            System.out.println("place: " + r.name + " | " + r.vicinity + " | rating=" + r.rating);
        });


    }



}
