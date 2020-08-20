import java.awt.*;

public class Sprites3 extends Game1 {
    private int x;
    private int y;
    private Image image3; //изображение home
    public Sprites3(Image image3) {
        this.image3 = image3;
    }
    public int getWidth3() { //получаем ширину картинки
        return image3.getWidth(null);

    }
    public int getHeight3() { //получаем высоту картинки

        return image3.getHeight(null);
    }
    public void draw3(Graphics g,int x,int y) { //рисуем картинку
        g.drawImage(image3,x,y,null);
    }
}
