package com.example.bai.adapter;

import  android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bai.databinding.IcontextBinding;
import com.example.bai.listener.keywordlistener;

import java.util.List;

public class InputKeyWordadapter extends RecyclerView.Adapter<InputKeyWordadapter.keywordViewHolder> {
    List<String> listinputtkeyword;
    private Context context;
    private final keywordlistener keywordlistener;

    public InputKeyWordadapter(List<String> listkeyword, Context context, keywordlistener keywordlistener) {
        this.listinputtkeyword = listkeyword;
        this.context = context;
        this.keywordlistener = keywordlistener;
    }

    @NonNull
    @Override
    public keywordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IcontextBinding icontextBinding = IcontextBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new keywordViewHolder(icontextBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull keywordViewHolder holder, int position) {
        holder.setData(listinputtkeyword.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listinputtkeyword.size();
    }

    public class keywordViewHolder extends RecyclerView.ViewHolder {

        private final IcontextBinding binding;


        public keywordViewHolder(IcontextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }



        void setData( String keyword,int position) {

            binding.key.setText(keyword);
            binding.getRoot().setOnClickListener(v -> keywordlistener.onClickkeywordlistener(position));
        }

    }

}
