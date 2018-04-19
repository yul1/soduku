public class Solution {

public static void main(String[] args) {
    Solution s = new Solution();
//    char[][] board = {{'6', '1', '5', '.', '.', '.', '.', '4', '7'},
//            {'.', '.', '.', '7', '.', '6', '9', '.', '.'},
//            {'7', '.', '9', '.', '.', '4', '.', '3', '.'},
//            {'2', '8', '7', '.', '6', '3', '5', '9', '4'},
//            {'3', '5', '.', '.', '.', '7', '.', '.', '8'},
//            {'9', '6', '.', '4', '8', '.', '.', '.', '2'},
//            {'8', '4', '.', '.', '.', '.', '.', '1', '.'},
//            {'5', '7', '.', '8', '.', '1', '6', '.', '9'},
//            {'1', '9', '.', '.', '.', '.', '.', '8', '.'}};
    
   // char[][] board2 = 
//    	{{___8_9_3_},
//    		{579____1_},
//    		{_____7_6_},
//    		{1_7_____3},
//    		{_________},
//    		{3_____8_7},
//    		{_9_3_____},
//    		{_2____451},
//    		{_8_6_5___	}};
    	 char[][] board = {{'.', '.' , '.' , '8' , '.' , '9' , '.' , '3' , '.'},
    	            {'5' , '7' , '9' , '.' , '.' , '.' , '.' , '1' , '.'},
    	            {'.' , '.' , '.' , '.' , '.' , '7' , '.' , '6' , '.'},
    	            {'1' , '.' , '7' , '.' , '.' , '.' , '.' , '.' , '3'},
    	            {'.' , '.' , '.' ,'.' , '.' , '.' , '.' , '.' , '.' },
    	            {'3' , '.' , '.' , '.' ,'.' , '.' , '8' , '.' , '7' },
    	            {'.' , '9' , '.' , '3' , '.' , '.' , '.' ,'.' , '.' },
    	            {'.' , '2' , '.' , '.' , '.' , '.' , '4' ,'5' , '1' },
    	            {'.' , '8' , '.' , '6' , '.' , '5' , '.' , '.', '.'}};

    s.solver(board);
    System.out.println(s.solver(board));
    for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
        	System.out.print(board[r][c] + " ");
        }
        System.out.println("");
    }
}
public boolean solver(char[][] board) {
    for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[0].length; c++) {
            if (board[r][c] == '.') {
                for (int k = 1; k <= 9; k++) {
                    board[r][c] = (char) ('0' + k);
                    //System.out.print(board[r][c]);
                    if (isValid(board, r, c) && solver(board)) {
                        return true;
                    } else {
                        board[r][c] = '.';
                    }
                 }
                return false;
             }
         }
     }
    return true;
}

public boolean isValid(char[][] board, int r, int c) {
    //check row
    boolean[] row = new boolean[9];
    for (int i = 0; i < 9; i++) {
        if (board[r][i] >= '1' && board[r][i] <= '9') {
        	System.out.println(board[r][i] - '1');
            if (row[board[r][i] - '1'] == false) {
                row[board[r][i] - '1'] = true;
            } else {
                return false;
            }
        }
    }

    //check column
    boolean[] col = new boolean[9];
    for (int i = 0; i < 9; i++) {
        if (board[i][c] >= '1' && board[i][c] <= '9') {
            if (col[board[i][c] - '1'] == false) {
                col[board[i][c] - '1'] = true;
            } else {
                return false;
            }
        }
    }

    //check the 3*3 grid
    boolean[] grid = new boolean[9];
    for (int i = (r / 3) * 3; i < (r / 3) * 3 + 3; i++) {
        for (int j = (c / 3) * 3; j < (c / 3) * 3 + 3; j++) {
            if (board[i][j] >= '1' && board[i][j] <= '9') {
                if (grid[board[i][j] - '1'] == false) {
                    grid[board[i][j] - '1'] = true;
                } else {
                    return false;
                }
            }
         }
    }

    return true;
}
}
