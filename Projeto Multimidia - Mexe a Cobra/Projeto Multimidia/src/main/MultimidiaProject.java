package main;

import camera.*;
import snake.*;

public class MultimidiaProject 
{
	
	public static void main(String[] args) 
	{
		CameraTracking camera = new CameraTracking();
		
		new Snake();
		camera.initializing(args);
		
		
	}

	
}
