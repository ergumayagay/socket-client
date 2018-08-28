package org.emil.socketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketConfig {

	
	@Bean
	public Socket clientSocket(SocketClientProperties socketClientProperties) throws UnknownHostException, IOException {
		return new Socket(socketClientProperties.getHostName(), socketClientProperties.getPort());
	}
	
	@Bean 
	public PrintWriter out(Socket clientSocket) throws IOException {
		return new PrintWriter(clientSocket.getOutputStream(),true);
	}

	@Bean
	public BufferedReader in(Socket clientSocket) throws IOException {
		return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
}
