package fr099y.lib.tools;

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
	 * Html string dotorh img tag-uudiig buten zamaar ni solih
	 * @param main_path image-iin urd nemegdeh undsen path Example: www.sample.com/uploads/images/
	 */
	public String setFullImgTag(String main_path, String full_body)
	{
		Matcher regexMatcher=getImgSrc(full_body);
		while (regexMatcher.find()) 
		{
			full_body=full_body.replace(regexMatcher.group(1), main_path+regexMatcher.group(1));
		}
		return full_body;
	}
	
	/**
	 * Html string dotroos img tag-uudiig avah
	 */
	public Matcher getImgSrc(String full_body)
	{
		Pattern titleFinder = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>" , Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Matcher regexMatcher = titleFinder.matcher(full_body);
		return regexMatcher;
	}
}
