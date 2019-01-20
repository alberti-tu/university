import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from "../models/user.model";

@Injectable()
export class UserAuthProvider {

  urlBase: string;

  constructor(public http: HttpClient) {
    this.urlBase = 'localhost:3000';
  }

  signIn(username: string, password: string) {
    const body = { name: username, password: password };
    return this.http.post<User>('http://' + this.urlBase + '/login', body);
  }

  signUp(name: string, surname: string, password: string, role: boolean) {
    const body = { name: name, surname: surname, password: password, role: role, state: true };
    return this.http.post<any>('http://' + this.urlBase + '/new', body)
  }
}
