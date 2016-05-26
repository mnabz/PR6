package Projet;

import java.util.Scanner;

public class DiffuseMess implements Runnable{
	Entite t;
	
	public DiffuseMess(Entite t){
		this.t=t;
	}
	
	public void run() {
		Scanner sc=new Scanner(System.in);
		while(true){
			System.out.println("envoyez un messages sur l'anneau :");
			String mess=sc.nextLine();
			Message message=new Message(t.id);
			mess=concatMess(mess, message);
			String id=t.id;
			while(!id.equals(t.id));
		}
		
	}
	public static String concatMess(String mess, Message messa){
		return "APPL "+Long.toString(messa.idm)+" DIFF#### "+Integer.toString(mess.length())+" "+mess;
	}
}
