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

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

/**
 *
 * @author Carlos
 */
public class CodigoBarras {

    public CodigoBarras() {

    }

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
    }
}
