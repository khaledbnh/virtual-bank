/*package tn.esprit.vbank.nlp;

import java.util.List;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class SentenceRecognizer {

	public static void main(String[] args) {
		
		StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
		
		String text = "Hey! I am Chiheb. I am a student.";
		
		CoreDocument coreDocument = new CoreDocument(text);
		stanfordCoreNLP.annotate(coreDocument);
		List<CoreSentence> sentences = coreDocument.sentences(); 
		
		for(CoreSentence sentence : sentences) {
			System.out.println(sentence.toString());
		}
	}
}*/
