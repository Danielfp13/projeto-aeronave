import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Aeronave } from './aeronave/aeronave'
import { environment } from '../environments/environment'
import { SomatorioDecada } from './aeronave/somatorioDecada';
@Injectable({
  providedIn: 'root'
})
export class AeronaveService {

  private uri: string = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  save(aeronave: Aeronave): Observable<Aeronave>{
    return this.http.post<Aeronave>(this.uri, aeronave)
  }

  findDecada(): Observable<any>{
    return this.http.get<any>(`${this.uri}/decadas`);
  }
}
