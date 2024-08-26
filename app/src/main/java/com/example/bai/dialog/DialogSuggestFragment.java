package com.example.bai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bai.databinding.FragmentDialogSuggestBinding;


public class DialogSuggestFragment extends DialogFragment{

FragmentDialogSuggestBinding fragmentDialogSuggestBinding;
    int ruby;

    public interface suggestListener {
        void suggest(boolean yes,String audiogoiy);
    }
    public suggestListener  suggestListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getDialog().getWindow().setDimAmount(0.0f);
        getDialog().setCanceledOnTouchOutside(false);
        String audiogoiy=getArguments().getString("audiogoiy");
         ruby = getArguments().getInt("ruby");
        // Inflate the layout for this fragment
        fragmentDialogSuggestBinding=FragmentDialogSuggestBinding.inflate(inflater);
        fragmentDialogSuggestBinding.no.setOnClickListener(v -> dismiss());
        fragmentDialogSuggestBinding.yes.setOnClickListener(v -> {
                suggestListener.suggest(true,audiogoiy);
                dismiss();

        });
        return fragmentDialogSuggestBinding.getRoot();
    }

    @Override public void onAttach(Context context)
    {
        super.onAttach(context);
        try {
            suggestListener = (suggestListener) getActivity();
        }
        catch (ClassCastException e) {

        }
    }

}