import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
   // public static String path = MyBot.FindPath();  ----->> Need to work from here...
    public static String Search(String srch) {

        try {
            //To PDF file path
            File file = new File("D:\\Java pppp\\MyRoutine\\src\\main\\java\\pdf.pdf"); // D:\Java pppp\MyRoutine\src\main\java\pdf.pdf
            var document = PDDocument.load(file);

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
