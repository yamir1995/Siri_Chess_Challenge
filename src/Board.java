
public class Board {
	int position[][];
	
	Board() {
		position = new int[8][8];
		//Initialize all squares on the chessboard to be 0 meaning not visited
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				position[i][j] = 0;
			}
		}
	}
	
}
