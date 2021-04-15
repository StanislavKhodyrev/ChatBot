package chatbot.client;

import chatbot.message.ConsoleHelper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: " +
                    "дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String name = message.replaceAll(":.*", "");
            String text = message.replaceAll(".*: ", "");
            Calendar calendar = Calendar.getInstance();
            String time;
            switch (text) {
                case "дата" -> {
                    time = new SimpleDateFormat("d.MM.yyyy").format(calendar.getTime());
                    sendTextMessage("Информация для " + name + ": " + time);
                }
                case "день" -> {
                    time = new SimpleDateFormat("d").format(calendar.getTime());
                    sendTextMessage("Информация для " + name + ": " + time);
                }
                case "месяц" -> {
                    time = new SimpleDateFormat("MMMM").format(calendar.getTime());
                    sendTextMessage("Информация для " + name + ": " + time);
                }
                case "год" -> {
                    time = new SimpleDateFormat("yyyy").format(calendar.getTime());
                    sendTextMessage("Информация для " + name + ": " + time);
                }
                case "время" -> {
                    time = new SimpleDateFormat("H:mm:ss").format(calendar.getTime());
                    sendTextMessage("Информация для " + name + ": " + time);
                }
                case "час" -> {
                    time = new SimpleDateFormat("H").format(calendar.getTime());
                    sendTextMessage("Информация для " + name + ": " + time);
                }
                case "минуты" -> {
                    time = new SimpleDateFormat("m").format(calendar.getTime());
                    sendTextMessage("Информация для " + name + ": " + time);
                }
                case "секунды" -> {
                    time = new SimpleDateFormat("s").format(calendar.getTime());
                    sendTextMessage("Информация для " + name + ": " + time);
                }
            }

        }
    }

    @Override
    protected SocketThread getSocketThread() {

        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();

    }
}
