package com.bats.admin.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class ToastNote {

    public void toastNotification(Context context, String text) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(true);
        dialog.setNeutralButton("OK", null);
        dialog.setMessage(text);
        dialog.show();
    }
}
