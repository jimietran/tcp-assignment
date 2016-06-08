import java.io.* ;
import java.net.* ;
import java.util.ArrayList;

final class CalRequest implements Runnable
{
	Socket socket;
	String operator;
	String clientInput;
	ArrayList<String> parsedClientInput = new ArrayList<String>();
	String operatedInput;
	
	
	// Constructor
	public CalRequest(Socket socket) throws Exception
	{
		this.socket = socket;
	}
	
	// Implement the run() method of the Runnable interface.
	public void run()
	{
		try {

			while(true) {
	
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				
				clientInput = inFromClient.readLine();
				
				if (clientInput != null) {
					parsedClientInput = parser(clientInput);
				}
				
				
				operator = inFromClient.readLine();
				
				if (clientInput != null && operator != null) {
					processRequest();
				}
				
				DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
				
				outToClient.writeBytes(operatedInput + '\n');
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	private void processRequest() throws Exception
	{
		if (operator.equals("SUM")) {
			double sum = 0;
			for (int i=1;i<parsedClientInput.size();i++)
			{
				sum += Double.parseDouble((String) parsedClientInput.get(i));
			}
			operatedInput = Double.toString(sum);
		}
		if (operator.equals("MAX")) {
			double max = Double.parseDouble((String) parsedClientInput.get(1));
			for (int i=1;i<parsedClientInput.size();i++)
			{
				if (max < Double.parseDouble((String) parsedClientInput.get(i)))
				max = Double.parseDouble((String) parsedClientInput.get(i));
			}
			operatedInput = Double.toString(max);
		}
		if (operator.equals("MIN")) {
			double min = Double.parseDouble((String) parsedClientInput.get(1));
			for (int i=1;i<parsedClientInput.size();i++)
			{
				if (Double.parseDouble((String) parsedClientInput.get(i)) < min)
				min = Double.parseDouble((String) parsedClientInput.get(i));
			}
			operatedInput = Double.toString(min);
		}
	}
	
	private static ArrayList<String> parser(String receivedInput)
	{
		ArrayList<String> parsedList = new ArrayList<String>();
		String parsedInput = "";
		for (int i=0;i<receivedInput.length();i++){
			if (i+1 < receivedInput.length()) {
				parsedInput += receivedInput.substring(i, i+1);
				if (receivedInput.substring(i, i+1).equals(" ")) {
					parsedList.add(parsedInput);
					parsedInput = "";
				}
			}
		}
		return parsedList;
	}
}