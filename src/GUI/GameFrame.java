package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import GUI.LoadData;

public class GameFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private LoadData loadData;

    private GamePanel gamePanel;

    private JMenuBar mnb;
    private JMenu menu;
    private JMenuItem basic, normal, hard, custom, newGame, exit;

    private String wCus, hCus, bombCus;

    public GameFrame(int w, int h, int bomb) {

        loadData = new LoadData();

        setJMenuBar(mnb = new JMenuBar());
        mnb.add(menu = new JMenu("Menu"));

        menu.add(newGame = new JMenuItem("New game"));
        menu.addSeparator();
        menu.add(basic = new JMenuItem("Basic"));
        menu.add(normal = new JMenuItem("Normal"));
        menu.add(hard = new JMenuItem("Hard"));
        menu.add(custom = new JMenuItem("Custom"));
        menu.addSeparator();
        menu.add(exit = new JMenuItem("Exit"));

        if (w == 8) {
            basic.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
        } else if (w == 16) {
            normal.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
        } else if (h == 30){
            hard.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
        } else {
            custom.setIcon(new ImageIcon(loadData.getListImage().get("tick")));
        }

        basic.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GameFrame(8, 8, 10);
            }
        });

        normal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GameFrame(16, 16, 40);
            }
        });

        hard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GameFrame(16, 30, 99);
            }
        });
        custom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                wCus = (String) JOptionPane.showInputDialog(null, "Weight (< 51)", "Custom", JOptionPane.INFORMATION_MESSAGE, null, null, null);
                while (Integer.parseInt(wCus) > 50) {
                    JOptionPane.showMessageDialog(null, "Số quá lớn, vui lòng nhập lại", "ERROR", JOptionPane.ERROR_MESSAGE);
                    wCus = (String) JOptionPane.showInputDialog(null, "Weight (< 51)", "Custom", JOptionPane.INFORMATION_MESSAGE, null, null, null);
                }

                hCus = (String) JOptionPane.showInputDialog(null, "Height (< 21)", "Custom", JOptionPane.INFORMATION_MESSAGE, null, null, null);
                while (Integer.parseInt(hCus) > 20) {
                    JOptionPane.showMessageDialog(null, "Số quá lớn, vui lòng nhập lại", "ERROR", JOptionPane.ERROR_MESSAGE);
                    hCus = (String) JOptionPane.showInputDialog(null, "Height (< 21)", "Custom", JOptionPane.INFORMATION_MESSAGE, null, null, null);
                }

                bombCus = (String) JOptionPane.showInputDialog(null, "Bomb (< " + (0.2 * (Integer.parseInt(hCus) * Integer.parseInt(wCus))) +")", "Custom", JOptionPane.INFORMATION_MESSAGE, null, null, null);
                while (Integer.parseInt(bombCus) > (0.2 * (Integer.parseInt(hCus) * Integer.parseInt(wCus)))) {
                    JOptionPane.showMessageDialog(null, "Số quá lớn, vui lòng nhập lại", "ERROR", JOptionPane.ERROR_MESSAGE);
                    bombCus = (String) JOptionPane.showInputDialog(null, "Bomb (< 200)", "Custom", JOptionPane.INFORMATION_MESSAGE, null, null, null);
                }

                setVisible(false);
                new GameFrame(Integer.parseInt(hCus), Integer.parseInt(wCus), Integer.parseInt(bombCus));
            }
        });

        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GameFrame(w, h, bomb);
            }
        });

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(gamePanel = new GamePanel(w, h, bomb, this));

        setIconImage(loadData.getListImage().get("icon"));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame(8, 8, 10);
    }

    public LoadData getLoadData() {
        return loadData;
    }

    public void setLoadData(LoadData loadData) {
        this.loadData = loadData;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}

