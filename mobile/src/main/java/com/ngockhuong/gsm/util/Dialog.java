package com.ngockhuong.gsm.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.StringRes;
import android.util.Log;

import com.ngockhuong.gsm.R;
import com.ngockhuong.gsm.exception.PermissionException;

public class Dialog {
    public static final String TAG = Dialog.class.getSimpleName();

    private static Dialog instance = new Dialog();

    private Dialog() {}

    public static Dialog getInstance() {
        return instance;
    }

    /**
     * Displays a confirmation dialog for non-authorizing actions for the application.
     * If the user presses the yes button, the application will continue requesting permission again.
     * Otherwise, if the user presses the exit button, the application closes immediately.
     * @param activity
     * @param titleString
     * @param messageString
     */
    public void showRequestPermissionsConfirmationDialog(final Activity activity,
                                                         @StringRes int titleString,
                                                         @StringRes int messageString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(titleString));
        builder.setMessage(activity.getResources().getString(messageString));

        builder.setPositiveButton(R.string.action_grant, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                try {
                    PermissionsUtil.askPermissions(activity);
                } catch (PermissionException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });

        builder.setNegativeButton(R.string.action_exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                activity.finish();
                System.exit(0);
            }
        });

        builder.show();
    }

    /**
     * Displays a dialog box to notify users of what permissions the application needs to do.
     * @param activity
     * @param titleString
     * @param messageString
     */
    public void showRequestPermissionsInfoDialog(final Activity activity,
                                                 @StringRes int titleString,
                                                 @StringRes int messageString) {
        // If the permissions are note granted, the notification dialog box is displayed
        try {
            if (PermissionsUtil.checkPermissions(activity, PermissionsUtil.getPermissions(activity))) {
                return;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getResources().getString(titleString));
        builder.setMessage(activity.getResources().getString(messageString));

        builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                try {
                    PermissionsUtil.askPermissions(activity);
                } catch (PermissionException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });

        builder.setCancelable(false);
        builder.show();
    }
}
