import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

const form_headers = new HttpHeaders({
      'content-type': 'application/x-www-form-urlencoded; charset=UTF-8'
   }
);
const req_url = 'http://www.qchannel04.cn/daily-mobile';

@Injectable()
export class SampleService {

   constructor(private http: HttpClient) {

   }

   multi_data_summary(dd: string): Observable<any> {
      return this.http.post(req_url + '/data/multi_data_summary',
         'dd=' + dd, {headers: form_headers});
   }

   multi_data_updown_summary(currentDate: string, updownDataPage: number, limit: number): Observable<any> {
      return this.http.post(req_url + '/data/multi_data_updown_summary',
         'page=' + updownDataPage + '&limit=' + limit + '&dd=' + currentDate, {headers: form_headers});
   }

   new_sdk_integrate(currentDate: string, newIntegrateDataPage: number, limit: number): Observable<any> {
      return this.http.post(req_url + '/data/new_sdk_integrate',
         'page=' + newIntegrateDataPage + '&limit=' + limit + '&dd=' + currentDate, {headers: form_headers});
   }

   options_list(number: number): Observable<any> {
      return this.http.post(req_url + '/data/options_list',
         'optionsid=' + number, {headers: form_headers});
   }

   zx_summary(id: string, sd: string, ed: string): Observable<any> {
      return this.http.post(req_url + '/data/echarts/zx_summary',
         'id=' + id + '&st=' + sd + '&et=' + ed, {headers: form_headers});
   }
   zx7_summary(id: string, sd: string, ed: string): Observable<any> {
      return this.http.post(req_url + '/data/echarts/zx7_summary',
         'id=' + id + '&st=' + sd + '&et=' + ed, {headers: form_headers});
   }

   qt_summary(id: string, sd: string, ed: string): Observable<any> {
      return this.http.post(req_url + '/data/echarts/qt_summary',
         'id=' + id + '&st=' + sd + '&et=' + ed, {headers: form_headers});
   }

   plus_summary(id: string, sd: string, ed: string): Observable<any> {
      return this.http.post(req_url + '/data/echarts/plus_summary',
         'id=' + id + '&st=' + sd + '&et=' + ed, {headers: form_headers});
   }

   zx_signal(id: string, sd: string, ed: string): Observable<any> {
      return this.http.post(req_url + '/data/echarts/zx_signal',
         'id=' + id + '&st=' + sd + '&et=' + ed, {headers: form_headers});
   }
   zx7_signal(id: string, sd: string, ed: string): Observable<any> {
      return this.http.post(req_url + '/data/echarts/zx7_signal',
         'id=' + id + '&st=' + sd + '&et=' + ed, {headers: form_headers});
   }

   qt_signal(id: string, sd: string, ed: string): Observable<any> {
      return this.http.post(req_url + '/data/echarts/qt_signal',
         'id=' + id + '&st=' + sd + '&et=' + ed, {headers: form_headers});
   }

   plus_signal(id: string, sd: string, ed: string): Observable<any> {
      return this.http.post(req_url + '/data/echarts/plus_signal',
         'id=' + id + '&st=' + sd + '&et=' + ed, {headers: form_headers});
   }

   zq_signal(id: string, sd: string, ed: string, osType: number, dataType: number): Observable<any> {
      return this.http.post(req_url + '/data/echarts/zq_signal',
         'id=' + id + '&st=' + sd + '&et=' + ed + '&ostype=' + osType + '&datatype=' + dataType, {headers: form_headers});
   }

   sdk_history_timeline(id: string): Observable<any> {
      return this.http.post(req_url + '/data/sdk_history_timeline',
         'id=' + id, {headers: form_headers});
   }
}
