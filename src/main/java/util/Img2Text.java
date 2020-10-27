/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import net.sourceforge.tess4j.Tesseract; 
import net.sourceforge.tess4j.TesseractException; 

/**
 *
 * @author polyfish
 */
public class Img2Text {
    Tesseract tesseract = new Tesseract();
    
    public String getText(String imgPath) {
        try { 
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            tesseract.setDatapath("resources/tessdata");
            String text = tesseract.doOCR(new File(imgPath));
            text = text.replaceAll("[^a-zA-Z]", "");
            text = text.toLowerCase();
            return text;
        } 
        catch (TesseractException e) {
        }
        
        return "";
    }
    
    public static void main(String[] args) {
        Img2Text i2t = new Img2Text();
        
        System.out.println(i2t.getText("resources/image.png"));
    }
}
