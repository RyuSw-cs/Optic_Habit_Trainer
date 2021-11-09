package com.oht.UI.main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oht.Data.Step;
import com.oht.R;
import com.oht.UI.FirstStep.FirstStepActivity;
import com.oht.UI.Loading.LoadingActivity;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<Step>getList;

    public MainRecyclerAdapter(List<Step>list){
        getList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.main_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int pos = position;
        holder.stepTitle.setText(getList.get(position).getStepTitle());
        holder.stepContent.setText(getList.get(position).getStepContent());
    }

    @Override
    public int getItemCount() {
        return getList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView stepTitle, stepContent;
        private Button startBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepTitle = itemView.findViewById(R.id.main_step_title);
            stepContent = itemView.findViewById(R.id.main_step_content);
            startBtn = itemView.findViewById(R.id.main_step_btn);

            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    switch (getAdapterPosition()){
                        case 0:
                            intent = new Intent(v.getContext(), FirstStepActivity.class);
                            v.getContext().startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(v.getContext(), LoadingActivity.class);
                            v.getContext().startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(v.getContext(), LoadingActivity.class);
                            v.getContext().startActivity(intent);
                            break;
                    }
                }
            });
        }
    }
}
