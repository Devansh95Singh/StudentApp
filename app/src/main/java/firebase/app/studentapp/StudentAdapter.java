package firebase.app.studentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    Context context;
    ArrayList<String> mName=new ArrayList<>();
    ArrayList<String> mEmail=new ArrayList<>();

    public StudentAdapter(Context context, ArrayList<String> mName, ArrayList<String> mEmail) {
        this.context = context;
        this.mName = mName;
        this.mEmail = mEmail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stu_layout,parent,false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.tv_name.setText(mName.get(i));
        holder.tv_email.setText(mEmail.get(i));

    }

    @Override
    public int getItemCount() {
        return mName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name,tv_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            tv_email=(TextView)itemView.findViewById(R.id.tv_email);
        }
    }
}

