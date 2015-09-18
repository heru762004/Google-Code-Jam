import java.io.File;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;


public class CJ_AlienLanguage {
	public static Vector<String> v = new Vector<String>();
	public static Vector<String> tempV = new Vector<String>();
	public static String dic[] = null;
	
	public static boolean checkDictionary(String cmp) {
		for(String d : dic) {
			if(d.startsWith(cmp)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkExactDictionary(String cmp) {
		for(String d : dic) {
			if(d.equalsIgnoreCase(cmp)) {
				return true;
			}
		}
		return false;
	}
	
	public static int getCharCount(String in) {
		int num = 0;
		v.removeAllElements();
		String splitStr[] = in.split("\\(");
		if(splitStr.length > 1) {
			int pos = 0;
			if(in.startsWith("("))pos = 1;
			String temp = splitStr[pos++];
			if(temp.contains(")")) {
				String tmpX[] = temp.split("\\)");
				String a = tmpX[0];
				for(int i=0; i < a.length(); i++) {
					String b = "";
					if(tmpX.length > 1)b = tmpX[1];
					String x = "";
					if(b.length() > 0) {
						x = a.charAt(i)+""+b;
					} else {
						x = a.charAt(i)+"";
					}
					if(checkDictionary(x)) v.add(x);
				}
			} else {
				v.add(temp);
			}
			while(pos < splitStr.length) {
				temp = splitStr[pos++];
				for(int j=0; j < v.size(); j++) {
					String x = v.elementAt(j);
					if(temp.contains(")")) {
						String tmpX[] = temp.split("\\)");
						String a = tmpX[0];
						for(int i=0; i < a.length(); i++) {
							String b = "";
							if(tmpX.length > 1)b = tmpX[1];
							String p = "";
							if(b.length() > 0) {
								p = x + temp.charAt(i)+""+b;
							}
							else {
								p = x + temp.charAt(i)+"";
							}
							if(checkDictionary(p)) {
//								System.out.println("p = " + p);
								tempV.add(p);
							}
						}
					} else {
						String p = x + temp + "";
						if(checkDictionary(p)) {
//							System.out.println("p = " + p);
							tempV.add(p);
						}
					}
				}
				if(tempV.size() > 0) {
					v.removeAllElements();
					v.addAll(tempV);
					tempV.removeAllElements();
				}
			}
			
			for(int i=0; i < v.size(); i++) {
				if(checkExactDictionary(v.elementAt(i))) {
					num++;
				}
			}
		} else {
			if(checkExactDictionary(in)) {
				num++;
			}
		}
		return num;
	}
	
	public static void main(String[] args) {
		Scanner fileScanner;
		String input[] = null;
		try {
			fileScanner = new Scanner(new File("alien_language.in"));
			int a=0, L=0, D=0, N=0, pos=0;
			while (fileScanner.hasNextLine()){
				if(a == 0) {
					String tmp = fileScanner.nextLine();
					String tmps[] = tmp.split(" ");
					L = Integer.parseInt(tmps[0]);
					D = Integer.parseInt(tmps[1]);
					N = Integer.parseInt(tmps[2]);
					dic = new String[D];
					input = new String[N];
					a++;
				} else {
					String x = fileScanner.nextLine();
					if(a == 1) {
						dic[pos++] = x;
						if(pos == D) {
							pos = 0;
							a++;
						}
					} else if( a== 2) {
						input[pos++] = x;
					}
				}
			}
			for(int i=0; i < N; i++) {
				System.out.println("Case #"+(i+1)+": "+getCharCount(input[i]));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
