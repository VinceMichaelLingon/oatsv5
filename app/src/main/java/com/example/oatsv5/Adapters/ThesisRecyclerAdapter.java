package com.example.oatsv5.Adapters;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.example.oatsv5.R.layout.citation_dialog;
import static com.example.oatsv5.R.layout.thesis_dialog_details;


import static java.lang.String.valueOf;



import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oatsv5.Constants.Constants;
import com.example.oatsv5.Models.Thesis.ThesisAuthors;
import com.example.oatsv5.Models.Thesis.ThesisKeywords;
import com.example.oatsv5.R;
import com.example.oatsv5.Models.Thesis.ThesesResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ThesisRecyclerAdapter extends RecyclerView.Adapter<ThesisRecyclerAdapter.MyViewHolder> {

    private TextView title;
    private Context mContext;
    private List<ThesesResult>thesisList;
//    private List<ThesesResult.KeywordsResult>keywordsLists;



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
    holder.department.setText(thesisList.get(position).getMoredepartments().getDeptname());
    holder.year.setText(String.valueOf(thesisList.get(position).getPublished()));
    holder.course.setText(thesisList.get(position).getMoreCourse().getCoursename());
//        Toast.makeText(mContext,thesisList.get(position).getToken(), Toast.LENGTH_LONG).show();
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            private String jsonString;

            @Override
            public void onClick(View view) {
                TextView dtitle;
                TextView abstrct;
                TextView status;
                TextView keyword;
                TextView  published;
                TextView authors;
                Button cite;
                Button pdfshow;
                TextView base64;

//               System.out.println(thesisList.get(position).getBase64());
//

//                Toast.makeText(mContext,thesisList.get(position).getTitle(), Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                View dialogView= LayoutInflater.from(view.getRootView().getContext()).inflate(thesis_dialog_details,null);


                dtitle = dialogView.findViewById(R.id.txt_title);
                abstrct = dialogView.findViewById(R.id.txt_abstrct);
                status = dialogView.findViewById(R.id.txt_status);
                keyword = dialogView.findViewById(R.id.txt_keyword);
                published = dialogView.findViewById(R.id.txt_published);
                authors = dialogView.findViewById(R.id.txt_authors);
                base64 = dialogView.findViewById(R.id.txt_base64);
                pdfshow = dialogView.findViewById(R.id.btn_pdf);
                pdfshow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String file = String.valueOf(thesisList.get(position).getBase64());

                        byte[] b = Files.toByteArray(pdf);
                        String encodedFile = Base64.encodeToString(b, Base64.DEFAULT);


                        System.out.println(file);
//






                    }
                    

                });

                cite=dialogView.findViewById(R.id.btn_cite);
                cite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Spinner citation;
                        AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                        View dialogView= LayoutInflater.from(view.getRootView().getContext()).inflate(citation_dialog,null);

                        builder.setView(dialogView);
                        builder.setCancelable(true);
                        builder.show();

                        citation = dialogView.findViewById(R.id.citation_format);

                        citation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {

                                switch (index){
                                    case 0:
                                        TextView none;

                                        none = dialogView.findViewById(R.id.cite_result);

                                        String empty = " ";

                                        none.setText(empty);
                                        break;
                                    case 1:
//                                        Toast.makeText(mContext,"MLA", Toast.LENGTH_LONG).show();

                                        TextView MLA;

                                        MLA = dialogView.findViewById(R.id.cite_result);
                                        final ThesisAuthors[] athrs = thesisList.get(position).getThesisAuthors();
                                        String  authrs = "" ;
                                        for(ThesisAuthors arry: athrs ){
                                            authrs = authrs + arry.getLname()+" " +arry.getFname().charAt(0) +"., " + "";
                                        }
                                        String ttle = thesisList.get(position).getTitle();
                                        String pblshd = String.valueOf(thesisList.get(position).getPublished());
                                        String crse = thesisList.get(position).getMoreCourse().getCoursename();
                                        String athrss = authrs;
                                        String source = Constants.BASE_URL;
                                        String citation = "";
                                        citation = citation +" "+athrss+"\""+ttle+"\""+","+" "+" "+pblshd+","+" "+source;
                                        MLA.setText(citation);
                                        break;
                                    case 2:

                                        TextView APA;
                                        APA = dialogView.findViewById(R.id.cite_result);
                                        final ThesisAuthors[] otors = thesisList.get(position).getThesisAuthors();
                                        String  awtors = "" ;
                                        for(ThesisAuthors arry: otors){
                                            awtors = awtors + arry.getLname()+" " +arry.getFname().charAt(0) +"., " + "";
                                        }
                                        String title = thesisList.get(position).getTitle();
                                        String publshd = String.valueOf(thesisList.get(position).getPublished());
                                        String authrss = awtors;
                                        String sourceurl = Constants.BASE_URL;
                                        String citationapa = "";
                                        String dateapa = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(new Date());
                                        citationapa = citationapa +" "+authrss+"("+publshd+")"+"."+" "+"\""+title+"\""+" "+"Retrieved"+" "+dateapa+" "+"from"+" "+sourceurl;
                                        APA.setText(citationapa);
                                        break;
                                    case 3:
                                        TextView IEEE;
                                        IEEE = dialogView.findViewById(R.id.cite_result);
                                        final ThesisAuthors[] otors1 = thesisList.get(position).getThesisAuthors();
                                        String  awtors1 = "" ;
                                        for(ThesisAuthors arry: otors1){
                                            awtors1 = awtors1 + arry.getLname()+" " +arry.getFname() .charAt(0) +"., " + "";
                                        }
                                        String title1 = thesisList.get(position).getTitle();
                                        String publshd1 = String.valueOf(thesisList.get(position).getPublished());
                                        String authrss1 = awtors1;
                                        String sourceurl1 = Constants.BASE_URL;
                                        String date = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
                                        String citationieee = "";
                                        citationieee = citationieee +" "+authrss1+publshd1+","+"\""+title1+"\""+","+" "+"[Online],"+" "+"Available:"+" "+sourceurl1+"."+" "+"[Accessed:"+" "+date+"]";
                                        IEEE.setText(citationieee);
                                           break;

                                    case 4:
                                        Toast.makeText(mContext,"APA", Toast.LENGTH_LONG).show();
                                        break;

                                    default:
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
//copy to clipboard function
                        TextView txtres;
                    ImageView copy;
                    txtres=dialogView.findViewById(R.id.cite_result);
                        copy= dialogView.findViewById(R.id.btn_copy_citation);
                        copy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String txtcopy = txtres.getText().toString();
                                ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(CLIPBOARD_SERVICE);
                                clipboard.setText(txtcopy);
                                Toast.makeText(mContext,"Citation Copied to clipboard", Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                });

               //getting value for each TextView
//                base64.setText(thesisList.get(position).getBase64());
                dtitle.setText(thesisList.get(position).getTitle());
                abstrct.setText(thesisList.get(position).getAbstrct());
                status.setText(thesisList.get(position).getStatus());
                published.setText(valueOf(thesisList.get(position).getPublished()));
//

//
                //kaya may ganto kase array yung kinukuha
                final ThesisKeywords[] kwrds = thesisList.get(position).getThesisKeywords();
                String  keys = "" ;
                for(ThesisKeywords arr: kwrds ){
                    keys = keys + arr.getKeyword()+", " + "";
                    keyword.setText(keys );
                }

                final ThesisAuthors[] athrs = thesisList.get(position).getThesisAuthors();
                String  authrs = "" ;
                for(ThesisAuthors arry: athrs ){
                    authrs = authrs + arry.getFname()+" " + arry.getLname()+", " + "";
                    authors.setText(authrs);
                }
                final String[] cites = {thesisList.get(position).getTitle(), thesisList.get(position).getAbstrct(),
                        valueOf(thesisList.get(position).getPublished()), keys, authrs};


////                final String[] cites = {thesisList.get(position).getTitle(), thesisList.get(position).getAbstrct(),
////                        valueOf(thesisList.get(position).getPublished()), keys, authrs};
//
                String ttle = thesisList.get(position).getTitle();
                String pblshd = String.valueOf(thesisList.get(position).getPublished());
                String crse = thesisList.get(position).getMoreCourse().getCoursename();
                String athrss = authrs;

                String citation = "";


                    citation = citation +" "+ttle+" " +pblshd+" " +crse+" "+athrss;
//
//                    cite.setText(citation);







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
        TextView department;
        TextView year;
        TextView course;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.thesis_title);
            year = itemView.findViewById(R.id.thesis_year);
            course = itemView.findViewById(R.id.thesis_course);
            department = itemView.findViewById(R.id.thesis_deptname);

            cardview=itemView.findViewById(R.id.thesis_card);

        }
    }




}
