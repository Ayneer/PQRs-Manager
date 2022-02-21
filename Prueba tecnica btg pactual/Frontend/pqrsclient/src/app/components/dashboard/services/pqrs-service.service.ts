import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PqrsService {

  constructor(
    private http: HttpClient
  ) { }

  createPqrs(pqrs: any): Observable<any> {
    return this.http.post(`${environment.API_BASE_URL}/pqr`, pqrs);
  }

  createClaim(body: string, filingNumber: string): Observable<any> {
    return this.http.post(`${environment.API_BASE_URL}/pqr/claim?filingNumber=${encodeURIComponent(filingNumber)}`, body);
  }

  getPqrsByUserAndFiling(userId: number, filingNumber: string, filterByClaim: boolean): Observable<any> {
    const pathUrl = filterByClaim ? `pqr/claim` : `pqr`;
    return this.http.get(`${environment.API_BASE_URL}/${pathUrl}/${userId}?filingNumber=${encodeURIComponent(filingNumber)}`);
  }

}
