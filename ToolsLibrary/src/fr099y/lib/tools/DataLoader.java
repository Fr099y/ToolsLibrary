package fr099y.lib.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class DataLoader extends AsyncTask<Object, Object, Object>{

	private DataLoaderListener listener;
	private final Activity act;
	private final boolean isMobileDataEnabled;
	private final DataLoaderDB db;
	
	/**
	 * <br>
	 * <strong>AsyncTask</strong>-аас удамшсан класс бөгөөд <strong>execute</strong> хийхдээ дамжуулах обьектийн 0 дугаар элемэнтийг  <strong>String</strong> төрлөөр(өгөгдөл унших URL), 
	 * 1 дүгээр элемэнтийг boolean(өгөгдлийг баазад хадгалах эсэх. Дараа нь интернэтгүй орчинд өгөгдөл уншиж харуулах боломжтой default:true) төрөлтэй зааж өгнө. 
	 * Тухайн зааж өгсөн URL-д байрлах өгөгдлийг уншина. Ирсэн өгөгдлийг яаж хөрвүүлж ашиглах нь таны сонголтын хэрэг. JSONArray, JSONObject, XML гэх мэт.
	 * 
	 * @param act дуудаж буй класс-ын <strong>Activity</strong>
	 * 
	 * @param listener <strong>DataLoaderLister</strong> interface. Энэ ToolsLibrary санд агуулагдаж байгаа. Дэлгэрэнгүйг <i>DataLoaderListener</i>-с харна уу.
	 * 
	 * @param isMobileDataEnabled өгөгдлийг уншихдаа MobileData ашиглах эсэх. true ашиглана:false ашиглахгүй
	 */
	public DataLoader(Activity act, DataLoaderListener listener, boolean isMobileDataEnabled)
	{
		this.listener=listener;
		this.act=act;
		this.isMobileDataEnabled=isMobileDataEnabled;
		this.db=new DataLoaderDB(act);
	}
	@Override
	protected void onPreExecute()
	{
		
	}
	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		String url=null;
		boolean isFromDB=true;
		try {
			url=(String)params[0];	
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		try {
			isFromDB=(Boolean)params[1];
		} catch (Exception e) {
			// TODO: handle exception
		}
		InputStream is = null;
		try{
			if(NetworkConnectionClass.isNetAvailable(act, isMobileDataEnabled, url))
			{
				HttpClient httpclient = new DefaultHttpClient();
	            HttpGet httppost = new HttpGet(url);
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
	            is = entity.getContent();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                    sb.append(line + "\n");
	            }
	            is.close();
	            if(isFromDB)
	            	db.insertData(url, sb.toString());
	            return sb.toString();
			}
			else if(isFromDB)
			{
				return db.getData(url);
			}
			else
				return null;
	    }catch(Exception e){
	            Log.e("TEST", "Error converting result "+e.toString());
	            return null;
	    }
	}
	@Override
	protected void onPostExecute(Object result)
	{
		listener.onPostExecuted((String)result);
	}

}
