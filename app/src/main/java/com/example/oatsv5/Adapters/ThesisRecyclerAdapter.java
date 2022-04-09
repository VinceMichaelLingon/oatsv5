package com.example.oatsv5.Adapters;

import static com.example.oatsv5.R.layout.thesis_dialog_details;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oatsv5.DashboardActivity;
import com.example.oatsv5.R;
import com.example.oatsv5.Models.ThesesResult;
import com.example.oatsv5.databinding.ActivityAdminLoginBinding;

import java.util.List;

public class ThesisRecyclerAdapter extends RecyclerView.Adapter<ThesisRecyclerAdapter.MyViewHolder> {

    private TextView title;
    private Context mContext;
    private List<ThesesResult>thesisList;



    public ThesisRecyclerAdapter(Context mContext, List<ThesesResult> thesisList) {
        this.mContext = mContext;
        this.thesisList= thesisList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        v = layoutInflater.inflate(R.layout.thesis_files,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.title.setText(thesisList.get(position).getTitle());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dtitle;
                TextView abstrct;
                TextView status;
                TextView keyword;

                Toast.makeText(mContext,thesisList.get(position).getTitle(), Toast.LENGTH_LONG).show();
              //  Dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                     AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                    View dialogView= LayoutInflater.from(view.getRootView().getContext()).inflate(thesis_dialog_details,null);

                    dtitle = dialogView.findViewById(R.id.txt_title);
                abstrct = dialogView.findViewById(R.id.txt_abstrct);
                status = dialogView.findViewById(R.id.txt_status);
                keyword = dialogView.findViewById(R.id.txt_keyword);

                dtitle.setText(thesisList.get(position).getTitle());
                abstrct.setText(thesisList.get(position).getAbstrct());
                status.setText(thesisList.get(position).getStatus());
                //`keyword.setText(thesisList.get(position).getKeywords());
//                    thesis_title = thesisList.get(position).getTitle();
//                    abstrct = thesisList.get(position).getAbstrct();
//                    status = thesisList.get(position).getStatus();

                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();



            }
        });
    }






    @Override
    public int getItemCount() {
        return thesisList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout cardview;
        TextView title;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.thesis_title);

            cardview=itemView.findViewById(R.id.thesis_card);

        }
    }




}
