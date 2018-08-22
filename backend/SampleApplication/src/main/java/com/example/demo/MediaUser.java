package com.example.demo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Users")
public class MediaUser {
	@Id
	String username;
	List<MediaFile> media;
	String password;
	String role;
	public MediaUser(String username,List<MediaFile> media,String password,String role) {
		this.username = username;
		this.media = media;
		this.setPassword(password);
		this.role = role;
	}
	public void setNewPassword(String password) {
		this.password = getMD5(password);
	}
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		System.out.println("GETPASS-> Password for "+ this.username+ " is "+ this.password);
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
		System.out.println("SETPASS-> Password for "+ this.username+ " is "+ this.password);
	}
	
	public boolean checkPasswordMatch(String password) {
		if(this.password!= null) {
			String testPass = getMD5(password);
			if(this.password == testPass) {
				return true;
			}
			else
				return false;
		}
		else
			return false;
		
	}
	public List<MediaFile> getMedia() {
		return media;
	}
	public void setMedia(List<MediaFile> media) {
		this.media = media;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void addMedia(MediaFile m) {
		this.media.add(m);
	}
	
	public void removeMedia(MediaFile m) {
		this.media.remove(m);
	}
	
	@Override
	public String toString() {
		return "Username: "+this.username+"\n Password: "+this.getPassword()+"\nRole: "+this.getRole();
	}
	
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	
}
