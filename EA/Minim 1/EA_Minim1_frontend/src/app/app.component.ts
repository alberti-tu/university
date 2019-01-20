import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

interface User {
  name: String;
  surname: String;
  password: String;
  role: Boolean;
  state: Boolean;
  result: String;
  _id: String;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  showLogin: Boolean;
  showHome: Boolean;
  showData: Boolean;
  respuesta: any;
  filter: String;
  msg: String;

  constructor(private http: HttpClient) {
    this.showLogin = true;
    this.showHome = false;
    this.showData = false;
    this.filter = 'name';
    this.msg = 'Buscar por Nombre';
  }

  list: any = [
    { id: 1, name: 'Name', search: 'name' },
    { id: 2, name: 'Surname', search: 'surname' },
    { id: 3, name: 'Role', search: 'role' },
    { id: 4, name: 'State', search: 'state' },
    { id: 5, name: 'Alphabetical', search: 'all' },
    { id: 6, name: 'Id', search: 'one' }
  ];
  current = 1;

  selectFilter(id: number): void {
    this.filter = this.list.find((item: any) => item.id === +id).search;
    if (this.filter === 'name') {
      this.msg = 'Buscar Nombre';
    } else if (this.filter === 'surname') {
      this.msg = 'Buscar Apellido';
    } else if (this.filter === 'role') {
      this.msg = 'Buscar Role (true o false)';
    } else if (this.filter === 'status') {
      this.msg = 'Buscar State (true o false)';
    } else if (this.filter === 'all') {
      this.msg = 'Dejar en blanco';
    } else if (this.filter === 'one') {
      this.msg = 'Buscar ID';
    }
  }

  newUser(username, surname, password, role) {
    const body = {
      name: username.value,
      surname: surname.value,
      password: password.value,
      role: role.value,
      state: true
    };

    const httpOptions = { headers: new HttpHeaders({ 'Content-Type':  'application/json'}) };
    this.http.post('http://localhost:3000/new', body, httpOptions).subscribe(
      data => { console.log(data); } );
  }

  login(username, password) {
    const body = { name: username.value, password: password.value };
    const httpOptions = { headers: new HttpHeaders({ 'Content-Type':  'application/json'}) };
    this.http.post<User>('http://localhost:3000/login', body, httpOptions).subscribe(
      data => {
        console.log(data);
        if (data.name !== username.value) {
          console.log('Usuario no permitido');
        } else {
          console.log('Usuario administrador');
          this.showLogin = false;
          this.showHome = true;
        }
      } );
  }

  busqueda(search) {
    if ( this.filter === 'all') { search.value = ''; }
    this.http.get<User>('http://localhost:3000/select/' + this.filter + '/' + search.value).subscribe(
      data => {
        console.log(data);
        if (data.result !== 'ERROR') {
          if (this.filter === 'one') {
            this.respuesta = [];
            this.respuesta.push(data);
          } else {
            this.respuesta = data;
          }
          search.value = '';
          this.showData = true;
        }
      });
  }

  update(username, surname, role, id) {
    const body = { name: username.value, surname: surname.value, role: role.value };
    const httpOptions = { headers: new HttpHeaders({ 'Content-Type':  'application/json'}) };
    this.http.post('http://localhost:3000/update/' + id.value, body, httpOptions).subscribe(
      data => { console.log(data); } );
  }

  block(id) {
    this.http.get('http://localhost:3000/block/' + id.value).subscribe(
      data => { console.log(data); });
  }
}
