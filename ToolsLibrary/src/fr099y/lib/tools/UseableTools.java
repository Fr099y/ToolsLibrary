package fr099y.lib.tools;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;


public class UseableTools {
	
	private final Context ctx;
	public UseableTools(Context ctx)
	{
		this.ctx=ctx;
	}
	
	/**
	 * <br>
	 * <strong>HTML</strong> өгөгдлөөс өгөгдсөн таг дахь өгөгдсон аttribut-ын утгыг <strong>ArrayList</strong> төрлөөр авах
	 * @param html_data <strong>HTML</strong> хэлбэрээр ирж буй өгөгдөл. 
	 * @param tag tag-ийн нэр
	 * @param attribute авах өгөгдлийн attribute-ийн мэдээлэл
	 */
	public ArrayList<String> getAttributeValues(String html_data, String tag, String attribute)
	{
		ArrayList<String> values=new ArrayList<String>();
		Matcher regexMatcher=getAttributes(html_data, tag, attribute);
		while (regexMatcher.find()) 
		{
			values.add(regexMatcher.group(1));
//			full_body=full_body.replace(regexMatcher.group(1), main_path+regexMatcher.group(1));
		}
		return values;
	}
	
	/**
	 * <br>
	 * <strong>HTML</strong> өгөгдлөөс өгөгдсөн таг дахь өгөгдсон аttribut-ын утгыг <strong>Matcher</strong> төрлөөр авах
	 * @param html_data <strong>HTML</strong> хэлбэрээр ирж буй өгөгдөл. 
	 * @param tag tag-ийн нэр
	 * @param attribute авах өгөгдлийн attribute-ийн мэдээлэл
	 */
	public Matcher getAttributes(String html_data, String tag, String attribute)
	{
		Pattern titleFinder = Pattern.compile("<"+tag+"[^>]+"+attribute+"\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>" , Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Matcher regexMatcher = titleFinder.matcher(html_data);
		return regexMatcher;
	}
}
