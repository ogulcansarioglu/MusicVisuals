package d20123805.ie.tudublin;


public class Main
{

    
    

    public static void myVisual()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Visual());
    }

   
    public static void main(String[] args)
    {



        userInterface myUserInterface = new userInterface();

        try {
  
      
       

        while(myUserInterface.getClicks() != 1) 

        {
          
         System.out.println(myUserInterface.getClicks());
            
        }
       
        myVisual();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
        
        
    }
}