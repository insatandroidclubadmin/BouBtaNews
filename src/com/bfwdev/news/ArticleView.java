package com.bfwdev.news;



import com.actionbarsherlock.app.ActionBar;




import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;


public class ArticleView  extends SherlockActivity implements ActionBar.TabListener{
	
	// XML node keys
	static final String KEY_TITLE = "title";
	static final String KEY_PUBDATE = "pubDate";
	static final String KEY_DESC = "description";
	static final String KEY_LINK = "link";
	static final String KEY_IMAGE = "enclosure";
	
	ImageLoader loader;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		setTheme(SampleList.THEME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_view);
        
    
        
        
        // getting intent data
        Intent in = getIntent();
        
        //
        loader = new ImageLoader(this);
        
        // Get XML values from previous intent
        String title = in.getStringExtra(KEY_TITLE);
        String pubDate = in.getStringExtra(KEY_PUBDATE);
        String description = in.getStringExtra(KEY_DESC);
        String link = in.getStringExtra(KEY_LINK);
        String link_image = in.getStringExtra(KEY_IMAGE);
        System.out.println(link);
        
        // Displaying all values on the screen
        TextView lblTitle = (TextView) findViewById(R.id.title_label);
        TextView lblDate = (TextView) findViewById(R.id.pubDate_label);
        
       //TextView lblDesc = (TextView) findViewById(R.id.image_label);
        ImageView image = (ImageView)findViewById(R.id.imageView1);
        //Uri uri = new Uri(link_image);
//        image.setImageURI(uri);
        loader.DisplayImage(link_image, image);
        
        WebView webView = (WebView)findViewById(R.id.webView1);
        webView.loadUrl(link);
        
        
        image.setImageResource(R.drawable.icon);
        lblTitle.setText(title);
        lblDate.setText(pubDate);
        //lblDate.setText(link_image);
           //lblDesc.setText(description);
       
    }

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		TextView lblDate = (TextView) findViewById(R.id.pubDate);
		lblDate.setText("selected");
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
