import java.awt.*;


public class Sprites2 extends Game1 {
    private int x;
    private int y;
    private Image image2; //изображение zombie
    public Sprites2(Image image2) {
        this.image2 = image2;
    }
    public int getWidth2() { //получаем ширину картинки
        return image2.getWidth(null);

    }
    public int getHeight2() { //получаем высоту картинки
        return image2.getHeight(null);
    }
    public void draw2(Graphics g,int x,int y) { //рисуем картинку
        g.drawImage(image2,x,y,null);
    }
    public void disapear2(Graphics g){
        g.drawImage(image2,1000,1000,null);

    }
}
