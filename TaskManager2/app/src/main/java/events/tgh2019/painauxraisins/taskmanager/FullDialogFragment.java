package events.tgh2019.painauxraisins.taskmanager;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FullDialogFragment extends DialogFragment {

    private Event event;



    public void setTask(Event event){
        this.event = event;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        /*
        builder.setTitle(task);
        builder.setMessage("よろしいですか?");
        builder.setPositiveButton("終了", null);
        builder.setNegativeButton("開始", null);

         */
        //AlertDialog dialog = builder.create();
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.task_manage);

        final TextView taskname = dialog.findViewById(R.id.taskName);
        taskname.setText(event.getName());
        Calendar start = event.getStartTime();
        Calendar end = event.getEndTime();
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        final TextView time = dialog.findViewById(R.id.time);
        time.setText(String.format("%s～%s", sf.format(start.getTime()), sf.format(end.getTime())));


        final Calendar startTime = Calendar.getInstance();
        final Button buttonStart =  dialog.findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] args;
                //Calendar startTime = Calendar.getInstance();
                System.out.println("開始時刻："+startTime.getTime());
                dialog.dismiss();


            }

        });

        final Calendar finishTime = Calendar.getInstance();
        final Button buttonFinish = dialog.findViewById(R.id.buttonFinish);
        final Date finishDate;
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] args;
                //Calendar finishTime = Calendar.getInstance();
                System.out.println("終了時刻"+finishTime.getTime());




            }

        });
/*
        long diffTime = finishTime.getTimeInMillis() - startTime.getTimeInMillis();
        return diffTime;
        }
*/

    final Button done = dialog.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RatingBar rating = dialog.findViewById(R.id.ratingBar2);
            float satisfy = rating.getRating();
            int point = calcPoint(event, satisfy);
            Log.e("TEST", "充実度 " + satisfy + ", Point: " + point);

            // トキノハナAPPの呼び出し
            PackageManager pm = getActivity().getPackageManager();
            Intent intent = pm.getLaunchIntentForPackage("events.tgh2019.painauxraisins.tokinohana");
            intent.putExtra("POINT", point);
            startActivity(intent);

            dialog.dismiss();



        }

    });






        return dialog;


    }

    public int calcPoint(Event event, float satisfy) {
        int result = 0;
//        long planTime = event.getEndTime().getTimeInMillis() - event.getStartTime().getTimeInMillis();
//        long actualTime = event.getActualEndTime().getTimeInMillis() - event.getActualEndTime().getTimeInMillis();
        float severity = event.getSeverity();
//        result = (int)((satisfy + severity/100) / (planTime/actualTime*100) + 0.5);
        result = (int)((satisfy + severity/100) + 0.5);
        return result;
    }

}


