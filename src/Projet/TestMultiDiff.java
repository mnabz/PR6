package Projet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TestMultiDiff implements Runnable{
	
	Entite t;
	
	public TestMultiDiff(Entite t){
		this.t=t;
	}
	public void run() {
		while(true){
		
		
		if(t.var==1){
			try {
				System.out.println("apres while "+t.var);
				Thread.sleep(4000);
				if(t.var==1){
					System.out.println("je suis dans var1"+t.var);
					String message="DOWN\n";
					byte[]data;
					data = message.getBytes();
					InetSocketAddress ia = new InetSocketAddress(t.ip_test,
							Integer.parseInt(t.port_test));
					DatagramPacket paquet1 = new DatagramPacket(data,
							data.length, ia);
					t.ds.send(paquet1);
				
				}else if(t.var==2){
					t.var=0;
				}
				
			
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	}

}
