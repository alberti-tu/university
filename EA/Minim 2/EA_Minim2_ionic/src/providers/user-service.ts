import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from "../models/user.model";

@Injectable()
export class UserServiceProvider {

  urlBase: string;

  constructor(public http: HttpClient) {
    this.urlBase = 'localhost:3000';
  }

  select(filter: string, search: string) {
    if ( filter == 'all') { search = ''; }
    return this.http.get<User>('http://' + this.urlBase + '/select/' + filter + '/' + search);
  }

  update(username: string, surname: string, role: boolean, id: string) {
    const body = { name: username, surname: surname, role: role };
    return this.http.post<any>('http://' + this.urlBase + '/update/' + id, body);
  }

  block(id) {
    return this.http.get<any>('http://' + this.urlBase + '/block/' + id);
  }
}
