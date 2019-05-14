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

import Pojos.Ejemplar;
import Pojos.Libro;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintException;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos
 */
public class CodigoBarras {

    /**
     * Metodo para generar un Barcode mediante un Codigo recibido
     *
     * @param codigo
     * @return
     */
    public Barcode39 generarCodigoIndividual(String codigo) throws Exception {
        Barcode39 barcode = new Barcode39();

        barcode.setCode(codigo.toUpperCase());

        barcode.setBarHeight(60);

        return barcode;
    }

    /**
     * Metodo para generar una lista de Barcodes mediante una lista de Codigos
     * recibida
     *
     * @param codigos
     * @return
     * @throws Exception
     */
    public List<Barcode39> generarCodigoList(List<String> codigos) throws Exception {
        List<Barcode39> barcodes = new ArrayList<Barcode39>();

        for (int i = 0; i < codigos.size(); i++) {
            Barcode39 barcode = new Barcode39();

            barcode.setCode(codigos.get(i).toUpperCase());

            barcode.setBarHeight(60);

            barcodes.add(barcode);
        }

        return barcodes;
    }

    /**
     * Metodo para generar y abrir un PDF correspondiente a un Barcode recibido
     * con el titulo del Ejemplar recibido
     *
     * @param ejemplar
     * @param barcode
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws IOException
     */
    public void imprimirIndividual(Ejemplar ejemplar, Barcode39 barcode, int filas, int columnas, int posicion, int cantidad) throws FileNotFoundException, DocumentException, IOException {
        comprobarDirectorio();

        int numEjemplar = 0;

        for (int i = 0; i < ejemplar.getLibro().getEjemplares().size(); i++) {
            if (ejemplar.getLibro().getEjemplares().get(i).equals(ejemplar)) {
                numEjemplar = i;
                break;
            }
        }

        String rutapdf = "C://Gestion_Libros//Impresiones//" + ejemplar.getLibro().getNombre() + "-CodigoEjemplar(" + (numEjemplar + 1) + ").pdf";

        OutputStream os = new FileOutputStream(new File(rutapdf));

        Document doc = new Document();

        PdfWriter pdf = PdfWriter.getInstance(doc, os);

        doc.open();

        doc.addTitle("CÓDIGO EJEMPLAR - " + ejemplar.getLibro().getNombre() + "(\"" + numEjemplar + "\")");

        int contador = 0, pos = 0;

        Image codeImgBase = barcode.createImageWithBarcode(pdf.getDirectContent(),
                BaseColor.BLACK, BaseColor.BLACK);

        //Etiquetas que queremos que se impriman en modo individual 
        int maxEtiquetas = cantidad;

        int etiquetasVertical = filas; //Etiquetas que caben verticalmente en el papel
        int etiquetasHorizontal = columnas; //Etiquetas que caben horizontalmente en el papel

        //Altura y anchura real de la pagina A4
        float heightPdf = PageSize.A4.getHeight(), widthPdf = PageSize.A4.getWidth();

        //Altura de los bordes
        int bordes = 2, alturaBordes = 21;
        int totalBordes = bordes * alturaBordes;

        //Altura y anchura real de las etiquetas
        float heigthEtiqueta = (heightPdf - totalBordes) / etiquetasVertical, widthEtiqueta = widthPdf / etiquetasHorizontal;

        //Altura y anchura real de las etiquetas
        float heigthCodigo = codeImgBase.getHeight(), widthCodigo = codeImgBase.getWidth();

        //Posicionamiento de la etiqueta
        float etiqueta1x = 0, etiqueta1y = heightPdf - heigthEtiqueta - alturaBordes;

        for (int i = 0; i < etiquetasVertical; i++) {
            for (int j = 0; j < etiquetasHorizontal; j++) {
                if (pos >= posicion) {
                    //Controlamos si hemos pintado todas las etiquetas
                    if (contador == maxEtiquetas) {
                        break;
                    }

                    //Pintamos las etiquetas
                    Image codeImg = barcode.createImageWithBarcode(pdf.getDirectContent(),
                            BaseColor.BLACK, BaseColor.BLACK);
                    float x = etiqueta1x + (widthEtiqueta * j), y = etiqueta1y - (heigthEtiqueta * i);
                    codeImg.setAbsolutePosition(x + ((widthEtiqueta / 2) - (widthCodigo / 2)),
                            y + ((heigthEtiqueta / 2) - (heigthCodigo / 2)));
                    doc.add(codeImg);
                    contador++;
                }
                pos++;
            }
        }
        doc.newPage();

        doc.close();

        os.close();

        Desktop.getDesktop().open(new File(rutapdf));
    }

    /**
     * Metodo para generar y abrir un PDF correspondiente a los Barcodes
     * recibidos y con el titulo del Libro recibido
     *
     * @param libro
     * @param barcodes
     * @param filas
     * @param columnas
     * @param filaStart
     * @param columnaStart
     * @throws PrinterException
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws IOException
     * @throws PrintException
     */
    public void imprimirList(Libro libro, List<Barcode39> barcodes, int filas, int columnas, int posicion) throws PrinterException, FileNotFoundException, DocumentException, IOException, PrintException {

        comprobarDirectorio();

        String rutapdf = "C://Gestion_Libros//Impresiones//" + libro.getNombre() + "-CodigosEjemplares.pdf";

        OutputStream os = new FileOutputStream(new File(rutapdf));

        Document doc = new Document();

        PdfWriter pdf = PdfWriter.getInstance(doc, os);

        doc.open();

        doc.addTitle("CÓDIGOS EJEMPLARES - " + libro.getNombre());

        int contador = 0, pos = 0;

        Image codeImgBase = barcodes.get(0).createImageWithBarcode(pdf.getDirectContent(),
                BaseColor.BLACK, BaseColor.BLACK);

        int etiquetasVertical = filas; //Etiquetas que caben verticalmente en el papel
        int etiquetasHorizontal = columnas; //Etiquetas que caben horizontalmente en el papel

        //Altura y anchura real de la pagina A4
        float heightPdf = PageSize.A4.getHeight(), widthPdf = PageSize.A4.getWidth();

        //Altura de los bordes
        int bordes = 2, alturaBordes = 21;
        int totalBordes = bordes * alturaBordes;

        //Altura y anchura real de las etiquetas
        float heigthEtiqueta = (heightPdf - totalBordes) / etiquetasVertical, widthEtiqueta = widthPdf / etiquetasHorizontal;

        //Altura y anchura real de las etiquetas
        float heigthCodigo = codeImgBase.getHeight(), widthCodigo = codeImgBase.getWidth();

        //Posicionamiento de la etiqueta
        float etiqueta1x = 0, etiqueta1y = heightPdf - heigthEtiqueta - alturaBordes;

        //Contadores de las etiquetas para controlar las páginas necesarias y la iteracion por la que vamos en el do-while
        int contadorMaxWhile = Math.round(libro.getEjemplaresDisponibles().size() / (etiquetasHorizontal * etiquetasVertical)) + posicion;
        int contadorWhile = 0;

        do {
            for (int i = 0; i < etiquetasVertical; i++) {
                for (int j = 0; j < etiquetasHorizontal; j++) {
                    if (pos >= posicion) {
                        //Controlamos si hemos pintado todas las etiquetas
                        if (contador == libro.getEjemplaresDisponibles().size()) {
                            break;
                        }

                        //Pintamos las etiquetas
                        Image codeImg = barcodes.get(contador).createImageWithBarcode(pdf.getDirectContent(),
                                BaseColor.BLACK, BaseColor.BLACK);
                        float x = etiqueta1x + (widthEtiqueta * j), y = etiqueta1y - (heigthEtiqueta * i);
                        codeImg.setAbsolutePosition(x + ((widthEtiqueta / 2) - (widthCodigo / 2)),
                                y + ((heigthEtiqueta / 2) - (heigthCodigo / 2)));
                        doc.add(codeImg);
                        contador++;
                    }
                    pos++;
                }
            }
            doc.newPage();
            contadorWhile++;
        } while (contadorWhile < contadorMaxWhile);

        doc.close();

        os.close();

        Desktop.getDesktop().open(new File(rutapdf));
    }

    /**
     * Nos devuelve una imageIcon del codigo de Barras.
     *
     * @param barcode El el barcode de donde generara la imágen
     * @param width El ancho de la imágen que queremos
     * @param height El alto de la imágen que queremos
     */
    public ImageIcon getImage(Barcode39 barcode, int width, int height) {
        //ImageIcon icon = new ImageIcon(barcode.drawBarcode());  
        java.awt.Image img = barcode.createAwtImage(Color.WHITE, Color.BLACK);
        ImageIcon icon = new ImageIcon(getScaledImage(img, width, height));
        return icon;
    }

    /**
     * Metodo para redimensionar la imagen que generamos del Barcode
     *
     * @param srcImg Imagen a redimensionar
     * @param w Ancho de la imágen que queremos
     * @param h Alto de la imágen que queremos
     * @return Devuelve la imagen redimensionada
     */
    private java.awt.Image getScaledImage(java.awt.Image srcImg, int w, int h) {
        BufferedImage imgRedimensionada = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imgRedimensionada.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return imgRedimensionada;
    }

    /**
     * Metodo para comprobar si existe un directorio, si no existe se crea
     */
    private void comprobarDirectorio() {
        File directorio = new File("C://Gestion_Libros//Impresiones//");

        //Si el directorio no existe lo creamos
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }
}
