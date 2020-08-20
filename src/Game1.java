import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt. Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import javax.swing.*;
import javax.swing.border.Border;


public class Game1 extends Canvas implements Runnable  {
    private static final long serialVersionUID = 1L;

    private boolean running;

    public static int WIDTH = 600;
    public static int HEIGHT = 500;
    public static String NAME = "GAME";

    private boolean aPressed = false;
    private boolean dPressed = false;
    private boolean sPressed = false;
    private boolean wPressed = false;
    private boolean spacePressed = false;
    private boolean shiftPressed = false;
    private boolean rPressed = false;



    public static Sprites hero;// hero
    public static Sprites1 bullets;
    public static Sprites2 zombie;
    public static Sprites3 home;
    public static Sprites4 boss;
    public static Sprites5 acid;

    //timers
    public static double timer=500;
    public static double timerofboss=700;
    public static double timerofbosswords=500;
    //hero
    public static double x1 = 0  ;
    public static double y1 = 0;

    //bullets
    public static double xb=x1;
    public static double yb=y1;
    public static int quantityBullets=8;
    private int r=2;
    private int speed=10;

    //zombie
    public static double xz=HEIGHT+60;
    public static double yz=0;
    public static double yz1=0;
    public static double yz2=0;
    public static int hit=0;

    //home
    public static double xh=0;
    public static double yh=0;

    //boss
    public static double xboss=HEIGHT;
    public static double yboss=WIDTH/2-120;
    public static int hpboss=1500;

    //acid
    public static double xacid=xboss+40;
    public static double yacid=yboss+65;


    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;



        init();

        while(running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            render();
            update(delta);
        }
    }


    public void init() {
        addKeyListener(new KeyInputHandler());
        hero = getSprite("man.png");
        bullets = getSprite1("bullet.jpg");
        zombie =getSprite2("zombie.png");
        home =getSprite3("home.png");
        boss=getSprite4("boss.png");
        acid=getSprite5("acid.jpg");
    }





    public void update(long delta) {



        /////////////////////////////////////////////////bullets
        if(spacePressed==true){
           xb++;

           if(xb>HEIGHT+43) {
               quantityBullets--;
           }
           if(quantityBullets==0){
               quantityBullets=0;
               xb=x1;
               yb=y1;
               xb--;

           }
           if(xb>HEIGHT+45){
                xb=x1;
           }

        }

        if (xb>x1) {
            xb++;
        }

        if(rPressed==true){
            quantityBullets=8;
            bullets.setVisible(true);
        }


////////////////////////////////////////////////////bullet

        if (aPressed == true && x1>-7) {
            x1-=0.4;
            //sleep();

        }
        if (dPressed == true && x1<HEIGHT+45) {
            x1+=0.4;
           // sleep();
        }
        if (wPressed==true && y1>-60){
            y1-=0.4;
          //  sleep();
        }
        if (sPressed==true && y1<460){
            y1+=0.4;
           // sleep();
        }
        if(shiftPressed==true){

            if (aPressed == true && x1>-7) {
                x1+=0.3;
                //sleep();

            }
            if (dPressed == true && x1<HEIGHT+45) {
                x1-=0.3;
                // sleep();
            }
            if (wPressed==true && y1>-60){
                y1+=0.3;
                //  sleep();
            }
            if (sPressed==true && y1<460){
                y1-=0.3;
                // sleep();
            }
        }

//////////////////////////////////////////////////zombie


            if(hit>=10){
                xz+=0.05;
            }


            if (xz > -20) {
                xz-=0.05;
            } else {
                xz = HEIGHT;
                yz=Math.random()*440+20;
            }

        Rectangle herozone = new Rectangle((int)x1,(int)y1,hero.getWidth(),hero.getHeight());
        Rectangle bulletszone = new Rectangle((int)xb,(int)y1+40,bullets.getWidth1(),bullets.getHeight1());
        Rectangle zombiezone = new Rectangle((int)xz-50,(int)yz-50,zombie.getWidth2(),zombie.getHeight2());
        Rectangle homezone = new Rectangle((int)xh,(int)yh-60,home.getWidth3(),home.getHeight3());
        Rectangle bosszone = new Rectangle((int)xboss-50,(int)yboss-50,boss.getWidth4(),boss.getHeight4());
        Rectangle acidzone = new Rectangle((int)xacid-20,(int)yacid-50,acid.getWidth5(),(int)0.5);
           if (zombiezone.contains(bulletszone)  ){

               hit++;
               xz=HEIGHT+70;
             yz=Math.random()*440+20;


           }
        if (homezone.contains(zombiezone)  ){

            String message ="";
            message+="Зомби добрался до дома!";
            JOptionPane.showMessageDialog(null,message,"Game over",JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }

        if (herozone.contains(zombiezone)  ){
            String message ="";
            message+="Вас съели!";
            JOptionPane.showMessageDialog(null,message,"Game over",JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
//////////////////////////////////////////////////zombie

//////////////////////////////////////////////////boss






        if(xacid<-20){
            xacid=xboss;
        }
        if(hit>=10) {
            xboss-=0.02;
            xacid-=0.3 ;
        }
        if(hit>=10) {
            if (bosszone.contains(bulletszone)) {
                hpboss--;
                if (hpboss == 0) {
                        String message = "";
                        message += "Вы защитили дом!";
                        JOptionPane.showMessageDialog(null, message, "Congratulations!!", JOptionPane.PLAIN_MESSAGE);
                        System.exit(0);
                    }

            }
            if (xboss<-7  ){

                String message ="";
                message+="Зомби босс добрался до дома!";
                JOptionPane.showMessageDialog(null,message,"Game over",JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
            }
            if (bosszone.contains(herozone)  ){
                String message ="";
                message+="Вас съел зомби босс!";
                JOptionPane.showMessageDialog(null,message,"Game over",JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
            }
        }
        if (herozone.contains(acidzone)  ){

            String message ="";
            message+="Вас облили кислотой!";
            JOptionPane.showMessageDialog(null,message,"Game over",JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }

//////////////////////////////////////////////////boss

    }







    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(4);
            requestFocus();
            return;
        }
        Graphics g =bs.getDrawGraphics();

          g.setColor(Color.GREEN);
          g.fillRect(0, 0, getWidth(), getHeight());

          hero.draw(g,(int) x1+56,(int) y1+56);
          g.dispose();



          Graphics g2 = bs.getDrawGraphics();
        if(hit<10) {
            zombie.draw2(g2, (int) xz, (int) yz);
            g2.dispose();
        }else{
            zombie.disapear2(g2);
            g2.dispose();
          }


          Graphics g1 = bs.getDrawGraphics();
          bullets.draw1(g1, (int)xb + 100, (int)y1 + 85);
          g1.dispose();

          Graphics g3 = bs.getDrawGraphics();
          home.draw3(g3, (int)xh, (int)yh-80);
          g3.dispose();

          Graphics g4 = bs.getDrawGraphics();
          String hit1 = Integer.toString(hit);
          g4.drawString("Попаданий: "+ hit1 ,90,30);
          g4.dispose();

          Graphics g5 = bs.getDrawGraphics();
          String quantityBullets1 = Integer.toString(quantityBullets);
          g5.drawString("Патроны: "+ quantityBullets1,180,30);
          g5.dispose();

          if (hit>=10){
              Graphics g6 = bs.getDrawGraphics();
              boss.draw4(g6, (int)xboss, (int)yboss);
              g6.dispose();
              Graphics g13 = bs.getDrawGraphics();
              acid.draw5(g13, (int)xacid, (int)yacid);
              g13.dispose();
          }

        if (hit>=10) {
            Graphics g7 = bs.getDrawGraphics();
            String quantityHp = Integer.toString(hpboss);
            g7.drawString("Здоровье босса: " + quantityHp, 250, 30);
            g7.dispose();
        }


        ////////////////////////////////// timer
        timer-=0.5;
        if(timer>0){
            Graphics g8 = bs.getDrawGraphics();
             g8.setFont(new Font("Arial",Font.PLAIN,18) );
             String s="Begin!";
             int length=(int)g8.getFontMetrics().getStringBounds(s,g).getWidth();
             g8.setColor(Color.WHITE);
             g8.drawString(s,WIDTH/2-length/2,HEIGHT/2);
       }
        if(quantityBullets==0){
            Graphics g9 = bs.getDrawGraphics();
            g9.setFont(new Font("Arial",Font.PLAIN,18) );
            String s="ПЕРЕЗАРЯДИСЬ!";
            int length=(int)g9.getFontMetrics().getStringBounds(s,g).getWidth();
            g9.setColor(Color.WHITE);
            g9.drawString(s,WIDTH/2-length/2,HEIGHT/2);
        }
        ////////////////////////////////// timer

        ////////////////////////////////// timer of boss
        if (hit==10){
            timerofboss-=0.3;
            if(timerofboss>0){
                Graphics g10 = bs.getDrawGraphics();
                g10.drawString("КАЙ ЖАКТЫКЫСЫН?",(int)xboss,(int)yboss);
                g10.dispose();
            }
        }

        String bosswords[]={"АЙ","БОЛЬНО ЖЕ","Я ГОЛОДЕН","ИСАТААЙ","ДА Я ТАКИХ КАК ТЫ..."};


            if(hpboss<1500 && hpboss>1370) {
                int i;
                Graphics g12 = bs.getDrawGraphics();
                for (i = 0; i < bosswords.length; i++) {
                    g12.drawString(bosswords[0], (int) xboss, (int) yboss);
                    g12.dispose();
                }
                bs.show();
            }
            if(hpboss<1370 && hpboss>1240) {
                int i;
                Graphics g12 = bs.getDrawGraphics();
                for (i = 0; i < bosswords.length; i++) {
                    g12.drawString(bosswords[1], (int) xboss-40, (int) yboss);
                    g12.dispose();

                }
                bs.show();
            }
            if(hpboss<1240 && hpboss>1110) {
                int i;
                Graphics g12 = bs.getDrawGraphics();
                for (i = 0; i < bosswords.length; i++) {
                    g12.drawString(bosswords[2], (int) xboss-40, (int) yboss);
                    g12.dispose();

                }
                bs.show();
            }
            if(hpboss<1110 && hpboss>980) {
                int i;
                Graphics g12 = bs.getDrawGraphics();
                for (i = 0; i < bosswords.length; i++) {
                    g12.drawString(bosswords[3], (int) xboss-40, (int) yboss);
                    g12.dispose();

                }
                bs.show();
            }
            if (hpboss < 980 && hpboss > 850) {
                int i;
                Graphics g12 = bs.getDrawGraphics();
                for (i = 0; i < bosswords.length; i++) {
                    g12.drawString(bosswords[4], (int) xboss-40, (int) yboss);
                    g12.dispose();

                }
                bs.show();
            }




        ////////////////////////////////// timer of boss

        bs.show();



    }







    public void sleep(){
        try
        {
            Thread.sleep(2);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
    public void sleep1(){
        try
        {
            Thread.sleep(1);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void sleep2(){
        try
        {
            Thread.sleep(10);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }



    public Sprites getSprite(String path) {
        BufferedImage sourceImage = null;

        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprites sprite = new Sprites(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));

        return sprite;
    }

    public Sprites1 getSprite1(String path1) {
        BufferedImage sourceImage1 = null;

        try {
            URL url1 = this.getClass().getClassLoader().getResource(path1);
            sourceImage1 = ImageIO.read(url1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprites1 sprite1 = new Sprites1(Toolkit.getDefaultToolkit().createImage(sourceImage1.getSource()));

        return sprite1;
    }

    public Sprites2 getSprite2(String path2) {
        BufferedImage sourceImage2 = null;

        try {
            URL url2 = this.getClass().getClassLoader().getResource(path2);
            sourceImage2 = ImageIO.read(url2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprites2 sprite2 = new Sprites2(Toolkit.getDefaultToolkit().createImage(sourceImage2.getSource()));

        return sprite2;
    }
    public Sprites3 getSprite3(String path3) {
        BufferedImage sourceImage3 = null;

        try {
            URL url3 = this.getClass().getClassLoader().getResource(path3);
            sourceImage3 = ImageIO.read(url3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprites3 sprite3 = new Sprites3(Toolkit.getDefaultToolkit().createImage(sourceImage3.getSource()));

        return sprite3;
    }

    public Sprites5 getSprite5(String path5) {
        BufferedImage sourceImage5 = null;
        try {
            URL url5 = this.getClass().getClassLoader().getResource(path5);
            sourceImage5 = ImageIO.read(url5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprites5 sprite5 = new Sprites5(Toolkit.getDefaultToolkit().createImage(sourceImage5.getSource()));

        return sprite5;
    }


        public Sprites4 getSprite4(String path3) {
        BufferedImage sourceImage4 = null;

        try {
            URL url4 = this.getClass().getClassLoader().getResource(path3);
            sourceImage4 = ImageIO.read(url4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprites4 sprite4 = new Sprites4(Toolkit.getDefaultToolkit().createImage(sourceImage4.getSource()));

        return sprite4;



    }

    public static void main(String[] args) {
        Game1 game = new Game1();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JFrame frame = new JFrame(Game1.NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        game.start();


}

    private class KeyInputHandler extends KeyAdapter {  // Управление
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                aPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                dPressed = true;
            }
            if (e.getKeyCode() ==KeyEvent.VK_W){
                wPressed = true;
            }
            if (e.getKeyCode()==KeyEvent.VK_S){
                sPressed = true;
            }
            if (e.getKeyCode()==KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
            if (e.getKeyCode()==KeyEvent.VK_SHIFT) {
                shiftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_R) {
                rPressed = true;
            }


        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                aPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
               dPressed = false;
            }
            if (e.getKeyCode() ==KeyEvent.VK_W){
                wPressed = false;
            }
            if (e.getKeyCode() ==KeyEvent.VK_S){
                sPressed = false;
            }
            if (e.getKeyCode() ==KeyEvent.VK_SPACE){
                spacePressed = false;
            }
            if (e.getKeyCode()==KeyEvent.VK_SHIFT) {
                shiftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_R) {
                rPressed = false;
            }
        }
    }
}
