package com.ipn.escom.GesRestaurantes.controlador;

import com.ipn.escom.GesRestaurantes.modelo.Calificacion;
import com.ipn.escom.GesRestaurantes.modelo.Categoria;
import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import com.ipn.escom.GesRestaurantes.services.CalificacionService;
import com.ipn.escom.GesRestaurantes.services.ReportesService;
import com.ipn.escom.GesRestaurantes.services.RestauranteService;
import com.ipn.escom.GesRestaurantes.services.UsuarioService;
import com.ipn.escom.GesRestaurantes.utils.FileUploadUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ReportesService reportesService;
    @Autowired
    private CalificacionService calificacionService;

    @GetMapping("/restaurantes")
    public String getRestaurantes(Model model) {
        model.addAttribute("restaurantes",restauranteService.getRestaurantes());
        return "restaurantes";
    }
    @GetMapping("/restaurantes/new")
    public String getRestaurantesNew(Model model) {
        List<Categoria> categorias = restauranteService.getCategorias();
        Restaurante r = new Restaurante();
        model.addAttribute("categorias",categorias);
        model.addAttribute("restaurante",r);
        return "restaurantesForm";
    }
    @PostMapping("/restaurantes/new")
    public String saveRestaurante(Restaurante restaurante,
                                  @RequestParam("imagen") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        restaurante.setFoto(fileName);
        restaurante = restauranteService.registrar(restaurante);
        String uploadDir = "restaurantes_photos/" + restaurante.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/admin/restaurantes";
    }
    @GetMapping("/restaurantes/{id}")
    public String editRestaurante(@PathVariable("id") long id, Model model){

        Restaurante r = new Restaurante();
        r.setId(id);

        r = restauranteService.getRestaurante(r);
        if(r == null){
            return "redirect:/admin/restaurantes";
        }
        model.addAttribute("restaurante",r);
        model.addAttribute("categorias",restauranteService.getCategorias());

        return "restaurantesForm";
    }
    @PostMapping("/restaurantes/{id}")
    public String updateRestaurante(@PathVariable("id") long id,Restaurante restaurante,@RequestParam("imagen") MultipartFile multipartFile)throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(!fileName.equals("")){
            restaurante.setFoto(fileName);
        }else{
            Restaurante r = restauranteService.getRestaurante(restaurante);
            restaurante.setFoto(r.getFoto());
            restauranteService.registrar(restaurante);
            return "redirect:/admin/restaurantes";
        }
        restaurante = restauranteService.registrar(restaurante);

        String uploadDir = "restaurantes_photos/" + restaurante.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/admin/restaurantes";
    }

    @GetMapping("/restaurantes/categorias")
    public String getCategoria(Model model){
        model.addAttribute("categorias",restauranteService.getCategorias());
        return "categoriasList";
    }
    @GetMapping("/restaurantes/{id}/delete")
    public String deleteRestaurante( @PathVariable("id") long id ){
        Restaurante r = new Restaurante();
        r.setId(id);
        restauranteService.eliminarRestaurante(r);
        return "redirect:/admin/restaurantes/";
    }
    @PostMapping("/restaurantes/categorias")
    public String saveCategoria(Categoria categoria){
        restauranteService.registrarCategoria(categoria);
        //model.addAttribute("categorias",restauranteService.getCategorias());
        return "redirect:/admin/restaurantes/categorias";
    }
    @GetMapping("/restaurantes/categorias/{id}/delete")
    public String deleteCategoria( @PathVariable("id") long id ){
        Categoria c = new Categoria();
        c.setId(id);
        restauranteService.eliminarCategoria(c);
        return "redirect:/admin/restaurantes/categorias";
    }
    @GetMapping("/usuarios")
    public String getUsuarios(Model model) {
        model.addAttribute("usuarios",usuarioService.list());
        return "usuarios";
    }
    @GetMapping("/usuarios/{id}/delete")
    public String deleteUsuario(@PathVariable("id") long id, Authentication authentication) {
        Usuario u = new Usuario();
        u.setId(id);
        u = usuarioService.getUserById(u);
        if(u.getUsername().equals(authentication.getName())){
            return "redirect:/admin/usuarios";
        }
        usuarioService.eliminar(u);
        return "redirect:/admin/usuarios";
    }
    @GetMapping("/usuarios/{id}/toggle")
    public String toggleUsuario(@PathVariable("id") long id, Authentication authentication) {
        Usuario u = new Usuario();
        u.setId(id);
        u = usuarioService.getUserById(u);
        if(u.getUsername().equals(authentication.getName())){
            return "redirect:/admin/usuarios";
        }
        usuarioService.toggle(u);
        return "redirect:/admin/usuarios";
    }
    @GetMapping("/estadisticas")
    public String getEstadisticas(Model model){
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
        List<Categoria> categorias = restauranteService.getCategorias();
        List<String> sCategorias = new ArrayList<String>();
        List<Integer> sNumero = new ArrayList<Integer>();
        for (Categoria c:
             categorias) {
            dictionary.put(c.getNombreCategoria(),0);
        }
        for(Restaurante r:
        restauranteService.getRestaurantes()){
            String scategoria = r.getTipo().getNombreCategoria();
            dictionary.put(scategoria, dictionary.get(scategoria)+1);
        }
        for(String s:
        dictionary.keySet().toArray(new String[0])){
            sNumero.add(dictionary.get(s));
            sCategorias.add(s);
        }
        System.out.println(sNumero);
        model.addAttribute("categorias",sCategorias);
        model.addAttribute("numero",sNumero);
        return "estadisticas";
    }
    @GetMapping(value="/reportes",produces= MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] getReportes( @RequestParam String name) {
        return reportesService.getRestaurantes(name);
    }
    @GetMapping("/comentarios")
    public String getComentarios(Model model){
        model.addAttribute("comentarios", calificacionService.getAll());
        return "calificaciones";
    }
    @GetMapping("/comentarios/{id}/delete")
    public String deleteComentario(@PathVariable("id") long id){
        Calificacion c = new Calificacion();
        c.setId(id);
        calificacionService.borrarComentario(c);
        return "redirect:/admin/comentarios";
    }
}
