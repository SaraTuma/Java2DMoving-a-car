/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caranimation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Sara Tuma
 */

// Classe para desenhar e fazer movimentos com as teclas (pageUp e pageDown)

public class DesenharEMovimentar extends JPanel implements Runnable{
    
    InputTeclado entrada = new InputTeclado(); //Para entrada de dados
    private int qmov=40; //Quantidade de movimentos para cima ou baixo
    
    private int angulo=0;
    Objecto carro = new Objecto(0,203,150,80,"");
     // roda 1 (0,280,40,40)
    Objecto roda1 = new Objecto(carro.getPosx()+2,carro.getPosy()+77,carro.getLargura()-110,carro.getAltura()-40,"roda.png");
    // roda 2 (150,280,40,40)
    Objecto roda2 = new Objecto(carro.getPosx()+105,carro.getPosy()+77,carro.getLargura()-110,carro.getAltura()-40,"roda.png");
    Objecto estrada = new Objecto(0,0,this.getWidth(),this.getHeight(),"estrada.jpg");
    Objecto rect1 = new Objecto(carro.getPosx() +1,carro.getPosy()+1,carro.getLargura()-120,carro.getAltura()-55,"");
    Objecto rect2 = new Objecto(carro.getPosx() +120,carro.getPosy()+35,carro.getLargura()-120,carro.getAltura()-55,"");
    
    
    
    Thread t = new Thread(this);
    
    public DesenharEMovimentar(){
        JFrame f = new JFrame();
        f.setSize(800,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.add(this);
        f.setVisible(true);
        f.addKeyListener(entrada); //Avisando a janela, das possiveis entradas via teclado
        t.start(); //Começa o trabalho da thread, executando a funcao RUN()
        // Mudando ou aumentando o valor do deslocamento em x dos objectos rectangulares
        //Para haver diferença entre o dx do carro e o deles
        //Um desenhado a esquerda(rect1) e o outro a direita(rect2)
        //Por esse motivo os movimentos inicialmente têm sentidos contrárioa
        rect1.setDx(2);
        rect2.setDx(-2);
    }

     @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(estrada.getImg(),estrada.getPosx(), estrada.getPosy(), this.getWidth(), this.getHeight(),null);
        

        
        g2d.setColor(Color.yellow);
        g2d.fillRect(carro.getPosx(),carro.getPosy(),carro.getLargura(),carro.getAltura());
        
        //Primeira roda
        AffineTransform obj = g2d.getTransform();
        g2d.rotate(angulo*-1, roda1.getPosx()+(roda1.getLargura()/2), roda1.getPosy()+(roda1.getAltura()/2));
        g2d.drawImage(roda1.getImg(),roda1.getPosx(), roda1.getPosy(), roda1.getLargura(), roda1.getAltura(),null);
        g2d.setTransform(obj);
        obj = g2d.getTransform();
        
        //Segunda roda
        
        obj = g2d.getTransform();
        g2d.rotate(angulo*-1, roda2.getPosx()+(roda2.getLargura()/2), roda2.getPosy()+(roda2.getAltura()/2));
        g2d.drawImage(roda2.getImg(),roda2.getPosx(), roda2.getPosy(), roda2.getLargura(), roda2.getAltura(),null);
        g2d.setTransform(obj);
        obj = g2d.getTransform();
        
        g2d.setColor(Color.black);
        g2d.fillRect(rect1.getPosx(),rect1.getPosy(),rect1.getLargura(),rect1.getAltura());
        
        g2d.setColor(Color.black);
        g2d.fillRect(rect2.getPosx(),rect2.getPosy(),rect2.getLargura(),rect2.getAltura());
        
        

        
    }
    
    private void dormir(){
        try {
            t.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(DesenharEMovimentar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void actualizar(){
        //Quando é pressionada a tecla pgDown
        if(entrada.isBaixo() && carro.getPosy()==203) {
            carro.setPosy(carro.getPosy()+qmov);
            roda1.setPosy(roda1.getPosy()+ qmov);
            roda2.setPosy(roda2.getPosy()+ qmov);
            rect1.setPosy(rect1.getPosy()+qmov);
            rect2.setPosy(rect2.getPosy()+qmov);
        }
        //Quando é pressionada a tecla pgUp
        if(entrada.isCima()&& carro.getPosy()>203){
            carro.setPosy(carro.getPosy()-qmov);
            roda1.setPosy(roda1.getPosy()- qmov);
            roda2.setPosy(roda2.getPosy()- qmov);
            rect1.setPosy(rect1.getPosy()-qmov);
            rect2.setPosy(rect2.getPosy()-qmov);
        }
        
        
        if(carro.getPosx()>=this.getWidth()){ //Quando os objectos saiem da tela
            //Verificando qual a posição do carro antes de sair da tela
            //Para adicona-la ao entrar na tela
            if((carro.getPosy()>203))
            {
                carro.setPosy(203+qmov);
            }else
                carro.setPosy(203);
            //Alterando os valores dos objectos depois de sair da tela
            carro.setPosx(0);
            //rect1.setDx(rect1.getDx()*-1); rect2.setDx(rect2.getDx()*-1);
            rect2.setDx(-2); rect1.setDx(2);
             roda1.setPosx(carro.getPosx()+2); roda1.setPosy(carro.getPosy()+77);
            roda2.setPosx(carro.getPosx()+105); roda1.setPosy(carro.getPosy()+77);
            rect1.setPosx(carro.getPosx() +1); rect1.setPosy(carro.getPosy()+1);
            
            rect2.setPosx(carro.getPosx() +120); rect2.setPosy(carro.getPosy()+35);
            
        }
        //Os ifs asseguir são para manter os objectos rectangulares (cor preta)
        //Dentro do carro
        if(rect1.getPosx()>=(carro.getPosx()+carro.getLargura())-rect1.getLargura()|| rect1.getPosx()==carro.getPosx())
            rect1.setDx(rect1.getDx()*-1);
        if(rect2.getPosx()>=(carro.getPosx()+carro.getLargura())-rect2.getLargura()|| rect2.getPosx()==carro.getPosx())
            rect2.setDx(rect2.getDx()*-1);
        //Actualizando as posiçoes em x, para repinta-las
        carro.setPosx(carro.getPosx()+carro.getDx());
        roda1.setPosx(roda1.getPosx()+roda1.getDx());
        roda2.setPosx(roda2.getPosx()+roda2.getDx());
        rect1.setPosx(rect1.getPosx()+rect1.getDx());
        rect2.setPosx(rect2.getPosx()-rect2.getDx());
        
        
        
            
    }
    
    @Override
    public void run() {
        while(true){
            dormir();
            actualizar(); //Para actualizar os dados do proximo desenho
            angulo++; //Angulo da rotação das rodas
            
            repaint(); //Chamada da função paintComponent()
        }
    }
    
}
