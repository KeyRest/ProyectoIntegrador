/*
*Keiron Garro M
*C23212
*UCR
 */

package Controlador;


import Vista.FRMGlogin;
import Vista.FRMMenu;
import Vista.FRMRecetas;
import Vista.FRMUsuario;
import Vista.GRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorFrameRecetas implements ActionListener {

    private FRMRecetas fRMRecetas;
    private ControladorFrameUsuario controladorFrameUsuario;
    private FRMUsuario frameUsuario;
    private FRMGlogin fRMGlogin;
    private GRegistro gRegistro;
    private FRMMenu fRMMenu;


    public ControladorFrameRecetas(FRMRecetas fRMRecetas) {
        this.fRMRecetas = fRMRecetas;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "CREAR" -> System.out.println("CREAR");
            case "CONSULTAR" -> System.out.println("CONSULTAR");
            case "ACTUALIZAR" -> System.out.println("ACTUALIZAR");
            case "ELIMINAR" -> System.out.println("ELIMINAR");
            case "AGREGAR" -> System.out.println("AGREGAR");
            case "VER INGREDIENTES" -> System.out.println("VER INGREDIENTES");
            case "Administrar Usuario"->{
                System.out.println("AdmiUsuario");
                frameUsuario = new FRMUsuario();
                fRMRecetas.dispose();
            }
            case "Regresar" ->  {
                System.out.println("pressed Atras");
                fRMMenu = new FRMMenu();
                fRMRecetas.dispose();

            }              
            case "Salir" -> System.exit(0);
            
        }
    }

}
