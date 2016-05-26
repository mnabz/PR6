package Projet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Scanner;
import java.util.Vector;

public class ComUdpR implements Runnable {

	Entite t;

	public ComUdpR(Entite t) {

		this.t = t;
	}

	public void run() {
		try {
			while (true) {

				byte[] data = new byte[100];
				Scanner sc = new Scanner(System.in);
				String st = "";
				st = sc.nextLine();

				String tab[] = st.split(" ");
				Message mess = null;
				Vector<String> message = new Vector<String>();
				Boolean isExiste = t.recherche(mess.codeMessage, tab[0]);

				// if (isExiste==true)
				// {
				Thread.sleep(2000);
				String s = null;
				mess = new Message(t.id);
				t.idMess.add(mess.idm);

				if (tab[0].equals("WHOS")) {
					System.out.print("NASS");
					message.add(mess.WHOS());
					Message mess1 = new Message(t.id);
					t.idMess.add(mess1.idm);
					message.add(mess1.MEMB(t.id, t.myIP, t.port_udp));

				} else if (tab[0].equals("APPL")) {
					if (tab[1].equals("DIFF####")) {
						// message.add(st);

						t.idMess.add(mess.idm);
						message.add(mess.APPL1(tab[2]));
					}
				} else if (tab[0].equals("TEST")) {
					t.idMess.add(mess.idm);
					
					message.add(mess.TEST(t.ip_diff, t.port_diff));
					t.var=1;
					
				} else if (tab[0].equals("MEMB")) {
					System.out.print("MEMB");

				} else if (tab[0].equals("GBYE")) {
					message.add(mess.GBYE(t.myIP, t.port_udp, t.myIA,
							t.port_udp_suivant));
					t.deco = 1;
				} else if (st.equals("MULTI")) {
					String o = "hello\n";
					System.out.print("NASS");
					data = o.getBytes();
					InetSocketAddress ia = new InetSocketAddress(t.ip_diff,
							t.port_diff);
					DatagramPacket paquet1 = new DatagramPacket(data,
							data.length, ia);
					t.ds.send(paquet1);
				}

				for (int i = 0; i < message.size(); i++) {
					data = message.elementAt(i).getBytes();
					InetSocketAddress ia = new InetSocketAddress(t.myIA,
							t.port_udp_suivant);
					DatagramPacket paquet1 = new DatagramPacket(data,
							data.length, ia);
					t.ds.send(paquet1);
				}

			}
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
