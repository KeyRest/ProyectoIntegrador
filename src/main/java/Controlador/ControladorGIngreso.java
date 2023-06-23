
package Controlador;

import Vista.FrameRecetas;
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
    private FrameRecetas frameRecetas;

    public ControladorGIngreso(GLogin gLogin) {
        this.gLogin = gLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ingresar":
                this.frameRecetas = new FrameRecetas();
                this.gLogin.dispose();

                break;
            case "Registrarse":
                System.out.println("BTNRegistrarse Pressed");
                this.gRegistro = new GRegistro();
                this.gLogin.dispose();

                break;

        }
    }

}
