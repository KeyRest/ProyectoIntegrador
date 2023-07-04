/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.RegistroUsuarios;
import Modelo.Usuario;
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
    private Usuario usuario;
    private RegistroUsuarios registroUsuarios;

    public ControladorGRegistro(GRegistro gRegistro) {
        this.gRegistro = gRegistro;
        this.registroUsuarios = new RegistroUsuarios();
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
                if (this.gRegistro.getJText_Id().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar el ID");
                } else if (this.gRegistro.getJText_Name().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar el Nombre");
                } else if (this.gRegistro.getJText_Username().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar el Usuario");
                } else if (this.gRegistro.getJText_Country().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar el Pais");
                } else if (this.gRegistro.getJText_Email().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar el Correo");
                } else if (this.gRegistro.getJText_Password().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar la Contrase\u00f1a");
                } else {
                    GRegistro.mensaje(this.registroUsuarios.agregar(new Usuario(this.gRegistro.getJText_Id(), this.gRegistro.getJText_Name(), this.gRegistro.getJText_Username(), this.gRegistro.getJText_Country(), this.gRegistro.getJText_Email(), this.gRegistro.getJText_Password())));

                    this.gRegistro.limpiar();
                }
                System.out.println("pressed Registrarse(SE REVISA DISPONIBILIDAD DE USUARIO Y SE ASIGNA ID)");

                break;

        }
    }

}
