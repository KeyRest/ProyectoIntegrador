/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.GLogin;
import Vista.GRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class ControladorGIngreso implements ActionListener {
    private GLogin gLogin;
    private GRegistro gRegistro;
    
    

    public ControladorGIngreso(GLogin gLogin) {
        this.gRegistro =new GRegistro();
        this.gLogin = gLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Ingresar":
                System.out.println("BTNIngrsar Pressed");
                break;
            case "Registrarse":
                System.out.println("BTNRegistrarse Pressed");
                gRegistro.setVisible(true);
                gLogin.dispose();
                
                break;
        
        
        }
    }
    
}
