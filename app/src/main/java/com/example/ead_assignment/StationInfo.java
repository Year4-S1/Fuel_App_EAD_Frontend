package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_assignment.model.MoreStationInfo;
import com.example.ead_assignment.model.Queue;
import com.example.ead_assignment.model.QueueCountsVehicles;
import com.example.ead_assignment.utils.WebServiceClient;
import com.example.ead_assignment.utils.WebServiceInterface;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StationInfo extends AppCompatActivity {

    private RadioGroup petrolTypes, dieselTypes, vehicles1, vehicles2, fuel1,fuel2;
    private RadioButton p92,p95, deisel,sdeisel, car,bike, tuk, van, lorry, bus;
    private Dialog dialog;
    private PopupWindow popupJoin, popupLeave;
    private static StationInfo context;
    private LinearLayout stationInfo;

    private MoreStationInfo moreStationInfo;
    private QueueCountsVehicles queueCountsVehicles;
    private TextView joinLeaveBtn, cancelJoin, joinQueueBtn, beforePump, afterPump, cancelLeave ;
    private Queue queue;
    private SharedPreferences prefs;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_info);
        context = this;
        petrolTypes = findViewById(R.id.petroltypes);
        dieselTypes = findViewById(R.id.dieselTypes);
        fuel1 = findViewById(R.id.petroltypesdialog);
        fuel2= findViewById(R.id.dieselTypes);
        vehicles1 = findViewById(R.id.vehicletypes);
        vehicles2 = findViewById(R.id.vehicletypes1);
        joinQueueBtn = findViewById(R.id.joinQueueBtn);
        stationInfo = findViewById(R.id.stationInfo);

        Gson gson = new Gson();
        moreStationInfo = gson.fromJson(getIntent().getStringExtra("moreStationInfo"), MoreStationInfo.class);
        queueCountsVehicles = gson.fromJson(getIntent().getStringExtra("counts"), QueueCountsVehicles.class);

        Log.e("moreStationInfo", String.valueOf(queueCountsVehicles));

        prefs = getSharedPreferences("FUELQ", Context.MODE_PRIVATE);



        clearPetrol(petrolTypes);
        clearDeisel(dieselTypes);
        joinQueueClicked();
    }

    public void clearPetrol(View view) {
        petrolTypes.clearCheck();
    }

    public void clearDeisel(View view) {
        dieselTypes.clearCheck();
    }
    public void clearVehicles1(View view) {
        vehicles2.clearCheck();
    }
    public void clearVehicles2(View view) {
        vehicles1.clearCheck();
    }
    public void clearFuel1(View view) {
        fuel2.clearCheck();
    }
    public void clearFuel2(View view) {
        fuel1.clearCheck();
    }

    public void joinQueueClicked() {
        joinQueueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(joinQueueBtn.getText().toString().matches("Join Queue")){
                    openJoinQueueDialog();
                }
                else{
                    openLeaveQueueDialog();
                }


            }
        });
    }

    public static int getDeviceWidth(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        return p.x;
    }

    public void openJoinQueueDialog() {

        stationInfo.setAlpha((float) 0.7);
        int deviceWidth = 900;
        popupJoin = new PopupWindow(deviceWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        View popupView = View.inflate(context, R.layout.join_dialog, null);
        popupJoin.setContentView(popupView);

        popupJoin.setFocusable(true);
        popupJoin.setOutsideTouchable(false);
        popupJoin.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_dialog_bg));

        popupJoin.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 1, 1);


        joinLeaveBtn = popupView.findViewById(R.id.joinleaveBtn);

        joinLeaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinQueue();
            }
        });

        cancelJoin = popupView.findViewById(R.id.canceljoin);

        cancelJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupJoin.dismiss();
                stationInfo.setAlpha((float) 1.0);
            }
        });




    }

    public void openLeaveQueueDialog() {

        stationInfo.setAlpha((float) 0.7);
        int deviceWidth = 900;
        popupLeave = new PopupWindow(deviceWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        View popupView = View.inflate(context, R.layout.leave_queue, null);
        popupLeave.setContentView(popupView);

        popupLeave.setFocusable(true);
        popupLeave.setOutsideTouchable(false);
        popupLeave.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_dialog_bg));

        popupLeave.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 1, 1);


        beforePump = popupView.findViewById(R.id.beforePump);

        beforePump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaveQueue();
            }
        });

        afterPump = popupView.findViewById(R.id.afterPump);

        afterPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaveQueue();
            }
        });

        cancelLeave = popupView.findViewById(R.id.cancelLeave);

        cancelLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupLeave.dismiss();
                stationInfo.setAlpha((float) 1.0);
            }
        });




    }

    public void joinQueue(){

        Queue queueDetails = new Queue("63519290461747b44ff81e1d","6358e51d8325b0950923a08c","car","Petrol 95 Octane","Joined");

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<Queue> call = webService.joinQueue(queueDetails);

        call.enqueue(new Callback<Queue>() {
            @Override
            public void onResponse(Call<Queue> call, Response<Queue> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                queue = response.body();


                Toast.makeText(getApplicationContext(),"Joined", Toast.LENGTH_LONG).show();

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Queue Status", "Joined");
                editor.putString("Queue Id", queue.getId());
                editor.putString("Joined station id", queue.getStationId());

                editor.apply();

                stationInfo.setAlpha((float) 1.0);
                popupJoin.dismiss();

                joinQueueBtn.setText("Leave Queue");

            }



            @Override
            public void onFailure(Call<Queue> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });
    }

    public void leaveQueue(){

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<Queue> call = webService.leaveQueue(prefs.getString("Queue Id",""));

        call.enqueue(new Callback<Queue>() {
            @Override
            public void onResponse(Call<Queue> call, Response<Queue> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                queue = response.body();


                Toast.makeText(getApplicationContext(),"Left", Toast.LENGTH_LONG).show();

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Queue Status", "Left");
                editor.putString("Queue Id", "");
                editor.putString("Joined station id", "");

                editor.apply();

                stationInfo.setAlpha((float) 1.0);
                popupLeave.dismiss();

                joinQueueBtn.setText("Join Queue");

            }



            @Override
            public void onFailure(Call<Queue> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });

    }

}