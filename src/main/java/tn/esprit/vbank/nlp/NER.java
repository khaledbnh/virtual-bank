/*package tn.esprit.vbank.nlp;

import java.util.List;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class NER {
	public static void main(String[] args) {
		
		StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
		
		String text = "Hey! My name is Chiheb and i have friend his name is Ahmed ." + "We both are living in Tunisia";
		
		CoreDocument coreDocument = new CoreDocument(text);
		
		stanfordCoreNLP.annotate(coreDocument);
		
		List<CoreLabel> coreLabels = coreDocument.tokens();
		
		for(CoreLabel coreLabel : coreLabels) {
			String ner = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
			
			System.out.println(coreLabel.originalText() + " = "+ ner);
		}
		
	}
}
*/