package com.example.Inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Inventario.model.Inventario;
import com.example.Inventario.repository.InventarioRepository;

@Service
public class InventarioService {
   @Autowired
   private InventarioRepository inventarioRepository;

   public Inventario guardarProductos(Inventario inventario) {
        return inventarioRepository.create(inventario);
   }
    public List<Inventario> listarProductos() {
          return inventarioRepository.readAll();
    }
    public Inventario buscarxId(int id) {
        return inventarioRepository.read(id);
    }

    public Inventario buscaxNombre(String nombre) {
        return inventarioRepository.readxNombre(nombre);
    }

    public Inventario modificarProducto(int id, Inventario producto) {
        return inventarioRepository.update(id, producto);
    }

    public String eliminarProducto(int id) {
        return inventarioRepository.delete(id);
    }
}
