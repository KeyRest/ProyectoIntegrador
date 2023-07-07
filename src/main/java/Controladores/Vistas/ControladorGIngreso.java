
package Controladores.Vistas;

import Vista.FRMLogin;
import Vista.FRMMenu;
import Vista.FRMRecetas;
import Vista.FRMRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class ControladorGIngreso implements ActionListener {

    private FRMLogin fRMGlogin;
    private FRMRegistro gRegistro;
    private FRMRecetas fRMRecetas;
      private FRMMenu fRMMenu;
    public ControladorGIngreso(FRMLogin fRMGlogin) {
        this.fRMGlogin = fRMGlogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ingresar":
                
                
                this.fRMMenu = new FRMMenu();
                fRMGlogin.limpiar();
                this.fRMGlogin.dispose();
                
                break;
            case "Registrarse":
                System.out.println("BTNRegistrarse Pressed");
                this.gRegistro = new FRMRegistro();
                this.fRMGlogin.dispose();

                break;

        }
    }

}
