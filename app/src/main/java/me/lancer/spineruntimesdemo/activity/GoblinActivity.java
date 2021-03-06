package me.lancer.spineruntimesdemo.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AppActivity;

import me.lancer.spineruntimesdemo.R;
import me.lancer.spineruntimesdemo.model.Goblin;

public class GoblinActivity extends AppActivity {

    Goblin goblin;
    View goblinView;

    Button btnMan, btnWoman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goblin);

        btnMan = (Button) findViewById(R.id.btn_man);
        btnMan.setOnClickListener(vOnClickListener);

        btnWoman = (Button) findViewById(R.id.btn_woman);
        btnWoman.setOnClickListener(vOnClickListener);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = cfg.g = cfg.b = cfg.a = 8;
        goblin = new Goblin();
        goblinView = initializeForView(goblin, cfg);
        if (goblinView instanceof SurfaceView) {
            SurfaceView glView = (SurfaceView) goblinView;
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderOnTop(true);
        }
        LinearLayout root = (LinearLayout) findViewById(R.id.content);
        root.addView(goblinView);
    }

    View.OnClickListener vOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnMan) {
                goblin.setSkin("goblin");
            } else if (view == btnWoman) {
                goblin.setSkin("goblingirl");
            }
        }
    };

    @Override
    protected void onDestroy() {
        goblin.dispose();
        super.onDestroy();
    }
}
