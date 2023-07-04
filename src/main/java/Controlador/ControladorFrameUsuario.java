/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.RegistroUsuarios;
import Modelo.Usuario;
import Vista.FRMGlogin;
import Vista.FRMRecetas;
import Vista.FRMUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author eddyi
 */
public class ControladorFrameUsuario implements ActionListener, MouseListener {

    private FRMUsuario frameUsuario;
    private FRMRecetas fRMRecetas;
    private FRMGlogin fRMGlogin;
    private Usuario usuario;
    private RegistroUsuarios registroUsuarios;

    public ControladorFrameUsuario(FRMUsuario frameUsuario) {
        this.frameUsuario = frameUsuario;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "CREAR" ->
                System.out.println("CREAR");
            case "CONSULTAR" ->
                System.out.println("CONSULTAR");
            case "ACTUALIZAR" ->
                System.out.println("ACTUALIZAR");
            case "ELIMINAR" ->
                System.out.println("ELIMINAR");
            case "AGREGAR" ->
                System.out.println("AGREGAR");
            case "VER INGREDIENTES" ->
                System.out.println("LISTA USUARIOS");
                case "Administrar Recetas"->{
                System.out.println("AdmiRecetas");
                fRMRecetas = new FRMRecetas();
                frameUsuario.dispose();
            }
            case "Regresar" ->  {
                System.out.println("pressed Atras");
                fRMGlogin = new FRMGlogin();
                frameUsuario.dispose();
                fRMGlogin.setVisible(true);
            }              
            case "Salir" -> System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    
}
