package me.lancer.spineruntimesdemo.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AppActivity;

import me.lancer.spineruntimesdemo.R;
import me.lancer.spineruntimesdemo.model.Raptor;

public class RaptorActivity extends AppActivity {

    Raptor raptor;
    View raptorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = cfg.g = cfg.b = cfg.a = 8;
        raptor = new Raptor();
        raptorView = initializeForView(raptor, cfg);
        if (raptorView instanceof SurfaceView) {
            SurfaceView glView = (SurfaceView) raptorView;
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderOnTop(true);
        }
        LinearLayout root = (LinearLayout) findViewById(R.id.content);
        root.addView(raptorView);
    }

    @Override
    protected void onDestroy() {
        raptor.dispose();
        super.onDestroy();
    }
}
