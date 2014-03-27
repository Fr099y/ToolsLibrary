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
	 * <br>
	 * <strong>HTML</strong> өгөгдөл уншиж байгаа үед заримдаа зургын замыг бүтэн бичээгүй байдаг. <strong>"<img src="uploads/sample.png">"</strong>. Эдгээр нь browser дээрээс зураг харагдах боловч Mobile дээр зургын зам
	 * бүтэн биш учир харагдадгүй. Үнийг засахын тулд зургын замыг солих шаардлагатай. 
	 * @param main_path <strong>img</strong> таг-уудын урд байвал зохих үндсэн зам
	 * @param full_body <strong>HTML</strong> хэлбэрээр ирж буй өгөгдөл. 
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
	 * <br>
	 * <strong>HTML</strong> өгөгдөл дотороос <strong>img</strong> таг-уудын утгыг авах. Зөвхөн зургыг нь авч ашиглах тохиолдолд хэрэглэнэ.
	 * @param full_body <strong>HTML</strong> бүтэцтэй өгөгдөл
	 */
	public Matcher getImgSrc(String full_body)
	{
		Pattern titleFinder = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>" , Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Matcher regexMatcher = titleFinder.matcher(full_body);
		return regexMatcher;
	}
}
