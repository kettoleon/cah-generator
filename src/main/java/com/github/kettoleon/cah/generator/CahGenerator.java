package com.github.kettoleon.cah.generator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

import java.awt.*;
import java.io.IOException;

public class CahGenerator {


    public static void main(String[] args) throws IOException {


        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        System.out.printf("Page size: %f,%f", page.getCropBox().getWidth(), page.getCropBox().getHeight());
        System.out.printf("Recalculating bounds: %f,%f", 210f * 2.91428571429f, 297f * 2.66666666667f);

        document.addPage(page);

        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        drawGrid(contentStream);

        // Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(10, 70);
        contentStream.drawString("Hello World");
        contentStream.endText();

        // Make sure that the content stream is closed:
        contentStream.close();

        // Save the results and ensure that the document is properly closed:
        document.save("hello-world.pdf");
        document.close();


    }

    private static void drawGrid(PDPageContentStream contentStream) throws IOException {
        contentStream.transform(Matrix.getScaleInstance(2.91428571429f, 2.66666666667f));
        contentStream.setNonStrokingColor(Color.BLACK);

        contentStream.setLineWidth(0.1f);
        for (int i = 0; i < 5; i++) {
            contentStream.drawLine(15 + (45 * i), 0, 15 + (45 * i), 12);
            contentStream.drawLine(15 + (45 * i), 297, 15 + (45 * i), 297 - 12);
        }

        for (int i = 0; i < 7; i++) {
            contentStream.drawLine(0, 13.5f + (45 * i), 14, 13.5f + (45 * i));
            contentStream.drawLine(210, 13.5f + (45 * i), 210 - 14, 13.5f + (45 * i));
        }
    }

}
