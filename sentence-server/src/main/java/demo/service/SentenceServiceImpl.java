package demo.service;

import demo.domain.Sentence;
import demo.domain.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Build a sentence by assembling randomly generated subjects, verbs, 
 * articles, adjectives, and nouns.  The individual parts of speech will 
 * be obtained by calling the various DAOs.
 */
@Service
public class SentenceServiceImpl implements SentenceService {

	@Autowired WordService wordService;

	/**
	 * Assemble a sentence by gathering random words of each part of speech:
	 */
	public String buildSentence() {
		Sentence sentence = new Sentence();

		List<Observable<Word>> observables = Arrays.asList(wordService.getSubject(),
				wordService.getVerb(),
				wordService.getArticle(),
				wordService.getAdjective(),
				wordService.getNoun());

		CountDownLatch latch = new CountDownLatch(observables.size());

		Observable.merge(observables).subscribe((word) -> {
			sentence.addWord(word);
			latch.countDown();
		});

		try {
			latch.await(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return sentence.toString();
	}
}
