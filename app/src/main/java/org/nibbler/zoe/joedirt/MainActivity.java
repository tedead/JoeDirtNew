package org.nibbler.zoe.joedirt;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;

import com.google.android.gms.ads.*;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

@SuppressLint("DefaultLocale")
public class MainActivity extends AppCompatActivity implements OnClickListener, OnLongClickListener {

    private MediaPlayer mp;
    private AdView adView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Initialize Mobile Ads SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // Setup AdView
        adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-6093624921351744/1422585714");
        adView.setAdSize(AdSize.BANNER);

        // Find a layout to add the AdView to (assuming mainLayout in your XML)
        View mainLayout = findViewById(R.id.mainLayout);
        if (mainLayout instanceof LinearLayout) {
            ((LinearLayout) mainLayout).addView(adView);
        }
        
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // Setup click listeners for all buttons
        setupButtons();
    }

    private void setupButtons() {
        int[] buttonIds = {
            R.id.button_a1976, R.id.button_autotrader, R.id.button_circustent, R.id.button_crap,
            R.id.button_crapper, R.id.button_cries, R.id.button_daddy, R.id.button_defleppard,
            R.id.button_dog, R.id.button_falldown, R.id.button_fireworks, R.id.button_isitdone,
            R.id.button_kickingwing, R.id.button_lotion, R.id.button_lotionskin, R.id.button_meteor,
            R.id.button_mic, R.id.button_neck, R.id.button_poo, R.id.button_rocker,
            R.id.button_rusty, R.id.button_sermon, R.id.button_spinning, R.id.button_takeajoe,
            R.id.button_tenfour, R.id.button_unicef, R.id.button_usuck, R.id.button_what,
            R.id.button_joedirthole, R.id.button_solderingiron, R.id.button_posi, R.id.button_hemi,
            R.id.button_woodchipper, R.id.button_leif, R.id.button_town, R.id.button_boeing,
            R.id.button_outofdate, R.id.button_homosnaked, R.id.button_buffout, R.id.button_cliff,
            R.id.button_charlene, R.id.button_largeandincharge, R.id.button_head, R.id.button_backtowork,
            R.id.button_notcool, R.id.button_bumperpool, R.id.button_exit
        };

        for (int id : buttonIds) {
            View v = findViewById(id);
            if (v != null) {
                v.setOnClickListener(this);
                v.setOnLongClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_exit) {
            stopPlaying();
            finish();
            return;
        }

        int resId = getRawResourceId(v.getId());
        if (resId != 0) {
            playSound(resId);
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

    private void playSound(int resId) {
        stopPlaying();
        mp = MediaPlayer.create(this, resId);
        if (mp != null) {
            mp.start();
        }
    }

    private void stopPlaying() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Intent popup = new Intent(this, SaveFile.class);
        popup.putExtra("id", v.getId());
        startActivity(popup);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onDestroy() {
        stopPlaying();
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }
}
