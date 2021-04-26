package com.app.madscalsi.Adapter;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.madscalsi.Model.HistoryModel;
import com.app.madscalsi.R;

import java.util.ArrayList;

import static com.app.madscalsi.Activity.MainActivity.RESULT_HISTORY_PICKER;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
        private ArrayList<HistoryModel> listdata;
        private Activity context;

        // RecyclerView recyclerView;
        public HistoryAdapter(Activity context, ArrayList<HistoryModel> listdata) {
            this.listdata = listdata;
            this.context = context;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.card_history, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final HistoryModel myListData = listdata.get(position);
            holder.tvExpression.setText(listdata.get(position).getExpression());
            holder.tvResult.setText("="+listdata.get(position).getResult());
            holder.LinLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent();
                    intent.putExtra("sum",listdata.get(position).getResult());
                    context.setResult(RESULT_HISTORY_PICKER,intent);
                    context.finish();//finishing activity
                }
            });
        }


        @Override
        public int getItemCount() {
            return listdata.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tvExpression;
            public TextView tvResult;
            public LinearLayout LinLay;
            public ViewHolder(View itemView) {
                super(itemView);
                this.tvExpression = (TextView) itemView.findViewById(R.id.tvExpression);
                this.tvResult = (TextView) itemView.findViewById(R.id.tvResult);
                this.LinLay = (LinearLayout) itemView.findViewById(R.id.LinLay);
            }
        }
    }
