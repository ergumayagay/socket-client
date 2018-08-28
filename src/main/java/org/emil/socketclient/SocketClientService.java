package org.emil.socketclient;

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
	
	
	@Autowired
	private Socket clientSocket;
	@Autowired
	private PrintWriter out;
	@Autowired
	private BufferedReader in;
	

	public String sendMessage(String message) {
		try {
			logger.info("Host: {}", clientSocket.getInetAddress());
			logger.info("Port: {}", clientSocket.getPort());
			logger.info("Message: {}", in.readLine());
			return in.readLine();
			
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
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
