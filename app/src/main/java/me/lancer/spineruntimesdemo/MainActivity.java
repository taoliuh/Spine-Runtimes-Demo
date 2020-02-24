package me.lancer.spineruntimesdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import me.lancer.spineruntimesdemo.activity.MixAndMatchActivity;
import me.lancer.spineruntimesdemo.activity.DragonYouthActivity;
import me.lancer.spineruntimesdemo.activity.GoblinActivity;
import me.lancer.spineruntimesdemo.activity.RaptorActivity;
import me.lancer.spineruntimesdemo.activity.SpineBoyActivity;

public class MainActivity extends AppCompatActivity {

    Button btnSpineBoy, btnGoblin, btnRaptor, btnMixAndMatch, btnDragonYouth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSpineBoy = (Button) findViewById(R.id.btn_spine_boy);
        btnSpineBoy.setOnClickListener(vOnClickListener);

        btnGoblin = (Button) findViewById(R.id.btn_goblin);
        btnGoblin.setOnClickListener(vOnClickListener);

        btnRaptor = (Button) findViewById(R.id.btn_raptor);
        btnRaptor.setOnClickListener(vOnClickListener);

        btnMixAndMatch = (Button) findViewById(R.id.btn_mix_and_match);
        btnMixAndMatch.setOnClickListener(vOnClickListener);

        btnDragonYouth = (Button) findViewById(R.id.btn_dragon_youth);
        btnDragonYouth.setOnClickListener(vOnClickListener);
    }

    View.OnClickListener vOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnSpineBoy) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SpineBoyActivity.class);
                startActivity(intent);
            } else if (view == btnGoblin) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GoblinActivity.class);
                startActivity(intent);
            } else if (view == btnRaptor) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RaptorActivity.class);
                startActivity(intent);
            } else if (view == btnMixAndMatch) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MixAndMatchActivity.class);
                startActivity(intent);
            } else if (view == btnDragonYouth) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DragonYouthActivity.class);
                startActivity(intent);
            }
        }
    };
}
