public class Chat {
    public Chat() {
        chatHistory = new String[100];
        chatCount = 0;
    }

    public void addChat(String chat) {
        chatHistory[chatCount++] = chat;
    }

//    public String getChat(int index) {
//        return chatHistory[index];
//    }
//
//    public int getChatSize() {
//        return chatCount;
//    }

    public String getAllChats() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < chatCount; i++) {
            ret.append((i + 1)).append(". ").append(chatHistory[i]);
            if (i != chatCount - 1){
                ret.append("\n");
            }
        }
        return ret.toString();
    }

    private int chatCount;
    private static String[] chatHistory ;
}
