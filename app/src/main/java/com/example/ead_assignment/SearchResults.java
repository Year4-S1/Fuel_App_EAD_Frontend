package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ead_assignment.model.AvailableFuel;
import com.example.ead_assignment.model.MoreStationInfo;
import com.example.ead_assignment.model.QueueCountsVehicles;
import com.example.ead_assignment.utils.WebServiceClient;
import com.example.ead_assignment.utils.WebServiceInterface;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.stream.Collectors;

//viewing search results
public class SearchResults extends AppCompatActivity {

    //creating variables

    private ListView shedList;
    private String [] names = {"lll","rrr","eee"};
    private String isLogin;
    private JSONArray json;
    private TextView shedcard;
    private User user;
    JSONObject ii;
    String value;


    public static  final String SHARED_PREF = "sharedPrefs";

    private List<AvailableFuel> availableFuel;

    private QueueCountsVehicles queueCountsVehicles;

    private List<String> fuelList;

    private String fuelListStr;

    private MoreStationInfo moreStationInfo;





    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);


        shedList = findViewById(R.id.shedList);


        MyAdapter adapter = new MyAdapter();
        shedList.setAdapter(adapter);


        isLogin = String.valueOf(getIntent().getStringExtra("fuelstationList"));

        Log.e("isLogin",isLogin);

        try {
            json = new JSONArray(isLogin);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.e("isLogin", String.valueOf(json.length()));



    }



    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount(){
            return names.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.shed_card,viewGroup, false);
            TextView shedName = view.findViewById(R.id.shedName);
            TextView location = view.findViewById(R.id.location);
            TextView stid = view.findViewById(R.id.stId);
            shedcard = view.findViewById(R.id.moreInfo);


             ii = new JSONObject();
            try {
                ii = json.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            try {
                shedName.setText(ii.getString("StationName"));
                location.setText(ii.getString("StationLocation"));
                stid.setText((ii.getString("Id")));
            } catch (JSONException e) {
                e.printStackTrace();
            }



            shedcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


//                    try {
//                        System.out.println(ii.getString("Id"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }


                    moreInfo(shedName.getText().toString(), location.getText().toString(), stid.getText().toString());

                }
            });


            return view;


        }


    }


    public void moreInfo(String shedName, String location, String stId){

//        System.out.println(shedName + location + stId);

        SharedPreferences sharedPreferences =  getSharedPreferences("FUELQ", Context.MODE_PRIVATE);

        String id = sharedPreferences.getString("uid","" );

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<List<AvailableFuel>> call = webService.getFuelDetails(id);

        call.enqueue(new Callback<List<AvailableFuel>>() {
            @Override
            public void onResponse(Call<List<AvailableFuel>> call, Response<List<AvailableFuel>> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                availableFuel = response.body();

                Log.e("res",response.toString());
                Log.e("res",availableFuel.toString());

                 fuelList = availableFuel.stream().map(AvailableFuel::getFuelType).collect(Collectors.toList());


                fuelListStr = String.join(", ", fuelList);

                moreStationInfo = new MoreStationInfo(stId, shedName, location, fuelListStr);

                Gson gson = new Gson();
                 value = gson.toJson(moreStationInfo);

//                Intent stationReg = new Intent(Signup.this, Home.class);
//                startActivity(stationReg);
            }

            @Override
            public void onFailure(Call<List<AvailableFuel>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });



        WebServiceInterface webService1 = WebServiceClient.getInstance().getWebService();
        Call<QueueCountsVehicles> call1 = webService.getQueueCount(id);

        call1.enqueue(new Callback<QueueCountsVehicles>() {
            @Override
            public void onResponse(Call<QueueCountsVehicles> call, Response<QueueCountsVehicles> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                queueCountsVehicles = response.body();

                Log.e("res",response.toString());





                Intent stationInfo = new Intent(SearchResults.this, StationInfo.class);
                Gson gson = new Gson();
                String count = gson.toJson(queueCountsVehicles);

                stationInfo.putExtra("moreStationInfo", value);
                stationInfo.putExtra("count", count);






                startActivity(stationInfo);



            }





            @Override
            public void onFailure(Call<QueueCountsVehicles> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });









    }
}