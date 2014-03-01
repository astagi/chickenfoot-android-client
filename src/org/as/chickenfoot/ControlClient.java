package org.as.chickenfoot;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ControlClient {

	private Socket socket;
	private String fwCommand = "{\"m\" : \"M2\", \"a\" : \"fw\", \"p\" : {\"speed\" : 5000}}\0";
	private String rwCommand = "{\"m\" : \"M2\", \"a\" : \"rw\", \"p\" : {\"speed\" : 5000}}\0";
	private String stopBackMotorCommand = "{\"m\" : \"M2\", \"a\" : \"stop\", \"p\" : {}}\0";
	private String rlCommand = "{\"m\" : \"M1\", \"a\" : \"rl\", \"p\" : {\"speed\" : 1000}}\0";
	private String rrCommand = "{\"m\" : \"M1\", \"a\" : \"rr\", \"p\" : {\"speed\" : 1000}}\0";
	private String stopFrontMotorCommand = "{\"m\" : \"M1\", \"a\" : \"stop\", \"p\" : {}}\0";
	
	private void send(String message) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			out.println(message);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connect(String host, int port) {
		try {
			InetAddress serverAddr = InetAddress.getByName(host);
			socket = new Socket(serverAddr, port);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void fw() {
		send(fwCommand);
	}

	public void rw() {
		send(rwCommand);
	}
	
	public void stopBackMotor() {
		send(stopBackMotorCommand);
	}

	public void rl() {
		send(rlCommand);
	}

	public void rr() {
		send(rrCommand);
	}
	
	public void stopFrontMotor() {
		send(stopFrontMotorCommand);
	}

}
