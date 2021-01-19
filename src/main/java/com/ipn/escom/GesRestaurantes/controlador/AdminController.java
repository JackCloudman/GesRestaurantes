package com.ipn.escom.GesRestaurantes.controlador;

import com.ipn.escom.GesRestaurantes.modelo.Categoria;
import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.services.RestauranteService;
import com.ipn.escom.GesRestaurantes.services.UsuarioService;
import com.ipn.escom.GesRestaurantes.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/restaurantes")
    public String getRestaurantes(Model model) {
        model.addAttribute("restaurantes",restauranteService.getRestaurantes());
        return "restaurantes";
    }
    @GetMapping("/restaurantes/new")
    public String getRestaurantesNew(Model model) {
        List<Categoria> categorias = restauranteService.getCategorias();
        model.addAttribute("categorias",categorias);
        return "restaurantesForm";
    }
    @PostMapping("/restaurantes/new")
    public String saveRestaurante(Restaurante restaurante,
                                  @RequestParam("imagen") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        restaurante.setFoto(fileName);
        restaurante = restauranteService.registrar(restaurante);
        String uploadDir = "restaurantes-photos/" + restaurante.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/admin/restaurantes";
    }

    @GetMapping("/restaurantes/categorias")
    public String getCategoria(Model model){
        model.addAttribute("categorias",restauranteService.getCategorias());
        return "categoriasList";
    }
    @PostMapping("/restaurantes/categorias")
    public String saveCategoria(Categoria categoria){
        restauranteService.registrarCategoria(categoria);
        //model.addAttribute("categorias",restauranteService.getCategorias());
        return "redirect:/admin/restaurantes/categorias";
    }

}
