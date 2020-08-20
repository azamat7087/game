import java.awt.*;

public class Sprites4 extends Game1 {
    private int x;
    private int y;
    private Image image4; //изображение home
    public Sprites4(Image image4) {
        this.image4 = image4;
    }
    public int getWidth4() { //получаем ширину картинки
        return image4.getWidth(null);

    }
    public int getHeight4() { //получаем высоту картинки

        return image4.getHeight(null);
    }
    public void draw4(Graphics g,int x,int y) { //рисуем картинку
        g.drawImage(image4,x,y,null);
    }
}

