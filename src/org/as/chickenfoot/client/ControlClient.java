package org.as.chickenfoot.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ControlClient {

	private Socket socket;
	private MapElement fwCommand;
	private MapElement rwCommand;
	private MapElement rlCommand;
	private MapElement rrCommand;
	private MapElement stopBackMotorCommand;
	private MapElement stopFrontMotorCommand;
	private ArrayList<ClientListener> listeners = new ArrayList<ClientListener>();
	
	public ControlClient() {
		fwCommand = new MapElement();
		fwCommand.setModule("M2");
		fwCommand.setAction("fw");
		fwCommand.setParameter("speed", 5000);
		rwCommand = new MapElement();
		rwCommand.setModule("M2");
		rwCommand.setAction("rw");
		rwCommand.setParameter("speed", 5000);
		rlCommand = new MapElement();
		rlCommand.setModule("M1");
		rlCommand.setAction("rl");
		rlCommand.setParameter("speed", 1000);
		rrCommand = new MapElement();
		rrCommand.setModule("M1");
		rrCommand.setAction("rr");
		rrCommand.setParameter("speed", 1000);
		stopBackMotorCommand = new MapElement();
		stopBackMotorCommand.setModule("M2");
		stopBackMotorCommand.setAction("stop");
		stopFrontMotorCommand = new MapElement();
		stopFrontMotorCommand.setModule("M1");
		stopFrontMotorCommand.setAction("stop");
	}
	
	public boolean isConnected() {
		if(socket == null)
			return false;
		return socket.isConnected();
	}
	
	private void send(String message) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			out.println(message);
			return;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (ClientListener listener : listeners)
			listener.onConnectionError();
	}
	
	public void addListener(ClientListener listener) {
		listeners.add(listener);
	}

	public void connect(String host, int port) {
		try {
			InetAddress serverAddr = InetAddress.getByName(host);
			socket = new Socket(serverAddr, port);
			for (ClientListener listener : listeners)
				listener.onConnect();
			return;
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (ClientListener listener : listeners)
			listener.onConnectionError();
	}

	public void fw() {
		send(fwCommand.toString());
	}

	public void rw() {
		send(rwCommand.toString());
	}
	
	public void stopBackMotor() {
		send(stopBackMotorCommand.toString());
	}

	public void rl() {
		send(rlCommand.toString());
	}

	public void rr() {
		send(rrCommand.toString());
	}
	
	public void stopFrontMotor() {
		send(stopFrontMotorCommand.toString());
	}

}
