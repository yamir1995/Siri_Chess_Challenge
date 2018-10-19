import java.util.ArrayList;

public class Knight {
	//To store current position on board
	int current_pos[];
	//Storing all possible moves as increments of current position
	int move_row[] = {-2, -2, -1, -1, 1, 1, 2, 2};
	int move_col[] = {-1, 1, -2, 2, -2, 2, -1, 1};
	int turns;
	ArrayList<Integer> path;
	
	Knight(int start_row, int start_col) {
		current_pos = new int[2];
		current_pos[0] = start_row;
		current_pos[1] = start_col;
		turns = 0;
		path = new ArrayList<Integer>();
	}
}
