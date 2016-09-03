System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var SearchRequest;
    return {
        setters:[],
        execute: function() {
            SearchRequest = (function () {
                function SearchRequest(from, to) {
                    this.from = from;
                    this.to = to;
                    this.filters = {};
                }
                return SearchRequest;
            }());
            exports_1("SearchRequest", SearchRequest);
        }
    }
});
//# sourceMappingURL=search-request.js.map