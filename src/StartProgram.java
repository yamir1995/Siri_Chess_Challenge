import java.io.*;

public class StartProgram {
	
	//Allows user to input game piece and start and end positions interactively via command line
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;
		while(true) {
			System.out.println("Choose piece you would like to test for");
			System.out.println("You have 4 options:");
			System.out.println("1: King");
			System.out.println("2: Bishop");
			System.out.println("3: Knight");
			System.out.println("4: All the above pieces");
			choice = Integer.parseInt(br.readLine());
			if(choice >=1 && choice <=4) {
				break;
			}
			else {
				System.out.println("Invalid choice");
				System.out.println();
			}
		}
		
		System.out.println("Here is our chessboard");
		System.out.println(" " + "  _ _ _ _ _ _ _ _");
		System.out.println("7 " + "|_|_|_|_|_|_|_|_|");
		System.out.println("6 " + "|_|_|_|_|_|_|_|_|");
		System.out.println("5 " + "|_|_|_|_|_|_|_|_|");
		System.out.println("4 " + "|_|_|_|_|_|_|_|_|");
		System.out.println("3 " + "|_|_|_|_|_|_|_|_|");
		System.out.println("2 " + "|_|_|_|_|_|_|_|_|");
		System.out.println("1 " + "|_|_|_|_|_|_|_|_|");
		System.out.println("0 " + "|_|_|_|_|_|_|_|_|");
		System.out.println("   0 1 2 3 4 5 6 7");
		System.out.println();
		
		System.out.println("Enter the starting co-ordinates for your piece(s)");
		System.out.println("Enter start row number:");
		int start_row = Integer.parseInt(br.readLine());
		System.out.println("Enter start column number:");
		int start_col = Integer.parseInt(br.readLine());
		
		System.out.println("Enter the target co-ordinates for your piece(s)");
		System.out.println("Enter target row number:");
		int end_row = Integer.parseInt(br.readLine());
		System.out.println("Enter target column number:");
		int end_col = Integer.parseInt(br.readLine());
		
		FindMinimumMoves.start(choice, start_row, start_col, end_row, end_col);
	}

}
