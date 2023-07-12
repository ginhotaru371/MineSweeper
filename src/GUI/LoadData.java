package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class LoadData {

    private HashMap<String, BufferedImage> listImage;

    public LoadData() {
        listImage = new HashMap<String, BufferedImage>();

        try {
            BufferedImage img = ImageIO.read(new File("resrc/minesweeper.png"));
            BufferedImage img2 = ImageIO.read(new File("resrc/minesweeper3.png"));

            listImage.put("icon", img2.getSubimage(476, 34, 32, 32));
            listImage.put("norButton", img2.getSubimage(14, 195, 16, 16));
            listImage.put("flag", img2.getSubimage(48, 195, 16, 16));
            listImage.put("bombRed", img2.getSubimage(116, 195, 16, 16));
            listImage.put("bombX", img2.getSubimage(133, 195, 16, 16));
            listImage.put("bomb", img2.getSubimage(99, 195, 16, 16));
            listImage.put("b0", img2.getSubimage(31, 195, 16, 16));
            listImage.put("b1", img2.getSubimage(14, 212, 16, 16));
            listImage.put("b2", img2.getSubimage(31, 212, 16, 16));
            listImage.put("b3", img2.getSubimage(48, 212, 16, 16));
            listImage.put("b4", img2.getSubimage(65, 212, 16, 16));
            listImage.put("b5", img2.getSubimage(82, 212, 16, 16));
            listImage.put("b6", img2.getSubimage(99, 212, 16, 16));
            listImage.put("b7", img2.getSubimage(116, 212, 16, 16));
            listImage.put("b8", img2.getSubimage(133, 212, 16, 16));
            listImage.put("smile", img2.getSubimage(14, 254, 24, 24));
            listImage.put("smilePress", img2.getSubimage(39, 254, 24, 24));
            listImage.put("smilePressPlay", img2.getSubimage(64, 254, 24, 24));
            listImage.put("emoLose", img2.getSubimage(114, 254, 24, 24));
            listImage.put("emoWin", img2.getSubimage(89, 254, 24, 24));
            listImage.put("0", img2.getSubimage(140, 230, 13, 23));
            listImage.put("1", img2.getSubimage(14, 230, 13, 23));
            listImage.put("2", img2.getSubimage(28, 230, 13, 23));
            listImage.put("3", img2.getSubimage(42, 230, 13, 23));
            listImage.put("4", img2.getSubimage(56, 230, 13, 23));
            listImage.put("5", img2.getSubimage(70, 230, 13, 23));
            listImage.put("6", img2.getSubimage(84, 230, 13, 23));
            listImage.put("7", img2.getSubimage(98, 230, 13, 23));
            listImage.put("8", img2.getSubimage(112, 230, 13, 23));
            listImage.put("9", img2.getSubimage(126, 230, 13, 23));
            listImage.put("voCuc", img2.getSubimage(154, 230, 13, 23));
            listImage.put("tick", img.getSubimage(140, 49, 7, 7));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, BufferedImage> getListImage() {
        return listImage;
    }

    public void setListImage(HashMap<String, BufferedImage> listImage) {
        this.listImage = listImage;
    }

}

