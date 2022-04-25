package npc.martin.cligoogletrans;


import java.util.HashMap;
import kong.unirest.JsonNode;

public abstract class TranslationProperties extends Translate {
    //hash map for the language-code pairs
    protected static HashMap<String, String> languageCodePair = new HashMap<>();
    
    //method to populate the hashmap
    protected static void createPairs() {
        languageCodePair.put("hi", "Hindi");
        languageCodePair.put("es", "Spanish");
        languageCodePair.put("en", "English");
        languageCodePair.put("ar", "Arabic");
        languageCodePair.put("pt", "Portuguese");
        languageCodePair.put("ru", "Russian");
        languageCodePair.put("ja", "Japanesse");
        languageCodePair.put("de", "German");
        languageCodePair.put("pa", "Punjabi");
        languageCodePair.put("jv", "Javanese");
        languageCodePair.put("ko", "Korean");
        languageCodePair.put("vi", "Vietnamese");
        languageCodePair.put("te", "Telugu");
        languageCodePair.put("mr", "Marathi");
        languageCodePair.put("ta", "Tamil");
        languageCodePair.put("ur", "Urdu");
        languageCodePair.put("fr", "French");
        languageCodePair.put("it", "Italian");
        languageCodePair.put("tr", "Turkish");
        languageCodePair.put("fa", "Persian");
        languageCodePair.put("gu", "Gujarati");
        languageCodePair.put("pl", "Polish");
        languageCodePair.put("uk", "Ukranian");
        languageCodePair.put("ml", "Malayalam");
        languageCodePair.put("kn", "Kannada");
        languageCodePair.put("my", "Burmese");
        languageCodePair.put("th", "Thai");
        languageCodePair.put("sw", "Swahili");
        languageCodePair.put("af", "Afrikaans");
        languageCodePair.put("am", "Amharic");
        languageCodePair.put("he", "Hebrew");
        languageCodePair.put("sv", "Swedish");    
    }
    
    //nested interface to handle single-target request
    interface SingeTargetRequest {
        public abstract void makeRequest();
        public abstract void destructureJson();
    }
    
    //nested interface to handle multi-target requests
    interface MultiTargetRequest {
        public abstract void makeMutipleRequests();
        public abstract void destructureJson();
    }
}
