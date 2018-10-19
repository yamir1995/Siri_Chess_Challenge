import java.util.ArrayList;

public class Bishop {
	//To store current position on board
	int current_pos[];
	//Storing all possible directions as increments of current position
	int move_row[] = {-1, -1, 1, 1};
	int move_col[] = {-1, 1, -1, 1};
	int turns;
	ArrayList<Integer> path;
	
	Bishop(int start_row, int start_col) {
		current_pos = new int[2];
		current_pos[0] = start_row;
		current_pos[1] = start_col;
		turns = 0;
		path = new ArrayList<Integer>();
	}
}
