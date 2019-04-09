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

import Daos.DaoAlumno;
import Daos.DaoCurso;
import Daos.DaoGrupo;
import Pojos.Alumno;
import Pojos.Curso;
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
public class ImportarAlumnosXML {

    DocumentBuilderFactory factory = null;
    DocumentBuilder builder = null;

    List<Alumno> alumnosCargados;

    public ImportarAlumnosXML(String ruta) throws Exception {
        factory = DocumentBuilderFactory.newInstance();

        builder = factory.newDocumentBuilder();

        Document doc = builder.parse(ruta);
        doc.normalize();

        cargarAlumnos(doc);
    }

    private void cargarAlumnos(Document doc) throws ParseException, Exception {
        alumnosCargados = new ArrayList<Alumno>();

        String nia = "", nombre = "", apellido1 = "", apellido2 = "",
                fecha_nac = "", municipio_nac = "", documento = "",
                telefono1 = "", sexo = "", email1 = "", cursostr = "",
                grupostr = "";

        NodeList nodos = doc.getElementsByTagName("alumno");

        if (nodos.getLength() > 0) {
            //Por cada matrícula obtenemos todos sus atributos
            for (int i = 0; i < nodos.getLength(); i++) {
                NamedNodeMap atributos = nodos.item(i).getAttributes();

                for (int k = 0; k < atributos.getLength(); k++) {
                    switch (atributos.item(k).getNodeName()) {
                        case "NIA":
                            nia = atributos.item(k).getNodeValue();
                            break;

                        case "nombre":
                            nombre = atributos.item(k).getNodeValue();
                            break;

                        case "apellido1":
                            apellido1 = atributos.item(k).getNodeValue();
                            break;

                        case "apellido2":
                            apellido2 = atributos.item(k).getNodeValue();
                            break;

                        case "fecha_nac":
                            fecha_nac = atributos.item(k).getNodeValue();
                            break;

                        case "municipio_nac":
                            municipio_nac = atributos.item(k).getNodeValue();
                            break;

                        case "documento":
                            documento = atributos.item(k).getNodeValue();
                            break;

                        case "telefono1":
                            telefono1 = atributos.item(k).getNodeValue();
                            break;

                        case "sexo":
                            sexo = atributos.item(k).getNodeValue();
                            break;

                        case "email1":
                            email1 = atributos.item(k).getNodeValue();
                            break;

                        case "curso":
                            cursostr = atributos.item(k).getNodeValue();

                        case "grupo":
                            grupostr = atributos.item(k).getNodeValue();
                    }
                }

                //Buscamos en la BD el curso y el grupo del alumno
                DaoCurso daoc = new DaoCurso(Main.gestorSesiones.getSession());

                Curso curso = daoc.buscar(cursostr);

                daoc.desconectar();

                DaoGrupo daog = new DaoGrupo(Main.gestorSesiones.getSession());

                Grupo grupo = daog.buscar(grupostr);

                daog.desconectar();

                alumnosCargados.add(new Alumno(
                        nia,
                        nombre,
                        apellido1,
                        apellido2,
                        fecha_nac,
                        municipio_nac,
                        documento,
                        telefono1,
                        sexo,
                        email1,
                        curso,
                        grupo
                ));
            }

            insertarCursosBD();
        } else {
            throw new Exception("No hay datos de alumnos en el XML.");
        }
    }

    private void insertarCursosBD() throws Exception {

        DaoAlumno dao = new DaoAlumno(Main.gestorSesiones.getSession());

        //Insertamos la lista de Alumnos
        try {
            dao.actualizarAlumnos(alumnosCargados);
        } catch (Exception e) {
            throw new Exception("Fallo al insertar los cursos en la base de datos.");
        }

        dao.desconectar();
    }
}
