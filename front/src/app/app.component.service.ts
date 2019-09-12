import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { HttpHeaders } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class AppService {

    constructor(
        private httpClient: HttpClient
    ) { }

    public buscar(): Promise<any> {
        return new Promise(sucesso => {
            this.httpClient.get(`http://localhost:8089/api/funcionario/buscar`)
                .subscribe(resposta => sucesso(resposta));
        });
    }

    public salvar(item: any): Promise<any> {
        return new Promise(sucesso => {
            this.httpClient.post(`http://localhost:8089/api/funcionario/salvar`, item)
                .subscribe(resposta => sucesso(resposta));
        });
    }

    public deletar(id: number): Promise<any> {
        return new Promise(sucesso => {
            this.httpClient.get(`http://localhost:8089/api/funcionario/deletar/${id}`)
                .subscribe(resposta => sucesso(resposta));
        });
    }
}
