package npc.martin.cligoogletrans;

public class HandleSingleTarget extends TranslationPrerequisites implements TranslationPrerequisites.SingeTargetRequest {

    @Override
    public void makeRequest(String ph, String tar) {
        System.out.println("Phrase: " + ph + "Taget: " + tar);
    }

    @Override
    public void destructureJson() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
