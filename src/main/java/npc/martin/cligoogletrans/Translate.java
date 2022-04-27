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
    @Option(names = { "-p", "--phrase" }, paramLabel = "PHRASE", description = "Phrase/ word to be translated.")
    String phrase;
    
    //option-parameter- target language
    //cannot be used with the '-lcs' flag
    @Option(names = { "-lc", "--language-code" }, paramLabel = "LANGUAGE CODE", 
            description = "Language being translated to.")
    String langCode;
    
    //option- activate interractive mode
    @Option(names = { "-i", "--interractive" }, description = "Run this utility in interractive mode.")
    protected Boolean interractive = false;
    
    //option-parameter- allow for targetting !> 5 but languages at once
    //cannot be used with -lc flag
    @Option(names = { "-lcs", "--language-codes" }, paramLabel = "MULTIPLE LANGUAGE CODES", arity = "1...5",
            description = "Choose up to five languages to translate into.")
    ArrayList<String> langCodes = new ArrayList(5);
    
    //the next four variables will be used to calculate how long it took a request-display cycle to run
    long start, end, executionTime; double timeInSeconds;
    
    @Override
    public void run() {
        //code to handle the options logic and options errors
        //1. The -i flag cannot be used with any other flag
        if(this.interractive == true) {
            if(this.phrase != null || this.langCode != null || !langCodes.isEmpty()) {
                System.out.println("Error! The -i option cannot be used with any other option. Use -h for help.");
            } else {
                new InterractiveHandler().takeInput();
            }
            
        //2. In all other cases(i.e when the -i flag is not used), the -p flag must be used.
        //3. When the -p flag is used, either the -lc or the -lcs flag should be used, but not both at once.
        //Remember that the -p flag calls on the class that handles single-target translation
        } else if(this.interractive == false) {
            if(this.phrase == null) {
                System.out.println("Fatal! The -p flag must be used when -i is not used. Use -h for help.");
            } else if(this.phrase != null && (this.langCode == null && langCodes.isEmpty())) {
                System.out.println("Error! The -p flag must be used with either -lc or -lcs flag. Use -h for help.");
            } else if(this.phrase != null && (this.langCode != null && !langCodes.isEmpty())) {
                System.out.println("Error! The -p flag cannot be used with -lc and -lcs flags together. Use -h for help.");
            } else if(this.phrase != null) {
                //4. When the -lcs flag is used, it must have not more than 5 parameters and not less than 1.
                if(!langCodes.isEmpty()) {
                    if(langCodes.size() > 5) {
                        System.out.println("Error. Provided too many arguments for the -lcs option. Use -h for help.");
                    } else if(langCodes.size() < 1) {
                        System.out.println("Error! Must provide at least one argument for the -lcs option.");
                    } else {
                        //first create the language-language_code pairs then call the method responsible for
                        //making the multiple requests
                        //we will also take note of the time(seconds) it takes the codes to run
                        start = System.nanoTime();
                        TranslationPrerequisites.createPairs();
                        new HandleMultiTargets().makeMutipleRequests(phrase, langCodes);
                        end = System.nanoTime();
                        timeInSeconds = (long) ((end - start) / 1000000000);
                        System.out.println("Took " + timeInSeconds + " seconds.");
                    }
                } else if(langCodes.isEmpty()) {
                    if(this.langCode != null) {
                        //first create the language-language_code pairs then call the method responsible for
                        //making the single request
                        //we will also take note of the time(seconds) it takes the codes to run
                        start = System.nanoTime();
                        TranslationPrerequisites.createPairs();
                        new HandleSingleTarget().makeRequest(phrase, langCode);
                        end = System.nanoTime();
                        timeInSeconds = (long) ((end - start) / 1000000000);
                        System.out.println("Took " + timeInSeconds + " seconds.");
                    }
                }
            }
        }    
    }
    
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Translate()).execute(args);
        System.exit(exitCode);
        
        //new CommandLine(new Translate()).execute("-p", "Hello World!", "-lc", "en");
    }
}
