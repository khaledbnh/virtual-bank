/*package tn.esprit.vbank.nlp;

import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Lemma {

	public static void main(String[] args) {
		
		StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
		
		String text = "I am trying to learn new technologies.";
		
		CoreDocument coreDocument = new CoreDocument(text);
		
		stanfordCoreNLP.annotate(coreDocument);
		
		List<CoreLabel> coreLabelList = coreDocument.tokens();
		
		for(CoreLabel coreLabel : coreLabelList) {
			String lemma = coreLabel.lemma();
			System.out.println(coreLabel.originalText() + " - "+ lemma);
		}
	}
}
*/