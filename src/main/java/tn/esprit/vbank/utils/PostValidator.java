package tn.esprit.vbank.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import okhttp3.*;

public class PostValidator {

	public static boolean validateImage(String filePath) {

		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {

			Path path = new File(filePath).toPath();
			String mimeType = null;
			try {
				mimeType = Files.probeContentType(path);
			} catch (IOException e) {
				return false;

			}

			if (mimeType != null && mimeType.startsWith("image")) {
				return true;

			}
		}
		return false;

	}

	public static Boolean validateVideo(String filePath) {

		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {

			Path path = new File(filePath).toPath();
			String mimeType = null;
			try {
				mimeType = Files.probeContentType(path);
			} catch (IOException e) {
				return false;

			}

			if (mimeType != null && mimeType.startsWith("video")) {
				return true;

			}
		}
		return false;
	}

	public static String filterBadWorlds(String data) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();

		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, data);

		Request request = new Request.Builder().url("https://api.apilayer.com/bad_words?censor_character=*")
				.addHeader("apikey", "RFwZDlnTnLAz1v2gTOBL2dUsH0uVVpuh").method("POST", body).build();
		Response response = client.newCall(request).execute();
		Object obj = null;
		try {
			obj = new JSONParser().parse(response.body().string());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject jo = (JSONObject) obj;

		String censored_content = (String) jo.get("censored_content");
		Long bad_words_total = (Long) jo.get("bad_words_total");
		System.out.println(response.body().string());
		return censored_content;

	}
	
	public static String detectImageNudity(String filePath) throws IOException {
		
		File f = new File(filePath);
		Path path = new File(filePath).toPath();
		String mimeType = null;
		try {
			mimeType = Files.probeContentType(path);
		} catch (IOException e) {

		}
		OkHttpClient client = new OkHttpClient().newBuilder().build();

	    MediaType mediaType = MediaType.parse(mimeType);
	    RequestBody body = RequestBody.create(mediaType,f);

	    Request request = new Request.Builder()
	      .url("https://api.apilayer.com/nudity_detection/upload")
	      .addHeader("apikey", "qza2ZExnvxJ8LVvoIWLeS2cRNJt02Jmq")
	      .method("POST", body)
	      .build();
	    Response response = client.newCall(request).execute();
	    //System.out.println(response.body().string());

	    //String s = response.body().string();
	    Object obj = null;
		try {
			obj = new JSONParser().parse(response.body().string());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		JSONObject jo = (JSONObject) obj;

		Long value = (Long) jo.get("value");
		String description = (String) jo.get("description");
	    
	    if(value>1) {
	    	return description;
	    }
	    else {
	    	return null;

	    }
	  

		
		
		
		
	}
}
