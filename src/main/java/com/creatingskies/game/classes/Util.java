package com.creatingskies.game.classes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

public final class Util {

	public static byte[] fileToByteArray(File file){
		if(file != null){
			byte[] image = new byte[(int) file.length()];
			
			try {
			     FileInputStream fileInputStream = new FileInputStream(file);
			     fileInputStream.read(image);
			     fileInputStream.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
			
			return image;
		}
		return null;
	}
	
	public static byte[] imageToByteArray(Image image,String fileType){
		
		try {
			BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			
			ImageIO.write(bImage, fileType, s);
			
			return s.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Image byteArrayToImage(byte[] image){
		if(image != null){
			try {
				BufferedImage img = ImageIO.read(new ByteArrayInputStream(image));
				return SwingFXUtils.toFXImage(img, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String getFileExtension(String fileName){
		if(fileName != null){
			String extension = "";

			int dotPos = fileName.lastIndexOf('.');
			int slashPos = Math.max(fileName.lastIndexOf('/'),
					fileName.lastIndexOf('\\'));

			if (dotPos > slashPos) {
			    extension = fileName.substring(dotPos+1);
			}
			return extension;
		}
		return null;
	}
	
}
