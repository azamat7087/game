import java.awt.*;

public class Sprites1 extends Game1 {
    private int x;
    private int y;

    private Image image1; //изображение bullet
    public Sprites1(Image image1) {
        this.image1 = image1;
    }
    public int getWidth1() { //получаем ширину картинки
        return image1.getWidth(null);

    }
    public int getHeight1() { //получаем высоту картинки
        return image1.getHeight(null);
    }
    public void draw1(Graphics g1,int x,int y) { //рисуем картинку
        g1.drawImage(image1,x,y,null);
    }

}
