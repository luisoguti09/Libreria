/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.Libreria.Controlador;

import com.libreria.Libreria.Excepciones.ExcepcionLibreria;
import com.libreria.Libreria.Repository.ClienteRepo;
import com.libreria.Libreria.Servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
    
    @Autowired
    private ClienteRepo clinR;
    @Autowired
    private ClienteServicio servC;

    
@GetMapping("/registro")
    public String registroCliente(ModelMap modelo) {  
        return "registroCliente.html";
    }

    @PostMapping("/registro")
    public String registroLibro(ModelMap m, @RequestParam Long dni,
            @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String telefono,
            @RequestParam String pass1,
            @RequestParam String pass2,
            @RequestParam String mail) {

        try {
            servC.regristrarCliente(dni, nombre, apellido, telefono, pass1, pass2,mail);

        } catch (ExcepcionLibreria ex) {
            m.put("error", ex.getMessage());
            m.put("dni", dni);
            m.put("nombre", nombre);
            m.put("apellido", apellido);
            m.put("telefono", telefono);
            m.put("pass1", pass1);
            m.put("pass2", pass2);
            m.put("mail", mail);
            return "registroCliente.html";
        }
        m.put("mensaje", "Te registraste como Cliente satisfactoriamente.");
        return "exito.html";
    }    
    
    
}
