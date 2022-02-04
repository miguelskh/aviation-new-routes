package com.comit.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import com.comit.modelo.Huerta;
import com.comit.repositories.RoutesRepository;


@Controller
@RequestMapping("/routes")
public class RoutesController{

    @Autowired
    private RoutesRepository routesRepository;

    @GetMapping
    public string listroutes(Model model){
        model.addAttribute("routes", routesRepository.findByDeleted(false));
        model.addAttribute("routesdeleted", routesRepository.findByDeleted(true));
        return "listRoutes";
    }

    @GetMapping("/new")
    public String newRoute(Model model){
       model.addAttribute("route", new Route());
       return "newRoute";
    }  
    
    @GetMapping(path = "/{id}")
    public String editRoute(@PathVariable(name = "id", required = true) Long id, Model model){
           Optional<Route> routeOp = routesRepository.findById(id);
           if(!routeOp.isPresent())
                return "redirect:/error";

           model.addAttribute("route", routeOp.get());
           return "editRoute";
    }

    @PostMapping("/{id}")
    public String updateRoute(@PathVariable(name = "id", required = true) Long id, @ModelAttribute(name = "route") Route route){
       Optional<Route> routeOp = routesRepository.findById(id);
	   if (!routeOp.isPresent() || route == null)
	            return "redirect:/error";
				
		Route routeRepo = routeOp.get();
		
		routeRepo.setAirline(route.getAirline());
		routeRepo.setDeparture(route.getDeparture());
		routeRepo.setDestination(route.getDestination());
		
		routesRepository.save(routeRepo);
		return "redirect:/routes";
}

    @PostMapping("/create")
    public String createRoute(@ModelAttibute(name = "route") Route route){
        route = routesRepository.save(route);
	    return "redirect:/routes";
}

    @PostMapping(path = "/{id}/delete")
    public String deleteRoute(@PathVariable(name = "id", required = true) Long id){
        Optional<Route> routeOp = routesRepository.findById(id);
	    if (!routeOp.isPresent())
	          return "redirect:/error";
			 
	    Route route = routeOp.get();
	    route.setDeleted(true);
	    routesRepository.save(route);
	    return "redirect:/routes";
}

    @GetMapping(path = "/{id}/show")
    public String showRoute(@PathVariable(name = "id", required = true) Long id){
        Optional<Route> routeOp = routesRepository.findById(id);
	    if(!routeOp.isPresent){
	         return "redirect:/error";
	   }

        Route route = routeOp.get();
		route.setDeleted(false);
		routesRepository.save(route);
		return "redirect:/routes"
}


}