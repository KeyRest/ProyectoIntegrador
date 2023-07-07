/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.Vistas;

import Entidades.RegistroUsuarios;
import Entidades.Users;
import Vista.FRMLogin;
import Vista.FRMMenu;
import Vista.FRMRecetas;
import Vista.FRMUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

/**
 *
 * @author eddyi
 */
public class ControladorFrameUsuario implements ActionListener, MouseListener {

    private FRMUsuario frameUsuario;
    private FRMRecetas fRMRecetas;
    private FRMLogin fRMGlogin;
    private Users usuario;
    private RegistroUsuarios registroUsuarios;
    private FRMMenu fRMMenu;

    public ControladorFrameUsuario(FRMUsuario frameUsuario) {
        this.frameUsuario = frameUsuario;
        this.registroUsuarios = new RegistroUsuarios();
        this.frameUsuario.setDatosTabla(this.registroUsuarios.getDatosTabla(), Users.ETIQUETAS_USUARIO, "Reporte de Usuarios");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Buscar" -> {
                System.out.println("Buscando");
                this.usuario = (Users) this.registroUsuarios.buscar(this.frameUsuario.getTxtId());
                if (this.usuario != null) {
                    this.frameUsuario.setTxtNombre(this.usuario.getName());
                    this.frameUsuario.setTxtApellido(this.usuario.getLastName());
                    this.frameUsuario.setTxtPais(this.usuario.getCountry());
                    this.frameUsuario.setTxtCorreo(this.usuario.getEmail());
                    this.frameUsuario.setTxtContraseña(this.usuario.getPassword());
                } else {
                    FRMUsuario.mensaje("El usuario con el ID: " + this.frameUsuario.getTxtId() + " no se encuntra registrado");
                    this.frameUsuario.limpiar();
                }
            }
            case "Modificar" -> {
                System.out.println("Modificando");
                if (this.usuario != null) {
                    this.usuario.setName(this.frameUsuario.getTxtNombre());
                    this.usuario.setLastName(this.frameUsuario.getTxtApellido());
                    this.usuario.setCountry(this.frameUsuario.getTxtPais());
                    this.usuario.setEmail(this.frameUsuario.getTxtCorreo());
                    this.usuario.setPassword(this.frameUsuario.getTxtContraseña());
                    this.registroUsuarios.escribirJSON();
                    this.frameUsuario.limpiar();
                    this.frameUsuario.setDatosTabla(this.registroUsuarios.getDatosTabla(), Users.ETIQUETAS_USUARIO, "Reporte de Usuarios");
                }
            }
            case "Agregar" ->
                System.out.println("Actualizando");
            case "Eliminar" -> {
                System.out.println("Eliminando");
                FRMUsuario.mensaje(this.registroUsuarios.eliminar(this.usuario));
                this.frameUsuario.limpiar();
                this.frameUsuario.setDatosTabla(this.registroUsuarios.getDatosTabla(), Users.ETIQUETAS_USUARIO, "Reporte de Usuarios");
            }
            case "Administrar Recetas" -> {
                System.out.println("AdmiRecetas");
                fRMRecetas = new FRMRecetas();
                frameUsuario.dispose();
            }
            case "Regresar" -> {
                System.out.println("pressed Regresar");
                fRMMenu = new FRMMenu();
                frameUsuario.dispose();
            }
            case "Salir" ->
                System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 &&  frameUsuario.tblReporte.getSelectedRow() != -1) {
            // Cargar los datos en los campos de texto correspondientes
            System.out.println("Controladores.Vistas.ControladorFrameUsuario.mouseClicked()");
            String[] vFila = this.frameUsuario.getFilaTabla();
            this.frameUsuario.setTxtNombre(vFila[0]);
            this.frameUsuario.setTxtApellido(vFila[1]);
            this.frameUsuario.setTxtPais(vFila[2]);
            this.frameUsuario.setTxtCorreo(vFila[3]);

        }

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
