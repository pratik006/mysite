System.register(['@angular/core', '@angular/forms', './search-request', './search.service', '@angular2-material/card', '@angular2-material/input', '@angular2-material/button', '@angular2-material/checkbox', '@angular2-material/progress-circle'], function(exports_1, context_1) {
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
    var core_1, forms_1, forms_2, search_request_1, search_service_1, card_1, input_1, button_1, checkbox_1, progress_circle_1;
    var SearchComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
                forms_2 = forms_1_1;
            },
            function (search_request_1_1) {
                search_request_1 = search_request_1_1;
            },
            function (search_service_1_1) {
                search_service_1 = search_service_1_1;
            },
            function (card_1_1) {
                card_1 = card_1_1;
            },
            function (input_1_1) {
                input_1 = input_1_1;
            },
            function (button_1_1) {
                button_1 = button_1_1;
            },
            function (checkbox_1_1) {
                checkbox_1 = checkbox_1_1;
            },
            function (progress_circle_1_1) {
                progress_circle_1 = progress_circle_1_1;
            }],
        execute: function() {
            SearchComponent = (function () {
                function SearchComponent(fb, service) {
                    this.searchRequest = new search_request_1.SearchRequest('Sec', 'Hwh');
                    this.service = service;
                    this.fb = fb;
                    this.from = new forms_1.FormControl("", forms_2.Validators.required);
                    this.to = new forms_1.FormControl("", forms_2.Validators.required);
                    this.suburbanTrains = new forms_1.FormControl("", forms_2.Validators.required);
                    this.passengerTrains = new forms_1.FormControl("", forms_2.Validators.required);
                    this.searchForm = fb.group({
                        from: this.from,
                        to: this.to,
                        suburbanTrains: this.suburbanTrains,
                        passengerTrains: this.passengerTrains
                    });
                }
                SearchComponent.prototype.ngOnInit = function () {
                    console.log('ngOnInit() called');
                };
                SearchComponent.prototype.onSubmit = function () {
                    var _this = this;
                    this.searchRequest = new search_request_1.SearchRequest(this.from.value, this.to.value);
                    this.searchRequest.filters["suburbanTrains"] = this.suburbanTrains.value;
                    this.searchRequest.filters["passengerTrains"] = this.passengerTrains.value;
                    console.log("submited.." + this.from.value + ' - ' + this.to.value + ' ' + this.suburbanTrains.value + ' ' + this.passengerTrains.value);
                    console.log(this.diagnostic);
                    this.service.search(this.searchRequest).subscribe(function (response) { _this.searchReponse = response; });
                };
                Object.defineProperty(SearchComponent.prototype, "diagnostic", {
                    get: function () { return JSON.stringify(this.searchRequest); },
                    enumerable: true,
                    configurable: true
                });
                SearchComponent = __decorate([
                    core_1.Component({
                        selector: 'search',
                        viewProviders: [forms_1.FormBuilder],
                        templateUrl: '../app/components/search/search.component.html',
                        directives: [
                            forms_1.FORM_DIRECTIVES,
                            forms_1.REACTIVE_FORM_DIRECTIVES,
                            button_1.MdButton,
                            checkbox_1.MdCheckbox,
                            progress_circle_1.MdSpinner,
                            progress_circle_1.MdProgressCircle,
                            card_1.MdCard,
                            input_1.MdInput],
                        providers: [search_service_1.SearchService]
                    }), 
                    __metadata('design:paramtypes', [forms_1.FormBuilder, search_service_1.SearchService])
                ], SearchComponent);
                return SearchComponent;
            }());
            exports_1("SearchComponent", SearchComponent);
        }
    }
});
//# sourceMappingURL=search.component.js.map