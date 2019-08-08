import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

public class ChangeWallpaperDesktop { 
	
	public static interface User32 extends Library {
		User32 INSTANCE = (User32) Native.loadLibrary("user32",User32.class,W32APIOptions.DEFAULT_OPTIONS);        
		boolean SystemParametersInfo (int one, int two, String s ,int three);         
	}
 
	public static void main(String[] args) {  
		
		final Runnable execution = new Runnable() {
			public void run() {
				  changeBackGroundDesktop();
			}
		};
		ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
		timer.scheduleAtFixedRate(execution, 5, 5, TimeUnit.SECONDS);
	}

	private static void changeBackGroundDesktop() {
		Random rand = new Random();
		int randomNumber =  rand.nextInt((10 - 1) + 1) + 1;		
		String path = "C:\\Users\\DevMind\\Pictures\\images\\" + randomNumber + ".jpg";
		User32.INSTANCE.SystemParametersInfo(0x0014, 0, path , 1);			
	}
 }
