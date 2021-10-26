package Main;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public  class DictionaryManagement {

    public static List<String> add_up = new ArrayList();
    public static List<String> removeout = new ArrayList<>();

    public static  String dictionaryLookup(String wordToLookup){
        for(int i=0;i<Dictionary.tudien.size();i++) {

            if (Dictionary.tudien.get(i).word_target.contains(wordToLookup)) {
                return Dictionary.tudien.get(i).word_explain;
            }
        }
        return "";
    }
    public static List<String> addNewWord(String addword_target,String addword_explain)
    {
        Word newWord=new Word(addword_target,addword_explain);
        Dictionary.tudien.add(newWord);
        add_up.add(newWord.word_target);
        return add_up;
    }
    public static List<String> removeWordFromDitionary(String id)
    {

        for(int i=Dictionary.tudien.size()-1;i>=0;i--) {
            Word word = Dictionary.tudien.get(i);
            String removing=word.word_target;
            if (removing.toLowerCase().equals(id.toLowerCase())) {
                Dictionary.tudien.remove(i);
            }
            else
            {
                removeout.add(Dictionary.tudien.get(i).word_target);
            }
        }
        return removeout;
    }
    public static void InsertFromFile() throws IOException {
        try {
            Path paths = Paths.get("dictionaries.txt");
            List<String> read = Files.readAllLines(paths);
            for (String wordData : read) {
                String[] data = wordData.split("\t");
                Word newWord = new Word(data[0], data[1]);
                Dictionary.tudien.add(newWord);
                Dictionary.listTarget.add(newWord.word_target);
            }
        } catch (IOException ignored) {}
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
                add_up.add(((Word)Dictionary.tudien.get(i)).word_target);
            }
        }

        return add_up;
    }
    public static String modified(String change_target,String change_explain)//hàm chỉnh sửa nghĩa từ//
    {
        for(int i=0;i<Dictionary.tudien.size();i++)
        {
            if(Dictionary.tudien.get(i).word_target.toLowerCase().equals(change_target.toLowerCase()))
            {
                Word changeWord = new Word(change_target, change_explain);
                Dictionary.tudien.set(i,changeWord);
            }
        }
        dictionaryExportToFile();
        return change_explain;
    }
}