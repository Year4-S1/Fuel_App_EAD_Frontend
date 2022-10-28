package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ead_assignment.model.FuelStation;
import com.example.ead_assignment.model.SearchQuery;
import com.example.ead_assignment.utils.WebServiceClient;
import com.example.ead_assignment.utils.WebServiceInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//home
public class Home extends AppCompatActivity {


    //creating variables
    RadioButton p92, p95, diesel, sdiesel;
    RadioGroup petrolTypes, dieselTypes;
    private Button btnDisplay;
    private ImageButton searchBtn;
    private EditText searchbar;
    private JsonObjectRequest request;
    private RequestQueue queue ;
    String url = "http://10.0.2.2:5000/api/station/search";
    public List<FuelStation> fuelStationList;
    SearchQuery query;
    private ImageView logo;



    //oncreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);



        //getting values from elements
        p92 = findViewById(R.id.p92);
        p95 = findViewById(R.id.p95);
        diesel = findViewById(R.id.diesel);
        sdiesel = findViewById(R.id.sdiesel);
        petrolTypes = findViewById(R.id.petroltypes);
        dieselTypes = findViewById(R.id.dieselTypes);
        searchBtn = findViewById(R.id.searchicon);
        searchbar = findViewById(R.id.search);
        logo = findViewById(R.id.logo);


        clearPetrol(petrolTypes);
        clearDeisel(dieselTypes);



        //onclick listener for search button
    searchBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                searchClicked();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    });

    logo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            Intent logoutpage = new Intent(Home.this, Logout.class);
            startActivity(logoutpage);

        }
    });



    }



    public void clearPetrol(View view) {
        petrolTypes.clearCheck();
    }
    public void clearDeisel(View view) {
        dieselTypes.clearCheck();
    }

    //search edit text validation to chek if search are is not empty
    public void searchClicked() throws JSONException {
        String searchQuery = searchbar.getText().toString();

        if (searchQuery.matches("")) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!searchQuery.matches("")){

            query = new SearchQuery(searchQuery);

            searchStation(searchQuery);

        }
    }

    //if validation id successfull, searching for station according to given name or location
    public void searchStation( String searchQuery){

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<List<FuelStation>> call = webService.getFuelStations(searchQuery);



        call.enqueue(new Callback<List<FuelStation>>() {
            @Override
            public void onResponse(Call<List<FuelStation>> call, Response<List<FuelStation>> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                fuelStationList = response.body();

                Log.e("res",response.toString());

                Gson gson = new Gson();
                String value = gson.toJson(fuelStationList);



              Intent searchResultRegistry = new Intent(Home.this, SearchResults.class);
              searchResultRegistry.putExtra("fuelstationList", value);
              startActivity(searchResultRegistry);




            }


            @Override
            public void onFailure(Call<List<FuelStation>> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

}