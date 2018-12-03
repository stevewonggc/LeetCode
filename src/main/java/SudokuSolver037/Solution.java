package SudokuSolver037;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public void solveSudoku(char[][] board) {

        List<Character>[][] characters = new ArrayList[board.length][board[0].length];

        Queue<Element> queue = new LinkedList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                characters[i][j] = new ArrayList<>();
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        characters[i][j].add(k);
                    }
                } else {
                    characters[i][j].add(board[i][j]);
                    queue.offer(new Element(i, j, board[i][j]));
                }
            }
        }

        while (!queue.isEmpty()) {
            Element element = queue.poll();

            System.out.println(element);
            for (int i = 0; i < 9; i++) {
                if (i != element.j) {
                    List<Character> list = characters[element.i][i];
                    if (list.remove(Character.valueOf(element.aChar))) {
                        if (list.size() == 1) {
                            queue.offer(new Element(element.i, i, list.get(0)));
                            board[element.i][i] = list.get(0);
                        }
                    }
                }
            }

            for (int i = 0; i < 9; i++) {
                if (i != element.i) {
                    List<Character> list = characters[i][element.j];
                    if (list.remove(Character.valueOf(element.aChar))) {
                        if (list.size() == 1) {
                            queue.offer(new Element(i, element.j, list.get(0)));
                            board[i][element.j] = list.get(0);
                        }
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!(element.i / 3 * 3 + i == element.i && element.j / 3 * 3 + j == element.j)) {
                        List<Character> list = characters[element.i / 3 * 3 + i][element.j / 3 * 3 + j];
                        if (list.remove(Character.valueOf(element.aChar))) {
                            if (list.size() == 1) {
                                queue.offer(new Element(element.i / 3 * 3 + i, element.j / 3 * 3 + j, list.get(0)));
                                board[element.i / 3 * 3 + i][element.j / 3 * 3 + j] = list.get(0);
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < characters.length; i++) {
                System.out.println(Arrays.toString(characters[i]));
            }
            System.out.println("===========================");
        }


    }

    class Element {
        public final int i;
        public final int j;
        public final char aChar;

        public Element(int i, int j, char aChar) {
            this.i = i;
            this.j = j;
            this.aChar = aChar;
        }

        public String toString() {
            return MessageFormat.format("i: {0}; j: {1}, char: {2}", i, j, aChar);
        }
    }

    public static void main(String[] args) {
        Solution wechatAuthController = new Solution();
        char[][] input = new char[][]{
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
        wechatAuthController.solveSudoku(input);


        for (int i = 0; i < input.length; i++) {
            System.out.println(Arrays.toString(input[i]));
        }

    }

}
