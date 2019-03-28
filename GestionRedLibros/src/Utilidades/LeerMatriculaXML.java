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
package Utilidades;

import Pojos.Matricula;
import java.io.IOException;
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
public class LeerMatriculaXML {

    DocumentBuilderFactory factory = null;
    DocumentBuilder builder = null;

    List<Matricula> matriculasCargadas;

    public LeerMatriculaXML(String ruta) {
        factory = DocumentBuilderFactory.newInstance();

        try {
            builder = factory.newDocumentBuilder();

            Document doc = builder.parse(ruta);
            doc.normalize();

            cargarMatriculas(doc);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(LeerMatriculaXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(LeerMatriculaXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerMatriculaXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarMatriculas(Document doc) {
        String curso_escolar, alumno, ensenanza, curso, contenido, idioma,
                tipo_basico, tipo_predom, acis, fec_ini_acis, fec_fin_acis,
                cur_ref_acis, curso_pendiente;

        NodeList nodos = doc.getElementsByTagName("contenido_alumno");

        for (int i = 0; i < nodos.getLength(); i++) {
            NamedNodeMap atributosPadrePadre = nodos.item(i).getParentNode().getParentNode().getAttributes();

            for (int j = 0; j < atributosPadrePadre.getLength(); j++) {
                if (atributosPadrePadre.item(j).getNodeName().equals("curso")) {
                    curso_escolar = atributosPadrePadre.item(j).getNodeValue();
                    System.out.println("Curso Escolar: " + curso_escolar);
                }
            }

            NamedNodeMap atributos = nodos.item(i).getAttributes();

            for (int k = 0; k < atributos.getLength(); k++) {
                switch (atributos.item(k).getNodeName()) {
                    case "acis":
                        acis = atributos.item(k).getNodeValue();
                        break;

                    case "alumno":
                        alumno = atributos.item(k).getNodeValue();
                        break;

                    case "contenido":
                        contenido = atributos.item(k).getNodeValue();
                        break;

                    case "cur_ref_acis":
                        cur_ref_acis = atributos.item(k).getNodeValue();
                        break;

                    case "curso":
                        curso = atributos.item(k).getNodeValue();
                        break;

                    case "curso_pendiente":
                        curso_pendiente = atributos.item(k).getNodeValue();
                        break;

                    case "ensenanza":
                        ensenanza = atributos.item(k).getNodeValue();
                        break;

                    case "fec_fin_acis":
                        fec_fin_acis = atributos.item(k).getNodeValue();
                        break;

                    case "fec_ini_acis":
                        fec_ini_acis = atributos.item(k).getNodeValue();
                        break;

                    case "idioma":
                        idioma = atributos.item(k).getNodeValue();
                        break;

                    case "tipo_basico":
                        tipo_basico = atributos.item(k).getNodeValue();
                        break;

                    case "tipo_predom":
                        tipo_predom = atributos.item(k).getNodeValue();
                        break;
                }
            }
        }
    }
}
