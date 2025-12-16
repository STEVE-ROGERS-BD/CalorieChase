package com.example.AgentService.agent;

import com.example.AgentService.api.PlacesApiService;
import com.example.AgentService.model.PlaceResponse;
import com.example.AgentService.util.RetrofitClient;
import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations.Schema;
import com.google.adk.tools.FunctionTool;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class RoutingAgent {

    public static BaseAgent ROOT_AGENT = initAgent();

    private static BaseAgent initAgent() {
        return LlmAgent.builder()
                .name("Routing-agent")
                .description("Creates a route from a given prompt and parameters")
                .instruction("""
                You are a helpful assistant that creates a route from a 
                given prompt and parameters. Use the 'getCurrentTime' 
                tool for this purpose.
                """) // this is to tell the agent which tool to use
                .model("gemini-2.5-flash")
                .tools(FunctionTool.create(RoutingAgent.class,
                        "getCurrentTime"))
                .build();
    }


    public PlaceResponse callPlacesApi() throws IOException {
        PlacesApiService service = RetrofitClient.getService();
        String location = "{39.105,-94.586}";
        int radius = 2000;
        String keyword = "cafe with a scenic view";
        String apiKey = "AIzaSyD6nuU_yZkx0gnmhlNW_9A6muRIIUwBOVQ";
        Call<PlaceResponse> call = service.
                getNearbyPlaces(location, radius, keyword, apiKey);
        Response<PlaceResponse> res = call.execute();
        if(res.isSuccessful())
        {
            return res.body();
        }

        return null;
    }

    /** Here describe the tool or you have to do so in the system instruction */
    @Schema(description =
            "")
    public static Map<String, String> getCurrentTime(
            @Schema(name = "city",
                    description = "Name of the city to get the time for") String city)
    {
        return Map.of(
                "city", city,
                "forecast", "The time is 10:30am."
        );
    }

    public static Map<String,Object> createRoute(
            @Schema(name="startingPointLat",
            description="Latitude of the starting point of the route")
            long startingPointLat,
            @Schema(name="StrPoint",
            description="Longitude of the ending point of the route")
            long endingPointLon,
            @Schema(name="prompt",
            description = "This is the prompt sent by the user, if he has any" +
                    "specific demands should be mentioned here.")
            String prompt,
            @Schema(name="distance",
                    description = "This is the prompt sent by the user, if he has any" +
                            "specific demands should be mentioned here.")
            long distance
    ){
        //filter the prompt if any

        if(prompt!=null && !prompt.isBlank())
        {

        }


        return null;
    }

    public List<String>extractKeyWords(String prompt)
    {
        return null;
    }


}
