import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class ArticleService {

   constructor(private http: HttpClient) {

   }

   queryCategories(): Observable<any> {
      return this.http.get('/api/admin/article/categories');
   }

   uploadBigImg(formData: FormData): Observable<any> {
      return this.http.post('/api/admin/article/cover-plan', formData);
   }

   createArticle(value: any): Observable<any> {
      return this.http.post('/api/admin/article/unpublished-article', value);
   }

   updateArticle(value: any): Observable<any> {
      return this.http.put('/api/admin/article/unpublished-article', value);
   }

   publishArticle(value: any): Observable<any> {
      return this.http.post('/api/admin/article/published-article', value);
   }

   deleteArticle(value: any): Observable<any> {
      return this.http.delete('/api/admin/article/unpublished-article?articleId=' + value);
   }

   offlineArticle(value: any): Observable<any> {
      return this.http.delete('/api/admin/article/published-article?articleId=' + value);
   }

   updateOnlineArticle(value: any): Observable<any> {
      return this.http.put('/api/admin/article/published-article', value);
   }


   pagingQueryArticles(selectedCategoryId: string, selectedVersion: string, selectedStatus: string, value: string,
                       articleListPageNum: number, limit: number): Observable<any> {
      if (value === null) {
         value = '';
      }
      return this.http.get('/api/admin/article/published-articles?' +
         'categoryId=' + selectedCategoryId +
         '&version=' + selectedVersion +
         '&status=' + selectedStatus +
         '&search=' + value +
         '&currentPage=' + articleListPageNum +
         '&limit=' + limit);
   }

   pagingQueryUnPublishedArticles(selectedCategoryId: string, value: any, articleListPageNum: number, limit: number): Observable<any> {
      if (value === null) {
         value = '';
      }
      return this.http.get('/api/admin/article/unpublished-articles?' +
         'categoryId=' + selectedCategoryId +
         '&search=' + value +
         '&currentPage=' + articleListPageNum +
         '&limit=' + limit);
   }

   queryAllSlideshowPic(): Observable<any> {
      return this.http.get('/api/admin/article/slideshow');
   }

   uploadSlideshowPic(formData: FormData): Observable<any> {
      return this.http.post('/api/admin/article/slideshow', formData);
   }

   initButtonAuthority(menuId: number): Observable<any>  {
      return this.http.get(`/api/admin/user/menu/${menuId}/resources`);
   }
   download(path: string, title: string ) {
      const url = `/api/admin/article/download/published-article?path=${path}`;
      return this.http.get(url, {responseType: 'blob'}).toPromise().then(res => {
         let a = document.createElement('a');
         let blob = new Blob([res], {'type': 'application/pdf'});
         a.href = URL.createObjectURL(blob);
         a.download = title;
         a.click();
      });
   }
}
