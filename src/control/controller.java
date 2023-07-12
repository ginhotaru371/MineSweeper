package control;

import java.util.Random;

import GUI.PlayButton;
import GUI.EmoButton;
import GUI.GamePanel;
import GUI.LabelNumber;

public class controller {

    private Random rd;

    private PlayButton[][] arrayButton;
    private int[][] arrayBomb;// bomb là số -1

    private boolean[][] arrayBoolean;

    private boolean[][] arrayFlagged;
    private int flag;

    private boolean isComplete;
    private boolean isEnd;

    private EmoButton emoButton;
    private LabelNumber lbTime, lbBomb;

    private int bomb;

    private GamePanel game;

    public controller(int w, int h, int bomb, GamePanel game) {

        this.game = game;
        this.bomb = bomb;

        arrayButton = new PlayButton[w][h];
        arrayBomb = new int[w][h];
        arrayBoolean = new boolean[w][h];
        arrayFlagged = new boolean[w][h];

        rd = new Random();

        createArrayBomb(bomb, w, h);
        createNumber();
    }

    public boolean clickDouble(int i, int j) {

        boolean isBomb = false;

        for (int l = i - 1; l <= i + 1; l++) {
            for (int k = j - 1; k <= j + 1; k++) {
                if (l >= 0 && l <= arrayBomb.length - 1 && k >= 0 && k <= arrayBomb[i].length - 1) {
                    if (!arrayFlagged[l][k]) {
                        if (arrayBomb[l][k] == -1) {
                            isBomb = true;
                            arrayButton[l][k].setNumber(12);
                            arrayButton[l][k].repaint();
                            arrayBoolean[l][k] = true;
                        } else if (!arrayBoolean[l][k]) {
                            if (arrayBomb[l][k] == 0) {
                                open(l, k);
                            } else {
                                arrayButton[l][k].setNumber(arrayBomb[l][k]);
                                arrayButton[l][k].repaint();
                                arrayBoolean[l][k] = true;
                            }
                        }
                    }
                }
            }
        }

        if (isBomb) {
            for (int j2 = 0; j2 < arrayBoolean.length; j2++) {
                for (int k = 0; k < arrayBoolean[i].length; k++) {
                    if (arrayBomb[j2][k] == -1 && !arrayBoolean[j2][k]) {
                        arrayButton[j2][k].setNumber(10);
                        arrayButton[j2][k].repaint();
                    }
                }
            }
            return false;
        }
        return true;
    }

    public void flagged(int i, int j) {
        if (!arrayBoolean[i][j]) {
            if (arrayFlagged[i][j]) {
                flag--;
                arrayFlagged[i][j] = false;
                arrayButton[i][j].setNumber(-1);
                arrayButton[i][j].repaint();
                game.getP1().updateLbBomb();
            } else if (flag < bomb) {
                flag++;
                arrayFlagged[i][j] = true;
                arrayButton[i][j].setNumber(9);
                arrayButton[i][j].repaint();
                game.getP1().updateLbBomb();
            }
        }
    }

    public boolean open(int i, int j) {

        if (!isComplete && !isEnd) {
            if (!arrayBoolean[i][j]) {
                if (arrayBomb[i][j] == 0) {

                    arrayBoolean[i][j] = true;
                    arrayButton[i][j].setNumber(0);
                    arrayButton[i][j].repaint();

                    if (checkWin()) {
                        isEnd = true;

                        return false;
                    }

                    for (int l = i - 1; l <= i + 1; l++) {
                        for (int k = j - 1; k <= j + 1; k++) {
                            if (l >= 0 && l <= arrayBomb.length - 1 && k >= 0 && k <= arrayBomb[i].length - 1) {
                                if (!arrayBoolean[l][k]) {
                                    open(l, k);
                                }
                            }
                        }
                    }

                    if (checkWin()) {
                        isEnd = true;

                        return false;
                    }

                } else {

                    int number = arrayBomb[i][j];

                    if (number != -1) {

                        arrayBoolean[i][j] = true;

                        arrayButton[i][j].setNumber(number);
                        arrayButton[i][j].repaint();

                        if (checkWin()) {
                            isEnd = true;

                            return false;
                        }
                        return true;
                    }
                }
            }

            if (arrayBomb[i][j] == -1) {
                arrayButton[i][j].setNumber(11);
                arrayButton[i][j].repaint();
                isComplete = true;

                for (int j2 = 0; j2 < arrayBoolean.length; j2++) {
                    for (int k = 0; k < arrayBoolean[i].length; k++) {
                        if (arrayBomb[j2][k] == -1 && !arrayBoolean[j2][k]) {
                            if (j2 != i || k != j) {
                                arrayButton[j2][k].setNumber(10);
                                arrayButton[j2][k].repaint();
                            }
                        }
                    }
                }
                return false;
            } else {
                if (checkWin()) {
                    isEnd = true;

                    return false;
                }
                return true;
            }
        } else {
            return false;
        }

    }

    public boolean checkWin() {
        int count = 0;
        for (int i = 0; i < arrayBoolean.length; i++) {
            for (int j = 0; j < arrayBoolean[i].length; j++) {
                if (!arrayBoolean[i][j]) {
                    count++;
                }
            }
        }
        if (count == bomb)
            return true;
        else
            return false;
    }

    public void createNumber() {
        for (int i = 0; i < arrayBomb.length; i++) {
            for (int j = 0; j < arrayBomb[i].length; j++) {
                if (arrayBomb[i][j] == 0) {
                    int count = 0;
                    for (int l = i - 1; l <= i + 1; l++) {
                        for (int k = j - 1; k <= j + 1; k++) {
                            if (l >= 0 && l <= arrayBomb.length - 1 && k >= 0 && k <= arrayBomb[i].length - 1)
                                if (arrayBomb[l][k] == -1) {
                                    count++;
                                }
                        }
                    }
                    arrayBomb[i][j] = count;
                }
            }
        }
    }

    public void createArrayBomb(int bomb, int w, int h) {
        int locationX = rd.nextInt(w);
        int locationY = rd.nextInt(h);

        arrayBomb[locationX][locationY] = -1;
        int count = 1;
        while (count != bomb) {
            locationX = rd.nextInt(w);
            locationY = rd.nextInt(h);
            if (arrayBomb[locationX][locationY] != -1) {

                arrayBomb[locationX][locationY] = -1;

                count = 0;
                for (int i = 0; i < arrayBomb.length; i++) {
                    for (int j = 0; j < arrayBomb[i].length; j++) {
                        if (arrayBomb[i][j] == -1)
                            count++;
                    }
                }
            }
        }

    }

    public void fullTrue() {
        for (int i = 0; i < arrayBoolean.length; i++) {
            for (int j = 0; j < arrayBoolean[i].length; j++) {
                if (!arrayBoolean[i][j]) {
                    arrayBoolean[i][j] = true;
                }
            }
        }
    }

    public PlayButton[][] getArrayButton() {
        return arrayButton;
    }

    public void setArrayButton(PlayButton[][] arrayButton) {
        this.arrayButton = arrayButton;
    }

    public EmoButton getEmoButton() {
        return emoButton;
    }

    public void setEmoButton(EmoButton emoButton) {
        this.emoButton = emoButton;
    }

    public LabelNumber getLbTime() {
        return lbTime;
    }

    public void setLbTime(LabelNumber lbTime) {
        this.lbTime = lbTime;
    }

    public LabelNumber getLbBomb() {
        return lbBomb;
    }

    public void setLbBomb(LabelNumber lbBomb) {
        this.lbBomb = lbBomb;
    }

    public boolean[][] getArrayBoolean() {
        return arrayBoolean;
    }

    public void setArrayBoolean(boolean[][] arrayBoolean) {
        this.arrayBoolean = arrayBoolean;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public boolean[][] getArrayFlagged() {
        return arrayFlagged;
    }

    public void setArrayFlagged(boolean[][] arrayFlagged) {
        this.arrayFlagged = arrayFlagged;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) { this.flag = flag; }

}
