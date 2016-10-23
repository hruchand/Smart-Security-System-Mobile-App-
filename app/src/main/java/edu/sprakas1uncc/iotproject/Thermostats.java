package edu.sprakas1uncc.iotproject;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;



/**
 * A simple {@link Fragment} subclass.
 */
public class Thermostats extends Fragment {
int counter=0;
    public static String temp_mode = "";
    public static  String fan_mode = "";
    public static String temp_mode_main = "";
    public static  String fan_mode_main = "";
    public static int control_temp_upstairs = 0;
    public static int control_temp_main = 0;
    public static int current_temp_upstairs = 60;
    public static int current_temp_main = 60;

    public static String mode = "";

    TextView textViewcurrent;
    Timer updateTimer;
TextView textViewcurrent1;
    public Thermostats() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thermostats, container, false);

        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radiogroupthermmode);
        RadioGroup radioGroupfan = (RadioGroup) v.findViewById(R.id.radiogroupthermfan);
        RadioGroup radioGroupmain = (RadioGroup) v.findViewById(R.id.radiogroupthermmodemain);
        RadioGroup radioGroupfanmain = (RadioGroup) v.findViewById(R.id.radiogroupthermfanmain);
        final TextView textViewupstair = (TextView) v.findViewById(R.id.controltemp);
        final SeekBar myseekbarupstair = (SeekBar) v.findViewById(R.id.seekbarcurrenttemp);
        final TextView textViewmain = (TextView) v.findViewById(R.id.controltempmain);
        final SeekBar myseekbarmain = (SeekBar) v.findViewById(R.id.seekbarcurrenttempmain);
         textViewcurrent = (TextView) v.findViewById(R.id.currenttempval);
        textViewcurrent1 =(TextView)v.findViewById(R.id.currenttempvalmain) ;

updateTimer = new Timer();

        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    temp_mode = "undefined mode upstairs";
                }
                if  (checkedId == R.id.heat){
                    temp_mode = "heat";
                    mode = temp_mode;

                }
                if (checkedId == R.id.cool){
                    temp_mode = "cool";
                    mode = temp_mode;
                    Log.d ("temp mode", "cool");
                }
                if (checkedId == R.id.off){
                    temp_mode = "off";
                }
             //   textViewcurrent.setText(Integer.toString(current_temp_upstairs));

            }
        });


        radioGroupfan.clearCheck();
        radioGroupfan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    fan_mode = "undefined mode upstairs fan";
                }
                if  (checkedId == R.id.auto){
                    temp_mode = "auto";
                    Log.d("fan mode", "auto");
                }
                if (checkedId == R.id.fanoff){
                    temp_mode = "off";
                }

            }
        });


        radioGroupmain.clearCheck();
        radioGroupmain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    temp_mode_main = "undefined mode upstairs";
                }
                if  (checkedId == R.id.heatmain){
                    temp_mode_main = "heatmain";
                }
                if (checkedId == R.id.coolmain){
                    temp_mode_main = "coolmain";
                    Log.d ("temp mode main", "coolmain");
                }
                if (checkedId == R.id.offmain){
                    temp_mode_main = "off";
                }



            }
        });


        radioGroupfanmain.clearCheck();
        radioGroupfanmain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    fan_mode_main = "undefined mode upstairs fan";
                }
                if  (checkedId == R.id.automain){
                    temp_mode_main = "auto";
                    Log.d("fan mode main", "auto");
                }
                if (checkedId == R.id.fanoffmain){
                    temp_mode_main = "off";
                }

            }
        });

        myseekbarupstair.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //if (status) {
                control_temp_upstairs = progress;

                Log.d("seekbar", "reached pregress change");
                textViewupstair.setText(Integer.toString(control_temp_upstairs));
                //  }
                // else
                // textView.setText("");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        myseekbarmain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //if (status) {
                control_temp_main = progress;

                Log.d("seekbar", "reached pregress change");
                textViewmain.setText(Integer.toString(control_temp_main));
                //  }
                // else
                // textView.setText("");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






        return v;
    }

@Override
public void onStart(){
    super.onStart();
    updateTimer.scheduleAtFixedRate(new updateTask(new Handler(),this),0, 1000);
}
    @Override
    public void onStop(){
        super.onStop();
        updateTimer.cancel();

    }


    public  void update(){
       textViewcurrent.setText(Integer.toString(current_temp_upstairs));
        textViewcurrent1.setText(Integer.toString(current_temp_main));



      //  textViewcurrent.setText(String.valueOf(++counter));
    }
private class  updateTask extends TimerTask{
   Handler handler;
    Thermostats thermostats;
    public updateTask(Handler handler, Thermostats thermostats){
        super();
        this.handler=handler;
        this.thermostats=thermostats;
    }
    @Override
    public void run(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                thermostats.update();
            }
        });

    }


}

}
