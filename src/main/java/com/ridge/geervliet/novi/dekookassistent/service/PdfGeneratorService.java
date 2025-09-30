package com.ridge.geervliet.novi.dekookassistent.service;


import com.ridge.geervliet.novi.dekookassistent.model.Recipe;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGeneratorService {

    public byte[] generatePdf(Recipe recipe) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                PDType1Font helveticaBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font helvetica = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                contentStream.setFont(helveticaBold, 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 750);

                contentStream.showText("Recept: " + recipe.getTitle());
                contentStream.newLine();
                contentStream.newLine();

                contentStream.showText("Beschrijving:");
                contentStream.newLine();
                contentStream.setFont(helvetica, 10);
                contentStream.showText(recipe.getDescription());
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(helveticaBold, 12);
                contentStream.showText("Ingrediënten:");
                contentStream.newLine();

                contentStream.setFont(helvetica, 10);
                recipe.getRecipeIngredients().forEach(ri -> {
                    try {
                        contentStream.showText("- " + ri.getQuantity() + " " + ri.getUnit() + " " + ri.getIngredient().getName());
                        contentStream.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                contentStream.endText();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();

        }
    }
}