package firebase.app.studentapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {
    Context context;
    ArrayList<String> mSubject=new ArrayList<>();
    ArrayList<String> mText=new ArrayList<>();
    ArrayList<String> mFile=new ArrayList<>();

    public AssignmentAdapter(Context context, ArrayList<String> mSubject, ArrayList<String> mText, ArrayList<String> mFile) {
        this.context = context;
        this.mSubject = mSubject;
        this.mText = mText;
        this.mFile = mFile;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        holder.tv_subject.setText(mSubject.get(i));
        holder.tv_text.setText(mText.get(i));

        holder.cardFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent(Intent.ACTION_VIEW, Uri.parse(mFile.get(i)));
                context.startActivity(I);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mSubject.size();
    }


    public static class  ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_subject,tv_text,tv_file;
        CardView cardFile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_subject=(TextView)itemView.findViewById(R.id.tv_subject);
            tv_text=(TextView)itemView.findViewById(R.id.tv_text);
            tv_file=(TextView)itemView.findViewById(R.id.tv_file);
            cardFile=(CardView)itemView.findViewById(R.id.cardFile);
        }
    }
}
