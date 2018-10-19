import java.util.*;

public class FindMinimumMoves {
	
	public static void start(int choice, int start_row, int start_col,
			int end_row, int end_col) {
		//Checking that start and end positions are in the board
		if(!inBoundary(start_row, start_col)) {
			System.out.println("Start position outside board; no path found");
		}
		else if(!inBoundary(end_row, end_col)) {
			System.out.println("End position outside board; no path found");
		}
		//Matching choice entered to determine which piece to calculate minimum turns for
		if(choice == 1) {
			King final_position = startKing(start_row, start_col, end_row, end_col);
			if(final_position != null) {
				printResult(final_position.turns, final_position.path, choice);
			}
			else {
				printResult(Integer.MAX_VALUE, null, choice);
			}
		}
		else if(choice == 2) {
			Bishop final_position = startBishop(start_row, start_col, end_row, end_col);
			if(final_position != null) {
				printResult(final_position.turns, final_position.path, choice);
			}
			else {
				printResult(Integer.MAX_VALUE, null, choice);
			}
		}
		else if(choice == 3) {
			Knight final_position = startKnight(start_row, start_col, end_row, end_col);
			if(final_position != null) {
				printResult(final_position.turns, final_position.path, choice);
			}
			else {
				printResult(Integer.MAX_VALUE, null, choice);
			}
		}
		else if(choice == 4) {
			King final_position_king = startKing(start_row, start_col, end_row, end_col);
			if(final_position_king != null) {
				printResult(final_position_king.turns, final_position_king.path, 1);
			}
			else {
				printResult(Integer.MAX_VALUE, null, choice);
			}
			Bishop final_position_bishop = startBishop(start_row, start_col, end_row, end_col);
			if(final_position_bishop != null) {
				printResult(final_position_bishop.turns, final_position_bishop.path, 2);
			}
			else {
				printResult(Integer.MAX_VALUE, null, choice);
			}
			Knight final_position_knight = startKnight(start_row, start_col, end_row, end_col);
			if(final_position_knight != null) {
				printResult(final_position_knight.turns, final_position_knight.path, 3);
			}
			else {
				printResult(Integer.MAX_VALUE, null, choice);
			}			

		}
		else {
			System.out.println("Invalid choice");
			System.exit(0);
		}
		
	}
	
	public static Knight startKnight(int start_row, int start_col, int end_row, int end_col) {
		
		Board board = new Board();
		Knight knight = new Knight(start_row, start_col);
		LinkedList<Knight> knights_list = new LinkedList<Knight>();
		knight.path.add(start_row);//
		knight.path.add(start_col);//
		knights_list.add(knight);
		//Setting the start position at being visited on the board object
		board.position[start_row][start_col] = 1;
		
		
		//Using BFS traversal to track all paths until target is reached 
		//or until piece hits boundary
		while(knights_list.size() != 0) {
			Knight current = knights_list.poll();
			if(isAtTarget(current.current_pos, end_row, end_col)) {
//				return current.turns;
				return current;//
			}
			//For each knight in the list, consider all possible moves that can be made
			for(int i = 0; i < current.move_row.length; i++) {
				int new_row = current.current_pos[0] + current.move_row[i];
				int new_col = current.current_pos[1] + current.move_col[i];
				if(inBoundary(new_row, new_col) && notVisited(board, new_row, new_col)) {
					Knight new_position = new Knight(new_row, new_col);
					new_position.turns = current.turns + 1;
					new_position.path = new ArrayList<Integer>(current.path);//
					new_position.path.add(new_row);//
					new_position.path.add(new_col);//
					knights_list.add(new_position);
					board.position[new_row][new_col] = 1;
				}
			}
		}
		//return Integer.MAX_VALUE;
		return null;//
	}
	
	public static Bishop startBishop(int start_row, int start_col, int end_row, int end_col) {
		
		if(!isOnSameColorSquare(start_row, start_col, end_row, end_col)) {
			return null;
		}
		Board board = new Board();
		Bishop bishop = new Bishop(start_row, start_col);
		
		LinkedList<Bishop> bishops_list = new LinkedList<Bishop>();
		bishop.path.add(start_row);//
		bishop.path.add(start_col);//
		bishops_list.add(bishop);
		board.position[start_row][start_col] = 1;
		
		//Using BFS traversal to track all paths until target is reached 
		//or until piece hits boundary
		while(bishops_list.size() != 0) {
			Bishop current = bishops_list.poll();
			if(isAtTarget(current.current_pos, end_row, end_col)) {
				return current;//
			}
			//For each of the 4 diagonal directions
			for(int i = 0; i < current.move_row.length; i++) {
				//Update the row and column to one magnitude greater in the same direction to 
				//consider all positions in that direction until you cross the board boundary
				for(int new_row = current.current_pos[0] + current.move_row[i], 
						new_col = current.current_pos[1] + current.move_col[i]; 
						inBoundary(new_row, new_col); 
						new_row += new_row > current.current_pos[0]? 1 : -1, 
						new_col += new_col > current.current_pos[1]? 1 : -1) {
					if(notVisited(board, new_row, new_col)) {
						Bishop new_position = new Bishop(new_row, new_col);
						new_position.turns = current.turns + 1;
						new_position.path = new ArrayList<Integer>(current.path);//
						new_position.path.add(new_row);//
						new_position.path.add(new_col);//
						bishops_list.add(new_position);
						board.position[new_row][new_col] = 1;
					}
				}
			}
		}
		return null;
	}
	
	public static King startKing(int start_row, int start_col, int end_row, int end_col) {
		
		Board board = new Board();
		King king = new King(start_row, start_col);

		LinkedList<King> kings_list = new LinkedList<King>();
		king.path.add(start_row);//
		king.path.add(start_col);//
		kings_list.add(king);
		board.position[start_row][start_col] = 1;
		
		//Using BFS traversal to track all paths until target is reached 
		//or until piece hits boundary
		while(kings_list.size() != 0) {
			King current = kings_list.poll();
			if(isAtTarget(current.current_pos, end_row, end_col)) {
				return current;//
			}
			//For each knight in the list, consider all possible moves that can be made
			for(int i = 0; i < current.move_row.length; i++) {
				int new_row = current.current_pos[0] + current.move_row[i];
				int new_col = current.current_pos[1] + current.move_col[i];
				if(inBoundary(new_row, new_col) && notVisited(board, new_row, new_col)) {
					King new_position = new King(new_row, new_col);
					new_position.turns = current.turns + 1;
					new_position.path = new ArrayList<Integer>(current.path);//
					new_position.path.add(new_row);//
					new_position.path.add(new_col);//
					kings_list.add(new_position);
					board.position[new_row][new_col] = 1;
				}
			}
		}
		return null;
	}
	
	//Helper function to check whether the position inputted lies within the boundaries of the board
	public static boolean inBoundary(int row, int col) {
		if(row < 0 || row > 7 || col < 0 || col > 7) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//Helper function to check whether an inputted position has been visited before on the this board
	public static boolean notVisited(Board board, int row, int col) {
		if(board.position[row][col] == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Helper function to check if the target position has been reached
	public static boolean isAtTarget(int[] pos, int end_row, int end_col) {
		if(pos[0] == end_row && pos[1] == end_col) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Helper function to check if the target and start positions are on the same
	//color square (for bishops)
	public static boolean isOnSameColorSquare(int start_row, int start_col, int end_row, int end_col) {
		if((start_row + start_col) % 2 == (end_row + end_col) % 2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Function to print output once minimum turns have been computed
	public static void printResult(int turns, ArrayList<Integer> path, int choice) {
		if(choice == 1) {
			System.out.println("KING:");
		}
		else if(choice == 2) {
			System.out.println("BISHOP:");
		}
		else if(choice == 3) {
			System.out.println("KNIGHT:");
		}
		if(turns != Integer.MAX_VALUE) {
			System.out.println("Minimum turns: " + turns);
			System.out.println("Path:");
			for(int i = 0; i < path.size(); i+=2) {
				System.out.print(path.get(i) + ", ");
				System.out.println(path.get(i + 1));
			}
		}
		else {
			System.out.println("Target not reachable");
		}
	
	}
}
