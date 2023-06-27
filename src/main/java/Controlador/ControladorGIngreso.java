
package Controlador;

import Vista.FRMGlogin;
import Vista.FrameRecetas;
import Vista.GRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class ControladorGIngreso implements ActionListener {

    private FRMGlogin fRMGlogin;
    private GRegistro gRegistro;
    private FrameRecetas frameRecetas;

    public ControladorGIngreso(FRMGlogin fRMGlogin) {
        this.fRMGlogin = fRMGlogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ingresar":
                this.frameRecetas = new FrameRecetas();
                this.fRMGlogin.dispose();

                break;
            case "Registrarse":
                System.out.println("BTNRegistrarse Pressed");
                this.gRegistro = new GRegistro();
                this.fRMGlogin.dispose();

                break;

        }
    }

}
