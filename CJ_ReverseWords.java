import java.io.File;
import java.util.Scanner;


public class CJ_ReverseWords {
	
	public static String reverseWords(String input) {
		String output = "";
		String splitStr[] = input.split(" ");
		if(splitStr.length == 1) {
			return input;
		} else {
			for(int i=splitStr.length-1; i>=0 ; i--) {
				if(output.length() > 0) output += " ";
				output += splitStr[i];
			}
		}
		return output;
	}
	
	public static void main(String[] args) {
		Scanner fileScanner;
		String input[] = null;
		try {
			fileScanner = new Scanner(new File("reverse.in"));
			int i=0, n=0;
			while (fileScanner.hasNextLine()){
				if(i == 0) {
					n = Integer.parseInt(fileScanner.nextLine());
					input = new String[n];
				} else {
					input[i-1] = fileScanner.nextLine();
				}
				i++;
			}
			
			for(int j=0; j < n; j++) {
				String out = reverseWords(input[j]);
				System.out.println("Case #"+(j+1)+": " + out);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
