//package com.example.blogpages;
//
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.example.blogpages.myclass.Blog;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//
//public class HomeFragment extends Fragment {
//
//    View view;
//    RecyclerView recyclerView;
////    ArrayList<Blog> arrayList;
//    HomeAdapter adapter;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        view =  inflater.inflate(R.layout.fragment_home, container, false);
//        recyclerView = view.findViewById(R.id.recycleView);
////        arrayList = new ArrayList<>();
//        new LoadBlogsTask().execute();
//
////        Log.e("Blog Array List",String.valueOf(arrayList.size()));
//        return view;
//    }
//
//
//
//    public class LoadBlogsTask extends AsyncTask<String,String,String>{
//
//        ProgressDialog progressDialog ;
//        ArrayList<Blog> arrayList;
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            arrayList = new ArrayList<>();
//            progressDialog  = new ProgressDialog(getContext());
//            progressDialog.setMessage("Loading....");
//            progressDialog.show();
//
//        }
//        @Override
//        protected String doInBackground(String... strings) {
////            String u = "https://reqres.in/api/users?per_page=5";
//            String u = "https://reqres.in/api/users";
//            String result = "";
//            WebServiceCall call = new WebServiceCall();
//            HashMap<String,String> hashMap = new HashMap<>();
//            hashMap.put("per_page","5");
//            try {
//                result = call.postData(u,WebServiceCall.GET,hashMap);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Log.d("Result",result);
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            progressDialog.dismiss();
//
//            try{
//                JSONObject object = new JSONObject(s);
//                JSONArray jsonArray = object.getJSONArray("data");
//                for(int i = 0;i < jsonArray.length() ; i++){
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    Blog blog = new Blog();
//                    blog.setTitle(jsonObject.getString("first_name"));
//                    blog.setDesc(jsonObject.getString("last_name"));
//                    blog.setPostedBy("Me");
//                    blog.setPostedDate(new Date());
//                    arrayList.add(blog);
//
//                    Toast.makeText(getContext(), jsonObject.get("first_name").toString(), Toast.LENGTH_SHORT).show();
//                }
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            Toast.makeText(getContext(), String.valueOf(arrayList.size()), Toast.LENGTH_SHORT).show();
//            adapter = new HomeAdapter(arrayList, getContext());
//            recyclerView.setAdapter(adapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            adapter.notifyDataSetChanged();
//        }
//    }
//
//}
