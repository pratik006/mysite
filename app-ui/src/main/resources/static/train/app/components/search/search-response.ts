import {Train} from './Train';

export class SearchResponse {
	trains: Train[];

	constructor(trains: Train[]) {
		this.trains = trains;
	}
}