public class DictionaryCommandline {
    public void showAllWords() {
        System.out.println("------------------------------");
        String english = String.format("%-20s", "English");
        String vietnamese = "Vietnamese";
        System.out.print(english);
        System.out.println(vietnamese);
        System.out.println("------------------------------");
        for (int i = 0; i < Dictionary.tudien.length - 1; i++) {
            String word = String.format("%-20s", Dictionary.tudien[i].word_target);
            System.out.print(word);
            System.out.println(Dictionary.tudien[i].word_explain);
        }
    }

    public void dictionaryBasic() {
        DictionaryManagement Manage = new DictionaryManagement();
        Manage.insertFromCommandline();
        showAllWords();
    }
}
