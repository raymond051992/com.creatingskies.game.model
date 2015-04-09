package com.creatingskies.game.classes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import javax.imageio.ImageIO;

public final class Util {

	public static boolean isBlank(String val){
		if(val == null){
			return true;
		}
		if(val.trim().isEmpty()){
			return true;
		}
		return false;
	}
	
	public static boolean isZero(String val){
		if(val == null){
			return true;
		}
		if(val.trim().isEmpty()){
			return true;
		}
		if(Integer.parseInt(val) == 0){
			return true;
		}
		return false;
	}
	
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
	
	public static EventHandler<KeyEvent> createIntegerOnlyKeyEvent(){
		return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (!t.getCharacter().matches("\\d")) {
                    t.consume();
                }
            }
        };
	}
	
	public static ChangeListener<String> createIntegerOnlyChangeListener(TextField textField,Integer maxValue){
		return new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                	if(newValue != null && !newValue.isEmpty()){
                		Integer val = Integer.parseInt(newValue);
                        if(maxValue != null && val > maxValue){
                        	throw new Exception("Exceeds max value.");
                        }
                	}else{
                		textField.setText(newValue);
                	}
                } catch (Exception e) {
                	textField.setText(oldValue);
               }
            }
        };
	}
	
	public static Date toDate(LocalDate localDate) {
		if(localDate != null){
	        LocalDate ld = localDate;
	        Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	        Date date = Date.from(instant);

	        return date;
		}else{
			return null;
		}
    }
	
	public static LocalDate toLocalDate(Date d) {
		if(d != null){
			Instant instant = Instant.ofEpochMilli(d.getTime());
	        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	        return localDate;
		}else{
			return null;
		}
    }
	
	public static int getHourFromDate(Date date){
		if(date != null){
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.setTime(date);
			return cal.get(Calendar.HOUR);
		}else{
			return 0;
		}
	}
	
	public static int getMinuteFromDate(Date date){
		if(date != null){
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.setTime(date);
			return cal.get(Calendar.MINUTE);
		}else{
			return 0;
		}
	}
	
	public static String getAMPMFromDate(Date date){
		if(date != null){
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.setTime(date);
			if(cal.get(Calendar.AM_PM) == 0){
				return "AM";
			}else{
				return "PM";
			}
		}else{
			return null;
		}
	}
}
