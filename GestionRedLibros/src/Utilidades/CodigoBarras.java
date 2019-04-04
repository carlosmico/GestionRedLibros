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

import Pojos.Libro;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintException;

/**
 *
 * @author Carlos
 */
public class CodigoBarras {

    public CodigoBarras() {

    }

    public List<Barcode39> generarCodigoList(List<String> codigos) throws Exception {
        try {
            List<Barcode39> barcodes = new ArrayList<Barcode39>();

            for (int i = 0; i < codigos.size(); i++) {
                Barcode39 barcode = new Barcode39();
                barcode.setCode(codigos.get(i));

                barcode.setBarHeight(30);

                barcodes.add(barcode);
            }

            return barcodes;
        } catch (Exception e) {
            throw new Exception();
        }
    }

    /*  Generamos el PDF correspondiente con los Barcode de cada ejemplar
    *   y cada 8 Barcodes insertados en el pdf pasamos a la siguiente pagina por temas visuales
     */
    public void imprimirList(Libro libro, List<Barcode39> barcodes) throws PrinterException, FileNotFoundException, DocumentException, IOException, PrintException {
        String rutapdf = "C://gestion_libros//impresiones//" + libro.getNombre() + "-CodigosEjemplares.pdf";

        OutputStream os = new FileOutputStream(new File(rutapdf));

        Document doc = new Document();

        PdfWriter pdf = PdfWriter.getInstance(doc, os);

        doc.open();

        doc.addTitle("CÓDIGOS EJEMPLARES - " + libro.getNombre());

        int contador = 8;

        for (int i = 0; i < barcodes.size(); i++) {
            if (contador == 0) {
                contador = 8;
                doc.newPage();
            }

            doc.add(new Paragraph(libro.getContenido().getNombre_cas()));

            Image codeImg = barcodes.get(i).createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);

            doc.add(codeImg);

            doc.add(new Paragraph(""));

            contador--;
        }

        doc.close();

        os.close();

        Desktop.getDesktop().open(new File(rutapdf));
    }


    /*
    public Barcode generarCodigo(String codigo) throws Exception {
        try {
            Barcode barcode = BarcodeFactory.createCode39(codigo, true);

            barcode.setDrawingText(true);

            barcode.setBarWidth(2);
            barcode.setBarHeight(60);

            return barcode;
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public List<Barcode> generarCodigoList(List<String> codigos) throws Exception {
        try {
            List<Barcode> barcodes = new ArrayList<Barcode>();

            for (int i = 0; i < codigos.size(); i++) {
                Barcode barcode = BarcodeFactory.createCode39(codigos.get(i), true);

                barcode.setDrawingText(true);

                barcode.setBarWidth(2);
                barcode.setBarHeight(60);

                barcodes.add(barcode);
            }

            return barcodes;
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void imprimir(Barcode barcode) throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(barcode);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                throw new PrinterException();
            }
        }
    }

    public void imprimirList(List<Barcode> barcodes) throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();

        for (int i = 0; i < barcodes.size(); i++) {
            job.setPrintable(barcodes.get(i));
        }

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                throw new PrinterException();
            }
        }
    }*/
}
