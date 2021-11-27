/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.Libreria;

import com.libreria.Libreria.Servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author Luis
 */
public class ServletInitializer extends SpringBootServletInitializer {
     @Autowired
    private ClienteServicio cServ;
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibreriaApplication.class);
	}

    
}
