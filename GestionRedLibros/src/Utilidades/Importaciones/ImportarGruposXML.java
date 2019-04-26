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

import Daos.DaoGrupo;
import Pojos.Grupo;
import Vistas.Main;
import java.io.IOException;
import java.text.ParseException;
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
public class ImportarGruposXML {

    DocumentBuilderFactory factory = null;
    DocumentBuilder builder = null;

    List<Grupo> gruposCargados;

    /**
     * Constructor de la clase ImportarGruposXML en el que preparamos un XML
     * para poder leerlo más tarde
     *
     * @param ruta
     * @throws Exception
     */
    public ImportarGruposXML(String ruta) throws Exception {
        factory = DocumentBuilderFactory.newInstance();

        builder = factory.newDocumentBuilder();

        Document doc = builder.parse(ruta);
        doc.normalize();

        cargarGrupos(doc);

    }

    /**
     * Metodo para obtener una lista de Grupos de un archivo XML
     */
    private void cargarGrupos(Document doc) throws ParseException, Exception {
        gruposCargados = new ArrayList<Grupo>();

        String codigo = "", nombre = "", ensenanza = "", linea = "", turno = "",
                modalidad = "", aula = "", capacidad = "", tutor_ppal = "",
                tutor_sec = "", oficial = "";

        NodeList nodos = doc.getElementsByTagName("grupo");

        if (nodos.getLength() > 0) {
            //Por cada matrícula obtenemos todos sus atributos
            for (int i = 0; i < nodos.getLength(); i++) {
                NamedNodeMap atributos = nodos.item(i).getAttributes();

                for (int k = 0; k < atributos.getLength(); k++) {
                    switch (atributos.item(k).getNodeName()) {
                        case "codigo":
                            codigo = atributos.item(k).getNodeValue();
                            break;

                        case "nombre":
                            nombre = atributos.item(k).getNodeValue();
                            break;

                        case "ensenanza":
                            ensenanza = atributos.item(k).getNodeValue();
                            break;

                        case "linea":
                            linea = atributos.item(k).getNodeValue();
                            break;

                        case "turno":
                            turno = atributos.item(k).getNodeValue();
                            break;

                        case "modalidad":
                            modalidad = atributos.item(k).getNodeValue();
                            break;

                        case "aula":
                            aula = atributos.item(k).getNodeValue();
                            break;

                        case "capacidad":
                            capacidad = atributos.item(k).getNodeValue();
                            break;

                        case "tutor_ppal":
                            tutor_ppal = atributos.item(k).getNodeValue();
                            break;

                        case "tutor_sec":
                            tutor_sec = atributos.item(k).getNodeValue();
                            break;

                        case "oficial":
                            oficial = atributos.item(k).getNodeValue();
                            break;
                    }
                }

                gruposCargados.add(new Grupo(
                        codigo,
                        nombre,
                        ensenanza,
                        linea,
                        turno,
                        modalidad,
                        aula,
                        capacidad,
                        tutor_ppal,
                        tutor_sec,
                        oficial
                ));
            }

            insertarGruposBD();
        } else {
            throw new Exception("No hay datos de grupos en el XML.");
        }
    }

    /**
     * Metodo para insertar una lista de Grupos en la BD
     */
    private void insertarGruposBD() throws Exception {
        DaoGrupo dao = new DaoGrupo(Main.gestorSesiones.getSession());

        //Insertamos la lista de Cursos
        try {
            dao.actualizarGrupos(gruposCargados);
        } catch (Exception e) {
            throw new Exception("Fallo al importar los grupos en la base de datos.");
        }

        dao.desconectar();
    }
}
