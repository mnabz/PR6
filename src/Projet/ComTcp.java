package Projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

import org.omg.CORBA.SystemException;

public class ComTcp implements Runnable{


	Entite t;
	Boolean encore=true;

	public ComTcp(Entite t){
		this.t=t;
	}

	public void run() {
		try{
	        ServerSocket server=new ServerSocket(t.port_tcp);

	        while(this.encore)
	        {
	        	try{

	        		System.out.println("Avant la lecture");
	        		Thread.currentThread().interrupt();
	        		Socket socket =server.accept();
	        PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));

	        pw.print("WELC "+t.myIA+" "+Integer.toString(t.port_udp_suivant)+" "+t.ip_diff+" "+Integer.toString(t.port_diff)+"\n");
	        pw.flush();

	        String mess=br.readLine();
					if(mess!=null){
	        System.out.println("Message recu :"+mess);

	        Pattern p = Pattern.compile(" ");
		    String[] items = p.split(mess);

		    pw.print("ACKC\n");
	        pw.flush();


	        t.port_udp_suivant=Integer.parseInt(items[2]);
					t.myIA=items[1];
					t.print();
				}

	        pw.close();
	        br.close();
	        socket.close();

	        System.out.println("aprés la lecture");

	        	  } catch (InterruptedIOException e) { // Si l'interruption a été gérée correctement.
	                  Thread.currentThread().interrupt();
	                  System.out.println("Interrompu via InterruptedIOException");
	                  this.encore=false;
	        } catch (IOException e) {
	        	System.out.print("2eme \n");

	        	this.encore=false;
	        }}
	}catch(Exception e){
				System.out.println(e);
				e.printStackTrace();	}

	}






}
