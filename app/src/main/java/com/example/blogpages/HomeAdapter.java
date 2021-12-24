package com.example.blogpages;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogpages.myclass.Blog;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeAdapter extends BaseAdapter {

    //        List<Blog> blogList;
    ArrayList<Blog> blogList ;
    Context context;
    LayoutInflater inflater;
    public HomeAdapter(ArrayList<Blog> blogList, Context context) {
        Log.d("HomeAdapter",context.getClass().getName());
        this.blogList = blogList;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    public class ViewHolder{
        TextView blogTitle;
        TextView blogDesc;
        TextView category;
        TextView postBy;
        TextView date;

    }
    @Override
    public int getCount() {
        return blogList.size();
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
            convertView = inflater.inflate(R.layout.blog_row_item, null);
            viewHolder.blogTitle = convertView.findViewById(R.id.tvTitle);
            viewHolder.blogDesc = convertView.findViewById(R.id.smallDesc);
            viewHolder.category = convertView.findViewById(R.id.category);
            viewHolder.postBy = convertView.findViewById(R.id.postedBy);
            viewHolder.date = convertView.findViewById(R.id.tvDate);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.blogTitle.setText("Title : " + blogList.get(position).getTitle());
        viewHolder.blogDesc.setText(blogList.get(position).getSmalldesc());
        viewHolder.category.setText("Category : "+blogList.get(position).getCategory());
        viewHolder.postBy.setText("Posted by : "+blogList.get(position).getPostedBy());
        viewHolder.date.setText("Posted On : "+(CharSequence) blogList.get(position).getPostedDate());

        return convertView;
    }



}


