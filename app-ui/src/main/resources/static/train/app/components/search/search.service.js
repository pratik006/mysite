System.register(['@angular/core', '@angular/http', 'rxjs/add/observable/throw', 'rxjs/Observable', './search-response'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, Observable_1, search_response_1, http_2;
    var SearchService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
                http_2 = http_1_1;
            },
            function (_1) {},
            function (Observable_1_1) {
                Observable_1 = Observable_1_1;
            },
            function (search_response_1_1) {
                search_response_1 = search_response_1_1;
            }],
        execute: function() {
            SearchService = (function () {
                function SearchService(http) {
                    this.http = http;
                    this.url = 'http://localhost:8080/rest/train/search'; // URL to JSON file
                }
                SearchService.prototype.search = function (request) {
                    var response = new search_response_1.SearchResponse(null);
                    var body = JSON.stringify(request);
                    var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
                    var options = new http_1.RequestOptions({ headers: headers });
                    return this.http.post(this.url, body, options)
                        .map(this.extractData);
                    //return new SearchResponse([new Train(100, 'Nanda Devi')]);
                };
                SearchService.prototype.extractData = function (res) {
                    var body = res.json();
                    console.log(body);
                    return body || {};
                };
                SearchService.prototype.handleError = function (error) {
                    // In a real world app, we might use a remote logging infrastructure
                    // We'd also dig deeper into the error to get a better message
                    var errMsg = (error.message) ? error.message :
                        error.status ? error.status + " - " + error.statusText : 'Server error';
                    console.error(errMsg); // log to console instead
                    return Observable_1.Observable.throw(errMsg);
                };
                SearchService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_2.Http])
                ], SearchService);
                return SearchService;
            }());
            exports_1("SearchService", SearchService);
        }
    }
});
//# sourceMappingURL=search.service.js.map