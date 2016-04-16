public class Message {

	static String[] codeMessage = { "WELC", "NEWC", "ACKC", "APPL", "WHOS",
			"MEMB", "GBYE", "EYBG", "TEST", "DOWN", "DUPL", "ACKD" };
	char[] typeMessage = new char[4];
	char[] num_mess = new char[8];
	char[] no_mess = new char[8];
	char[] id = new char[8];
	char[] id_app = new char[8];
	char[] idm = new char[8];
	char[] id_trans = new char[8];
	char[] size_mess = new char[3];
	char[] ip = new char[15];
	char[] ip_diff = new char[15];
	char[] ip_succ = new char[15];
	char[] port = new char[4];
	char[] port_diff = new char[4];
	char[] port_succ = new char[4];
	char[] size_nom = new char[2];
	char[] size_content = new char[3];

	public Message(String typeMessage, int num_mess, int no_mess, int id,
			int id_app, int idm, int id_trans, int size_mess, String ip,
			String ip_diff, String ip_succ, int port, int port_diff,
			int port_succ, int size_nom, int size_content) {
		this.typeMessage = rempliTypeMessage(typeMessage);

		this.ip = convertIP(ip);
		this.ip_diff = convertIP(ip_diff);
		this.ip_succ = convertIP(ip_succ);

		this.port = convertPort(port);
		this.port_diff = convertPort(port_diff);
		this.port_succ = convertPort(port_succ);

		this.size_mess = convertSize3octet(size_mess);
		this.size_content = convertSize3octet(size_content);
		this.size_nom = convertSize2octet(size_nom);

		this.num_mess = convertLittleEndian(num_mess);
		this.no_mess = convertLittleEndian(no_mess);

		this.id = convert8octet(id);
		this.id_app = convert8octet(id_app);
		this.id_trans = convert8octet(id_trans);
		this.idm = convert8octet(idm);
	}

	public static char[] convert8octet(int id) {
		String a;
		char[] charTmp = new char[8];
		if (id < 0 && id > 255) {
			System.out.println("mauvais num");
			System.exit(0);
		}
		a = Integer.toBinaryString(id);
		System.out.println(a);
		int nbr0 = 8 - a.length();
		System.out.println(nbr0);
		if (nbr0 > 0) {
			for (int i = 0; i < nbr0; i++) {
				charTmp[i] = '0';
			}
			for (int i = nbr0; i < 8; i++) {
				charTmp[i] = a.charAt(i - nbr0);
			}
		} else {
			for (int i = 0; i < 8; i++) {
				charTmp[i] = a.charAt(i);
			}
		}
		for (int i = 0; i < 8; i++) {
			System.out.print(charTmp[i]);
		}
		return charTmp;

	}

	public static char[] convertLittleEndian(int num) {
		String a;
		char[] charTmp = new char[8];
		if (num < 0 && num > 255) {
			System.out.println("mauvais num");
			System.exit(0);
		}
		a = Integer.toBinaryString(num);
		System.out.println(a);
		int nbr0 = 8 - a.length();
		System.out.println(nbr0);
		if (nbr0 > 0) {
			for (int i = 0; i < nbr0; i++) {
				charTmp[i] = '0';
			}
			for (int i = nbr0; i < 8; i++) {
				charTmp[i] = a.charAt(i - nbr0);
			}
		} else {
			for (int i = 0; i < 8; i++) {
				charTmp[i] = a.charAt(i);
			}
		}
		for (int i = 0; i < 8; i++) {
			System.out.print(charTmp[i]);
		}
		return charTmp;
	}

	public static char[] convertSize2octet(int size) {
		String a;
		char[] tmpSize = new char[2];
		if (size > 99 && size < 0) {
			System.out.println("mauvaise taille");
			System.exit(0);
		}
		if (size < 10) {
			a = String.valueOf(size);
			tmpSize[0] = '0';
			tmpSize[1] = a.charAt(0);
		} else {
			a = String.valueOf(size);
			tmpSize[0] = a.charAt(0);
			tmpSize[1] = a.charAt(1);
		}
		return tmpSize;
	}

	public static char[] convertSize3octet(int size) {
		String a;
		char[] tmpSize = new char[3];
		if (size > 512 && size < 0) {
			System.out.println("mauvaise taille");
			System.exit(0);
		}
		if (size < 10) {
			a = String.valueOf(size);
			tmpSize[0] = '0';
			tmpSize[1] = '0';
			tmpSize[2] = a.charAt(0);
		}
		if (size < 100) {
			a = String.valueOf(size);
			tmpSize[0] = '0';
			tmpSize[1] = a.charAt(0);
			tmpSize[2] = a.charAt(1);
		} else {
			a = String.valueOf(size);
			tmpSize[0] = a.charAt(0);
			tmpSize[1] = a.charAt(1);
			tmpSize[2] = a.charAt(2);
		}
		return tmpSize;
	}

	public static char[] convertPort(int Port) {
		char[] port = new char[4];
		if (Port > 9999 && Port < 0) {
			System.out.println("mauvais port");
			System.exit(0);
		}
		int u = 3;
		String a;
		while (Port != 0) {
			int mod = Port % 10;
			a = String.valueOf(mod);
			port[u] = a.charAt(0);
			System.out.print(port[u]);
			Port -= mod;
			Port /= 10;
			u--;
		}
		return port;
	}

	public static char[] rempliTypeMessage(String typeMessage) {
		char[] mess = new char[4];
		if (typeMessage.length() != 4) {
			System.out.println("mauvais type Message");
			System.exit(0);
		} else {
			int u = 0;
			while (u < 12) {
				if (typeMessage.equals(codeMessage[u])) {
					for (int i = 0; i < 4; i++) {
						mess[i] = typeMessage.charAt(i);
					}
				}
				u++;
			}
			if (u == 12) {
				System.out.println("mauvais Message");
				System.exit(0);
			}
		}
		return mess;
	}

	public static char[] convertIP(String ip) {
		String[] test = new String[4];
		test = ip.split("\\.");
		// System.out.println(test.length);
		String tmp;
		for (int i = 0; i < test.length; i++) {

			switch (test[i].length()) {
			case 1:
				tmp = test[i];
				test[i] = "00" + tmp;
				tmp = "";
				break;
			case 2:
				tmp = test[i];
				test[i] = "0" + tmp;
				tmp = "";
				break;
			default:
				break;
			}
		}
		System.out.println("test");
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}

		int cmp = 0;
		char[] res = new char[15];
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test[i].length(); j++) {
				res[cmp] = test[i].charAt(j);
				cmp++;
				if (cmp == 3 || cmp == 7 || cmp == 11) {
					res[cmp] = '.';
					cmp++;
				}

			}

		}
		return res;
	}
}
