package events.tgh2019.painauxraisins.taskmanager;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import events.tgh2019.painauxraisins.library.CalendarDayView;
import events.tgh2019.painauxraisins.library.EventView;
import events.tgh2019.painauxraisins.library.data.IEvent;
import events.tgh2019.painauxraisins.library.decoration.CdvDecorationDefault;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CalendarDayView dayView;

    ArrayList<IEvent> events;

    //newTaskDialogというインスタンスを作成 by南條
    private SimpleDialogFragment newTaskDialog = new SimpleDialogFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayView = (CalendarDayView) findViewById(R.id.calendar);

        /** スケジュールの時刻目盛りの始めと終わりの時刻。日またぎはNG */
        dayView.setLimitTime(0, 23);

        ((CdvDecorationDefault) (dayView.getDecoration())).setOnEventClickListener(
                new EventView.OnEventClickListener() {
                    @Override
                    public void onEventClick(EventView view, IEvent data) {
                        Log.e("TAG", "onEventClick:" + data.getName());
                    }

                    /** 登録済みのタスクのコマをタップすると走るのはこっちです。 */
                    @Override
                    public void onEventViewClick(View view, EventView eventView, IEvent data) {
                        Log.e("TAG", "onEventViewClick:" + data.getName());
                        if (data instanceof Event) {
                            // change event (ex: set event color)
                            dayView.setEvents(events);
                        }
                    }
                });

        events = new ArrayList<>();

        /** 以下、3つの予定をベタ書きで作成する例（TODO: 朝起きたらユーティリティメソッド作る） */
        {
            int eventColor = ContextCompat.getColor(this, R.color.eventColor);
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 5);
            timeStart.set(Calendar.MINUTE, 30);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 11);
            timeEnd.set(Calendar.MINUTE, 00);
            Event event = new Event(1, timeStart, timeEnd, "馬術部ッ", "日吉馬場", eventColor);

            events.add(event);
        }
        {
            int eventColor = ContextCompat.getColor(this, R.color.eventColor);
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 13);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 15);
            timeEnd.set(Calendar.MINUTE, 30);
            Event event = new Event(1, timeStart, timeEnd, "脳科学レポート", "お家", eventColor);

            events.add(event);
        }

        {
            int eventColor = ContextCompat.getColor(this, R.color.eventColor);
            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 18);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 23);
            timeEnd.set(Calendar.MINUTE, 30);
            Event event = new Event(1, timeStart, timeEnd, "ハッカソン居残り", "多摩", eventColor);

            events.add(event);
        }


        dayView.setEvents(events);

        //タスク新規作成のダイアログ by南條
        final EditText editText = findViewById(R.id.editText);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        editText.setText(sf.format(new Date()));


        final Calendar calendar= Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
                editText.setText(fmt.format(calendar.getTime()));

            }
        };

        editText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(events.tgh2019.painauxraisins.taskmanager.MainActivity.this,
                        dialog,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        final ImageButton btnNewTask = findViewById(R.id.btnNewTask);
        btnNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                newTaskDialog.show(manager, "...");
            }
        });
        //タスク新規作成のダイアログ終了
    }

    public void addEvent(Event event) {
        events.add(event);
        dayView.setEvents(events);


    }
}
