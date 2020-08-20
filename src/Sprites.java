import java.awt.*;

public class Sprites extends Game1 {
    private Image image; //изображение hero



    private int x;
    private int y;


    public Sprites(Image image) {
        this.image = image;

    }


    public int getWidth() { //получаем ширину картинки
        return image.getWidth(null);

    }


    public int getHeight() { //получаем высоту картинки
        return image.getHeight(null);
    }


    public void draw(Graphics g,int x,int y) { //рисуем картинку
        g.drawImage(image,x,y,null);
    }


}
