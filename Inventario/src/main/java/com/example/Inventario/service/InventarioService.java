package com.example.Inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Inventario.model.Inventario;
import com.example.Inventario.repository.InventarioRepository;

@Service
public class InventarioService {
   @Autowired
   private InventarioRepository inventarioRepository;

   public Inventario guardarProductos(Inventario inventario) {
        inventario.setId(0);
        return inventarioRepository.save(inventario);
   }
    public List<Inventario> listarProductos() {
          return inventarioRepository.findAll();
    }
    public Optional<Inventario> buscarxId(int id) {
        return inventarioRepository.findById(id);
    }

    public Inventario modificarProducto(int id, Inventario producto) {
        Optional<Inventario> optionalInventario = inventarioRepository.findById(id);
        if (optionalInventario.isPresent()){
            Inventario inventario = optionalInventario.get();
            inventario.setNombre(producto.getNombre());
            return inventarioRepository.save(inventario);
        }
        return null; 
    }

    public void eliminarProducto(int id) {
        inventarioRepository.deleteById(id);
    }
}
