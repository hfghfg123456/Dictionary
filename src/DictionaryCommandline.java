import java.util.Objects;
import java.util.Scanner;

public class DictionaryCommandline {
    public void showAllWords() {
        System.out.println("---------------------------------------");
        System.out.format("%-5s %-20s %-20s \n",
                "No",
                "English",
                "Vietnamese");
        System.out.println("---------------------------------------");
        for (int i = 0; i < Dictionary.tudien.size(); i++) {
            if (!Objects.equals(Dictionary.tudien.get(i).word_target, "")) {
                System.out.format("%-5s %-20s %-20s \n",
                        i + 1,
                        Dictionary.tudien.get(i).word_target,
                        Dictionary.tudien.get(i).word_explain);
            }
        }
    }
    public void dictionaryBasic() {
        DictionaryManagement Manage = new DictionaryManagement();
        Manage.insertFromCommandline();
        showAllWords();
    }

    public void DictionaryAdvanced() {
        DictionaryManagement Manage = new DictionaryManagement();
        Manage.insertFromFile();
        showAllWords();
        Manage.dictionaryLookUp();
    }

    public void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);
        String findWord = scanner.nextLine();
        System.out.println("List word starts with " + "\"" + findWord + "\"");
        for (int i = 0; i < Dictionary.tudien.size(); i++) {
            if (Dictionary.tudien.get(i).word_target.startsWith(findWord)) {
                System.out.format("%-20s %-20s \n",
                        Dictionary.tudien.get(i).word_target,
                        Dictionary.tudien.get(i).word_explain);
            }
        }
    }
}
