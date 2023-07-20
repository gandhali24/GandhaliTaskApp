package com.example.gandhalitaskapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gandhalitaskapp.R;
import com.example.gandhalitaskapp.model.CommonRepo;
import com.example.gandhalitaskapp.model.Facility;
import com.example.gandhalitaskapp.model.Option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomAdapter  extends BaseExpandableListAdapter {

    private Context context;
    private List<Facility> deptList;
    HashMap<Integer, Integer> childCheckedState = new HashMap<>();

    RadioButton selected=null;
    private boolean userSelected = false;

 private int selectedIndex;
    private int selectedPosition;


    public CustomAdapter(Context context, List<Facility> deptList) {
        this.context = context;
        this.deptList = deptList;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Option> productList = deptList.get(groupPosition). getOptions();




        return productList.get(childPosition);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    private void buttonCheckChanged(View view) {
        selectedPosition = (Integer) view.getTag();
        notifyDataSetChanged();
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        Option detailInfo = (Option) getChild(groupPosition, childPosition);

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.child_layout, null);
        }

        ImageView imgExc = (ImageView) view.findViewById(R.id.ivExc);

        ImageView imgOption = (ImageView) view.findViewById(R.id.ivOption);
        imgOption.setImageResource(R.drawable.ic_garage);
        RadioButton rbOptionName = (RadioButton) view.findViewById(R.id.rbOptionName);



        rbOptionName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectedIndex = childPosition;



                notifyDataSetChanged();
            }
        });

        if (selectedIndex == childPosition){
            rbOptionName.setChecked(true);
        }else {
            rbOptionName.setChecked(false);
        }

        rbOptionName.setText(detailInfo.getName().trim());

        LinearLayout relativeExpView = (LinearLayout) view.findViewById(R.id.LinearExpView);

        TextView tvDes = (TextView) view.findViewById(R.id.tvExclusions);
//Toast.makeText(context.getApplicationContext(), "hhh  " +deptList.get(groupPosition).options.get(childPosition).getId() ,Toast.LENGTH_SHORT).show();


        if(detailInfo.getId().equals("1"))
        {
            imgOption.setImageResource(R.drawable.ic_apartment);

        }


        if(detailInfo.getId().equals("2"))
        {
            imgOption.setImageResource(R.drawable.ic_condo);

        }
        if(detailInfo.getId().equals("3"))
        {
            imgOption.setImageResource(R.drawable.ic_boat_house);

        }

        if(detailInfo.getId().equals("4"))
        {
            imgOption.setImageResource(R.drawable.ic_land);

        }
        if(detailInfo.getId().equals("6"))
        {
            imgOption.setImageResource(R.drawable.ic_rooms);

        }
        if(detailInfo.getId().equals("7"))
        {
            imgOption.setImageResource(R.drawable.ic_no_room);

        }
        if(detailInfo.getId().equals("10"))
        {
            imgOption.setImageResource(R.drawable.ic_swimming);

        }
        if(detailInfo.getId().equals("11"))
        {
            imgOption.setImageResource(R.drawable.ic_garden);

        }
        if(detailInfo.getId().equals("12"))
        {
            imgOption.setImageResource(R.drawable.ic_garage);

        }


        rbOptionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(detailInfo.getId().equalsIgnoreCase( "4" ))

              {
                  relativeExpView.setVisibility(View.VISIBLE);
                  tvDes.setVisibility(View.VISIBLE);
                  tvDes.setText("1 to 3 Rooms");
                  imgExc.setImageResource(R.drawable.ic_rooms);

                }

                if(detailInfo.getId().equalsIgnoreCase( "3" ))

                {
                    relativeExpView.setVisibility(View.VISIBLE);
                    tvDes.setVisibility(View.VISIBLE);
                    tvDes.setText("Garage");
                    imgExc.setImageResource(R.drawable.ic_garage);


                }
                if(detailInfo.getId().equalsIgnoreCase( "7" ))

                {
                    relativeExpView.setVisibility(View.VISIBLE);
                    tvDes.setVisibility(View.VISIBLE);
                    tvDes.setText("Garage");
                    imgExc.setImageResource(R.drawable.ic_garage);

                }
            }
        });


        return view;
    }

    public void setSelectedIndex(int index) {
        //some range-checks, maybe
        selectedPosition = index;
        //invalidate
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Option> optionArrayListt = deptList.get(groupPosition).getOptions();
        return optionArrayListt.size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }


    @Override
    public int getGroupCount() {
        return deptList.size();
    }



    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        Facility headerInfo = (Facility) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.parent_layout, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.tv_heading);
        heading.setText("   " +headerInfo.getName().trim());



        return view;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }




    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
