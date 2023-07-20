package com.example.gandhalitaskapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gandhalitaskapp.R;
import com.example.gandhalitaskapp.model.CommonRepo;
import com.example.gandhalitaskapp.model.Facility;
import com.example.gandhalitaskapp.model.Option;

import java.util.ArrayList;
import java.util.List;

public class CommonRepoAdapter extends RecyclerView.Adapter<CommonRepoAdapter.MyViewHolder>{


    private Context context;
    CommonRepo commonRepo;

    Facility facility;
    private ArrayList<CommonRepo> commonRepoArrayList;
    private ArrayList<Option> optionArrayList;
    private List<Facility> facilityArrayList;

    public CommonRepoAdapter(Context context, List<Facility> facilityArrayList) {
        this.context = context;
        this.facilityArrayList = facilityArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.commonrepo_card_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonRepoAdapter.MyViewHolder holder, int position) {
        facility = facilityArrayList.get(position);

        holder.tvFacName.setText(facility.getName());
    }

    @Override
    public int getItemCount() {


        if (facilityArrayList != null) {
            // Log.d(Const.DEBUG, "mBusinesses Count: " + mBusinesses.size());
            return facilityArrayList.size();
        }
        return 0;


}


    public   class MyViewHolder extends RecyclerView.ViewHolder {

        // private final TextView Id;

        //  private final TextView tvFacId;
        TextView tvFacName;
        TextView tvOptName;
        ImageView imgview;
        RelativeLayout expandibleview;
        // private final TextView TVDepartment;
        TextView tvDesc;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOptName = itemView.findViewById(R.id.tvOptionName);
            tvFacName = itemView.findViewById(R.id.tv_facilityTypes);

            imgview=itemView.findViewById(R.id.imageview);
            expandibleview=itemView.findViewById(R.id.relative_expandible_View);
            tvDesc = itemView.findViewById(R.id.tvExclusions);



            tvOptName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandibleview.setVisibility(View.VISIBLE);
                    tvDesc.setVisibility(View.VISIBLE);




                }
            });
        }
    }

}
