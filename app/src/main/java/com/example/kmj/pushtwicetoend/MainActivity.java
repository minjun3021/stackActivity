package com.example.kmj.pushtwicetoend;

import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import static android.app.ActivityManager.*;

public class MainActivity extends AppCompatActivity {
    Button i, btn;
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = findViewById(R.id.intent);
        btn = findViewById(R.id.check);

        backPressCloseHandler = new BackPressCloseHandler(MainActivity.this);
        ActivityManager mngr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<RunningTaskInfo> taskList = mngr.getRunningTasks(10);

        if (taskList.get(0).numActivities == 1 && taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityManager mngr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                    List<RunningTaskInfo> taskList = mngr.getRunningTasks(10);

                    Toast.makeText(MainActivity.this, "액티비티수 : " + String.valueOf(taskList.get(0).numActivities + "현재 액티비티 이름 : " + taskList.get(0).topActivity.getClassName()), Toast.LENGTH_SHORT).show();

                }
            });

            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                }
            });



        }
    }
}
