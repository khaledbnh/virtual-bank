package tn.esprit.vbank.nlp;

import java.util.List;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class SentimentAnalysis {

	public static void main(String[] args) {
		
		StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
		
		String text = "Hello this is Chiheb. I really don't like this place. I love this place";
		
		CoreDocument coreDocument = new CoreDocument(text);
		
		stanfordCoreNLP.annotate(coreDocument);
		
		List<CoreSentence> sentences = coreDocument.sentences();
		
		for(CoreSentence sentence : sentences) {
			String sentiment = sentence.sentiment();
			
			System.out.println(sentiment + "\t" + sentence);
		}
	}
	public static List<CoreSentence> TestM(String text) {
	StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
		
	//	String text = "Hello this is Chiheb. I really don't like this place. I love this place";
		
		CoreDocument coreDocument = new CoreDocument(text);
		
		stanfordCoreNLP.annotate(coreDocument);
		
		return coreDocument.sentences();
	}
}
