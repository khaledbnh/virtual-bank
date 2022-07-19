package tn.esprit.vbank.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import tn.esprit.vbank.enums.TypeFeeling;

@RestController
@RequestMapping(value = "/api/v2")
public class SentimentAnalysisRestController {

	@Autowired
	private StanfordCoreNLP stanfordCoreNLP;

	@PostMapping
	@RequestMapping(value = "/sentimentAnalysis")
	public Set<String> sentimentAnalysis(@RequestBody final String input, @RequestParam final TypeFeeling type) {
		// System.out.println(type.toString());
		CoreDocument coreDocument = new CoreDocument(input);
		stanfordCoreNLP.annotate(coreDocument);
		List<CoreSentence> sentences = coreDocument.sentences();

		sentences = tn.esprit.vbank.nlp.SentimentAnalysis.TestM(input);
		List<CoreSentence> Tested = checkSentiment(sentences, type);
		Set<String> theResult = new HashSet<String>();
		for (CoreSentence s : Tested) {
			theResult.add(s.toString());
		}

		return theResult;
	}

	private List<CoreSentence> checkSentiment(List<CoreSentence> sent, TypeFeeling feel) {
		List<CoreSentence> newList = new ArrayList<CoreSentence>();
		for (CoreSentence sentence : sent) {
			// System.out.print(sentence.sentiment().equals(feel.toString()));
			if (sentence.sentiment().equals(feel.toString())) {

				newList.add(sentence);
			}

			// System.out.println(sentiment + "\t" + sentence);
		}

		return newList;

	}
//	private List<String> collectList(List<CoreSentence> sentences, final TypeFeeling type) {
//		return 
//				
	// cntr+shift+/

}
