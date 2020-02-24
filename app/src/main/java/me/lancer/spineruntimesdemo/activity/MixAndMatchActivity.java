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
import me.lancer.spineruntimesdemo.model.MixAndMatch;

public class MixAndMatchActivity extends AppActivity {

    MixAndMatch mixAndMatch;
    View mixAndMatchView;

    Button btnGirl, btnBoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_and_match);

        btnGirl = (Button) findViewById(R.id.btn_girl);
        btnGirl.setOnClickListener(vOnClickListener);

        btnBoy = (Button) findViewById(R.id.btn_boy);
        btnBoy.setOnClickListener(vOnClickListener);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.r = cfg.g = cfg.b = cfg.a = 8;
        mixAndMatch = new MixAndMatch();
        mixAndMatchView = initializeForView(mixAndMatch, cfg);
        if (mixAndMatchView instanceof SurfaceView) {
            SurfaceView glView = (SurfaceView) mixAndMatchView;
            glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            glView.setZOrderOnTop(true);
        }
        LinearLayout root = (LinearLayout) findViewById(R.id.content);
        root.addView(mixAndMatchView);
    }

    View.OnClickListener vOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnGirl) {
                mixAndMatch.setSkin("full-skins/girl");
            } else if (view == btnBoy) {
                mixAndMatch.setSkin("full-skins/boy");
            }
        }
    };

    @Override
    protected void onDestroy() {
        mixAndMatch.dispose();
        super.onDestroy();
    }
}
