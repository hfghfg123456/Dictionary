package Main;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public  class DictionaryManagement {

    public static List<String> addup = new ArrayList();

    public static  String dictionaryLookup(String wordToLookup){
        for(int i=0;i<Dictionary.tudien.size();i++) {

            if (Dictionary.tudien.get(i).word_target.contains(wordToLookup)) {
                return Dictionary.tudien.get(i).word_explain;
            }
        }

        return "";
    }

    public static void  removeWordFromDitionary(String id)
    {

        for(int i=Dictionary.tudien.size()-1;i>=0;i--) {
            Word word = Dictionary.tudien.get(i);
            String removing=word.word_target;
            if (removing.equals(id)) {
                Dictionary.tudien.remove(i);
            }
        }
    }
    public static void InsertFromFile() throws IOException {
        Scanner sc = null;
        try {

            sc = new Scanner(new File("dictionaries.txt"));

            while (sc.hasNext()) {
                String word = sc.next();
                String word_mean = sc.nextLine();
                Dictionary.tudien.add(new Word(word, word_mean));
            }
            for (int i = 0; i < Dictionary.tudien.size(); i++) {
                Dictionary.listTarget.add(Dictionary.tudien.get(i).word_target);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void dictionaryExportToFile()
    {
        try{
            FileWriter write=new FileWriter("dictionaries.txt");
            Writer output=new BufferedWriter(write);
            for(int i=0;i<Dictionary.tudien.size();i++ )
            {
                Word outfile=Dictionary.tudien.get(i);
                output.write(outfile.word_target+"\t"+outfile.word_explain+"\n");
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> dictionarySearcher(String wordSearch) {
        for(int i = 0; i < Dictionary.tudien.size(); ++i) {
            if (((Word)Dictionary.tudien.get(i)).word_target.toLowerCase().contains(wordSearch.toLowerCase())) {
                addup.add(((Word)Dictionary.tudien.get(i)).word_target);
            }
        }

        return addup;
    }
}