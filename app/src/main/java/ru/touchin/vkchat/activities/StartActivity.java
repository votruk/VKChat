package ru.touchin.vkchat.activities;

import android.os.Bundle;



import org.zuzuk.ui.activities.AbstractExecutorActivity;

import ru.touchin.vkchat.R;

public class StartActivity extends AbstractExecutorActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
