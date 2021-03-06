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

import Daos.DaoAlumno;
import Daos.DaoContenido;
import Daos.DaoMatricula;
import Pojos.Alumno;
import Pojos.Contenido;
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
public class ImportarMatriculasXML {

    SimpleDateFormat sdf;

    DocumentBuilderFactory factory = null;
    DocumentBuilder builder = null;

    List<Matricula> matriculasCargadas;

    /**
     *  Constructor de la clase ImportarMatriculasXML en el que preparamos un XML
     *  para poder leerlo más tarde
     * @param ruta
     * @throws Exception
     */
    public ImportarMatriculasXML(String ruta) throws Exception {
        factory = DocumentBuilderFactory.newInstance();

        builder = factory.newDocumentBuilder();

        Document doc = builder.parse(ruta);
        doc.normalize();

        cargarMatriculas(doc);
    }

    /**
     * Metodo para obtener una lista de Matriculas de un archivo XML
     */
    private void cargarMatriculas(Document doc) throws ParseException, Exception {
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        matriculasCargadas = new ArrayList<Matricula>();

        String curso_escolar = "", alumno = "", ensenanza = "", curso = "",
                contenido = "", idioma = "", tipo_basico = "", tipo_predom = "",
                acis = "", fec_ini_acis = "", fec_fin_acis = "",
                cur_ref_acis = "", curso_pendiente = "";

        NodeList nodos = doc.getElementsByTagName("contenido_alumno");

        //Si existen matriculas obtenemos el curso_escolar
        if (nodos.getLength() > 0) {
            NamedNodeMap atributosPadrePadre = nodos.item(0).getParentNode().getParentNode().getAttributes();

            //Obtenemos el curso 
            for (int j = 0; j < atributosPadrePadre.getLength(); j++) {
                if (atributosPadrePadre.item(j).getNodeName().equals("curso")) {
                    curso_escolar = atributosPadrePadre.item(j).getNodeValue();
                    System.out.println("Curso Escolar: " + curso_escolar);
                }
            }

            //Por cada matrícula obtenemos todos sus atributos
            for (int i = 0; i < nodos.getLength(); i++) {
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

                /**
                 * Convertimos las fechas de String a Java.Sql.Date si hay algún
                 * error, la fecha será asignada a 2000-01-01
                 */
                java.util.Date date;
                java.sql.Date fec_ini_acis_date, fec_fin_acis_date;

                try {
                    date = sdf.parse(fec_ini_acis);
                    fec_ini_acis_date = new java.sql.Date(date.getTime());
                } catch (ParseException ex) {
                    date = sdf.parse("2000-01-01");
                    fec_ini_acis_date = new java.sql.Date(date.getTime());
                }

                try {
                    date = sdf.parse(fec_fin_acis);
                    fec_fin_acis_date = new java.sql.Date(date.getTime());
                } catch (ParseException ex) {
                    date = sdf.parse("2000-01-01");
                    fec_fin_acis_date = new java.sql.Date(date.getTime());
                }

                //Buscamos el alumno y el contenido recuperado del XML
                DaoAlumno dao = new DaoAlumno(Main.gestorSesiones.getSession());

                Alumno alumnoObj = dao.buscar(alumno);

                dao.desconectar();

                DaoContenido daoc = new DaoContenido(Main.gestorSesiones.getSession());

                Contenido contenidoObj = daoc.buscarPorCodigo(contenido, curso);

                daoc.desconectar();

                matriculasCargadas.add(new Matricula(0, Integer.parseInt(
                        curso_escolar), alumnoObj, ensenanza, curso, contenidoObj,
                        idioma, tipo_basico, tipo_predom, acis, fec_ini_acis_date,
                        fec_fin_acis_date, cur_ref_acis, curso_pendiente));
            }

            insertarMatriculasBD();
        } else {
            throw new Exception("No hay datos de matriculas en el XML.");
        }
    }

    /**
     * Metodo para insertar una lista de Matriculas en la BD
     */
    private void insertarMatriculasBD() throws Exception {

        DaoMatricula dao = new DaoMatricula(Main.gestorSesiones.getSession());

        //Insertamos la lista de Matriculas
        try {
            dao.session.beginTransaction();
            dao.actualizarMatriculas(matriculasCargadas);
            dao.session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: ImportarMatriculasXML - insertarMatriculasBD()");
            e.printStackTrace();
            throw new Exception("Fallo al insertar las matriculas en la base de datos.");
        }

        dao.desconectar();
    }
}
