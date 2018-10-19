import java.util.*;
public class King {
	//To store current position on board
	int current_pos[];
	//Storing all possible moves as increments of current position
	int move_row[] = {-1, -1, -1, 0, 0, 1, 1, 1};
	int move_col[] = {-1, 0, 1, -1, 1, -1, 0, 1};
	int turns;
	ArrayList<Integer> path;
	
	King(int start_row, int start_col) {
		current_pos = new int[2];
		current_pos[0] = start_row;
		current_pos[1] = start_col;
		turns = 0;
		path = new ArrayList<Integer>();
	}
}
