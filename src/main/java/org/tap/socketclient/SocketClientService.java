package org.tap.socketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Component
public class SocketClientService {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(SocketClientService.class);
	
	private SocketClientProperties socketClientProperties;
	
	@Autowired
	public SocketClientService(SocketClientProperties socketClientProperties) {
		this.socketClientProperties = socketClientProperties;
	}

	private Socket clientSocket;
	private PrintWriter out;
	
	private BufferedReader in;
	
	public void startConnection() {
		try {
			clientSocket = new Socket(socketClientProperties.getHostName(), socketClientProperties.getPort());
			logger.info("Client socket connected at {}:{}",clientSocket.getLocalAddress(), clientSocket.getPort());
			
			out = new PrintWriter(clientSocket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}	
	}
	
	public String sendMessage(String message) {
		
		String response = "";
		try {
			response = in.readLine();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return response;
	}
	
	public void stopConnection() {	
		try {
			clientSocket.close();
			in.close();
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
