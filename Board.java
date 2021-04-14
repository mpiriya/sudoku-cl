import java.util.ArrayList;

public class Board {
	public int[][] values;
	
	public Board() {
		values = new int[9][9];
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < values[i].length; j++) {
				values[i][j] = -1;
			}
		}
	}

	public Board(int[][] values) {
		this.values = values;
	}

	public boolean valid() {
		//row, col, box
		int[] count = new int[10];
		int i, j, k;
		//row major order O(n^2)
		for(i = 0; i < 9; i++) {
			for(j = 0; j < 9; j++) 
				count[values[i][j]]++; // count instances of each value
			for(k = 1; k < count.length; k++) 
				if(count[k] > 1) 
					return false; // board not valid if any value appears more than once in a row
			for(k = 0; k < count.length; k++) 
				count[k] = 0;
		}

		//col major order O(n^2)
		for(j = 0; j < 9; j++) {
			for(i = 0; i < 9; i++) 
				count[values[i][j]]++;
			for(k = 1; k < count.length; k++) 
				if(count[k] > 1) 
					return false; // board not valid if any value appears more than once in a col
			for(k = 0; k < count.length; k++) 
				count[k] = 0;			
		}

		//go thru each box
		int br, bc, rowLim, colLim;
		for(i = 0; i < 3; i++) {
			for(j = 0; j < 3; j++) {
				rowLim = i*3 + 3;
				colLim = j*3 + 3;
				for(br = i*3; br < rowLim; br++) 
					for(bc = j*3; bc < colLim; bc ++) 
						count[values[br][bc]]++;
				for(k = 1; k < count.length; k++) 
					if(count[k] > 1) 
						return false; // board not valid if any value appears more than once in a box
				for(k = 0; k < count.length; k++) 
					count[k] = 0;	
			}
		}

		return true;
	}

	public String toString() {
		String toRet = "";
		int i, j, current, N = values.length;
		for(i = 0; i < N; i++) {
			toRet += "| ";
			for(j = 0; j < N; j++) {
				current = values[i][j];
				toRet += (current > 0 ? current : " ") + 
					((j + 1) % 3 == 0 ? " | " : " ! ");
			}
			if((i + 1) % 3 == 0) {
				toRet += "\n" + "=====================================";
			}
			toRet += "\n";
		}

		return toRet;
	}

	public static void main(String[] taco) {
		int[][] values = new int[9][9];
		for(int i = 0; i < values.length; i++) {
			for(int j = 0; j < values.length; j++) {
				values[i][j] = (int) (Math.random() * 9) + 1;
			}
		}

		Board b = new Board(values);
		System.out.println(b);
	}
}

class Tile {
	int value;
	int row;
	int col;
	ArrayList<Integer> possible;

	public Tile(int row, int col, ArrayList<Integer> possible) {
		this.value = -1;
		this.row = row;
		this.col = col;
		this.possible = possible;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setPossible(ArrayList<Integer> possible) {
		this.possible = possible;
	}
}
