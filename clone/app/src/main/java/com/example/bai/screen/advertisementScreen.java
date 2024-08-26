package com.example.bai.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.example.bai.R;
import com.example.bai.databinding.ActivityAdvertisementBinding;
import com.example.bai.databinding.FragmentDialogAdvBinding;
import com.example.bai.dialog.DialogSuggestFragment;
import com.example.bai.utilities.Constants;
import com.example.bai.utilities.PreferenceManager;

public class advertisementScreen extends AppCompatActivity {
    public interface adfinishListener {
        void adfinish(boolean yes);
    }
    public adfinishListener adfinishListener1;
    public DialogSuggestFragment.suggestListener yesListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdvertisementBinding activityAdvertisementBinding = ActivityAdvertisementBinding.inflate(getLayoutInflater());
        String path = "android.resource://" + getPackageName() + "/" + R.raw.adver;
        activityAdvertisementBinding.video.setVideoURI(Uri.parse(path));
        activityAdvertisementBinding.video.start();

        activityAdvertisementBinding.video.setOnCompletionListener(mediaPlayer ->{ finish();
            PreferenceManager preferenceManager = new PreferenceManager(getApplicationContext());
            preferenceManager.putInt(Constants.KEY_RUBY,preferenceManager.getruby(Constants.KEY_RUBY)+25);
        });

        setContentView(activityAdvertisementBinding.getRoot());

    }
}