import {Component, OnInit} from '@angular/core';
import {FORM_DIRECTIVES, REACTIVE_FORM_DIRECTIVES, FormBuilder, FormGroup, FormControl, AbstractControl} from '@angular/forms';
import {Validators} from '@angular/forms';
import {SearchRequest} from './search';

import { MdCard } from '@angular2-material/card';
import { MdInput } from '@angular2-material/input';
import { MdButton } from '@angular2-material/button';
import { MdCheckbox } from '@angular2-material/checkbox';
import { MdSpinner, MdProgressCircle } from '@angular2-material/progress-circle';

@Component({
	selector: 'search',
	viewProviders: [FormBuilder],
	templateUrl: '../app/components/search/search.component.html',
	directives: [
	FORM_DIRECTIVES,
	REACTIVE_FORM_DIRECTIVES,
	MdButton,
	MdCheckbox,
	MdSpinner,
	MdProgressCircle,
	MdCard,
	MdInput]
})
export class SearchComponent implements OnInit {
	fb: FormBuilder;
	searchForm: FormGroup;

	from: FormControl;
	to: FormControl;
	suburbanTrains: FormControl;
	passengerTrains: FormControl;

	searchRequest:SearchRequest = new SearchRequest('Sec', 'Hwh');

	constructor(fb: FormBuilder) {
		this.fb = fb;
		this.from = new FormControl("", Validators.required);
		this.to = new FormControl("", Validators.required);
		this.suburbanTrains = new FormControl("", Validators.required);
		this.passengerTrains = new FormControl("", Validators.required);
	    this.searchForm = fb.group({
	      from: this.from,
	      to: this.to,
	      suburbanTrains: this.suburbanTrains,
	      passengerTrains: this.passengerTrains
	    });
	  }

	ngOnInit(): void {
		console.log('ngOnInit() called');
	}

	onSubmit(): void {
		this.searchRequest = new SearchRequest(this.from.value, this.to.value);
		this.searchRequest.filters["suburbanTrains"] = this.suburbanTrains.value;
		this.searchRequest.filters["passengerTrains"] = this.passengerTrains.value;
		console.log("submited.."+this.from.value+' - '+this.to.value+' '+this.suburbanTrains.value+' '+this.passengerTrains.value);
		console.log(this.diagnostic);
	}	

	get diagnostic() { return JSON.stringify(this.searchRequest); }
}