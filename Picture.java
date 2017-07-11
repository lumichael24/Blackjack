import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture {
	public static final String ace = "ace.png";
	public static final String two = "two.png";
	public static final String three = "three.png";
	public static final String four = "four.png";
	public static final String five = "five.png";
	public static final String six = "six.png";
	public static final String seven = "seven.png";
	public static final String eight = "eight.png";
	public static final String nine = "nine.png";
	public static final String ten = "ten.png";
	public static final String jack = "jack.png";
	public static final String queen = "queen.png";
	public static final String king = "king.jpg";
	
	public static BufferedImage getImage(String file) {
		BufferedImage img = null;
		try {
			if (file == "king") {
				img = ImageIO.read(new File(file + ".jpg"));
			}
			else {
				img = ImageIO.read(new File(file + ".png"));
			}

	    } 
		catch (IOException e) {
	    	System.out.println("Internal Error: " + e.getMessage());
	    }
		return img;
    }
}
