/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caranimation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Sara Tuma
 */
//Ouvir do teclado (keylistener)

public class InputTeclado implements KeyListener{ //Apenas vai se encarregar da entrada de dados (teclado,mouse,outros...)

    private boolean cima=false;
    private boolean baixo=false;
    
    public InputTeclado (){
        
    }
    
    @Override
    public void keyTyped(KeyEvent ke) //Como um get - retorna o codigo da tecla pressionada
    {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) // Diz quando foi pressionado uma tecla (metodo de escuta)
    {
        if(ke.getKeyCode()==KeyEvent.VK_UP){
            this.cima=true;
            
        }
        if(ke.getKeyCode()==KeyEvent.VK_DOWN){
            this.baixo=true;
            
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke) // O que acontecece enquanto a tecla despressionada*
    {
        if(ke.getKeyCode()==KeyEvent.VK_UP){
            this.cima=false;
            
        }
        if(ke.getKeyCode()==KeyEvent.VK_DOWN){
            this.baixo=false;
            
        }
        
    }

    public boolean isCima() {
        return cima;
    }

    public boolean isBaixo() {
        return baixo;
    }


}
