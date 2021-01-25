package com.ipn.escom.GesRestaurantes.services;

import com.ipn.escom.GesRestaurantes.modelo.Restaurante;
import com.ipn.escom.GesRestaurantes.repositorio.RestauranteDAO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ReportesService {
    @Autowired
    private RestauranteDAO restauranteDAO;
    private Connection con;
    @Value("${spring.datasource.username}")
    private String usr;
    @Value("${spring.datasource.password}")
    private String pass;
    @Value("${spring.datasource.url}")
    private String url;
    public byte[] getRestaurantes(String nombreReporte){
        try {
            InputStream resource = ReportesService.class.getResourceAsStream("/static/reportes/"+nombreReporte+".jasper");
            File file = new File(nombreReporte+".jasper");
            byte[] bytes = JasperRunManager.runReportToPdf(resource,null, obtenerConexion());
            return bytes;
        } catch (JRException ex) {
            Logger.getLogger(ReportesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Connection obtenerConexion() {
        String driver = "org.postgresql.Driver";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usr, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReportesService.class.getName()).log(Level.SEVERE, "Error en la conexion", ex);
        }
        return con;
    }


}
