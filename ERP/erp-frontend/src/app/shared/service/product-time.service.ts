import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ProductTimeService {
   constructor(private http: HttpClient) {
   }

}
