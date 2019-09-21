package events.tgh2019.painauxraisins.tokinohana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DatabaseHelper(getApplication());
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                setName("志田");

                helper.saveData(new Date(), 10);
                refreshPoint();
            }
        });
        Button reset = findViewById(R.id.button2);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();

                helper.saveData(new Date(), helper.calculateTotal() * -1);
                growFlower(0);
                displayPoint(0);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            int point = intent.getIntExtra("POINT", -1);
            if (point > 0) {
                helper.saveData(new Date(), point);
                refreshPoint();
            }
        }
        displayPoint(helper.calculateTotal());
    }

    private void refreshPoint() {
        int total = helper.calculateTotal();
        growFlower(total);
        displayPoint(total);
    }

    private void setName(String name) {
        TextView title = findViewById(R.id.title);
        title.setText(name + "さんのトキノハナ");
    }

    private void displayPoint(int point) {
        TextView totalpoints = findViewById(R.id.totalpoints);
        totalpoints.setText("累計ポイント　" + point + "pt");
    }

    private void growFlower(int point) {
        ImageView flower = findViewById(R.id.imageView);
        if (point < 0) {

        } else if (point > 0 && point <= 30) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_12);
        } else if (point <= 60) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_11);
        } else if (point <= 90) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_10);
        } else if (point <= 120) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_09);
        } else if (point <= 150) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_08);
        } else if (point <= 180) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_07);
        } else if (point <= 210) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_06);
        } else if (point <= 240) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_05);
        } else if (point <= 270) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_04);
        } else if (point <= 300) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_03);
        } else if (point <= 330) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_02);
        } else if (point <= 360) {
            flower.setImageResource(R.drawable.tokinohana_growflowers_01);
        }
    }

}
