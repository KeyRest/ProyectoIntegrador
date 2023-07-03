/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Legion
 */
public class RegistroUsuarios {

    private ArrayList<Usuario> listaUsuario;
    private JSONObject baseJSONUsuario;
    private File archivo;

    public RegistroUsuarios() {
        this.listaUsuario = new ArrayList();
        this.archivo = new File("Usuarios.json");
        this.leerJSON();
    }

    public void escribirJSON() {
        JSONArray arregloUsuarios = new JSONArray();
        this.baseJSONUsuario = new JSONObject();
        for (Usuario usuario : listaUsuario) {
            JSONObject objJSONUsuario = new JSONObject();
            objJSONUsuario.put("id", usuario.getId());
            objJSONUsuario.put("nombre", usuario.getNombre());
            objJSONUsuario.put("nombreUsuario", usuario.getNombreUsuario());
            objJSONUsuario.put("pais", usuario.getPais());
            objJSONUsuario.put("correo", usuario.getCorreo());
            objJSONUsuario.put("contrase\u00f1a", usuario.getContraseña());
            arregloUsuarios.add(objJSONUsuario);
        }
        this.baseJSONUsuario.put("listaUsuarios", arregloUsuarios);
        try {
            FileWriter escribir = new FileWriter(this.archivo);
            escribir.write(this.baseJSONUsuario.toJSONString());
            escribir.flush();
            escribir.close();
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo");
        }
    }

    public void leerJSON() {
        JSONParser parser = new JSONParser();
        try {
            FileReader leer = new FileReader(this.archivo);
            Object obj = parser.parse(leer);
            this.baseJSONUsuario = (JSONObject) obj;
            JSONArray arregloJSON = (JSONArray) this.baseJSONUsuario.get("listaUsuarios");
            for (Object object : arregloJSON) {
                JSONObject objUsuario = (JSONObject) object;
                Usuario usuario = new Usuario();
                usuario.setId(objUsuario.get("id").toString());
                usuario.setNombre(objUsuario.get("nombre").toString());
                usuario.setNombreUsuario(objUsuario.get("nombreUsuario").toString());
                usuario.setPais(objUsuario.get("pais").toString());
                usuario.setCorreo(objUsuario.get("correo").toString());
                usuario.setContraseña(objUsuario.get("contrase\u00f1a").toString());
                this.listaUsuario.add(usuario);

            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer");
        } catch (IOException ex) {
            System.err.println("Error al leer");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public String agregar(Object obj) {
        Usuario usuario = (Usuario) obj;
        if (this.buscar(usuario.getId()) == null) {
            if (this.listaUsuario.add(usuario)) {
                this.escribirJSON();
                return "El usuario se ha agregado con exito";
            } else {
                return "Error al registrar el usuario";
            }

        }
        return "El usuario ya se encuentra registrado";
    }

    public String eliminar(Object usuario) {
        if (this.listaUsuario.remove((Usuario) usuario)) {
            this.escribirJSON();
            return "El usuario ha sido eliminada";
        } else {
            return "Error al eliminar el usuario";
        }
    }

    public Object buscar(String id) {
        for (int indice = 0; indice < this.listaUsuario.size(); indice++) {
            if (this.listaUsuario.get(indice).getId().equalsIgnoreCase(id)) {
                return (Object) this.listaUsuario.get(indice);
            }

        }
        return null;
    }
    public String toString()
    {
        String salida="Lista de Usuarios: \n";
        Iterator it=this.listaUsuario.iterator();
        while(it.hasNext())
        {
            salida+=it.next()+"\n";
        }
        return salida;
    }
    public String[][] getDatosTabla()
    {
        String[][] matrizTabla=new String[this.listaUsuario.size()][Usuario.ETIQUETAS_USUARIO.length];
        for(int f = 0; f <this.listaUsuario.size(); f++)
        {
            for(int c = 0; c <matrizTabla[0].length; c++)
            {
                matrizTabla[f][c]=this.listaUsuario.get(f).setDatosUsuario(c);
            }
        }
        return matrizTabla;
    }


}
