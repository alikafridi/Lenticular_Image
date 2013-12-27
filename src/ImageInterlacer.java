/**
 * @author: Ali Afridi
 * @version: 1.0
 * Date Created: 11/09/2013
 * Last Modified: 12/26/2013
 * 
 * This code combines two images into a single interlaced image.
 * The code here goes along with the Lenticular Screen project.
 * More info available at http://hackiton.com/lenticular
 * 
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageInterlacer 
{
	public static void main(String [] args)
	{
		// Variable Declarations
		
		String image1_path = "images/obama.jpg";
		String image2_path = "images/romney.jpeg";
		String output_image_path = "output_examples/pixels1.png";
		int pixels = 1; // pixels value must be a power of two 
		
		
		// Load Images
		
		BufferedImage img1 = null;
		BufferedImage img2 = null;

		try {
			img1 = ImageIO.read(new File(image1_path));
			img2 = ImageIO.read(new File(image2_path));
			System.out.println("Image Loading: Successful");
		} catch (IOException e) {
			System.out.println("Image Loading: Unsuccessful");
			e.printStackTrace();
		}
		
		
		// Interlace the Images

		if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight() && powerOfTwo(pixels) & pixels > 0) {
			int image_width = img1.getWidth();
			int image_height = img1.getHeight();
			BufferedImage img3 = new BufferedImage(image_width*2, image_height, BufferedImage.TYPE_INT_RGB);
			
			for (int i = 0; i <image_width; i=i+pixels) {
				for (int h = 0; h < image_height; h++) {
					for (int a = 0; a < pixels; a++) {
						img3.setRGB(i*2 + a, h, img1.getRGB(i+a,h));
						img3.setRGB(i*2 + pixels + a, h, img2.getRGB(i+a,h));
					}
				}
			}
			
			System.out.println("Image Interlaced.");
			
			
			// Save the interlaced image

			try {
				File outputfile = new File(output_image_path);
				ImageIO.write(img3, "png", outputfile);
				System.out.println("Image Save: Successful");
			} catch (Exception e) {
				System.out.println("Image Save: Unsuccessful");
			}
		}
		else 
			System.out.println("Error: Images must be of the same size");
	}
	
	/**
	 * Checks to see whether the inputed number is a power of two. 
	 * Returns a boolean.
	 * 
	 * @param	number	the integer to be tested
	 * @return			true if the number is a power of two
	 */
	public static boolean powerOfTwo(int number) {
		// Take the log (base 2) of number
		double power = Math.log(number)/Math.log(2);
		
		//Check whether power is a whole number
		if (power == Math.floor(power))
			return true;
		else
			return false;
	}
}
