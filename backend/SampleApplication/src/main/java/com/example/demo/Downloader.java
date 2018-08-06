package com.example.demo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloader {
	public static void downloadFile(URL url, String file) throws Exception{
		//URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        String name = "not retrieved";
        String str[]=url.toString().split("/");
        Pattern p = Pattern.compile(".mp4$");
        
        FileOutputStream fis = new FileOutputStream(file);
        name = str[str.length-1].replaceAll(".mp4", "");
        System.out.println("Name of the video: "+ name);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        File file1 =new File(file);
        fis.close();
        bis.close();
	}
}
