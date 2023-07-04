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
 * @author Admin
 */
public class RegistroRecetas {

    private ArrayList<Receta> listaReceta;
    private JSONObject baseJSONReceta;
    private File archivo;

    public RegistroRecetas() {
        this.listaReceta = new ArrayList();
        this.archivo = new File("Usuarios.json");
        this.leerJSON();
    }

    public void escribirJSON() {
        JSONArray arregloRecetas = new JSONArray();
        this.baseJSONReceta = new JSONObject();
        for (Receta receta : listaReceta) {
            JSONObject objJSONReceta = new JSONObject();
            objJSONReceta.put("id", receta.getId());
            objJSONReceta.put("nombre", receta.getNombre());
            objJSONReceta.put("descripcion", receta.getDescripcion());
            objJSONReceta.put("tiempoCoccion", receta.getTiempoCoccion());
            objJSONReceta.put("tiempoPreparacion", receta.getTiempoPreparacion());
            objJSONReceta.put("tiempoTotal", receta.getTiempoTotal());
            objJSONReceta.put("instrucciones", receta.getInstrucciones());
            objJSONReceta.put("porciones", receta.getPorciones());
            arregloRecetas.add(objJSONReceta);

        }
        this.baseJSONReceta.put("listaRecetas", arregloRecetas);
        try {
            FileWriter escribir = new FileWriter(this.archivo);
            escribir.write(this.baseJSONReceta.toJSONString());
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
            this.baseJSONReceta = (JSONObject) obj;
            JSONArray arregloJSON = (JSONArray) this.baseJSONReceta.get("listaRecetas");
            for (Object object : arregloJSON) {
                JSONObject objUsuario = (JSONObject) object;
                Receta receta = new Receta();
                receta.setId(objUsuario.get("id").toString());
                receta.setNombre(objUsuario.get("nombre").toString());
                receta.setDescripcion(objUsuario.get("descripcion").toString());

                receta.setTiempoCoccion(objUsuario.get("tiempoCoccion").toString());
                receta.setTiempoPreparacion(objUsuario.get("tiempoPreparacion").toString());
                receta.setTiempoTotal(objUsuario.get("tiempoTotal").toString());
                receta.setInstrucciones(objUsuario.get("instrucciones").toString());
                receta.setPorciones(objUsuario.get("porciones").toString());

                this.listaReceta.add(receta);

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
        Receta receta = (Receta) obj;
        if (this.buscar(receta.getId()) == null) {
            if (this.listaReceta.add(receta)) {
                this.escribirJSON();
                return "El usuario se ha agregado con exito";
            } else {
                return "Error al registrar el usuario";
            }

        }
        return "El usuario ya se encuentra registrado";
    }

    public String eliminar(Object receta) {
        if (this.listaReceta.remove((Receta) receta)) {
            this.escribirJSON();
            return "El usuario ha sido eliminada";
        } else {
            return "Error al eliminar el usuario";
        }
    }

    public Object buscar(String id) {
        for (int indice = 0; indice < this.listaReceta.size(); indice++) {
            if (this.listaReceta.get(indice).getId().equalsIgnoreCase(id)) {
                return (Object) this.listaReceta.get(indice);
            }

        }
        return null;
    }

    public String toString() {
        String salida = "Lista de Usuarios: \n";
        Iterator it = this.listaReceta.iterator();
        while (it.hasNext()) {
            salida += it.next() + "\n";
        }
        return salida;
    }

    public String[][] getDatosTabla() {
        String[][] matrizTabla = new String[this.listaReceta.size()][Usuario.ETIQUETAS_USUARIO.length];
        for (int f = 0; f < this.listaReceta.size(); f++) {
            for (int c = 0; c < matrizTabla[0].length; c++) {
                matrizTabla[f][c] = this.listaReceta.get(f).setDatosReceta(c);
            }
        }
        return matrizTabla;
    }

}
