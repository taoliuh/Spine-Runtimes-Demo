package me.lancer.spineruntimesdemo.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AppActivity;

import me.lancer.spineruntimesdemo.R;
import me.lancer.spineruntimesdemo.model.SpineBoy;

public class SpineBoyActivity extends AppActivity {

    SpineBoy spineBoy;
    View spineBoyView;

    Button btnWalk, btnRun, btnJump, btnIdle, btnShoot, btnDeath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spine_boy);

        btnWalk = (Button) findViewById(R.id.btn_walk);
        btnWalk.setOnClickListener(vOnClickListener);

        btnRun = (Button) findViewById(R.id.btn_run);
        btnRun.setOnClickListener(vOnClickListener);

        btnJump = (Button) findViewById(R.id.btn_jump);
        btnJump.setOnClickListener(vOnClickListener);

        btnIdle = (Button) findViewById(R.id.btn_idle);
        btnIdle.setOnClickListener(vOnClickListener);

        btnShoot = (Button) findViewById(R.id.btn_shoot);
        btnShoot.setOnClickListener(vOnClickListener);

        btnDeath = (Button) findViewById(R.id.btn_death);
        btnDeath.setOnClickListener(vOnClickListener);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = cfg.g = cfg.b = cfg.a = 8;
        spineBoy = new SpineBoy();
        spineBoyView = initializeForView(spineBoy, cfg);
        if (spineBoyView instanceof SurfaceView) {
            SurfaceView glView = (SurfaceView) spineBoyView;
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderOnTop(true);
        }
        FrameLayout root = (FrameLayout) findViewById(R.id.animate_content);
        root.addView(spineBoyView);
    }

    View.OnClickListener vOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnWalk) {
                spineBoy.setAnimate("walk");
            } else if (view == btnRun) {
                spineBoy.setAnimate("run");
            } else if (view == btnJump) {
                spineBoy.setAnimate("jump");
            } else if (view == btnIdle) {
                spineBoy.setAnimate("idle");
            } else if (view == btnShoot) {
                spineBoy.setAnimate("shoot");
            } else if (view == btnDeath) {
                spineBoy.setAnimate("death");
            }
        }
    };

    @Override
    protected void onDestroy() {
        spineBoy.dispose();
        super.onDestroy();
    }
}
