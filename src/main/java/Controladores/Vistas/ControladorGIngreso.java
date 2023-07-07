package Controladores.Vistas;

import Controladores.DB.UsersJpaController;
import Entidades.RegistroUsuarios;
import Entidades.User;
import Vista.FRMLogin;
import Vista.FRMMenu;
import Vista.FRMRecetas;
import Vista.FRMRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ControladorGIngreso implements ActionListener {

    private FRMLogin fRMGlogin;
    private FRMRegistro gRegistro;
    private FRMRecetas fRMRecetas;
    private FRMMenu fRMMenu;
    private String user;
    private String password;
    private RegistroUsuarios registroUsuarios;

    public ControladorGIngreso(FRMLogin fRMGlogin) {
        this.fRMGlogin = fRMGlogin;
        this.registroUsuarios = new RegistroUsuarios();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ingresar":

                this.user = this.fRMGlogin.getjTextField_USER().getText();
                this.password = this.fRMGlogin.getjPasswordField().getText();

                if (validarLogin(user)) {
                    this.fRMMenu = new FRMMenu();
                    fRMGlogin.limpiar();
                    this.fRMGlogin.dispose();
                } else {
                    JOptionPane.showMessageDialog(fRMMenu, "Usuario o Contraseña invalida");
                }

                break;
            case "Registrarse":
                System.out.println("BTNRegistrarse Pressed");
                this.gRegistro = new FRMRegistro();
                this.fRMGlogin.dispose();

                break;

        }
    }

    public boolean validarLogin(String user) {
        User u = (User) registroUsuarios.buscarName(user);
        if (u != null) {
            return true;
        }
        return false;
    }

}
