package com.github.kettoleon.cah.generator;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public interface CahCard {

    void print(PDPageContentStream pdPageContentStream, float x, float y) throws IOException;
}
