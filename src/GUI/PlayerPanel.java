package GUI;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private GamePanel game;

    private PlayButton[][] arrayButton;

    public PlayerPanel(GamePanel game) {
        this.game = game;

        setLayout(new GridLayout(game.getW(), game.getH()));

        arrayButton = game.getWorld().getArrayButton();

        setBorder(BorderFactory.createLoweredBevelBorder());
        for (int i = 0; i < arrayButton.length; i++) {
            for (int j = 0; j < arrayButton[i].length; j++) {
                add(arrayButton[i][j] = new PlayButton(this));
                arrayButton[i][j].addMouseListener(game);
            }
        }
    }

    public PlayButton[][] getArrayButton() {
        return arrayButton;
    }

    public void setArrayButton(PlayButton[][] arrayButton) {
        this.arrayButton = arrayButton;
    }

    public GamePanel getGame() {
        return game;
    }

    public void setGame(GamePanel game) {
        this.game = game;
    }

}

