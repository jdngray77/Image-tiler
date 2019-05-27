import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.nio.file.Path;

import javax.swing.ImageIcon;
public class RenderHandler 
{
	public static String[][] DefaultCharSet = new String[1000][1000], ActiveCharSet = new String[50][50], ItemSet = new String[50][50];
	public static Font title;
	
	
	
	public RenderHandler(int width, int height) {
	}


	public void render(Graphics graphics)
	{
		int ex;
		for (int x = 0; x <= SplashX4.WindowX / getImage(SplashX4.ResourcePath).getHeight(null); x++) {//Rows
			for (int y = 0; y <= SplashX4.WindowY / getImage(SplashX4.ResourcePath).getWidth(null); y++) {//Columns
				if (SplashX4.Space) {
					ex = 1;
				} else {
					ex = 0;
				}
				
				try {
				graphics.drawImage(getImage(SplashX4.ResourcePath), x * getImage(SplashX4.ResourcePath).getWidth(null) + (ex * (x + 1)), y *  getImage(SplashX4.ResourcePath).getHeight(null) + (ex * (y + 1)), null);
				}catch (Exception e) {}
			}}
	}
	

	
	private static Image getImage(Path resources) {
		ImageIcon drawer = new ImageIcon(resources.toString());
		return drawer.getImage();
	}
	
	
}


