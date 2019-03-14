package com.github.kettoleon.cah.generator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.util.Matrix;

import java.awt.*;
import java.io.IOException;

import static com.github.kettoleon.cah.generator.CahPage.CahPageType.BLACK;

public class CahPagePrinter {

    private static final int CARD_WIDTH_MM = 45;
    private static final int GRID_HORIZONTAL_MARGIN_MM = 15;
    private static final float GRID_VERTICAL_MARGIN_MM = 13.5f;
    private static final int DINA4_H_MM = 297;
    private static final int DINA4_W_MM = 210;
    private static final int CARD_HEIGHT_MM = 45;
    private static final int GRID_LENGTH_MM = 5;
    private static final int GRID_MARGIN_MM = 1;
    private static final float SCALE_X_MM = 2.91428571429f;
    private static final float SCALE_Y_MM = 2.66666666667f;
    private static final float GRID_LINE_WIDTH = 0.1f;

    private PDDocument document = new PDDocument();

    public void addAndPrintPage(CahPage cahPage) throws IOException {
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        setUnitTransformToMillimeters(contentStream);

        drawPageBackground(contentStream, cahPage.getPageType());

        setPageColors(contentStream, cahPage.getPageType());

        drawGrid(contentStream);

        drawCards(cahPage, contentStream);

        contentStream.close();
    }

    private void drawCards(CahPage cahPage, PDPageContentStream contentStream) throws IOException {
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 4; i++) {
                int cardIdx = i + 4 * j;
                if (cahPage.getCards().size() <= cardIdx) {
                    return;
                }
                float xpos = 0;
                float ypos = 0;

                cahPage.getCards().get(cardIdx).print(contentStream, xpos, ypos);

            }

        }
    }

    private void setUnitTransformToMillimeters(PDPageContentStream contentStream) throws IOException {
        contentStream.transform(Matrix.getScaleInstance(SCALE_X_MM, SCALE_Y_MM));
    }

    private void setPageColors(PDPageContentStream contentStream, CahPage.CahPageType pageType) throws IOException {
        switch (pageType) {
            case BLACK:
                contentStream.setNonStrokingColor(Color.WHITE);
                contentStream.setStrokingColor(Color.WHITE);
                break;
            case WHITE:
                contentStream.setNonStrokingColor(Color.BLACK);
                break;
        }
    }

    private void drawPageBackground(PDPageContentStream contentStream, CahPage.CahPageType pageType) throws
            IOException {
        if (BLACK.equals(pageType)) {
            contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.fillRect(0, 0, DINA4_W_MM, DINA4_H_MM);
        }
    }

    private void drawGrid(PDPageContentStream contentStream) throws IOException {

        contentStream.setLineWidth(GRID_LINE_WIDTH);
        for (int i = 0; i < 5; i++) {
            int ruleX = GRID_HORIZONTAL_MARGIN_MM + (CARD_WIDTH_MM * i);
            contentStream.drawLine(ruleX, GRID_VERTICAL_MARGIN_MM - GRID_MARGIN_MM - GRID_LENGTH_MM, ruleX, GRID_VERTICAL_MARGIN_MM - GRID_MARGIN_MM);
            contentStream.drawLine(ruleX, DINA4_H_MM - GRID_VERTICAL_MARGIN_MM + GRID_MARGIN_MM, ruleX, DINA4_H_MM - GRID_VERTICAL_MARGIN_MM + GRID_MARGIN_MM + GRID_LENGTH_MM);
        }

        for (int i = 0; i < 7; i++) {
            float ruleY = GRID_VERTICAL_MARGIN_MM + (CARD_HEIGHT_MM * i);
            contentStream.drawLine(GRID_HORIZONTAL_MARGIN_MM - GRID_MARGIN_MM - GRID_LENGTH_MM, ruleY, GRID_HORIZONTAL_MARGIN_MM - GRID_MARGIN_MM, ruleY);
            contentStream.drawLine(DINA4_W_MM - GRID_HORIZONTAL_MARGIN_MM + GRID_MARGIN_MM, ruleY, DINA4_W_MM - GRID_HORIZONTAL_MARGIN_MM + GRID_MARGIN_MM + GRID_LENGTH_MM, ruleY);
        }
    }

    public void writeDocument(String file) throws IOException {
        document.save(file);
        document.close();
    }
}
