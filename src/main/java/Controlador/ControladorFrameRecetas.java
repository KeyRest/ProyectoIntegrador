/*
*Keiron Garro M
*C23212
*UCR
 */

package Controlador;


import Modelo.Receta;
import Modelo.RegistroRecetas;
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
    private Receta receta;
    private RegistroRecetas registroRecetas;
    


    public ControladorFrameRecetas(FRMRecetas fRMRecetas) {
        this.fRMRecetas = fRMRecetas;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
              case "Buscar" -> {
                System.out.println("Buscando");
                this.receta = (Receta) this.registroRecetas.buscar(this.fRMRecetas.getTxtId());
                if (this.receta != null) {
                    this.fRMRecetas.setTxtNombre(this.receta.getNombre());
                    this.fRMRecetas.setTxtDescripcion(this.receta.getDescripcion());
                    this.fRMRecetas.setTxtTiempoCoccion(this.receta.getTiempoCoccion());
                    this.fRMRecetas.setTxtTiempoTotal(this.receta.getTiempoTotal());
                    this.fRMRecetas.setTxtInstrucciones(this.receta.getInstrucciones());
                } else {
                    FRMRecetas.mensaje("La receta con el ID: " + this.fRMRecetas.getTxtId() + " no se encuentra registrado");
                    this.fRMRecetas.limpiar();
                }
            }
            case "Modificar" -> {
                System.out.println("Modificando");
                if (this.receta != null) {
                    this.receta.setNombreReceta(this.frameReceta.getNombre());
                    this.receta.setDescripcion(this.frameReceta.getDescripcion());
                    this.receta.setTiempoCoccion(this.frameReceta.getTiempoCoccion());
                    this.receta.setTiempoTotal(this.frameReceta.getTiempoTotal());
                    this.receta.setInstrucciones(this.frameReceta.getInstrucciones());
                    this.registroRecetas.escribirJSON();
                    this.fRMRecetas.limpiar();
                    this.frameReceta.setDatosTabla(this.registroRecetas.getDatosTabla(), Receta.ETIQUETAS_Receta, "Reporte de Recetas");
                }
            } 
case "Actualizar" -> {
                System.out.println("ACTUALIZAR");
            }

            case "Elminar" -> {
                System.out.println("ELIMINAR");

                FRMRecetas.mensaje(this.registroRecetas.eliminar(this.receta));
                this.fRMRecetas.limpiar();
                this.fRMRecetas.setDatosTabla(this.registroRecetas.getDatosTabla(), Receta.ETIQUETAS_RECETA, "Reporte de Receta");
                break;
            }
            case "Administrar Usuarios" -> {
                System.out.println("AdmiUsuarios");
                fRMRecetas = new FRMRecetas();
                fRMRecetas.dispose();
            }
            case "Regresar" -> {
                System.out.println("pressed Atras");
                fRMMenu = new FRMMenu();
                fRMRecetas.dispose();

            }
            case "Salir" ->
                System.exit(0);
    
    }
    }
}
