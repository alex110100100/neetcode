package com.alex.neetcode150.arraysAndHashing.validSudoku;


import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] columnSets = new HashSet[9];
        Set<Character>[] rowSets = new HashSet[9];
        Set<Character>[][] boxSets = new HashSet[3][3];

        int row, column;

        for (row = 0; row < board.length; row++) {
            rowSets[row] = new HashSet<>();
            for (column = 0; column < board.length; column++) {
                char c = board[row][column];
                if (c == '.') continue;

                if (columnSets[column] == null)
                    columnSets[column] = new HashSet<>();

                if (boxSets[row/3][column/3] == null)
                    boxSets[row/3][column/3] = new HashSet<>();

                if (!columnSets[column].add(c) || !rowSets[row].add(c) || !boxSets[row/3][column/3].add(c))
                    return false;
            }
        }
        return true;
    }
}


// Same approach as above solution, but maybe easier to understand
class VerboseSolution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] columnSets = new HashSet[9];
        Set<Character>[] rowSets = new HashSet[9];
        Set<Character>[][] boxSets = new HashSet[3][3];

        columnSets[0] = new HashSet<>();
        columnSets[1] = new HashSet<>();
        columnSets[2] = new HashSet<>();
        columnSets[3] = new HashSet<>();
        columnSets[4] = new HashSet<>();
        columnSets[5] = new HashSet<>();
        columnSets[6] = new HashSet<>();
        columnSets[7] = new HashSet<>();
        columnSets[8] = new HashSet<>();

        rowSets[0] = new HashSet<>();
        rowSets[1] = new HashSet<>();
        rowSets[2] = new HashSet<>();
        rowSets[3] = new HashSet<>();
        rowSets[4] = new HashSet<>();
        rowSets[5] = new HashSet<>();
        rowSets[6] = new HashSet<>();
        rowSets[7] = new HashSet<>();
        rowSets[8] = new HashSet<>();

        boxSets[0][0] = new HashSet<>();
        boxSets[0][1] = new HashSet<>();
        boxSets[0][2] = new HashSet<>();
        boxSets[1][0] = new HashSet<>();
        boxSets[1][1] = new HashSet<>();
        boxSets[1][2] = new HashSet<>();
        boxSets[2][0] = new HashSet<>();
        boxSets[2][1] = new HashSet<>();
        boxSets[2][2] = new HashSet<>();


        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                char c = board[row][column];
                if (c == '.') continue;

                if (columnSets[column].contains(c) || rowSets[row].contains(c) || boxSets[row/3][column/3].contains(c))
                    return false;

                columnSets[column].add(c);
                rowSets[row].add(c);
                boxSets[row/3][column/3].add(c);
            }
        }

        return true;
    }
}



class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Valid incomplete board (from the example)
        char[][] board1 = {
                {'1','2','.','.','3','.','.','.','.'},
                {'4','.','.','5','.','.','.','.','.'},
                {'.','9','8','.','.','.','.','.','3'},
                {'5','.','.','.','6','.','.','.','4'},
                {'.','.','.','8','.','3','.','.','5'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','.','.','.','.','.','2','.','.'},
                {'.','.','.','4','1','9','.','.','8'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println("Test 1 (Valid incomplete board): " + solution.isValidSudoku(board1));  // Expected: true

        // Test Case 2: Invalid board with duplicate in row
        char[][] board2 = {
                {'1','1','.','.','.','.','.','.','.'},  // Two 1's in first row
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
        };
        System.out.println("Test 2 (Invalid row): " + solution.isValidSudoku(board2));  // Expected: false

        // Test Case 3: Invalid board with duplicate in column
        char[][] board3 = {
                {'1','.','.','.','.','.','.','.','.'},
                {'1','.','.','.','.','.','.','.','.'},  // Two 1's in first column
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
        };
        System.out.println("Test 3 (Invalid column): " + solution.isValidSudoku(board3));  // Expected: false

        // Test Case 4: Invalid board with duplicate in 3x3 box
        char[][] board4 = {
                {'1','.','.','.','.','.','.','.','.'},
                {'.','1','.','.','.','.','.','.','.'},  // Two 1's in top-left 3x3 box
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
        };
        System.out.println("Test 4 (Invalid 3x3 box): " + solution.isValidSudoku(board4));  // Expected: false
    }
}






