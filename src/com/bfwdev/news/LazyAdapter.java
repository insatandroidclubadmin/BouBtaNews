package com.bfwdev.news;

import java.util.ArrayList;
import java.util.HashMap;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_articles, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView description = (TextView)vi.findViewById(R.id.desciption); // artist name
        TextView pubDate = (TextView)vi.findViewById(R.id.pubDate); // duration
        TextView link=(TextView)vi.findViewById(R.id.link); // thumb image
        TextView image=(TextView)vi.findViewById(R.id.image);
        ImageView imagev=(ImageView)vi.findViewById(R.id.imageView1);
        
        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
        
        // Setting all values in listview
        title.setText(song.get(Home.KEY_TITLE));
        pubDate.setText(song.get(Home.KEY_PUBDATE));
        description.setText(song.get(Home.KEY_DESC));
        link.setText(song.get(Home.KEY_LINK));
        image.setText(song.get(Home.KEY_IMAGE));
        imageLoader.DisplayImage(song.get(Home.KEY_IMAGE), imagev);
        
        //imageLoader.DisplayImage(song.get(Home.KEY_IMAGE), image);
        return vi;
    }
}