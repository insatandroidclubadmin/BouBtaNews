package com.bfwdev.news;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;




import com.actionbarsherlock.ActionBarSherlock.OnOptionsItemSelectedListener;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;


import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;





import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
//import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends SherlockActivity implements ActionBar.TabListener{

	// All static variables
	private String URL = "http://www.lemonde.fr/rss/une.xml";
	// XML node keys
	static final String KEY_ITEM = "item"; // parent node
	// static final String KEY_ID = "id";
	static final String KEY_TITLE = "title";
	static final String KEY_PUBDATE = "pubDate";
	static final String KEY_DESC = "description";
	static final String KEY_LINK = "link";
	static final String KEY_IMAGE = "enclosure";
	private HashMap<String, String> map;
	private String choix;

	//Menu menu;

	//SimpleCursorAdapter adapter1;
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu subMenu1 = menu.addSubMenu("Rubriques");
        subMenu1.add("A La Une");
        subMenu1.add("Tunisie");
        subMenu1.add("International");
        subMenu1.add("Politique");
        subMenu1.add("Société");
        subMenu1.add("Economie");
        subMenu1.add("Sport");
        subMenu1.add("Technologie");
        subMenu1.add("Culture");
        subMenu1.add("Santé");
        

        
        MenuItem subMenu1Item = subMenu1.getItem();
        subMenu1Item.setIcon(R.drawable.play);
        subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        
        
        SubMenu subMenu2 = menu.addSubMenu("Menu");
        subMenu2.add("A Propos");


        MenuItem subMenu2Item = subMenu2.getItem();
        subMenu2Item.setIcon(R.drawable.heart);


        return super.onCreateOptionsMenu(menu);
    }
	
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getTitle()=="A Propos"){
		AlertDialog alertDialog = new AlertDialog.Builder(this,R.style.Sherlock___Theme_Dialog).create();
		AlertDialog a = new AlertDialog.Builder(this).create();
			// Setting Dialog Title
			alertDialog.setTitle("A Propos");	
			
			// Setting Dialog Message
			
			alertDialog.setMessage("BouBtaNews V1.0\nRéalisé Par:\nDellel Firas\nBarhoumi Bilel\nLakhal Wafa\nINSAT ANDROID CLUB\nIAC");
			
			// Setting Icon to Dialog
			//alertDialog.setIcon(R.drawable.tick);	

			// Setting OK Button
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		        // Write your code here to execute after dialog closed
		        //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
		        }
			});
			
			// Showing Alert Message
			alertDialog.show();
		}
		
		if (item.getTitle()=="A La Une"){
			getSupportActionBar().getTabAt(0).setText("A La Une");
			URL="http://www.lemonde.fr/rss/une.xml";
			loadparsing();
			}
		if (item.getTitle()=="Tunisie"){
			getSupportActionBar().getTabAt(0).setText("Tunisie");
			URL="http://www.lemonde.fr/rss/tag/tunisie.xml";
			loadparsing();
			}
		if (item.getTitle()=="International"){
			getSupportActionBar().getTabAt(0).setText("International");
			URL="http://www.lemonde.fr/rss/tag/international.xml";
			loadparsing();
			}
		if (item.getTitle()=="Politique"){
			getSupportActionBar().getTabAt(0).setText("Politique");
			URL="http://www.lemonde.fr/rss/tag/politique.xml";
			loadparsing();
			}
		if (item.getTitle()=="Société"){
			getSupportActionBar().getTabAt(0).setText("Société");
			URL="http://www.lemonde.fr/rss/tag/societe.xml";
			loadparsing();
			}
		if (item.getTitle()=="Economie"){
			getSupportActionBar().getTabAt(0).setText("Economie");
			URL="http://www.lemonde.fr/rss/tag/economie.xml";
			loadparsing();
			}
		if (item.getTitle()=="Sport"){
			getSupportActionBar().getTabAt(0).setText("Sport");
			URL="http://www.lemonde.fr/rss/tag/sport.xml";
			loadparsing();
			}
		if (item.getTitle()=="Technologie"){
			getSupportActionBar().getTabAt(0).setText("Technologie");
			URL="http://www.lemonde.fr/rss/tag/technologies.xml";
			loadparsing();
			}
		if (item.getTitle()=="Culture"){
			getSupportActionBar().getTabAt(0).setText("Culture");
			URL="http://www.lemonde.fr/rss/tag/culture.xml";
			loadparsing();
			}
		if (item.getTitle()=="Santé"){
			getSupportActionBar().getTabAt(0).setText("Santé");
			URL="http://www.lemonde.fr/rss/tag/sante.xml";
			loadparsing();
			}
		return true;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Sherlock___Theme_DarkActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
	
		
        
      //ActionBar
        
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		getSupportActionBar().setTitle("BBN");
		ActionBar.Tab tab = getSupportActionBar().newTab();
        tab.setText("A La Une");
        tab.setTabListener(this);

        
        getSupportActionBar().addTab(tab);

        
        ActionBar.Tab tab2 = getSupportActionBar().newTab();
        tab2.setText("");
        tab2.setIcon(R.drawable.icon_plus);
        tab2.setTabListener(this);
        getSupportActionBar().addTab(tab2);

        //Parsing + Insertion dans listview
        loadparsing();
       
		
	

	}

	public static boolean isNetworkConnected(Context context) {
		boolean value = false; 
    	ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
    	NetworkInfo info = manager.getActiveNetworkInfo(); 
 
    	 if (info == null | info.isAvailable()==false) 
    	{
 
    	}
    	else if (info != null && info.isAvailable()) 
    	{ 
    	value = true; 
		}
    	 return value; 
	}
	
	public void loadparsing(){
		if(isNetworkConnected(this)){
		 ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

			XMLParser parser = new XMLParser();
			String xml = parser.getXmlFromUrl(URL); // getting XML
			Document doc = parser.getDomElement(xml); // getting DOM element

			NodeList nl = doc.getElementsByTagName(KEY_ITEM);
			// looping through all item nodes <item>
			for (int i = 0; i < nl.getLength(); i++) {
				// creating new HashMap
				map = new HashMap<String, String>();
				Element e = (Element) nl.item(i);
				// adding each child node to HashMap key => value
				// map.put(KEY_ID, parser.getValue(e, KEY_ID));
				map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
				map.put(KEY_PUBDATE, parser.getValue(e, KEY_PUBDATE));
				map.put(KEY_DESC, parser.getValue(e, KEY_DESC));
				map.put(KEY_LINK, parser.getValue(e, KEY_LINK));
				map.put(KEY_IMAGE, parser.getValue(e, KEY_IMAGE));

				// adding HashList to ArrayList
				menuItems.add(map);
			}

			final ListView lv = (ListView) findViewById(R.id.list);
	        lv.setAdapter(new LazyAdapter(this, menuItems));
			// Adding menuItems to ListView
			//ListAdapter adapter = new LazyAdapter(this, menuItems);
			
			//setListAdapter(adapter);

			// selecting single ListView item
			//ListView lv = getListView();

			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// getting values from selected ListItem
					String name = ((TextView) view.findViewById(R.id.title))
							.getText().toString();
					String cost = ((TextView) view.findViewById(R.id.pubDate))
							.getText().toString();
					String description = ((TextView) view
							.findViewById(R.id.desciption)).getText().toString();
					String link = ((TextView) view.findViewById(R.id.link))
							.getText().toString();
					String image = ((TextView) view.findViewById(R.id.image))
							.getText().toString();

					// Starting new intent
					Intent in = new Intent(getApplicationContext(),
							ArticleView.class);
					in.putExtra(KEY_TITLE, name);
					in.putExtra(KEY_PUBDATE, cost);
					in.putExtra(KEY_DESC, description);
					in.putExtra(KEY_IMAGE, image);
					in.putExtra(KEY_LINK, link);

					startActivity(in);

				}
			});}
		else
		{
			Toast.makeText(getParent(), "Problème de Connexion Internet", 3500);
		}
	}
	@Override
	public void onTabSelected(Tab tab,
			android.support.v4.app.FragmentTransaction ft) {
		if((tab.getPosition()==(getSupportActionBar().getTabCount()-1))&&(tab.getPosition()!=0)){
			if ((getSupportActionBar().getTabAt(getSupportActionBar().getTabCount()-1)).getText().equals("")){
			final CharSequence[] items = {"Tunisie","International","Politique","Société","Economie","Sport","Technologie","Culture","Santé"};
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    builder.setTitle("Ajouter aux Favoris");
		    //builder.setIcon(R.drawable.icon);
		    final String rubrique;
		    builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int item) {
		     Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
		     
		        choix = items[item].toString();
		       
		        }
		    });
		 // Setting Positive "Yes" Button
		    builder.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,int which) {
	 
	            	getSupportActionBar().getTabAt(getSupportActionBar().getTabCount()-1).setIcon(null);
	   		     getSupportActionBar().getTabAt(getSupportActionBar().getTabCount()-1).setText(choix);
	   		     getSupportActionBar().setSelectedNavigationItem(0);
	   		     if (getSupportActionBar().getTabCount()<3){
	   		     ActionBar.Tab tab2 = getSupportActionBar().newTab();
	   		        tab2.setText("");
	   		        tab2.setIcon(R.drawable.icon_plus);
	   		        tab2.setTabListener(Home.this);
	   		        getSupportActionBar().addTab(tab2);}
	   		        }
	        });
	 
	        // Setting Negative "NO" Button
		    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            	getSupportActionBar().setSelectedNavigationItem(0);
	            dialog.cancel();
	            }
	        });
		    AlertDialog alert = builder.create();
		    alert.show();
		}
		
	}
	if (!(tab.getText().equals(""))){
		if (tab.getText().equals("")){
	
			URL="http://www.lemonde.fr/rss/une.xml";
			loadparsing();
			}
		if (tab.getText().equals("Tunisie")){

			URL="http://www.lemonde.fr/rss/tag/tunisie.xml";
			loadparsing();
			}
		if (tab.getText().equals("International")){

			URL="http://www.lemonde.fr/rss/tag/international.xml";
			loadparsing();
			}
		if (tab.getText().equals("Politique")){

			URL="http://www.lemonde.fr/rss/tag/politique.xml";
			loadparsing();
			}
		if (tab.getText().equals("Société")){

			URL="http://www.lemonde.fr/rss/tag/societe.xml";
			loadparsing();
			}
		if (tab.getText().equals("Economie")){

			URL="http://www.lemonde.fr/rss/tag/economie.xml";
			loadparsing();
			}
		if (tab.getText().equals("Sport")){
	
			URL="http://www.lemonde.fr/rss/tag/sport.xml";
			loadparsing();
			}
		if (tab.getText().equals("Technologie")){

			URL="http://www.lemonde.fr/rss/tag/technologies.xml";
			loadparsing();
			}
		if (tab.getText().equals("Culture")){

			URL="http://www.lemonde.fr/rss/tag/culture.xml";
			loadparsing();
			}
		if (tab.getText().equals("Santé")){

			URL="http://www.lemonde.fr/rss/tag/sante.xml";
			loadparsing();
			}
		
	}
		
	}

	@Override
	public void onTabUnselected(Tab tab,
			android.support.v4.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab,
			android.support.v4.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
}