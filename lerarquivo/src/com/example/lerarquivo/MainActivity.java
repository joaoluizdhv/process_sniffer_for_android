package com.example.lerarquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.ContextWrapper;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	 private TextView txtRoot;
	 private TextView txtNomeArq;
	 private TextView txtSalvar;
	 private TextView txtLer;
	 private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button b = (Button) findViewById(R.id.btLerArquivo);
		final EditText ab = (EditText) findViewById(R.id.txtpro);
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				try {
					Process root = Runtime.getRuntime().exec("su");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String lstrNomeArq = "/proc/net/udp";
		        File arq; 
		        String lstrlinha;
		        ArrayList<String> a = new ArrayList<String>();
		        try
		        {
		        	
		            //pega o nome do arquivo a ser lido
		         
		 
		            //limpa a caixa de texto que irá receber os dados do arquivo
		            
		 
		             
		            arq = new File(Environment.getExternalStorageDirectory(), lstrNomeArq);
		            
		            BufferedReader br = new BufferedReader(new FileReader(lstrNomeArq));
		            //efetua uma leitura linha a linha do arquivo a carrega 
		            //a caixa de texto com a informação lida
		            
		            while ((lstrlinha = br.readLine()) != null) {
		            	ab.setText(lstrlinha);
		                
		            }
		            
		        } catch (Exception e) {
		        	//ab.setText("qualquer merda");
		            //trace("Erro : " + e.getMessage());
		        }
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
