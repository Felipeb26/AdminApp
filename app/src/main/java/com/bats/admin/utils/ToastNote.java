package com.bats.admin.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastNote {

    public void toastNotification(Context context, String text){
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(text);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
