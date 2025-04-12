import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static String Search(String srch) {

        try {
            //To PDF file path
                // use of input stream to access direct from resources ----->
            InputStream input = Main.class.getResourceAsStream("/pdf/pdf.pdf");

            if (input == null) {
                try {
                    Thread.sleep(2000); // 2 second wait server response er jnno...
                    input = Main.class.getResourceAsStream("/pdf/pdf.pdf");
                    if (input == null) return "❌ PDF still not found after retry.";
                } catch (InterruptedException e) {
                    return "❌ Thread interrupted while retrying.";
                }
            }

           // File file = new File("D:\\Java pppp\\MyRoutine\\src\\main\\java\\pdf.pdf"); // D:\Java pppp\MyRoutine\src\main\java\pdf.pdf
            var document = PDDocument.load(input);

            // all pdf read korar jnno
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            document.close();

            // break text....
            String[] lines = text.split("\n");

            String currentDate = "";
            String currentSlot = "";
            String currentTime = "";

            for (String line : lines) {
                line = line.trim();

                //  findnig exm date
                if (line.toLowerCase().startsWith("day") && line.contains(",")) {
                    int colonIndex = line.indexOf(":");
                    if (colonIndex != -1) {
                        currentDate = line.substring(colonIndex + 1).trim();
                    }
                }

                // slot er jnno
                if (line.startsWith("Slot")) {
                    currentSlot = line.split(":")[0].trim();
                }

                // time er jnno
                if ((line.contains("AM") || line.contains("PM")) && line.contains("-") && line.length() < 30) {
                    currentTime = line.trim();
                }

                // Subject search korbo ......
                if (line.toUpperCase().contains(srch.toUpperCase())) {
                    return "📘 Subject: " + line + "\n\n" +
                            "🗓️ Date   : " + currentDate + "\n" +
                            "🔢 Slot   : " + currentSlot + "\n";
                }
            }

        } catch (IOException e) {
            return "❌ Something error " + e.getMessage();
        }

        return "❌ Subject not found!";
    }
}
