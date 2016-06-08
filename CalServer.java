import java.net.* ;

public final class CalServer
{
	public static void main(String argv[]) throws Exception
	{

		ServerSocket welcomeSocket = new ServerSocket(6789);
		while(true) {
			
			// Construct an object to process the Calculation request message.
			CalRequest request = new CalRequest(welcomeSocket.accept());
			// Create a new thread to process the request.
			Thread thread = new Thread(request);
			// Start the thread.
			thread.start();
			
		}
		
	}
	
}
