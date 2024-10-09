package com.cmolina.encuestaSatis.controller;

import com.cmolina.encuestaSatis.entity.Encuesta;
import com.cmolina.encuestaSatis.repository.EncuestaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller //Anotación que le indica a Spring que esta clase es un controlador
public class EncuestaController {

    //Para acceder al repositorio creamos una propiedad y la asignamos en el constructor
    private EncuestaRepository EncuestaRepository;

    public EncuestaController(EncuestaRepository repository){
        this.EncuestaRepository = repository;
    }

    @GetMapping("/encuesta")
    public String index(Model model){
        model.addAttribute("encuesta", new Encuesta());
         return "encuesta";
     }

     @PostMapping("/encuesta/success")
     public String newEncuesta(Model model,@Valid Encuesta encuesta, BindingResult result){
         if (result.hasErrors()) {
             // Si hay errores de validación, vuelve a mostrar el formulario con los mensajes de error
             return "encuesta"; // Asegúrate de que esta sea la vista del formulario
         }

        model.addAttribute("encuesta", new Encuesta());
        EncuestaRepository.save(encuesta);
        return "success";
     }

    @GetMapping("/encuesta/list")
    public String listEncuesta(Model model){
        List<Encuesta> encuestas = this.EncuestaRepository.findAll();
        model.addAttribute("encuestas",encuestas);


        int totalEncuestas = encuestas.size();
        model.addAttribute("totalEncuestas", totalEncuestas);


        double mediaEdad = encuestas.stream().mapToInt(Encuesta::getEdad).average().orElse(0.0);
        model.addAttribute("mediaEdad", mediaEdad);

        Map<String, Long> totalPorValoracion = encuestas.stream().collect(Collectors.groupingBy(Encuesta::getSatisfaccion, Collectors.counting()));


        totalPorValoracion.putIfAbsent("Muy Satisfecho", 0L);
        totalPorValoracion.putIfAbsent("Satisfecho", 0L);
        totalPorValoracion.putIfAbsent("Neutral", 0L);
        totalPorValoracion.putIfAbsent("Insatisfecho", 0L);

        model.addAttribute("totalPorValoracion", totalPorValoracion);


        return "list-encuestas";
    }

    @GetMapping("/encuesta/view/{id}")
    public String viewEncuesta(@PathVariable long id, Model model, HttpServletRequest request){
        Optional encuesta = EncuestaRepository.findById(id);

        if(encuesta.isPresent()) {
            model.addAttribute("encuesta", encuesta.get());
            model.addAttribute("currentUri", request.getRequestURI());
            //Redirigir al listado de productos
            return "encuesta.html";
        }else{
            return "redirect:/encuesta";
        }
    }

    @GetMapping("/encuesta/edit/{id}")
    public String editEncuestaView(@PathVariable long id, Model model){
        Encuesta encuesta = EncuestaRepository.findById(id).orElse(new Encuesta());
        model.addAttribute("encuesta", encuesta);
        return "encuesta";
    }

    @PostMapping("/encuesta/edit/")
    public String editEncuestaSave(Encuesta encuesta){
        EncuestaRepository.save(encuesta);

        return "success";
    }

    @GetMapping("/encuesta/del/{id}")
    public String deleteEncuesta(@PathVariable long id, Model model){

        EncuestaRepository.deleteById(id);

        return "redirect:/encuesta/list";
    }

    @PostMapping("/encuesta/list")
    public String filtro(Model model,String motivo){
        List<Encuesta> encuestas = this.EncuestaRepository.findByMotivo(motivo);
        model.addAttribute("encuestas",encuestas);


        int totalEncuestas = encuestas.size();
        model.addAttribute("totalEncuestas", totalEncuestas);


        double mediaEdad = encuestas.stream().mapToInt(Encuesta::getEdad).average().orElse(0.0);
        model.addAttribute("mediaEdad", mediaEdad);


        Map<String, Long> totalPorValoracion = encuestas.stream().collect(Collectors.groupingBy(Encuesta::getSatisfaccion, Collectors.counting()));


        totalPorValoracion.putIfAbsent("Muy Satisfecho", 0L);
        totalPorValoracion.putIfAbsent("Satisfecho", 0L);
        totalPorValoracion.putIfAbsent("Neutral", 0L);
        totalPorValoracion.putIfAbsent("Insatisfecho", 0L);

        model.addAttribute("totalPorValoracion", totalPorValoracion);

        return "list-encuestas";

    }
}
