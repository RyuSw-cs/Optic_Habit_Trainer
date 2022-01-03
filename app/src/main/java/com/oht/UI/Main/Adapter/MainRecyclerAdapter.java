package com.oht.UI.Main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.oht.Data.Step;
import com.oht.R;
import com.oht.UI.FirstStep.Ready.FirstStepReadyActivity;
import com.oht.UI.SecondStep.Ready.SecondStepReadyActivity;
import com.oht.UI.ThirdStep.Ready.ThirdStepReadyActivity;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<Step> getList;
    private boolean[] check;
    private Context context;

    public MainRecyclerAdapter(List<Step> list, boolean[] getCheck, Context context) {
        getList = list;
        check = getCheck;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.main_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.stepTitle.setText(getList.get(position).getStepTitle());
        holder.stepContent.setText(getList.get(position).getStepContent());
        if(holder.itemView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            holder.subTitle.setText(getList.get(position).getSubTitle());
        }
        //완료
        if (check[position]) {
            holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.main_list_after));
            holder.startBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.main_btn_shape_after));
            if(holder.itemView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                holder.startBtn.setPadding(16,16,16,16);
            }
            else{
                holder.startBtn.setPadding(90,50,90,50);
            }
            holder.startBtn.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return getList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView stepTitle, stepContent, subTitle;
        private Button startBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepTitle = itemView.findViewById(R.id.main_step_title);
            stepContent = itemView.findViewById(R.id.main_step_content);
            startBtn = itemView.findViewById(R.id.main_step_btn);

            if(itemView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                subTitle = itemView.findViewById(R.id.main_step_sub_title);
            }

            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    switch (getAdapterPosition()) {
                        case 0:
                            intent = new Intent(v.getContext(), FirstStepReadyActivity.class);
                            v.getContext().startActivity(intent);
                            break;
                        case 1:
                            if (!check[0]) {
                                Toast.makeText(context, "Step 1을 진행해주세요!", Toast.LENGTH_LONG).show();
                            } else {
                                intent = new Intent(v.getContext(), SecondStepReadyActivity.class);
                                v.getContext().startActivity(intent);
                            }
                            break;
                        case 2:
                            if (!check[1]) {
                                Toast.makeText(context, "Step 2를 진행해주세요!", Toast.LENGTH_LONG).show();
                            } else {
                                intent = new Intent(v.getContext(), ThirdStepReadyActivity.class);
                                v.getContext().startActivity(intent);
                            }
                            break;
                    }
                }
            });
        }
    }
}