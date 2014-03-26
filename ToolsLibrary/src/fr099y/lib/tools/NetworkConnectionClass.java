package fr099y.lib.tools;

import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkConnectionClass {
	
	static Boolean isNetAvailable(Context con, boolean isMobileData, String url)  {

	    try{
	        ConnectivityManager connectivityManager = (ConnectivityManager) 
	        con.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	        if(wifiInfo.isConnected() && isConnectedToServer(url))
	        	return true;
	        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	        if(mobileInfo.isConnected() && isMobileData && isConnectedToServer(url))
	        	return true;
	    }
	    catch(Exception e){
	       e.printStackTrace();
	    }
	    return false;
	}
	static boolean isConnectedToServer(String url) {
	    try{
	        URL myUrl = new URL(url.toString());
	        URLConnection connection = myUrl.openConnection();
	        connection.setConnectTimeout(7000);
	        connection.connect();
	        return true;
	    } catch (Exception e) {
	        // Handle your exceptions
	    	Log.d("TEST", "e="+e);
	        return false;
	    }
	}

}
