package tn.esprit.vbank.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import tn.esprit.vbank.enums.Type;

@RestController
@RequestMapping(value = "/api/v1")
public class NERRestController {
	
	@Autowired
	private StanfordCoreNLP stanfordCoreNLP;
	
	@PostMapping
	@RequestMapping(value = "/ner")
	public Set<String> ner(@RequestBody final String input, @RequestParam final Type type){
		
		CoreDocument coreDocument = new CoreDocument(input);
		stanfordCoreNLP.annotate(coreDocument);
		List<CoreLabel> coreLabels = coreDocument.tokens();
		return new HashSet<>(collectList(coreLabels, type));
	}
	
	private List<String> collectList(List<CoreLabel> coreLabels, final Type type) {
		return coreLabels
			.stream()
			.filter(CoreLabel -> type.getName().equalsIgnoreCase(CoreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
			.map(CoreLabel::originalText)
			.collect(Collectors.toList());
	}

}
