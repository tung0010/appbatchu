package com.example.bai.dialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bai.databinding.DialogcompleteBinding;
import com.example.bai.databinding.DialogfailBinding;

public class DialogFail extends DialogFragment {
    DialogfailBinding binding ;
        private DialogFail.okbtnlistener callout;

        public interface okbtnlistener {
            void finish();
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            callout = (DialogFail.okbtnlistener) context;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            getDialog().getWindow().setDimAmount(0.0f);
            getDialog().setCanceledOnTouchOutside(false);
            binding= DialogfailBinding.inflate(inflater);
            binding.txtLevelCounter.setText(String.valueOf("LEVEL"+" "+String.valueOf(getArguments().getInt("level")+1)));
            binding.score.setText(String.valueOf(getArguments().getInt("diemso")));
            binding.frameOkbtn.setOnClickListener(view -> {
                callout.finish();
            });
            return binding.getRoot();

        }
}