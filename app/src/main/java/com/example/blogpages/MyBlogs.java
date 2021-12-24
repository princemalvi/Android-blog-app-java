package com.example.blogpages;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogpages.myclass.Blog;
import com.example.blogpages.myclass.MyBlog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyBlogs extends AppCompatActivity {

    View view;
    ListView listView;
    ArrayList<MyBlog> myBlogList;
    MyBlogAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blogs);

        listView = findViewById(R.id.mylstView);
        new LoadMyBlogs().execute();


    }
    public class LoadMyBlogs extends AsyncTask<String,String,String> {

        ProgressDialog progressDialog ;
        //        ArrayList<Blog> arrayList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myBlogList  = new ArrayList<>();
            progressDialog  = new ProgressDialog(MyBlogs.this);
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
                    MyBlog blog = new MyBlog();
                    blog.setTitle(jsonObject.getString("title"));
                    blog.setPostedDate(jsonObject.getString("posteddate"));
                    blog.setCategory(jsonObject.getString("category"));
                    blog.setFulldesc(jsonObject.getString("fulldesc"));
                    myBlogList.add(blog);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            Toast.makeText(MyBlogs.this, String.valueOf(myBlogList.size()), Toast.LENGTH_SHORT).show();
            adapter = new MyBlogAdapter();
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }



    public class MyBlogAdapter extends BaseAdapter {

        //        List<Blog> blogList;
        LayoutInflater inflater;
        public MyBlogAdapter() {
            inflater = LayoutInflater.from(MyBlogs.this);

        }

        public class ViewHolder{
            TextView blogTitle;
            TextView blogDesc;
            TextView category;
            TextView date;

        }
        @Override
        public int getCount() {
            return myBlogList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.myblog_all, null);
                viewHolder.blogTitle = convertView.findViewById(R.id.tvTitle);
                viewHolder.blogDesc = convertView.findViewById(R.id.desc);
                viewHolder.category = convertView.findViewById(R.id.category);
                viewHolder.date = convertView.findViewById(R.id.tvDate);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.blogTitle.setText("Title : " + myBlogList.get(position).getTitle());
            viewHolder.blogDesc.setText(myBlogList.get(position).getFulldesc());
            viewHolder.category.setText("Category : "+myBlogList.get(position).getCategory());
            viewHolder.date.setText("Posted On : "+(CharSequence) myBlogList.get(position).getPostedDate());

            return convertView;
        }



    }

}