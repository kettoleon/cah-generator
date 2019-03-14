package com.github.kettoleon.cah.generator;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class CahWitheCard implements CahCard {

    PDFont font = PDType1Font.HELVETICA_BOLD;

    public void print(PDPageContentStream contentStream, float x, float y) throws IOException {

        //TODO Wow, no built in support for wrapping... need to find another library or approach :s
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(10, 70);
        contentStream.drawString("Hello World");
        contentStream.endText();

    }
}
