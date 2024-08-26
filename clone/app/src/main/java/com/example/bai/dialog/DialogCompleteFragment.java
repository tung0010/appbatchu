package com.example.bai.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.bai.databinding.DialogcompleteBinding;

public class DialogCompleteFragment extends DialogFragment {
    DialogcompleteBinding dialogcompleteBinding;

    private okbtnlistener callback;

    public interface okbtnlistener {
        void onClicknextquestion();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (okbtnlistener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getDialog().getWindow().setDimAmount(0.0f);
        getDialog().setCanceledOnTouchOutside(false);
        dialogcompleteBinding=DialogcompleteBinding.inflate(inflater);
        dialogcompleteBinding.txtLevelCounter.setText(String.valueOf("LEVEL"+" "+String.valueOf(getArguments().getInt("level")+1)));
        dialogcompleteBinding.score.setText(String.valueOf(getArguments().getInt("diemso")));
        dialogcompleteBinding.txtdapan.setText(String.valueOf(getArguments().getString("dapan")));
        dialogcompleteBinding.frameOkbtn.setOnClickListener(view -> {
            dismiss();
            callback.onClicknextquestion();

        });
        return dialogcompleteBinding.getRoot();

    }
}
