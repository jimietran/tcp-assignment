import java.io.* ;
import java.net.* ;

public final class TCPClient
{
	/**
	 * @param argv
	 * @throws Exception
	 */
	public static void main(String argv[]) throws Exception
	{
		String option = "";
		String option2 = "";
		String modifiedInput = "";
		String number = "";
		boolean choice;
		

		while (!option.equals("5")) {
			
			String input = "LIST";
			
			Socket clientSocket = new Socket("localhost", 6789);
		    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
		    		clientSocket.getInputStream()));
		    
			System.out.println("Please select one of the following:");
			System.out.println("(1) Enter a list of numbers");
			System.out.println("(2) Summation");
			System.out.println("(3) Maximum");
			System.out.println("(4) Minimum");
			System.out.println("(5) Exit\n");
			
			BufferedReader opFromUser = new BufferedReader(new InputStreamReader(System.in));
		    option = opFromUser.readLine();
		    
		    System.out.println("\nYou have chosen option: " + option + '\n');
		    
		    if (option.equals("1")) {
		    	System.out.println("Please enter your numbers (enter 0 to finish): ");
		    	while (!number.equals("0")){
		    		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				    number = inFromUser.readLine();
				    input += " " + number;
				    input.substring(0);
		    	}
		    	System.out.println("You are sending: " + input + '\n');
		    	
			    outToServer.writeBytes(input + '\n');
			    choice = false; 
		    	while (choice == false) {
			    	System.out.println("Now please select one of the following operations:");
					System.out.println("(2) Summation");
					System.out.println("(3) Maximum");
					System.out.println("(4) Minimum");
					System.out.println("(5) Exit\n");
					
					BufferedReader op2FromUser = new BufferedReader(new InputStreamReader(System.in));
				    option2 = op2FromUser.readLine();
				    
				    System.out.println("\nYou have chosen option: " + option2 + '\n');
				    
				    if (option2.equals("2")) {
				    	outToServer.writeBytes("SUM" + '\n');
				    	choice = true;
				    }
				    
				    if (option2.equals("3")) {
				    	outToServer.writeBytes("MAX" + '\n');
				    	choice = true;
				    }
				    
				    if (option2.equals("4")) {
				    	outToServer.writeBytes("MIN" + '\n');
				    	choice = true;
				    }
				    
				    if (option2.equals("5")) {
				    	System.out.println("Thank you!");
				    	choice = true;
				    	break;
				    } 
			    }
			    
			    modifiedInput = inFromServer.readLine();
			    
			    System.out.println("Your result is: " + modifiedInput + '\n');
			    
			    clientSocket.close();
			    
			    number = "";
			    input = "LIST";
			    
		    }
		    
		    if (option.equals("2")) {
		    	System.out.println("Please enter in a new list of numbers.");
		    }
		    
		    if (option.equals("3")) {
		    	System.out.println("Please enter in a new list of numbers.");
		    }
		    
		    if (option.equals("4")) {
		    	System.out.println("Please enter in a new list of numbers.");
		    }
		    
		    if (option.equals("5")) {
		    	System.out.println("Thank you!");
		    	clientSocket.close();
		    	break;
		    }
		    	
		    
		    
		}

	}
	
}