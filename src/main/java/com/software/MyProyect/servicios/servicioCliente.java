package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Clientes;
import com.software.MyProyect.repositorios.repositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class servicioCliente {
    private final repositorioCliente clientesRepository;

    @Autowired
    public servicioCliente(repositorioCliente clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public Clientes saveCliente(Clientes cliente) {
        return clientesRepository.save(cliente);
    }

    public List<Clientes> getAllClientes() {
        return clientesRepository.findAll();
    }
    
    public Clientes getClienteById(String id) {
        Optional<Clientes> cliente = clientesRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new IllegalArgumentException("Cliente con ID " + id + " no encontrado.");
        }
    }
  
    public Clientes updateCliente(String id, Clientes clienteActualizado) {
        Clientes clienteExistente = clientesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente con ID " + id + " no encontrado."));
        
        // Actualizar los campos del cliente
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setNumeroDocumento(clienteActualizado.getNumeroDocumento());
        clienteExistente.setDireccion(clienteActualizado.getDireccion());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());
        clienteExistente.setEmail(clienteActualizado.getEmail());
        clienteExistente.setCiudad(clienteActualizado.getCiudad());
        clienteExistente.setDepartamento(clienteActualizado.getDepartamento());

        return clientesRepository.save(clienteExistente);
    }

    public void deleteCliente(String id) {
        if (!clientesRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente con ID " + id + " no encontrado.");
        }
        
        clientesRepository.deleteById(id);
    }
}
