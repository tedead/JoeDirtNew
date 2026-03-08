package org.nibbler.zoe.joedirt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SaveFile extends AppCompatActivity implements OnClickListener {

    int resId, viewId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Save As...");
        setContentView(R.layout.savefile);

        findViewById(R.id.btn_ringtone).setOnClickListener(this);
        findViewById(R.id.btn_notification).setOnClickListener(this);
        findViewById(R.id.btn_alarm).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            viewId = extras.getInt("id");
            resId = getRawResourceId(viewId);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_ringtone) {
            checkAndSet(resId, RingtoneManager.TYPE_RINGTONE);
        } else if (id == R.id.btn_notification) {
            checkAndSet(resId, RingtoneManager.TYPE_NOTIFICATION);
        } else if (id == R.id.btn_alarm) {
            checkAndSet(resId, RingtoneManager.TYPE_ALARM);
        } else if (id == R.id.btn_cancel) {
            finish();
        }
    }

    private void checkAndSet(int resId, int type) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                Toast.makeText(this, "Please allow 'Write Settings' and try again", Toast.LENGTH_LONG).show();
                return;
            }
        }
        saveAndSetRingtone(resId, type);
    }

    private void saveAndSetRingtone(int resId, int type) {
        String soundFile = getResources().getResourceEntryName(resId);
        String fileName = soundFile + ".mp3";
        String title = getTitleForFile(fileName);

        Uri uri = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Modern MediaStore approach for Android 10+
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mpeg");
                values.put(MediaStore.MediaColumns.TITLE, title);
                
                String relativePath;
                if (type == RingtoneManager.TYPE_RINGTONE) {
                    relativePath = Environment.DIRECTORY_RINGTONES;
                    values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
                } else if (type == RingtoneManager.TYPE_NOTIFICATION) {
                    relativePath = Environment.DIRECTORY_NOTIFICATIONS;
                    values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
                } else {
                    relativePath = Environment.DIRECTORY_ALARMS;
                    values.put(MediaStore.Audio.Media.IS_ALARM, true);
                }
                
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath);

                uri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
                if (uri != null) {
                    try (OutputStream os = getContentResolver().openOutputStream(uri);
                         InputStream is = getResources().openRawResource(resId)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = is.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                        }
                    }
                }
            } else {
                // Legacy approach for older versions
                File path = Environment.getExternalStoragePublicDirectory(
                        type == RingtoneManager.TYPE_RINGTONE ? Environment.DIRECTORY_RINGTONES :
                                type == RingtoneManager.TYPE_NOTIFICATION ? Environment.DIRECTORY_NOTIFICATIONS :
                                        Environment.DIRECTORY_ALARMS);
                
                if (!path.exists()) path.mkdirs();
                File file = new File(path, fileName);

                try (InputStream is = getResources().openRawResource(resId);
                     FileOutputStream fos = new FileOutputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                }

                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());
                values.put(MediaStore.MediaColumns.TITLE, title);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mpeg");
                if (type == RingtoneManager.TYPE_RINGTONE) values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
                else if (type == RingtoneManager.TYPE_NOTIFICATION) values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
                else values.put(MediaStore.Audio.Media.IS_ALARM, true);

                uri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
            }

            if (uri != null) {
                RingtoneManager.setActualDefaultRingtoneUri(this, type, uri);
                Toast.makeText(this, title + " set successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private int getRawResourceId(int viewId) {
        if (viewId == R.id.button_a1976) return R.raw.a1976;
        if (viewId == R.id.button_autotrader) return R.raw.autotrader;
        if (viewId == R.id.button_circustent) return R.raw.circustent;
        if (viewId == R.id.button_crap) return R.raw.crap;
        if (viewId == R.id.button_crapper) return R.raw.crapper;
        if (viewId == R.id.button_cries) return R.raw.cries;
        if (viewId == R.id.button_daddy) return R.raw.daddy;
        if (viewId == R.id.button_defleppard) return R.raw.defleppard;
        if (viewId == R.id.button_dog) return R.raw.dog;
        if (viewId == R.id.button_falldown) return R.raw.falldown;
        if (viewId == R.id.button_fireworks) return R.raw.fireworks;
        if (viewId == R.id.button_isitdone) return R.raw.isitdone;
        if (viewId == R.id.button_kickingwing) return R.raw.kickingwing;
        if (viewId == R.id.button_lotion) return R.raw.lotion;
        if (viewId == R.id.button_lotionskin) return R.raw.lotionskin;
        if (viewId == R.id.button_meteor) return R.raw.meteor;
        if (viewId == R.id.button_mic) return R.raw.mic;
        if (viewId == R.id.button_neck) return R.raw.neck;
        if (viewId == R.id.button_poo) return R.raw.poo;
        if (viewId == R.id.button_rocker) return R.raw.rocker;
        if (viewId == R.id.button_rusty) return R.raw.rusty;
        if (viewId == R.id.button_sermon) return R.raw.sermon;
        if (viewId == R.id.button_spinning) return R.raw.spinning;
        if (viewId == R.id.button_takeajoe) return R.raw.takeajoe;
        if (viewId == R.id.button_tenfour) return R.raw.tenfour;
        if (viewId == R.id.button_unicef) return R.raw.unicef;
        if (viewId == R.id.button_usuck) return R.raw.usuck;
        if (viewId == R.id.button_what) return R.raw.what;
        if (viewId == R.id.button_joedirthole) return R.raw.joedirthole;
        if (viewId == R.id.button_solderingiron) return R.raw.solderingiron;
        if (viewId == R.id.button_posi) return R.raw.posi;
        if (viewId == R.id.button_hemi) return R.raw.hemi;
        if (viewId == R.id.button_woodchipper) return R.raw.woodchipper;
        if (viewId == R.id.button_leif) return R.raw.leif;
        if (viewId == R.id.button_town) return R.raw.town;
        if (viewId == R.id.button_boeing) return R.raw.boeing;
        if (viewId == R.id.button_outofdate) return R.raw.outofdate;
        if (viewId == R.id.button_homosnaked) return R.raw.homosnaked;
        if (viewId == R.id.button_buffout) return R.raw.buffout;
        if (viewId == R.id.button_cliff) return R.raw.cliff;
        if (viewId == R.id.button_charlene) return R.raw.charlene;
        if (viewId == R.id.button_largeandincharge) return R.raw.largeandincharge;
        if (viewId == R.id.button_head) return R.raw.head;
        if (viewId == R.id.button_backtowork) return R.raw.backtowork;
        if (viewId == R.id.button_notcool) return R.raw.notcool;
        if (viewId == R.id.button_bumperpool) return R.raw.bumperpool;
        return 0;
    }

    private String getTitleForFile(String fileName) {
        return fileName.replace(".mp3", "").replace("_", " ");
    }
}
