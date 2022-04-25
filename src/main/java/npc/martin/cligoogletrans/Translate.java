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
    //cannot be used with the '-lcs' flag
    @Option(names = { "-lc", "--language-code" }, paramLabel = "LANGUAGE CODE", 
            description = "Language being translated to.")
    String langCode;
    
    //option- activate interractive mode
    @Option(names = { "-i", "--interractive" }, description = "Run this utility in interractive mode.")
    protected Boolean interractive = false;
    
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
        //code to handle option errors
        //1. When the -p flag is not invoked. It must always be used
        if(phrase == null) {
            System.out.println("Error! Must provide a phrase or word to translate. Use -h for Help.");    
        //2. When the '-lc' and '-lcs' flags are used together
        } else if(langCode != null && langCodes.isEmpty() == false) {
            System.out.println("Fatal! -lc and -lcs cannot be used together. Use -h for Help.");
        //if both of these tests pass, then the code can start running
        } else {
            //populate the hashmap on the go first before executing rest of code
            TranslationPrerequisites.createPairs();

            if(this.interractive == true) {
                new InterractiveHandler().takeInput();
            } else if(langCode != null) {
                //do call the makeRequest() method from the class that Handles Single Requests(target)
                new HandleSingleTarget().makeRequest(phrase, langCode);
            } else if(langCodes.isEmpty() == false) {
                //do call the makeMultipleRequests() method from the class that Handles Multiple Requests(targets)
                new HandleMultiTargets().makeMutipleRequests(phrase, langCodes);
            }
        } 
    }
    
    public static void main(String[] args) {
        new CommandLine(new Translate()).execute(args);
    }
}
