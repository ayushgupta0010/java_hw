public class Sudoku {
    public static boolean solve(int board[][]) {
        int row = -1, col = -1;
        boolean isEmpty = true;

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }

            if (!isEmpty)
                break;
        }

        if (isEmpty)
            return true;

        for (int num = 1; num <= board.length; ++num) {
            // check if it's safe to place the number
            if (isSafe(board, row, col, num)) {
                // place the number
                board[row][col] = num;

                // solve the rest of the board
                if (solve(board))
                    return true;

                // backtrack if solution doesn't work
                board[row][col] = 0;
            }
        }

        return false;
    }

    public static boolean isSafe(int board[][], int row, int col, int num) {
        // ensure row is unique
        for (int i = 0; i < board.length; ++i) {
            if (board[row][i] == num)
                return false;
        }

        // ensure col is unique
        for (int j = 0; j < board.length; ++j) {
            if (board[j][col] == num)
                return false;
        }

        // check the box
        int sqrt = (int) Math.sqrt(board.length);
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;

        for (int i = rowStart; i < rowStart + sqrt; ++i) {
            for (int j = colStart; j < colStart + sqrt; ++j) {
                if (board[i][j] == num)
                    return false;
            }
        }

        return true;
    }

    public static void printBoard(int board[][]) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
