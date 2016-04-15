package projet;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;
import java.net.SocketException;

public class Entite {
	long id;
 	static int port_tcp;
	static int port_udp;
	static	int port_udp_suivant;
	static	String myIA;
	String ip_multi;
	int port_multi;
	int a=1024;
	int b=9999;
	String c="localhost";
	DatagramSocket ds;

public int portTcp(){

for(int i=a;i<b+1;i++)
 {  try{
	Socket socket=new Socket(c,i);
		socket.close();
	  }
	catch(Exception e)
	{
		return i;
	}
}

return -1;
}

public int portUdp(){

for(int i=a;i<=b;i++)
 {  try{

   DatagramSocket dso=new DatagramSocket(i);
	 dso.close();
	return i;
	  }
	catch(Exception e)
	{
	}
}

return -1;
}
public String myIA(String s){
	Pattern p = Pattern.compile("[/]");
String[] items = p.split(s);
return items[1];

}

public Entite (String myIA,int port,int port_suivant) throws UnknownHostException, SocketException{
this.port_tcp=portTcp();
this.port_udp=port;
this.id=port_tcp;
this.myIA=myIA;
this.port_udp_suivant=port_suivant;
ds=new DatagramSocket(port_udp);
}

public Entite () throws UnknownHostException, SocketException{
this.port_tcp=portTcp();
this.port_udp=portUdp();
this.id=port_tcp;
this.myIA=myIA(InetAddress.getLocalHost().toString());
this.port_udp_suivant=port_udp;
ds=new DatagramSocket(port_udp);
}
/*
public void udp() throws InterruptedException, SocketException{

	ds=new DatagramSocket(port_udp);

	 t1=new Thread(new ComUdpE(port_udp,ds,this));
	 t2=new Thread(new ComUdpR(port_udp,ds,this));
	 t3=new Thread(new ComTcp(port_tcp,this));
	t1.start();
	t2.start();
	t3.start();
//	t1.join();
//	t2.join();
//	t3.join();


}
*/

/*public void maj(String myIA,int port_udp_suivant) throws InterruptedException{


this.print();
}*/


public static void print(){
	System.out.println("port_tcp :"+port_tcp);
	System.out.println("port_udp :"+port_udp);
	System.out.println("port_udp_suivant :"+port_udp_suivant);
	System.out.println("myIA :"+myIA);

}




}
