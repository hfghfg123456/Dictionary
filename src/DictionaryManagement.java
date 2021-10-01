import com.sun.org.apache.xpath.internal.objects.XObject;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class DictionaryManagement {
    public void insertFromCommandline() {
        String Eng_word;
        String Vie_word;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("input English word: ");
            Eng_word = sc.nextLine();
            System.out.print("input Vietnamese word: ");
            Vie_word = sc.nextLine();
            Word new_word = new Word();
            new_word.word_target = Eng_word;
            new_word.word_explain = Vie_word;
            Word[] temp = Arrays.copyOf(Dictionary.tudien, Dictionary.tudien.length + 1);
            temp[temp.length - 1] = new_word;
            Dictionary.tudien = temp;
        } while (!Objects.equals(Eng_word, ""));
    }
}
