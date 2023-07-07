/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.Vistas;

import Entidades.RegistroUsuarios;
import Entidades.User;
import Vista.FRMLogin;
import Vista.FRMRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class ControladorGRegistro implements ActionListener {

    private FRMLogin fRMGlogin;
    private FRMRegistro gRegistro;
    private User usuario;
    private RegistroUsuarios registroUsuarios;

    public ControladorGRegistro(FRMRegistro gRegistro) {
        this.gRegistro = gRegistro;
        this.registroUsuarios = new RegistroUsuarios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Atras":
                System.out.println("pressed Atras");
                fRMGlogin = new FRMLogin();
                gRegistro.dispose();
                fRMGlogin.setVisible(true);
                break;
            case "Registrarse":
                if (this.gRegistro.getJText_Name().equalsIgnoreCase("")) {
                    FRMRegistro.mensaje("Debe ingresar el Nombre");
                } else if (this.gRegistro.getJText_LastName().equalsIgnoreCase("")) {
                    FRMRegistro.mensaje("Debe ingresar el Apellido");
                } else if (this.gRegistro.getJText_Country().equalsIgnoreCase("")) {
                    FRMRegistro.mensaje("Debe ingresar el Pais");
                } else if (this.gRegistro.getJText_Email().equalsIgnoreCase("")) {
                    FRMRegistro.mensaje("Debe ingresar el Correo");
                } else if (this.gRegistro.getJText_Password().equalsIgnoreCase("")) {
                    FRMRegistro.mensaje("Debe ingresar la Contrase\u00f1a");
                } else {
                    FRMRegistro.mensaje(this.registroUsuarios.agregar(new User(0, this.gRegistro.getJText_Name(), this.gRegistro.getJText_LastName(), this.gRegistro.getJText_Country(), this.gRegistro.getJText_Email(), this.gRegistro.getJText_Password())));
                    this.gRegistro.limpiar();
                }

                break;

        }
    }

}
