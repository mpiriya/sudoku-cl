public class Board {
	int[][] values;
	
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
