package npc.martin.cligoogletrans;

import java.util.Iterator;
import java.util.List;

public class HandleMultiTargets extends TranslationPrerequisites implements TranslationPrerequisites.MultiTargetRequest {

    @Override
    public void makeMutipleRequests(String ph, List<String> lc) {
        System.out.print("Phrase: " + ph + "\n");
        
        Iterator it = lc.iterator();
        
        while(it.hasNext()) {
            System.out.println("Code: " + it.next());
        }
    }

    @Override
    public void destructureJson() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
