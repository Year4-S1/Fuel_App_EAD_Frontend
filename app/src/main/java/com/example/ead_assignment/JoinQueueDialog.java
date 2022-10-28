package com.example.ead_assignment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class JoinQueueDialog extends AppCompatDialogFragment {

    String selectedFuel = "92";
    Dialog dialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        dialog = new Dialog(getActivity());

        dialog.setContentView(R.layout.join_queue_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

//        String[] fuels = {"Petrol 92 Octane", "Petrol 95 Octane", "Diesel", "Super Diesel"};
//
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        builder.setTitle("Join Queue");
//        builder.setSingleChoiceItems(fuels, 0, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                selectedFuel = fuels[i];
//                Log.e("Clicked", "yeo");
//            }
//        });
//
//
        return builder.show();

    }
}
