package com.example.Inventario.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Inventario.model.Inventario;

@Repository
public class InventarioRepository {
   private List<Inventario> inventario = new ArrayList<>();

    public Inventario create(Inventario newInventario) {
        inventario.add(newInventario);
        System.out.println(newInventario.toString());
        return newInventario;
    }
    public InventarioRepository() {
        // Inicializar algunos productos de ejemplo
        inventario.add(new Inventario(1, "Producto 1", "Categoria 1", 10, 10));
        inventario.add(new Inventario(2, "Producto 2", "Categoria 2", 20, 7));
        inventario.add(new Inventario(3, "Producto 3", "Categoria 3", 30, 12));
    }

    public List<Inventario> readAll(){
        return inventario;
    }

    public Inventario read(int id){
        for (Inventario inventario : inventario) {
            if(inventario.getId()==id){
                return inventario;
            }
        }
        return null;

    }
    public Inventario readxNombre(String nombre){
        for (Inventario inventario : inventario) {
            if(inventario.getNombre().equalsIgnoreCase(nombre)){
                return inventario;
            }
        }
        return null;

    }

    public Inventario update(int id, Inventario inventario){
        Inventario modi=this.read(id);
        if (modi!=null) {
            //no va el rut ni id para evitar errores de inconsistencia de datos
            modi.setNombre(inventario.getNombre());
            modi.setCategoria(inventario.getCategoria());
            modi.setPrecio(inventario.getPrecio());
            modi.setStock(inventario.getStock());
            return inventario;
        }

        return null;

    }

    public String delete(int id) {
        if(inventario.removeIf(kill->kill.getId()==id))
        {
            return "Producto eliminado";
        }
        
        return null;
    } 
}
