import java.util.*;
import java.security.InvalidParameterException;

public class Sub {
    
    public static void main(String[] args) {
        System.out.println((new Sub()).replace_func(args[0], args[1], args[2]));
    }

    public String replace_func(String pat, String repl, String str) {
        return String.join(repl, str.split("\""+pat+"\""));
    }

    /**
     * Replace occurrences of pat with repl in str using recursion
     */
    public String replace_rec(String pat, String repl, String str) {
        if(pat.length() == 0) throw new InvalidParameterException("Cannot replace occurences of empty string");
        if(str.length()<pat.length()) return str;
        if(str.substring(0, pat.length()).equals(pat)) {
            return repl + replace(pat, repl, str.substring(pat.length()));
        }
        else {
            return replace(pat, repl, str.substring(1));
        }
    }

    /**
     * Replace occurrences of pat with repl in str using iteration
     */
    public String replace(String pat, String repl, String str) {
        for(int i=0; i < str.length() - pat.length(); i++) {
            if(str.substring(i, i + pat.length()).equals(pat)) {
                str = str.substring(0, i) + repl + str.substring(i + pat.length(), str.length());
                i += pat.length() - 1;
            }
        }
        return str;
    }
}
