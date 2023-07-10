/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Controladores.DB.UsersJpaController;
import Controladores.DB.exceptions.IllegalOrphanException;
import Controladores.DB.exceptions.NonexistentEntityException;
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

    private ArrayList<Users> listaUsuario;
    private JSONObject baseJSONUsuario;
    private File archivo;
    private UsersJpaController usersJpaController;
    private int lastId;
    private final String path = "src/main/resources/usuarios.json";

    public RegistroUsuarios() {
        this.listaUsuario = new ArrayList();
        this.archivo = new File(path);
        this.leerJSON();
        toString();
        crearJSON();
    }

    
    public void crearJSON(){
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }    
    }
    
    
    public void escribirJSON() {
        JSONArray arregloUsuarios = new JSONArray();
        this.baseJSONUsuario = new JSONObject();
        for (Users usuario : listaUsuario) {
            JSONObject objJSONUsuario = new JSONObject();
            objJSONUsuario.put("id", usuario.getId());
            objJSONUsuario.put("nombre", usuario.getName());
            objJSONUsuario.put("apellido", usuario.getLastName());
            objJSONUsuario.put("pais", usuario.getCountry());
            objJSONUsuario.put("correo", usuario.getEmail());
            objJSONUsuario.put("contrase\u00f1a", usuario.getPassword());
            arregloUsuarios.add(objJSONUsuario);
            this.lastId = usuario.getId();

        }
        this.baseJSONUsuario.put("listaUsuarios", arregloUsuarios);
        this.baseJSONUsuario.put("ultimoId", lastId);
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
        this.listaUsuario = new ArrayList<>();
        try {
            FileReader leer = new FileReader(this.archivo);
            Object obj = parser.parse(leer);
            this.baseJSONUsuario = (JSONObject) obj;
            JSONArray arregloJSON = (JSONArray) this.baseJSONUsuario.get("listaUsuarios");
            for (Object object : arregloJSON) {
                JSONObject objUsuario = (JSONObject) object;
                Users usuario = new Users();
                usuario.setId(Integer.parseInt(objUsuario.get("id").toString()));
                usuario.setName(objUsuario.get("nombre").toString());
                usuario.setLastName(objUsuario.get("apellido").toString());
                usuario.setCountry(objUsuario.get("pais").toString());
                usuario.setEmail(objUsuario.get("correo").toString());
                usuario.setPassword(objUsuario.get("contrase\u00f1a").toString());
                this.listaUsuario.add(usuario);
            }
            this.lastId = Integer.parseInt(this.baseJSONUsuario.get("ultimoId").toString()) + 1;

        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer");
        } catch (IOException ex) {
            System.err.println("Error al leer");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public String agregar(Object obj) {
        Users usuario = (Users) obj;
        if (this.buscar(usuario.getId().toString()) == null) {
            if (this.listaUsuario.add(usuario)) {
                this.escribirJSON();
               // this.usersJpaController = new UsersJpaController();
               // this.usersJpaController.create(usuario);
                this.leerJSON();
                return "El usuario se ha agregado con exito";

            } else {
                return "Error al registrar el usuario";
            }

        }
        return "El usuario ya se encuentra registrado";
    }

    public String eliminar(Object usuario) throws IllegalOrphanException, NonexistentEntityException {
        if (this.listaUsuario.remove((Users) usuario)) {
            this.usersJpaController = new UsersJpaController();
            this.usersJpaController.destroy(((Users) usuario).getId());
            this.escribirJSON();
            this.leerJSON();
            return "El usuario ha sido eliminada";
        } else {
            return "Error al eliminar el usuario";
        }
    }

    public void modificar(Object user) throws NonexistentEntityException, Exception {
        this.usersJpaController = new UsersJpaController();
        this.usersJpaController.edit((Users) user);
    }

    public int size() {
        this.usersJpaController = new UsersJpaController();
        return this.usersJpaController.getUsersCount();
    }

    public Object buscar(String id) {
        for (int indice = 0; indice < this.listaUsuario.size(); indice++) {
            if (this.listaUsuario.get(indice).getId().toString().equalsIgnoreCase(id)) {
                return (Object) this.listaUsuario.get(indice);
            }

        }
        return null;
    }

    public Object buscarName(String name) {
        for (int indice = 0; indice < this.listaUsuario.size(); indice++) {
            if (this.listaUsuario.get(indice).getName().equalsIgnoreCase(name)) {
                return (Object) this.listaUsuario.get(indice);
            }

        }
        return null;
    }

    public String toString() {
        String salida = "Lista de Usuarios: \n";
        Iterator it = this.listaUsuario.iterator();
        while (it.hasNext()) {
            salida += it.next() + "\n";
        }
        return salida;
    }

    public String[][] getDatosTabla() {
        String[][] matrizTabla = new String[this.listaUsuario.size()][Users.ETIQUETAS_USUARIO.length];
        for (int f = 0; f < this.listaUsuario.size(); f++) {
            for (int c = 0; c < matrizTabla[0].length; c++) {
                matrizTabla[f][c] = this.listaUsuario.get(f).setDatosUsuario(c);
            }
        }
        return matrizTabla;
    }

    public int getLastId() {
        return lastId;
    }

}
