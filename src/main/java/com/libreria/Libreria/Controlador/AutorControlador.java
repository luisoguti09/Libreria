
package com.libreria.Libreria.Controlador;

import com.libreria.Libreria.Excepciones.ExcepcionLibreria;
import com.libreria.Libreria.Servicio.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorServicio servA;

    @GetMapping("/registro")
    public String registroLibro() {
        return "InsertarAutor.html";
    }

    @PostMapping("/registro")
    public String registroAutor(ModelMap modelo, @RequestParam String nombre) {

        try {
            servA.crearAutor(nombre);
        } catch (ExcepcionLibreria ex) {
            modelo.put("error",ex.getMessage());
           
            return "InsertarAutor.html";
        }

        return "index.html";
    }
}
