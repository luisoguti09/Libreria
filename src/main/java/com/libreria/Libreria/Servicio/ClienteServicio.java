/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.Libreria.Servicio;

import com.libreria.Libreria.Entidades.Cliente;
import com.libreria.Libreria.Enum.RolesEnum;
import com.libreria.Libreria.Excepciones.ExcepcionLibreria;
import com.libreria.Libreria.Repository.ClienteRepo;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ClienteServicio implements UserDetailsService{
    
    @Autowired 
    private ClienteRepo clinR;
    
    

    
    public void validacionesUser(Long dni, String nombre, String apellido, String telefono,  String pass1, String pass2, String mail) throws ExcepcionLibreria{
        
      if(dni.toString().length()<7 || dni.toString().length()>8 || dni.toString().isEmpty() || dni == null){
          throw new ExcepcionLibreria("Algo malio sal... ingresa bien tu DNI");
      }
      if(nombre.isEmpty() || nombre == null){
          throw new ExcepcionLibreria("Algo malio sal... ingresa bien tu Nombre");
      }
      if(apellido.isEmpty() || apellido == null){
          throw new ExcepcionLibreria("Algo malio sal... ingresa bien tu Apellido");
      }
      if(telefono.toString().length()<10 || telefono.toString().length()>10 ||telefono.isEmpty() || telefono == null){
          throw new ExcepcionLibreria("Algo malio sal... ingresa bien tu Celu");
      }
      if(pass1.toString().length()<6 || pass1.toString().length()>8 || pass1.toString().isEmpty() || pass1 == null){
          throw new ExcepcionLibreria("Algo malio sal... la Contraseña tiene que contener entre 6 y 8 caractreres y fecha de fabricacion de la primera coca que tomaste");
      }
      if(pass2.toString().length()<6 || pass2.toString().length()>8 || pass2.toString().isEmpty() || pass2 == null){
          throw new ExcepcionLibreria("Algo malio sal... la Contraseña tiene que contener entre 6 y 8 caractreres y fecha de fabricacion de la primera coca que tomaste");
      }
      if (!pass1.equals(pass2)) {
            throw new ExcepcionLibreria("Las claves deben ser iguales");
        }
      if(mail.isEmpty() || mail==null){
          throw new ExcepcionLibreria("Algo malio sal... ingresa bien tu direccion de correo electronico");
      }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void regristrarCliente(Long dni, String nombre, String apellido, String telefono,  String pass1, String pass2, String mail) throws ExcepcionLibreria{
        validacionesUser(dni, nombre, apellido, telefono, pass1, pass2,mail);
        Cliente cliente = new Cliente();
        cliente.setDocumento(dni);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setMail(mail);
        String encriptada = new BCryptPasswordEncoder().encode(pass1);
        cliente.setPass1(encriptada);
        cliente.setRol(RolesEnum.USER);
        cliente.setAlta(true);
        
        clinR.save(cliente);  
    } 
    
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Cliente clin = clinR.buscarPorMail(mail);
        if (clin != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
                        
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" +clin.getRol());
            permisos.add(p1);
         
            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", clin);

            User user = new User(clin.getMail(), clin.getPass1(), permisos);
            return user;

        } else {
            return null;
        }

    }
    
    
}
