package npc.martin.cligoogletrans;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "ggtranslate", mixinStandardHelpOptions = true, version = "Translate 1.0",
        description = "Starts this Google translate utility to help you translate words/ phrases")
public class Translate implements Runnable {
    //option-parameter- phrase/ word to be translated
    @Option(names = { "-p" }, paramLabel = "PHRASE", description = "Phrase/ word to be translated.")
    String phrase;
    
    //option-parameter- target language
    @Option(names = { "-t" }, paramLabel = "TARGET LANGUAGE", description = "Language being translated to.")
    String target;
    
    //option- activate interractive mode
    @Option(names = { "-i, --interractive" }, description = "Run this utility in interractive mode.")
    Boolean interractive;
    
    //option- allow for logging of current translation
    @Option(names = { "-l, --log" }, description = "Allow for logging of a translation")
    Boolean logTranslation;
    
    //option-parameter- allow for targetting !> 5 languages at once
    @Option(names = "-m", paramLabel = "MULTIPLE LANGUAGES", arity = "1...5",
            description = "Choose up to five languages to translate into.")
    String multiple;
    
    @Override
    public void run() {
        System.out.print("Phrase: " + phrase + "Target: " + target);
    }
    
    public static void main(String[] args) {
        new CommandLine(new Translate()).execute("-p", "Hello World", "-t", "ru");
    }
}
