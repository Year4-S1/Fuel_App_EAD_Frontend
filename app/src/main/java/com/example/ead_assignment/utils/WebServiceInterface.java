package com.example.ead_assignment.utils;

import com.example.ead_assignment.User;
import com.example.ead_assignment.model.AvailableFuel;
import com.example.ead_assignment.model.FuelStation;
import com.example.ead_assignment.model.LoginModel;
import com.example.ead_assignment.model.QueueCounts;
import com.example.ead_assignment.model.Station;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

//web interface for accessing api calls in a centralized location
public interface WebServiceInterface {
    String BASE_URL = "http://10.0.2.2:5000/api/";

    @GET("station/search/{station}/")
    Call<List<FuelStation>> getFuelStations(@Path("station") String query);

    @POST("user/post/user")
    Call<User> signupUser(@Body User user);

    @POST("station/post/")
    Call<Station> signupStation(@Body Station station);

    @POST("user/login/")
    Call<User> login(@Body LoginModel loginModel);

    @GET("fueldetails/getfuel/perstation/{shedid}/")
    Call<List<AvailableFuel>> getFuelDetails(@Path("shedid") String query);

    @GET("queue/count/vehicle/type/{shedid}/")
    Call<QueueCounts> getQueueCount(@Path("shedid") String query);

    @PUT("user/logout/{uid}")
    Call<User> logout(@Path("uid") String userid);
}

//    @POST("FuelStation/{id}/VehiclesInQueue")
//    Call<UserQueue> joinQueue(@Path("id") String id,@Body UserQueue userQueue);
//
//    @PATCH("FuelStation/{id}/VehiclesInQueue")
//    Call<UserQueue> leaveQueue(@Path("id") String id,@Body UserQueue userQueue);


//    @POST("User")
//    Call<User> registerVehicle(@Body User user);
//
//    @POST("FuelStation")
//    Call<FuelStation> registerFuelStation(@Body FuelStation fuelStation);
//}
