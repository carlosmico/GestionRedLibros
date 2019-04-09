package Utilidades;

import Daos.DaoCurso;
import Pojos.Curso;
import Utilidades.ImportarMatriculasXML;
import Vistas.Main;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * Copyright (C) 2019 Carlos
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/**
 *
 * @author Carlos
 */
public class ImportarCursoXML {

    DocumentBuilderFactory factory = null;
    DocumentBuilder builder = null;

    List<Curso> cursosCargados;

    /**
     * Constructor de la clase ImportarCursoXML en el que preparamos un XML para
     * poder leerlo más tarde
     *
     * @param ruta
     * @throws Exception
     */
    public ImportarCursoXML(String ruta) throws Exception {
        factory = DocumentBuilderFactory.newInstance();

        builder = factory.newDocumentBuilder();

        Document doc = builder.parse(ruta);
        doc.normalize();

        cargarCursos(doc);
    }

    /**
     * Metodo para obtener una lista de Cursos de un archivo XML
     */
    private void cargarCursos(Document doc) throws ParseException, Exception {
        cursosCargados = new ArrayList<Curso>();

        String codigo = "", ensenanza = "", abreviatura = "", nombre_cas = "",
                nombre_val = "", padre = "";

        NodeList nodos = doc.getElementsByTagName("curso");

        if (nodos.getLength() > 0) {
            //Por cada matrícula obtenemos todos sus atributos
            for (int i = 0; i < nodos.getLength(); i++) {
                NamedNodeMap atributos = nodos.item(i).getAttributes();

                for (int k = 0; k < atributos.getLength(); k++) {
                    switch (atributos.item(k).getNodeName()) {
                        case "codigo":
                            codigo = atributos.item(k).getNodeValue();
                            break;

                        case "ensenanza":
                            ensenanza = atributos.item(k).getNodeValue();
                            break;

                        case "abreviatura":
                            abreviatura = atributos.item(k).getNodeValue();
                            break;

                        case "nombre_cas":
                            nombre_cas = atributos.item(k).getNodeValue();
                            break;

                        case "nombre_val":
                            nombre_val = atributos.item(k).getNodeValue();
                            break;

                        case "padre":
                            padre = atributos.item(k).getNodeValue();
                            break;
                    }
                }

                cursosCargados.add(new Curso(codigo, ensenanza, abreviatura, nombre_cas,
                        nombre_val, padre));
            }

            insertarCursosBD();
        } else {
            throw new Exception("No hay datos de cursos en el XML.");
        }
    }

    /**
     * Metodo para insertar una lista de Cursos en la BD
     */
    private void insertarCursosBD() throws Exception {

        DaoCurso dao = new DaoCurso(Main.gestorSesiones.getSession());

        //Insertamos la lista de Cursos
        try {
            dao.actualizarCursos(cursosCargados);
        } catch (Exception e) {
            throw new Exception("Fallo al insertar los cursos en la base de datos.");
        }

        dao.desconectar();
    }

}
