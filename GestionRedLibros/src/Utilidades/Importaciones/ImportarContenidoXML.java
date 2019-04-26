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
package Utilidades.Importaciones;

import Daos.DaoContenido;
import Daos.DaoCurso;
import Pojos.Contenido;
import Pojos.Curso;
import Pojos.Matricula;
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

/**
 *
 * @author Carlos
 */
public class ImportarContenidoXML {

    DocumentBuilderFactory factory = null;
    DocumentBuilder builder = null;

    List<Contenido> contenidosCargados;

    /**
     * Constructor de la clase ImportarContenidoXML en el que preparamos un XML
     *  para poder leerlo más tarde
     *
     * @param ruta
     * @throws java.text.ParseException
     */
    public ImportarContenidoXML(String ruta) throws Exception {
        factory = DocumentBuilderFactory.newInstance();

        builder = factory.newDocumentBuilder();

        Document doc = builder.parse(ruta);
        doc.normalize();

        cargarContenidos(doc);

    }

    /**
     *  Metodo para obtener una lista de Contenidos de un archivo XML
     */
    private void cargarContenidos(Document doc) throws ParseException, Exception {
        contenidosCargados = new ArrayList<Contenido>();

        String codigo = "", curso = "", nombre_cas = "", nombre_val = "",
                ensenanza = "";

        NodeList nodos = doc.getElementsByTagName("contenido");

        if (nodos.getLength() > 0) {
            //Por cada matrícula obtenemos todos sus atributos
            for (int i = 0; i < nodos.getLength(); i++) {
                NamedNodeMap atributos = nodos.item(i).getAttributes();

                for (int k = 0; k < atributos.getLength(); k++) {
                    switch (atributos.item(k).getNodeName()) {
                        case "codigo":
                            codigo = atributos.item(k).getNodeValue();
                            break;

                        case "curso":
                            curso = atributos.item(k).getNodeValue();
                            break;

                        case "nombre_cas":
                            nombre_cas = atributos.item(k).getNodeValue();
                            break;

                        case "nombre_val":
                            nombre_val = atributos.item(k).getNodeValue();
                            break;

                        case "ensenanza":
                            ensenanza = atributos.item(k).getNodeValue();
                            break;
                    }
                }

                //Buscamos el curso recuperado del XML
                DaoCurso dao = new DaoCurso(Main.gestorSesiones.getSession());

                Curso c = dao.buscar(curso);

                dao.desconectar();

                contenidosCargados.add(new Contenido(c, codigo, ensenanza, nombre_cas,
                        nombre_val));
            }

            insertarContenidosBD();
        } else {
            throw new Exception("No hay datos de asignaturas en el XML.");
        }
    }

    /**
     *  Metodo para insertar una lista de Contenidos en la BD
     */
    private void insertarContenidosBD() throws Exception {

        DaoContenido dao = new DaoContenido(Main.gestorSesiones.getSession());

        //Insertamos la lista de Contenidos
        try {
            dao.actualizarContenidos(contenidosCargados);
        } catch (Exception e) {
            throw new Exception("Fallo al insertar las asignaturas en la base de datos.");
        }

        dao.desconectar();
    }
}
