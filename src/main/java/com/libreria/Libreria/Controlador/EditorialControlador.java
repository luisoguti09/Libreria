/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.Libreria.Controlador;

import com.libreria.Libreria.Excepciones.ExcepcionLibreria;
import com.libreria.Libreria.Servicio.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("/Editorial")
public class EditorialControlador {
    @Autowired (required = false)
    private EditorialServicio edit;
    
    @GetMapping("/registrar")
    public String registrarEditorial(){
        
    return "insertarEditorial";
    }
    
    @PostMapping("/registrar")
    public String registrarEditorial(ModelMap modelo, @RequestParam String nombre){
        
        try {
            edit.crearEditorial(nombre);
            return "index"; 
        } catch (ExcepcionLibreria e) {
            modelo.put("error", e.getMessage());
            return "insertarEditorial";
        }
        
    }

    

    
    
    
}
