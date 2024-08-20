import {Observable} from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstants} from '../constants/app.constants';


@Injectable({
    providedIn: 'root',
  })
export class GuessService{

    constructor(private http : HttpClient){}

    public  callGuessData(guessedWord: string): Observable<string[]>{
      return this.http.get<any>( AppConstants.API_ENDPOINT+guessedWord);
    }
}