package npc.martin.cligoogletrans;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ArrayList;

@Command(name = "ggtranslate", mixinStandardHelpOptions = true, version = "Translate 1.0",
        description = "Starts this Google translate utility to help you translate words/ phrases")
public class Translate implements Runnable {
    //option-parameter- phrase/ word to be translated
    //every command must have a phrase
    @Option(names = { "-p" }, paramLabel = "PHRASE", description = "Phrase/ word to be translated.")
    String phrase;
    
    //option-parameter- target language
    @Option(names = { "-lc", "--language-code" }, paramLabel = "LANGUAGE CODE", 
            description = "Language being translated to.")
    String langCode;
    
    //option- activate interractive mode
    @Option(names = { "-i", "--interractive" }, description = "Run this utility in interractive mode.")
    private Boolean interractive;
    
    //option- allow for logging of current translation
    @Option(names = { "-l", "--log" }, description = "Allow for logging of a translation")
    Boolean logTranslation;
    
    //option-parameter- allow for targetting !> 5 but languages at once
    //cannot be used with -lc flag
    @Option(names = { "-lcs", "--language-codes" }, paramLabel = "MULTIPLE LANGUAGE CODES", arity = "1...5",
            description = "Choose up to five languages to translate into.")
    ArrayList<String> langCodes = new ArrayList(5);
    
    @Override
    public void run() {
        //always populate the hashmap on the go first before executing rest of code
        TranslationProperties.createPairs();
        
        if(this.interractive == true) {
            new InterractiveHandler().takeInput();
        }
    }
    
    public static void main(String[] args) {
        new CommandLine(new Translate()).execute("-p", "Hello World!", "-lc", "es");
    }
}
