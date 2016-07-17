import {Injectable} from '@angular/core';
import { Headers, RequestOptions, Response } from '@angular/http';
import 'rxjs/add/observable/throw';
import {Observable} from 'rxjs/Observable';

import {SearchResponse} from './search-response';
import {SearchRequest} from './search-request';
import {Train} from './Train';


//import {Injectable} from 'angular2/angular2';
import {Http} from '@angular/http';

@Injectable()
export class SearchService {

	private url = 'http://localhost:8080/rest/train/search'; // URL to JSON file

	constructor (private http: Http) {}

	search(request: SearchRequest): Observable<SearchResponse> {
		let response: SearchResponse = new SearchResponse(null);
		let body = JSON.stringify( request );
    	let headers = new Headers({ 'Content-Type': 'application/json' });
    	let options = new RequestOptions({ headers: headers });
	    return this.http.post(this.url, body, options)
                .map(this.extractData);
		//return new SearchResponse([new Train(100, 'Nanda Devi')]);
	}

	  private extractData(res: Response) {
	    let body = res.json();
	    console.log(body);
	    return body || { };
	  }
	  private handleError (error: any) {
	    // In a real world app, we might use a remote logging infrastructure
	    // We'd also dig deeper into the error to get a better message
	    let errMsg = (error.message) ? error.message :
	      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
	    console.error(errMsg); // log to console instead
	    return Observable.throw(errMsg);
	  }
}