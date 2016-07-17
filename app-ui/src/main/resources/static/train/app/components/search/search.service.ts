import {SearchResponse} from './search-response';
import {SearchRequest} from './search-request';
import {Train} from './Train';

export class SearchService {
	search(request: SearchRequest): SearchResponse {
		return new SearchResponse([new Train(100, 'Nanda Devi')]);
	}
}