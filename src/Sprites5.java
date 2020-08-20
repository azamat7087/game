import java.awt.*;

public class Sprites5 extends Game1 {
    private int x;
    private int y;

    private Image image5; //изображение acid

    public Sprites5(Image image5) {
        this.image5 = image5;
    }

    public int getWidth5() { //получаем ширину картинки
        return image5.getWidth(null);

    }

    public int getHeight5() { //получаем высоту картинки
        return image5.getHeight(null);
    }

    public void draw5(Graphics g1, int x, int y) { //рисуем картинку
        g1.drawImage(image5, x, y, null);
    }

}