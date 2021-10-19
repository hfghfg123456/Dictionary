package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class Controller extends Dictionary implements Initializable {



    @FXML
    TextArea textArea ;
    @FXML
    TextField searchField,addTarget,addExplain;
    @FXML
    Button voice, delete, add;

    @FXML
    ListView listView = new ListView();
    public void clicked (MouseEvent e){
        searchField.setText(listView.getSelectionModel().getSelectedItem().toString());
        textArea.setText(DictionaryManagement.dictionaryLookup(listView.getSelectionModel().getSelectedItem().toString()));
    }
    public void deleteword(ActionEvent event) {
        textArea.setText(DictionaryManagement.dictionaryLookup(listView.getSelectionModel().getSelectedItem().toString()));
        String del=listView.getSelectionModel().getSelectedItem().toString();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText(null);
            alert.setContentText('"'+del+'"'+" đã bị xóa!");
            alert.showAndWait();
            List<String> pull_out= DictionaryManagement.removeWordFromDitionary(del);
            DictionaryManagement.dictionaryExportToFile();
            ObservableList <String> data = FXCollections.observableArrayList(pull_out);
            listView.setItems(data);
            pull_out.clear();
    }
    public void add_word(ActionEvent event)
    {
            String engrisk=addTarget.getText();
            String meaning=addExplain.getText();
            if(engrisk.length()>0 &&meaning.length()>0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add");
                alert.setHeaderText(null);
                alert.setContentText('"' + engrisk + '"' + " đã được thêm vào!");
                alert.showAndWait();
                List<String> newWord = DictionaryManagement.addmoreword(engrisk, meaning);
                DictionaryManagement.dictionaryExportToFile();
                ObservableList<String> data = FXCollections.observableArrayList(newWord);
                listView.setItems(data);
                newWord.clear();
                addExplain.clear();
                addTarget.clear();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
    }
    public void inputsearch(KeyEvent event)
    {
        String se=searchField.getText().toString();
        List<String> s= DictionaryManagement.dictionarySearcher(se);
        Collections.sort(s);
        ObservableList<String> input = FXCollections.observableArrayList(s);
        listView.setItems(input);
        DictionaryManagement.add_up.clear();

    }
    public void voice(ActionEvent event)
    {
        try
        {
            System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            Synthesizer syn=Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            syn.allocate();
            syn.resume();
            syn.speakPlainText(listView.getSelectionModel().getSelectedItem().toString(),null);
            syn.waitEngineState(Synthesizer.QUEUE_EMPTY);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void modified(KeyEvent event)
    {
            textArea.setEditable(true);
            if(event.getCode()==KeyCode.ENTER) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("Đã thay đổi");
                alert.showAndWait();
                alert.close();
                String modding=textArea.getText();
                String point=searchField.getText();
                String s=DictionaryManagement.modified(point, modding);
                textArea.setText(s);
                textArea.setEditable(false);
            }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            DictionaryManagement.InsertFromFile();
        } catch (IOException e){
            e.printStackTrace();
        }
        Collections.sort(listTarget);
        ObservableList <String> data = FXCollections.observableArrayList(listTarget);
        listView.setItems(data);
    }

}