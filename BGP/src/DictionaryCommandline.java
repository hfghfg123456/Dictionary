import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {

    public void showAllWord() {
        System.out.println("---------------------------------------");
        System.out.format("%-5s %-20s %-20s \n",
                "No",
                "English",
                "Vietnamese");
        System.out.println("---------------------------------------");
        int n = dictionaries.size();
        for (int i = 0; i < n; i++) {
            System.out.format("%-5s %-20s %-20s \n",
                    i + 1,
                    dictionaries.get(i).getWord_target(),
                    dictionaries.get(i).getWord_explain());
        }
    }

    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWord();
    }

    public void dictionaryAdvanced() {
        insertFromFile();
        showAllWord();
        dictionaryLookup();
    }

    public void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);
        String findWord = scanner.nextLine();
        System.out.println("List word starts with " + "\"" + findWord + "\"");
        System.out.println("---------------------------------------");
        System.out.format("%-5s %-20s %-20s \n",
                "No",
                "English",
                "Vietnamese");
        System.out.println("---------------------------------------");
        int num = 1;
        for (int i = 0; i < dictionaries.size(); i++) {
            if (dictionaries.get(i).getWord_target().startsWith(findWord)) {
                System.out.format("%-5s %-20s %-20s \n",
                        num,
                        dictionaries.get(i).getWord_target(),
                        dictionaries.get(i).getWord_explain());
                num++;
            }
        }
    }
}


