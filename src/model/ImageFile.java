/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import controller.ImageProcessor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author aminda
 */
public class ImageFile {
    
    private static String imagePath;
    BufferedImage  image;
    int width;
    int height;

    /**
     * @return the imagePath
     */
    public static String getImagePath() {
        return imagePath;
    }

    /**
     * @param aImagePath the imagePath to set
     */
    public static void setImagePath(String aImagePath) {
        imagePath = aImagePath;
    }
    
    public void originalImage(){
        try {
            File input = new File(imagePath);
            image = ImageIO.read(input);
            File current = new File("current.jpg");
            ImageIO.write(image, "jpg", current);
            showImage();
        } catch (IOException|NullPointerException ex) {
            Logger.getLogger(ImageFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showImage(){
        System.out.println("before");
        System.out.println(System.getProperty("java.class.path"));
        try {
            ImageProcessor.refCntrlPnel.showImage();
        } catch (Exception e) {
            System.out.print(e);
        }       
        System.out.println("after");
    }
    
    public void greyScaleImage(){
        try {
         File input = new File("current.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();      
         for(int i=0; i<height; i++){        
            for(int j=0; j<width; j++){            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *0.114);
               Color newColor = new Color(red+green+blue,               
               red+green+blue,red+green+blue);              
               image.setRGB(j,i,newColor.getRGB());
            }
         }        
         File ouptut = new File("current.jpg");
         ImageIO.write(image, "jpg", ouptut);        
        } catch (Exception e) {}
        showImage();
    }
    
    public void brightness(int change){
        try {
         File input = new File("current.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight(); 
         int red=255;
         int green=255;
         int blue=255;
         for(int i=0; i<height; i++){        
            for(int j=0; j<width; j++){            
               Color c = new Color(image.getRGB(j, i));
               red = (int)((255-c.getRed()) * (change/50) + c.getRed());
               green = (int)( (255-c.getGreen()) * (change/50) + c.getGreen());
               blue = (int)( (255-c.getBlue()) * (change/50) + c.getGreen());
               
               Color newColor = new Color(red,green,blue);              
               image.setRGB(j,i,newColor.getRGB());
            }
         }  
         System.out.println(red+" "+green+" "+blue);
         File ouptut = new File("current.jpg");
         ImageIO.write(image, "jpg", ouptut);        
        } catch (Exception e) {}
        showImage();
    } 
    
}
