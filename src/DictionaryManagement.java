import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
            Dictionary.tudien.add(new_word);
        } while (!Objects.equals(Eng_word, ""));
    }

    public void insertFromFile() {
        try {
            Path paths = Paths.get("dictionaries.txt");
            List<String> read = Files.readAllLines(paths);
            for (String wordData : read) {
                String[] data = wordData.split("\t");
                Word newWord = new Word();
                newWord.setWord_target(data[0]);
                newWord.setWord_explain(data[1]);
                Dictionary.tudien.add(newWord);
            }
        } catch (IOException e) {}
    }

    public void addNewWord() {
        Scanner scanner = new Scanner(System.in);
        Word newWord = new Word();
        System.out.println("Input English word");
        String word_target = scanner.nextLine();
        newWord.setWord_target(word_target);
        System.out.println("Input Vietnamese mean of word");
        String word_explain = scanner.nextLine();
        newWord.setWord_explain(word_explain);
        Dictionary.tudien.add(newWord);
        System.out.println("Add done!");
    }

    public void modifyWord() {
        System.out.println("Input modified word");
        Scanner scanner = new Scanner(System.in);
        String wordNeedModify = scanner.nextLine();
        for (int i = 0; i < Dictionary.tudien.size(); i++) {
            if (wordNeedModify.equals(Dictionary.tudien.get(i).word_target)) {
                System.out.println("modify word " + "\"" + wordNeedModify + "\"");
                System.out.println("Input new word target and new word explain");
                String newWT = scanner.nextLine();
                String newWE = scanner.nextLine();
                Dictionary.tudien.get(i).setWord_target(newWT);
                Dictionary.tudien.get(i).setWord_explain(newWE);
            }
        }
        System.out.println("Modify done!");
    }

    public void removeWord() {
        System.out.println("Input removed word");
        Scanner scanner = new Scanner(System.in);
        String remWord = scanner.nextLine();
        for (int i = 0; i < Dictionary.tudien.size(); i++) {
            if (remWord.equals(Dictionary.tudien.get(i).word_target)) {
                Dictionary.tudien.remove(i);
            }
        }
        System.out.println("Remove done!");
    }

    public void dictionaryLookUp() {
        Scanner scanner = new Scanner(System.in);
        boolean check_exist;
        String enter;
        String find;
        System.out.println("Do you want look up a word? (Y/N)");
        enter = scanner.nextLine();
        if (enter.equals("y") || enter.equals("Y")) {
            do {
                find = scanner.nextLine();
                check_exist = false;
                for (int i = 0; i < Dictionary.tudien.size(); i++) {
                    if (Objects.equals(find, Dictionary.tudien.get(i).word_target) ||
                            Objects.equals(find, Dictionary.tudien.get(i).word_explain)) {
                        System.out.format("%-20s %-20s \n",
                                Dictionary.tudien.get(i).word_target,
                                Dictionary.tudien.get(i).word_explain
                        );
                        check_exist = true;
                    }
                }
                if (!check_exist && !find.equals("")) {
                    System.out.println("Oops! This Dictionary doesn't have this word.");
                }
            } while (!find.equals(""));
        }
    }

    public void dictionaryExportToFile() {
        try {

            FileOutputStream fos = new FileOutputStream("dictionaries.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            for (int i = 0; i < Dictionary.tudien.size(); i++) {
                dos.writeBytes(Dictionary.tudien.get(i).word_target);
                dos.writeBytes("\t");
                dos.writeBytes(Dictionary.tudien.get(i).word_explain);
                dos.writeBytes("\n");
            }
            fos.close();
            dos.close();
            System.out.println("Export done!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


