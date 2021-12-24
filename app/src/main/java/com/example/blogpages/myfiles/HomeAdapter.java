//package com.example.blogpages;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.blogpages.myclass.Blog;
//
//import java.util.ArrayList;
//
//public class HomeAdapter extends  RecyclerView.Adapter<HomeAdapter.ViewHolder>{
//
//    //        List<Blog> blogList;
//    ArrayList<Blog> blogList ;
//    Context context;
//
//    public HomeAdapter(ArrayList<Blog> blogList, Context context) {
//        Log.d("HomeAdapter",context.getClass().getName());
//        this.blogList = blogList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.blog_row_item,parent,false);
//        return new ViewHolder(view);
//    }
//    @Override
//    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
////            Toast.makeText(getContext(),blogList.size(), Toast.LENGTH_SHORT).show();
//        holder.blogTitle.setText(blogList.get(position).getTitle());
////            holder.blogDesc.setText("Desc");
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return blogList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//        TextView blogTitle;
//        TextView blogDesc;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            blogTitle = itemView.findViewById(R.id.tvTitle);
//            blogDesc = itemView.findViewById(R.id.tvDesc);
//        }
//    }
//}
//
//
