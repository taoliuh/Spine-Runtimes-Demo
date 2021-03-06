package me.lancer.spineruntimesdemo.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AppActivity;

import me.lancer.spineruntimesdemo.R;
import me.lancer.spineruntimesdemo.model.DragonYouth;

public class DragonYouthActivity extends AppActivity {

    DragonYouth dragon;
    View dragonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = cfg.g = cfg.b = cfg.a = 8;
        dragon = new DragonYouth();
        dragonView = initializeForView(dragon, cfg);
        if (dragonView instanceof SurfaceView) {
            SurfaceView glView = (SurfaceView) dragonView;
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderOnTop(true);
        }
        LinearLayout root = (LinearLayout) findViewById(R.id.content);
        root.addView(dragonView);
    }

    @Override
    protected void onDestroy() {
        dragon.dispose();
        super.onDestroy();
    }
}
