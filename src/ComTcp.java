package projet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class ComTcp implements Runnable{


	Entite t;

	public ComTcp(Entite t){
		this.t=t;
	}

	public void run(){
		try{
	        ServerSocket server=new ServerSocket(t.port_tcp);
					
	        while(true)
	        {
	          Socket socket =server.accept();
	        PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));


	        pw.print("WELC "+t.myIA+" "+Integer.toString(t.port_udp_suivant)+"\n");
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



	      }
	      }
	    catch(Exception e){
	      System.out.println(e);
	      e.printStackTrace();
	    }


	}



}
