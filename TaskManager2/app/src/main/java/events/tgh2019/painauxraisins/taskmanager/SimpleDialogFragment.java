package events.tgh2019.painauxraisins.taskmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleDialogFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setTitle("新規タスク追加");
        //builder.setMessage("タスク名");

        final Dialog dialog1 = new Dialog(getActivity());
        dialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog1.setContentView(R.layout.dialog_new_task);

        //builder.setPositiveButton("OK", new DialogButtonClickListener());
        //builder.setNeutralButton("??", new DialogButtonClickListener());
        //builder.setNegativeButton("NG", new DialogButtonClickListener());
        //AlertDialog dialog2 = builder.create();

        //予定開始日時のカレンダー設定
        final EditText startDateInput = dialog1.findViewById(R.id.startDateInput);
        final Calendar calendarStart= Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dialogCalendarStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendarStart.set(Calendar.YEAR, year);
                calendarStart.set(Calendar.MONTH, month);
                calendarStart.set(Calendar.DAY_OF_MONTH, day);
                SimpleDateFormat fmtStart = new SimpleDateFormat("yyyy/MM/dd");
                startDateInput.setText(fmtStart.format(calendarStart.getTime()));

            }
        };
        startDateInput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),
                        dialogCalendarStart,
                        calendarStart.get(Calendar.YEAR),
                        calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //予定終了日時のカレンダー設定
        final EditText endDateInput = dialog1.findViewById(R.id.endDateInput);
        final Calendar calendarEnd = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dialogCalendarEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendarEnd.set(Calendar.YEAR, year);
                calendarEnd.set(Calendar.MONTH, month);
                calendarEnd.set(Calendar.DAY_OF_MONTH, day);
                SimpleDateFormat fmtEnd = new SimpleDateFormat("yyyy/MM/dd");
                endDateInput.setText(fmtEnd.format(calendarEnd.getTime()));

            }
        };
        endDateInput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),
                        dialogCalendarEnd,
                        calendarEnd.get(Calendar.YEAR),
                        calendarEnd.get(Calendar.MONTH),
                        calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //"作成"ボタンが押されたときは入力された値を変数に格納し、Eventクラスを作成
        final Button buttonOK = dialog1.findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //すべてString型のデータにする
                final EditText titleInput = dialog1.findViewById(R.id.titleInput);
                String title = titleInput.getText().toString();
                final EditText priorityInput = dialog1.findViewById(R.id.priorityInput);
                String priority = priorityInput.getText().toString();
                final EditText startHourInput = dialog1.findViewById(R.id.startHourInput);
                String startHour = startHourInput.getText().toString();
                final EditText startMinutesInput = dialog1.findViewById(R.id.startMinutesInput);
                String startMinutes = startMinutesInput.getText().toString();
                final EditText endHourInput = dialog1.findViewById(R.id.endHourInput);
                String endHour = endHourInput.getText().toString();
                final EditText endMinutesInput = dialog1.findViewById(R.id.endMinutesInput);
                String endMinutes = endMinutesInput.getText().toString();
                //日時のデータはCalendarクラスに入れる
                calendarStart.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHour));
                calendarStart.set(Calendar.MINUTE, Integer.parseInt(startMinutes));
                calendarEnd.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHour));
                calendarEnd.set(Calendar.MINUTE, Integer.parseInt(endMinutes));

                int eventColor = ContextCompat.getColor(getActivity(), R.color.eventColor);

                //Eventクラスの作成 Event(long mId, Calendar mStartTime, Calendar mEndTime, String mName, String mLocation, int mColor)
                Event event = new Event(1, calendarStart, calendarEnd, title, priority, eventColor);

                Log.d("TEST", getActivity().getClass().toString());
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity)getActivity()).addEvent(event);
                }

                //ダイアログを閉じる
                dialog1.dismiss();
            }
        });

        //"キャンセル"ボタンが押されたときはそのまま終了
        final Button buttonNG = dialog1.findViewById(R.id.buttonNG);
        buttonNG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ダイアログを閉じる
                dialog1.dismiss();
            }
        });

        return dialog1;
    };
};


    /*private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Activity parent = getActivity();
            String msg = "";
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    msg = "新規タスク追加：OKが選択されました";
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    msg = "新規タスク追加：??が選択されました";
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    msg = "新規タスク追加：NGが選択されました";
                    break;
            }
            Toast.makeText(parent, msg, Toast.LENGTH_SHORT).show();
        }

    }*/
