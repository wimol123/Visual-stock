package th.co.gosoft.audit.cpram.testUnit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import th.co.gosoft.audit.cpram.utils.PasswordGenerator;

public class TextToImage{
	
	
	public static void execute() throws IOException {
		
		PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
				.useDigits(true)
				.useLower(true)
				.usePunctuation(true)
				.useUpper(true)
				.build();
		String password = passwordGenerator.generate(6);
		
	    String fileName = "TextToImage";
	    File newFile = new File("D:\\Test\\" + fileName + ".jpeg");
	    
	    Font font = new Font("Tahoma", Font.PLAIN, 50);
	    
	  //create the FontRenderContext object which helps us to measure the text
	    FontRenderContext frc = new FontRenderContext(null, true, true);
	    
	  //get the height and width of the text
	    Rectangle2D bounds = font.getStringBounds(password, frc);
	    int w = (int) bounds.getWidth();
	    int h = (int) bounds.getHeight();
	    
	  //create a BufferedImage object
	    BufferedImage image = new BufferedImage(w, h,   BufferedImage.TYPE_INT_RGB);
	            
	  //calling createGraphics() to get the Graphics2D
	    Graphics2D graphic2D = image.createGraphics();
	    
	  //set color and other parameters
        graphic2D.setColor(Color.WHITE);
        graphic2D.fillRect(0, 0, w, h);
        graphic2D.setColor(Color.BLACK);
        graphic2D.setFont(font);
             
        graphic2D.drawString(password, (float) bounds.getX(), (float) -bounds.getY());
        
      //releasing resources
        graphic2D.dispose();
        
       
          //creating the file
         ImageIO.write((RenderedImage)image, "jpeg", newFile);
	}
	
	
    
    
}
