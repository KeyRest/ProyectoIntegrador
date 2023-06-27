/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.FRMGlogin;
import Vista.GRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class ControladorGRegistro implements ActionListener {

    private FRMGlogin fRMGlogin;
    private GRegistro gRegistro;

    public ControladorGRegistro(GRegistro gRegistro) {
        this.gRegistro = gRegistro;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Atras":
                System.out.println("pressed Atras");
                fRMGlogin = new FRMGlogin();
                gRegistro.dispose();
                fRMGlogin.setVisible(true);
                break;
            case "Registrarse":
                System.out.println("pressed Registrarse(SE REVISA DISPONIBILIDAD DE USUARIO Y SE ASIGNA ID)");

                break;

        }
    }

}
