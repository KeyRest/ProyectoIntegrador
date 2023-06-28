/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.FRMUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author eddyi
 */
public class ControladorFrameUsuario implements ActionListener {

    private FRMUsuario frameUsuario;

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
        }
    }

    
}
