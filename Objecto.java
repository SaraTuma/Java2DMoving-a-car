/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caranimation;

import java.awt.Image;
import javax.swing.ImageIcon;


/**
 *
 * @author Sara Tuma
 */
public class Objecto{
    
    private int posx;
    private int posy;
    private int largura;
    private int altura;
    private int dx=1;
    private int dy=1;
    private Image img;
    
    public Objecto (int posx,int posy,int largura,int altura,String s){
        this.posx=posx; this.posy=posy; this.largura=largura;
        this.altura=altura;
        if(!s.equals(""))
            this.img = new ImageIcon(getClass().getResource(s)).getImage();
        
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    
}
