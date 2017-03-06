package demo.service;

import demo.domain.Word;
import rx.Observable;

public interface IWordService {
	Word getSubject();
	Word getVerb();
	Word getArticle();
	Word getAdjective();
	Word getNoun();
}
