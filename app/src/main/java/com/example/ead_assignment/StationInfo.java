package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_assignment.model.MoreStationInfo;
import com.example.ead_assignment.model.QueueCounts;
import com.google.gson.Gson;

public class StationInfo extends AppCompatActivity {

    private RadioGroup petrolTypes, dieselTypes;
    private TextView joinQueueBtn;
    private Dialog dialog;
    private PopupWindow popup;
    private static StationInfo context;
    private LinearLayout stationInfo;

    private MoreStationInfo moreStationInfo;
    private QueueCounts queueCounts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_info);
        context = this;
        petrolTypes = findViewById(R.id.petroltypes);
        dieselTypes = findViewById(R.id.dieselTypes);
        joinQueueBtn = findViewById(R.id.joinQueueBtn);
        stationInfo = findViewById(R.id.stationInfo);

        Gson gson = new Gson();
        moreStationInfo = gson.fromJson(getIntent().getStringExtra("moreStationInfo"), MoreStationInfo.class);
        queueCounts = gson.fromJson(getIntent().getStringExtra("counts"), QueueCounts.class);

        Log.e("moreStationInfo", String.valueOf(queueCounts));



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

    public void joinQueueClicked() {
        joinQueueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stationInfo.setAlpha((float) 0.7);
//                stationInfo.setBackground("#ffffff");

                Toast.makeText(getApplicationContext(), "Text View Component has been clicked", Toast.LENGTH_LONG).show();

                openJoinQueueDialog();
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
        int deviceWidth = 900;
        popup = new PopupWindow(deviceWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        View popupView = View.inflate(context, R.layout.leave_queue, null);
        popup.setContentView(popupView);

        popup.setFocusable(true);
        popup.setOutsideTouchable(true);
        popup.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_dialog_bg));

        popup.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

}