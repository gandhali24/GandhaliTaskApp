package com.example.gandhalitaskapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gandhalitaskapp.adapter.CommonRepoAdapter;
import com.example.gandhalitaskapp.adapter.CustomAdapter;
import com.example.gandhalitaskapp.helper.DBHelper;
import com.example.gandhalitaskapp.model.CommonRepo;
import com.example.gandhalitaskapp.model.Facility;
import com.example.gandhalitaskapp.model.Option;
import com.example.gandhalitaskapp.retrofit.RetrofitAPI;
import com.example.gandhalitaskapp.retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    //TextView  listView;
    ArrayList<Facility> facilities;
    ArrayList<Option>options;
    ArrayList<CommonRepo>commonRepos;
    CommonRepo commonRepo;
    RetrofitAPI api;
    RecyclerView recyclerView;
    ArrayList<CommonRepo> commonRepoArrayList;
    ArrayList<Option> optionArrayList;
    CommonRepoAdapter commonRepoAdapter;
    Facility facility;
    TextView textview;

    CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;
    private LinkedHashMap<String, Facility> subjects = new LinkedHashMap<String, Facility>();
    DBHelper dbHelper;
    private List<Facility> deptList = new ArrayList<Facility>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControl();
        dbHelper.deleteTableData();
        getData();



    }
    public void initControl()
    {
dbHelper=new DBHelper(MainActivity.this);
        simpleExpandableListView=findViewById(R.id.simpleExpandableListView);
simpleExpandableListView.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);

    }



    private  void getData()
    {        Call<CommonRepo> call = RetrofitClientInstance.getInstance().getRetrofitAPI().getsuperHeroes();

call.enqueue(new Callback<CommonRepo>() {
    @Override
    public void onResponse(Call<CommonRepo> call, Response<CommonRepo> response) {
        if (response.isSuccessful())
        {
            commonRepo = response.body();




if(commonRepo!=null) {
                deptList=commonRepo.getFacilities();
                //get reference of the ExpandableListView
                simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
                // create the adapter by passing your ArrayList data
                listAdapter = new CustomAdapter(MainActivity.this, deptList);
                // attach the adapter to the expandable list view
                simpleExpandableListView.setAdapter(listAdapter);
for(int i=0;i<deptList.size();i++) {
    boolean isFacInserted = dbHelper.addFacilities(deptList.get(i));
    if(isFacInserted==true)
    {
        Toast.makeText(getApplicationContext(), "Facilities Added", Toast.LENGTH_SHORT).show();

    }
    else
    {
        Toast.makeText(getApplicationContext(), "Error,Facilities not added)", Toast.LENGTH_SHORT).show();

    }
    for(int j=0;j<deptList.get(i).options.size();j++) {
        {

            boolean isOptionsInserted = dbHelper.addOptions(deptList.get(i).options.get(j));
            if(isFacInserted==false)
            {
                Toast.makeText(getApplicationContext(), "Option Data Not Added", Toast.LENGTH_SHORT).show();

            }
        }


    }

    //expand all the Groups
}
                expandAll();
//collapseAll();

simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


        //get the group header
        Facility headerInfo = deptList.get(groupPosition);
        Option detailInfo =  headerInfo.getOptions().get(childPosition);
       // simpleExpandableListView.getExpandableListAdapter().onGroupCollapsed(childPosition);

        Toast.makeText(getBaseContext(), " Clicked on :: " + headerInfo.getName()
                + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
        return false;
    }
});
            }

        }}

    @Override
    public void onFailure(Call<CommonRepo> call, Throwable t) {
        Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();

    }
});
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }

    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }



                }).create().show();
    }

}