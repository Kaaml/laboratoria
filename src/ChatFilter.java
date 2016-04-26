/**
 * Created by kaaml on 25.04.16.
 */
public class ChatFilter {
    String[] tokens;
    String[] prohibitedWords = { "dupa", "kupa", "donald", "tusk" };    //more in future

    public ChatFilter( String msg ){
        tokens = msg.split( " " );

        for( int i = 0; i < tokens.length; i++ ) {
            for (String pw : prohibitedWords) {
                if (tokens[i].compareToIgnoreCase(pw) == 0) {
                    tokens[i].replaceAll("[A-Za-z]", "*");
                }
            }
        }
    }

    public String getCensoredMessage(){
        String out = new String();
        for( String s : tokens ){
            out += s + " ";
        }
        return out;
    }

}
