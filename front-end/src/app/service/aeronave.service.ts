import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Aeronave } from '../aeronave/aeronave'
import { environment } from '../../environments/environment'
import { PaginaAeronave } from '../aeronave/paginaAeronave';

@Injectable({
  providedIn: 'root'
})
export class AeronaveService {

  private uri: string = environment.apiBaseUrl;
  aeronave: Aeronave

  constructor(private http: HttpClient) { }

  save(aeronave: Aeronave): Observable<Aeronave>{
    return this.http.post<Aeronave>(this.uri, aeronave)
  }

  getAeronaveById(id: any): Observable<Aeronave>{
    return this.http.get<Aeronave>(`${this.uri}/${id}`);
  }

  alterarAeronave(aeronave: Aeronave, id: any):Observable<any>{
    return this.http.put<any>(`${this.uri}/${id}`,aeronave);
  }


  findDecada(): Observable<any>{
    return this.http.get<any>(`${this.uri}/decadas`);
  }

  findMarca(): Observable<any>{
    return this.http.get<any>(`${this.uri}/marcas`);
  }

  findSemana(): Observable<any>{
    return this.http.get<any>(`${this.uri}/semanas`);
  }

  findPage(page: number, linePerPage: number, direction: string, orderBy: string, id: string, marca: string): Observable<PaginaAeronave>{
    const params = new HttpParams()
      .set('page', page.toString())
      .set('linePerPage' , linePerPage.toString())
      .set( 'direction', direction)
      .set( 'orderBy', orderBy)
      .set( 'id', id)
      .set( 'marca', marca)
    
    return this.http.get<any>(`${this.uri}/find?${params.toString()}`);
  }

  deleteById(id: any) : Observable<any>{
    return this.http.delete<any>(`${this.uri}/${id}`);
  }

  recebeAeronave(aeronave: Aeronave){
    this.aeronave = aeronave
  }

  
}
