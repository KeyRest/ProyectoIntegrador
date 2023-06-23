/*
*Keiron Garro M
*C23212
*UCR
 */

package Controlador;

import Vista.FrameRecetas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorFrameRecetas implements ActionListener {

    private FrameRecetas frameRecetas;

    public ControladorFrameRecetas(FrameRecetas frameRecetas) {
        this.frameRecetas = frameRecetas;
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
        }
    }

}
