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
                    this.fRMRecetas.setTxtnombreReceta(this.receta.getNombre());
                    this.fRMRecetas.setTxtdescripcion(this.receta.getDescripcion());
                    this.fRMRecetas.setTxtinstrucciones(this.receta.getInstrucciones());
                    this.fRMRecetas.setTxttiempoCoccion(this.receta.getTiempoCoccion());
                    this.fRMRecetas.setTxttiempoTotal(this.receta.getTiempoTotal());
                    this.fRMRecetas.setTxtId(this.receta.getId());
                } else {
                    FRMRecetas.mensaje("La receta con el ID: " + this.fRMRecetas.getTxtId() + " no se encuentra registrado");
                    this.fRMRecetas.limpiar();
                }
            }
            case "Modificar" -> {
                System.out.println("Modificando");
                if (this.receta != null) {
                    this.receta.setNombre(this.fRMRecetas.getTxtnombreReceta());
                    this.receta.setDescripcion(this.fRMRecetas.getTxtdescripcion());
                    this.receta.setTiempoCoccion(this.fRMRecetas.getTxttiempoCoccion());
                    this.receta.setTiempoTotal(this.fRMRecetas.getTxttiempoTotal());
                    this.receta.setInstrucciones(this.fRMRecetas.getTxtinstrucciones());
                    this.receta.setId(this.fRMRecetas.getTxtId());
                    this.registroRecetas.escribirJSON();
                    this.fRMRecetas.limpiar();
                    this.fRMRecetas.setDatosTabla(this.registroRecetas.getDatosTabla(), Receta.ETIQUETAS_RECETA, "Reporte de Recetas");
                }
            } 
            case "Agregar" -> {
                System.out.println("Agregar");
                if (this.fRMRecetas.getTxtId().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar el ID");
                } else if (this.fRMRecetas.getTxtnombreReceta().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar el Nombre");
                } else if (this.fRMRecetas.getTxtdescripcion().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar la descripción");
                } else if (this.fRMRecetas.getTxttiempoCoccion().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar el tiempo de cocción");
                } else if (this.fRMRecetas.getTxttiempoTotal().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar el tiempo total");
                } else if (this.fRMRecetas.getTxtinstrucciones().equalsIgnoreCase("")) {
                    GRegistro.mensaje("Debe ingresar las instrucciones");
                } else {
                    GRegistro.mensaje(this.registroRecetas.agregar(new Receta()));
                }
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
