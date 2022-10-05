package main;

public class PointerPosition {

	//those parameters will be changed frame by frame
	public static int lastX;
	public static int lastY;
	
	//move the snake depending of tracker position
	public static String playerKey()
	{
		String key = null;
		
		if(lastX > 0 && lastX < 200 && lastY > 150 && lastY < 300)
		{
			key = "left";
		}
		else if(lastX > 200 && lastX < 400 && lastY > 0 && lastY < 150)
		{
			key = "down";
		}
		else if(lastX > 200 && lastX < 400 && lastY > 300 && lastY < 450)
		{
			key = "up";
		}
		else if(lastX > 400 && lastX < 650 && lastY > 150 && lastY < 300)
		{
			key = "right";
		}
		else
		{
			key = null;
		}
		
		return key;
	}
	
	
}
