/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.Vistas;

import Vista.FRMLogin;
import Vista.FRMMenu;
import Vista.FRMRecetas;
import Vista.FRMUsuario;
import Vista.FRMRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class ControladorMenu implements ActionListener{
    
         private FRMMenu fRMMenu;
         private FRMLogin fRMGlogin;
         private FRMRegistro gRegistro;
         private FRMRecetas fRMRecetas;
         private FRMUsuario fRMUsuario;
    
    public ControladorMenu(FRMMenu fRMMenu) {
        this.fRMMenu = fRMMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "RECETAS":
                
                this.fRMRecetas = new FRMRecetas();
                this.fRMMenu.dispose();

                break;
            case "USUARIOS":
                
                this.fRMUsuario = new FRMUsuario();
                this.fRMMenu.dispose();

                break;

        }
    }
    
}
