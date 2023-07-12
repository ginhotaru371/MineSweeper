package GUI;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.controller;

public class GamePanel extends JPanel implements MouseListener {

    private static final long serialVersionUID = 1L;
    private Notification p1;
    private PlayerPanel p2;

    private GameFrame gameFrame;

    private controller controller;

    private int w;
    private int h;
    private int boom;

    public GamePanel(int w, int h, int boom, GameFrame gameFrame) {

        this.gameFrame = gameFrame;

        this.boom = boom;
        this.w = w;
        this.h = h;

        controller = new controller(w, h, boom, this);

        setLayout(new BorderLayout(20, 20));

        add(p1 = new Notification(this), BorderLayout.NORTH);
        add(p2 = new PlayerPanel(this), BorderLayout.CENTER);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        getP1().getBt().setStage(EmoButton.wow);
        getP1().getBt().repaint();
        PlayButton[][] arrayButton = p2.getArrayButton();
        for (int i = 0; i < arrayButton.length; i++) {
            for (int j = 0; j < arrayButton[i].length; j++) {
                if (e.getButton() == 1 && e.getSource() == arrayButton[i][j] && !controller.getArrayFlagged()[i][j]) {

                    if (!getP1().getTime().isRunning()) {
                        getP1().getTime().start();
                    }

                    if (!controller.open(i, j)) {

                        if (controller.isComplete()) {

                            getP1().getTime().stop();
                            getP1().getBt().setStage(EmoButton.lose);
                            getP1().getBt().repaint();

                            int option = JOptionPane.showConfirmDialog(this, "You lost, play again?", "Notification",
                                    JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                gameFrame.setVisible(false);
                                new GameFrame(w, h, boom);
                            } else {
                                controller.fullTrue();
                            }
                        } else if (controller.isEnd()) {

                            getP1().getTime().stop();
                            getP1().getBt().setStage(EmoButton.win);
                            getP1().getBt().repaint();

                            int option = JOptionPane.showConfirmDialog(this, "You win, play again ?", "Notification",
                                    JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                gameFrame.setVisible(false);
                                new GameFrame(w, h, boom);
                            }
                        }
                    }
                } else if (e.getButton() == 3 && e.getSource() == arrayButton[i][j]) {
                    controller.flagged(i, j);
                }
                if (e.getClickCount() == 2 && e.getSource() == arrayButton[i][j] && controller.getArrayBoolean()[i][j]) {
                    if (!controller.clickDouble(i, j)) {

                        int option = JOptionPane.showConfirmDialog(this, "You lost, play again?", "Notification",
                                JOptionPane.YES_NO_OPTION);

                        if (option == JOptionPane.YES_OPTION) {
                            gameFrame.setVisible(false);
                            new GameFrame(w, h, boom);
                        } else {
                            controller.fullTrue();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        getP1().getBt().setStage(EmoButton.now);
        getP1().getBt().repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public controller getWorld() {
        return controller;
    }

    public void setWorld(controller controller) {
        this.controller = controller;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public int getBomb() {
        return boom;
    }

    public void setBoom(int boom) {
        this.boom = boom;
    }

    public Notification getP1() {
        return p1;
    }

    public void setP1(Notification p1) {
        this.p1 = p1;
    }

    public PlayerPanel getP2() {
        return p2;
    }

    public void setP2(PlayerPanel p2) {
        this.p2 = p2;
    }
}
