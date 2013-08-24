package com.tarc.learningactivities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ResultActivity extends Activity {
	
	class PInfo {
	    private String appname = "";
	    private String pname = "";
	    private String versionName = "";
	    private int versionCode = 0;
	    private Drawable icon;
	    private void prettyPrint() {
	    	System.out.println((appname + "t" + pname + "t" + versionName) + "t" + versionCode + "t");
	    	//Log.v(appname + "\t" + pname + "\t" + versionName + "\t" + versionCode, appname);
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
	        newInfo.versionName = p.versionName;
	        newInfo.versionCode = p.versionCode;
	        newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
	        res.add(newInfo);
	    }
	    return res; 
	}
	
	ArrayList<PInfo> pacotes;
	
	ApplicationInfo packageInfo;
	
	@Override
	public void unbindService(ServiceConnection conn) {
		// TODO Auto-generated method stub
		super.unbindService(conn);
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		// Para lidar com o Toast, para ajudar a debugar
		final Context context = getApplicationContext();
		final int duration = Toast.LENGTH_SHORT;
		
		// Cria spinner
		Spinner appspinner = (Spinner) findViewById(R.id.spinner1);
		
		//Usa o PackageManager para pegar a lista dos aplicativos instalados
		final PackageManager pm_aux = getPackageManager();
		//get a list of installed apps.
        List<ApplicationInfo> packages_aux = pm_aux.getInstalledApplications(
                PackageManager.GET_META_DATA);
        //cria um array com todos os aplicativos instalados
		final ArrayAdapter<ApplicationInfo> adapter = new ArrayAdapter<ApplicationInfo>(this, android.R.layout.simple_spinner_item, packages_aux);                           
		appspinner.setAdapter(adapter);
		        
		
		//final TextView tv = (TextView) findViewById(R.id.textView1);
		//Quando um item da lista for selecionado 
		appspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

	        @Override
	        public void onItemSelected(AdapterView<?> parent, View arg1,
	                int app_pos, long app_id) {
	        // TODO Auto-generated method stub
	        //passa as informacoes contidas na linha selecionada e salva em app_selected
	        String app_selected=parent.getItemAtPosition(app_pos).toString();
	        ApplicationInfo app_selecionado = (ApplicationInfo) parent.getItemAtPosition(app_pos);
	        CharSequence text = app_selecionado.className;
	        
	        
	        //int t = 1;
	        //tv.setText(t);
	        // Cria um toast para exibir as informacoes da linha
	        //CharSequence text = app_selected;
    		Toast toast = Toast.makeText(context, text, duration);
    		toast.show();
	        
	        
	        final PackageManager pm = getPackageManager();
	        //get a list of installed apps.
	        List<ApplicationInfo> packages = pm.getInstalledApplications(
	                PackageManager.GET_META_DATA);
	        long UID = 0;
	        
	        //loop through the list of installed packages and see if the selected
	        //app is in the list
	        for (ApplicationInfo packageInfo : packages) {
	            //UID = packageInfo.uid;
	        	if(packageInfo.packageName.equals(app_selected)){
	                //get the UID for the selected app
	                UID = packageInfo.uid;

	            }

	            
	            //CharSequence text = "UID is: " + UID;
        		//Toast toast = Toast.makeText(context, text, duration);
        		//toast.show();
	           //Do whatever with the UID
	           Log.i("Check UID", "UID is: " + UID);


		    }
	
		    
	    
	        }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	});
		

			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}


