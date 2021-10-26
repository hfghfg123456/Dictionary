import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DictionaryManagement extends Dictionary {

    public void insertFromCommandline() {
        String word_target;
        String word_explain;
        Scanner scanner = new Scanner(System.in);
        word_target = scanner.nextLine();
        word_explain = scanner.nextLine();
        Word newWord = new Word(word_target, word_explain);
        dictionaries.add(newWord);
    }

    public void insertFromFile() {
        try {
            Path paths = Paths.get("dictionaries.txt");
            List<String> read = Files.readAllLines(paths);
            for (String wordData : read) {
                String[] data = wordData.split("\t");
                Word newWord = new Word(data[0], data[1]);
                dictionaries.add(newWord);
            }
        } catch (IOException e) {}
    }

    public void dictionaryLookup() {
        String wordFind;
        System.out.println("-------------------------");
        System.out.println("input /end to end look up");
        do {
            Scanner scanner = new Scanner(System.in);
            boolean exist = false;
            wordFind = scanner.nextLine();
            for (Word s : dictionaries) {
                if (s.getWord_target().equals(wordFind)) {
                    System.out.println("Vietnamese: " + s.getWord_explain());
                    exist = true;
                    break;

                }
                if (s.getWord_explain().equals(wordFind)) {
                    System.out.println("English: " + s.getWord_target());
                    exist = true;
                    break;
                }
            }
            if (!exist && !wordFind.equals("/end")) {
                System.out.println("this dictionary don' have this word");
            }
        } while (!wordFind.equals("/end"));
            System.out.println("-------------------------");
    }

    public void addNewWord() {
        System.out.println("-------------------------");
        System.out.println("Input new word");
        String word_target;
        String word_explain;
        Scanner scanner = new Scanner(System.in);
        word_target = scanner.nextLine();
        word_explain = scanner.nextLine();
        Word newWord = new Word(word_target, word_explain);
        dictionaries.add(newWord);
        try {
            PrintWriter writer = new PrintWriter("dictionaries.txt", "UTF-8");
            for (Word w: dictionaries) {
                writer.println(w.getWord_target() + "\t" + w.getWord_explain());
            }
            writer.close();
            System.out.println("Add done!");
            System.out.println("-------------------------");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void removeWord() {
        System.out.println("-------------------------");
        System.out.println("Input remove word");
        String word_target;
        String word_explain;
        Scanner scanner = new Scanner(System.in);
        word_target = scanner.nextLine();
        word_explain = scanner.nextLine();
        dictionaries.removeIf(s -> s.getWord_target().equals(word_target));
        dictionaries.removeIf(s -> s.getWord_explain().equals(word_explain));
        try {
            PrintWriter writer = new PrintWriter("exportFile.txt", "UTF-8");
            for (Word w: dictionaries) {
                writer.println(w.getWord_target() + "\t" + w.getWord_explain());
            }
            writer.close();
            System.out.println("Remove done!");
            System.out.println("-------------------------");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void modifyWord() {
        System.out.println("-------------------------");
        System.out.println("Input word need modified");
        String wordNeedModified;
        Scanner scanner = new Scanner(System.in);
        wordNeedModified = scanner.nextLine();
        System.out.println("Input new word and new mean");
        String new_word_target;
        String new_word_explain;
        new_word_target = scanner.nextLine();
        new_word_explain = scanner.nextLine();
        for (Word w : dictionaries) {
            if (w.getWord_target().equals(wordNeedModified) || w.getWord_explain().equals(wordNeedModified)) {
                dictionaries.get(dictionaries.indexOf(w)).setWord_target(new_word_target);
                dictionaries.get(dictionaries.indexOf(w)).setWord_explain(new_word_explain);
            }
        }
        try {
            PrintWriter writer = new PrintWriter("dictionaries.txt", "UTF-8");
            for (Word w: dictionaries) {
                writer.println(w.getWord_target() + "\t" + w.getWord_explain());
            }
            writer.close();
            System.out.println("Modify done!");
            System.out.println("-------------------------");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void exportToFile() {
        try {
            PrintWriter writer = new PrintWriter("exportFile.txt", "UTF-8");
            for (Word w: dictionaries) {
                writer.println(w.getWord_target() + "\t" + w.getWord_explain());
            }
            writer.close();
            System.out.println("Export done!");
            System.out.println("-------------------------");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

