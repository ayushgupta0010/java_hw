import java.util.Scanner;

public class Chess {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = -1, nQueens = -1;

        do {
            System.out.print("Enter size of the board (greater than or equal to 4): ");
            size = sc.nextInt();
        } while (size < 4);

        do {
            System.out.print("Enter number of queens (cannot be more than the size): ");
            nQueens = sc.nextInt();
        } while (nQueens < 1 || nQueens > size);

        boolean board[][] = new boolean[size][size];

        solveNQueens(board, 0, nQueens);

        printBoard(board);

        sc.close();
    }

    public static boolean solveNQueens(boolean board[][], int col, int nQueens) {
        if (col >= nQueens)
            return true;

        for (int i = 0; i < board.length; ++i) {
            // check if it's safe to place the queen here
            if (isSafe(board, i, col)) {
                // place the queen 
                board[i][col] = true;

                // place rest of the queens
                if (solveNQueens(board, col + 1, nQueens))
                    return true;

                // backtrack if solution doesn't work
                board[i][col] = false;
            }
        }

        return false;
    }

    public static boolean isSafe(boolean board[][], int row, int col) {
        // check row
        for (int j = 0; j < col; ++j) {
            if (board[row][j])
                return false;
        }

        // check upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j])
                return false;
        }

        // check lower diagonal
        for (int i = row, j = col; j >= 0 && i < board.length; ++i, --j) {
            if (board[i][j])
                return false;
        }

        return true;
    }

    public static void printBoard(boolean board[][]) {
        int size = board.length;

        for (int i = 0; i < size; ++i) {
            System.out.print(size - i);

            for (int j = 0; j < size; ++j) {
                System.out.print(board[i][j] ? " Q" : " .");
            }

            System.out.println();
        }

        System.out.print(" ");
        for (char ch = 'a'; ch < 'a' + size; ++ch) {
            System.out.print(" " + ch);
        }

        System.out.println();
    }
}
