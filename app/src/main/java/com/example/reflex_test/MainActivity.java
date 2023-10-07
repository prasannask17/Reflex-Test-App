package com.example.reflex_test;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView stimulusText;
    private Button tapButton;
    private Button resetButton;
    private long startTime;
    private boolean isGreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stimulusText = findViewById(R.id.stimulusText);
        tapButton = findViewById(R.id.tapButton);
        resetButton = findViewById(R.id.resetButton);

        // Disable the "Reset" button initially
        resetButton.setEnabled(false);

        tapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stimulusText.getText().equals("Tap when ready")) {
                    // Change text to "Tap now" and start measuring time
                    stimulusText.setText("Tap now");
                    startTime = System.currentTimeMillis();

                    // Generate a random delay (e.g., 2-5 seconds) before showing the stimulus
                    int delay = (int) (2000 + Math.random() * 3000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showStimulus();
                        }
                    }, delay);
                } else if (isGreen) {
                    // Calculate and display the reaction time
                    long reactionTime = System.currentTimeMillis() - startTime;
                    stimulusText.setText("Reaction Time: " + reactionTime + " ms");

                    // Change the color back to red
                    stimulusText.setTextColor(Color.RED);

                    // Enable the "Reset" button
                    resetButton.setEnabled(true);
                    isGreen = false;
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset the test by clearing the text and disabling the "Reset" button
                stimulusText.setText("Tap when ready");
                resetButton.setEnabled(false);
            }
        });
    }

    private void showStimulus() {
        stimulusText.setText("Tap!");
        // Change the color to green
        stimulusText.setTextColor(Color.GREEN);
        // The stimulus is displayed; record the start time for reaction measurement
        startTime = System.currentTimeMillis();
        isGreen = true;
    }
}
