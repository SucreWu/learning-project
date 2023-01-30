package com.wujie.learning.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Image2PdfUtils {

    public static void image2Pdf(String input, String output) throws Exception {
        File file = new File(input);
        BufferedImage image = ImageIO.read(file);
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
        PDPageContentStream pdPageContentStream = new PDPageContentStream(document, page);
        float height = page.getMediaBox().getHeight();
        float y = page.getMediaBox().getHeight() - height;
        pdPageContentStream.drawImage(pdImage, 0, y, page.getMediaBox().getWidth(), height);
        pdPageContentStream.close();
        document.save(new File(output));
        document.close();
    }
}
