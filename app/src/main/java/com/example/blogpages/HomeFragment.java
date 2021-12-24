package com.example.blogpages;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blogpages.myclass.Blog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    View view;
    ListView listView;
    ArrayList<Blog> arrayList;
    HomeAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.lstView);
        new LoadBlogsTask().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),PostPage.class);
                intent.putExtra("title",arrayList.get(position).getTitle());
                intent.putExtra("desc",arrayList.get(position).getFulldesc());
                intent.putExtra("category",arrayList.get(position).getCategory());
                intent.putExtra("date",arrayList.get(position).getPostedDate());
                intent.putExtra("postedBy",arrayList.get(position).getPostedBy());
                startActivity(intent);
            }
        });
        return view;
    }



    public class LoadBlogsTask extends AsyncTask<String,String,String>{

        ProgressDialog progressDialog ;
//        ArrayList<Blog> arrayList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList = new ArrayList<>();
            progressDialog  = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading....");
            progressDialog.show();

        }
        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            WebServiceCall call = new WebServiceCall();
            HashMap<String,String> hashMap = new HashMap<>();
            try {
                result = call.postData(GlobalURL.GETPOST_URL,WebServiceCall.GET,hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("Result",result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            try{
                JSONObject object = new JSONObject(s);
                JSONArray jsonArray = object.getJSONArray("msg");
                for(int i = 0;i < jsonArray.length() ; i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Blog blog = new Blog();
                    blog.setPostid(jsonObject.getString("postid"));
                    blog.setTitle(jsonObject.getString("title"));
                    blog.setPostedDate(jsonObject.getString("posteddate"));
                    blog.setSmalldesc(jsonObject.getString("shortdesc"));
                    blog.setCategory(jsonObject.getString("category"));
                    blog.setPostedBy(jsonObject.getString("postedBy"));
                    blog.setFulldesc(jsonObject.getString("fulldesc"));
                    arrayList.add(blog);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            Toast.makeText(getContext(), String.valueOf(arrayList.size()), Toast.LENGTH_SHORT).show();
            adapter = new HomeAdapter(arrayList, getContext());
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

}
