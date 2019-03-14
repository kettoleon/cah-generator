package com.github.kettoleon.cah.generator;

import java.io.IOException;

public class CahGenerator {

    public static void main(String[] args) throws IOException {

        // Create a document and add a page to it
        CahPagePrinter pagePrinter = new CahPagePrinter();

        // Create a new font object selecting one of the PDF base fonts
//        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" the to be created content
//        PDPageContentStream contentStream = new PDPageContentStream(document, page);
//
//        drawGrid(contentStream);
//
//        // Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
//        contentStream.beginText();
//        contentStream.setFont(font, 12);
//        contentStream.moveTextPositionByAmount(10, 70);
//        contentStream.drawString("Hello World");
//        contentStream.endText();


        CahPage cahPage = CahPage.aWhitePage();
        cahPage.getCards().add(new CahWitheCard());
        pagePrinter.addAndPrintPage(cahPage);
        pagePrinter.addAndPrintPage(CahPage.aBlackPage());
        pagePrinter.writeDocument("hello-world.pdf");

        // Save the results and ensure that the document is properly closed:


    }


}
