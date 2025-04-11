import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class MyBot extends TelegramLongPollingBot {

    private static final String BOT_USERNAME = "@coder22bot";  // ur bot username
    private static final String BOT_TOKEN = "6817651266:AAHDzZjfl3JyMhubKWcg-_hmTvqiCPw1JSY";       // Bot token

    @Override
    public void onUpdateReceived(Update update) {



        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText().trim();
            Long chatId = update.getMessage().getChatId();

            // catching user data
            var usr = update.getMessage().getContact();
            System.out.println(usr);

            File file = new File("Data.txt");
            boolean isNew = true;

            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.equals(chatId.toString())) {
                            isNew = false;
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // if new then write
            if (isNew) {
                try (BufferedWriter bf = new BufferedWriter(new FileWriter("Data.txt", true))) {
                    bf.write(chatId.toString());
                    bf.newLine();
                    System.out.println("New chatId saved.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("chatId already exists: " + chatId);
            }


            String Search;

            Search = Main.Search(text);

            SendMessage message = new SendMessage();

            SendMessage tempMsg = new SendMessage();

            message.setChatId(String.valueOf(chatId));





            message.setText(Search);


            // msg exicute korbo
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }




    //  ************  always kisu msg likhe pathate hole ei ekhan theke pathabo **********

    //------------------------------------------------------------------------------------------
 /*  public void SendImportantMSg() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("Data.txt"));
            String line;
            while ((line = bf.readLine()) != null) {
                Long chatID = Long.parseLong(line.trim());

                SendMessage importantMsg = new SendMessage();
                importantMsg.setChatId(chatID.toString());
                importantMsg.setText("Test msg..😊");

                try {
                    execute(importantMsg);
                    System.out.println("Msg sent to: " + chatID);
                } catch (TelegramApiException e) {
                    System.err.println("Error sending msg to: " + chatID);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    //-----------------------------------------------------------------------------------------



    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }



    // main path passing er jnno --->
/*    public static String FindPath(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter pdf's path: ");
        String path = sc.next().trim();
        return path;

    }*/


    public static void main(String[] args) {

            try {

                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                MyBot b = new MyBot();

                // b.SendImportantMSg();


                botsApi.registerBot(new MyBot());
                System.out.println("Bot is running...🫡");

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        // tested

    }
}
