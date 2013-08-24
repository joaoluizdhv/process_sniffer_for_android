package com.tarc.learningactivities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
 
public class ListViewMultipleSelectionActivity extends Activity implements
        OnClickListener {
    Button button;
    ListView listView;
    ArrayAdapter<String> adapter;
 
    
    class PInfo {
        private String appname = "";
        private String pname = "";
        private String versionName = "";
        private int versionCode = 0;
        public int userid = 0;
        private Drawable icon;
        private void prettyPrint() {
        	System.out.println((appname + "\t" + pname + "\t" + versionName) + "\t" + versionCode + "\t");
        	//System.out.println(appname);
        	//Log.v("Nome do aplicativo",appname);
        }
    }

    private ArrayList<PInfo> getPackages() {
        ArrayList<PInfo> apps = getInstalledApps(false); /* false = no system packages */
        final int max = apps.size();
        for (int i=0; i<max; i++) {
            apps.get(i).prettyPrint();
        }
        return apps;
    }
    
    @SuppressWarnings("null")
	private ArrayList<String> getPackagesNames() {
        ArrayList<String> nomes = getInstalledAppsNames(false); /* false = no system packages */
        return nomes;
    }
    
    private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {	
    	ArrayList<PInfo> res = new ArrayList<PInfo>();        
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }
            PInfo newInfo = new PInfo();
            newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
            newInfo.pname = p.packageName;
            newInfo.userid = p.applicationInfo.uid;
            newInfo.versionName = p.versionName;
            newInfo.versionCode = p.versionCode;
            newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
            //System.out.println((newInfo.appname + "t" + newInfo.pname + "t" + newInfo.versionName) + "t" + newInfo.versionCode + "t");
            
            res.add(newInfo);
        }
        return res; 
    }
    
    private ArrayList<String> getInstalledAppsNames(boolean getSysPackages) {
        ArrayList<String> res = new ArrayList<String>();        
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }
            PInfo newInfo = new PInfo();
            newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
            newInfo.pname = p.packageName;
            newInfo.userid = p.applicationInfo.uid;
            newInfo.versionName = p.versionName;
            newInfo.versionCode = p.versionCode;
            newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
            //System.out.println((newInfo.appname + "t" + newInfo.pname + "t" + newInfo.versionName) + "t" + newInfo.versionCode + "t");
            
            res.add(newInfo.appname+"->"+newInfo.userid);
        }
        return res; 
    }
    
    
    
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
 
        findViewsById();
 
        //String[] sports = getResources().getStringArray(R.array.sports_array);
        
        ArrayList<PInfo> pi = getPackages();
        ArrayList<String> pi_nomes = getPackagesNames();
        
        //ListAdapter adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, pi_nomes);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, pi_nomes);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
 
        button.setOnClickListener(this);
    }
 
    private void findViewsById() {
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.testbutton);
    }
 
    public void onClick(View v) {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }
 
        String[] outputStrArr = new String[selectedItems.size()];
 
        for (int i = 0; i < selectedItems.size(); i++) {
           outputStrArr[i] = selectedItems.get(i);
        }
 
        Intent intent = new Intent(getApplicationContext(),
                ResultActivity2.class);
 
        // Create a bundle object
        Bundle b = new Bundle();
        b.putStringArray("selectedItems", outputStrArr);
 
        // Add the bundle to the intent.
        intent.putExtras(b);
 
        // start the ResultActivity
        startActivity(intent);
    }
}