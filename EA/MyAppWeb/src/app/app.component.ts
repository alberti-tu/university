import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

interface DataResponse {
  userId: string;
  id: string;
  title: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  /*******************  INICIALIZACIONES  *******************/

  title: string;
  web_name: string;
  web_site: string;
  webs: string[];
  showWeb: boolean;
  msg: string;
  latitud_map: number;
  longitud_map: number;
  latitud_marker: number;
  longitud_marker: number;
  showMap: boolean;

  constructor(private http: HttpClient) {
    this.title = 'Hello World';
    this.web_name = 'Web EETAC';
    this.web_site = 'https://eetac.upc.edu';
    this.webs = ['Google', 'Facebook', 'Twitter'];
    this.showWeb = false;
    this.msg = null;
    this.showMap = false;
    this.setUpPosicion();
  }

  /*******************  LOGICA DE LA APP WEB  *******************/

  // Muestra u oculta un div
  showWebs() {
    return this.showWeb = !this.showWeb;
  }

  // Añade un nuevo elemento a un Array
  newWeb(nuevaWeb) {
    this.webs.push(nuevaWeb.value);
    nuevaWeb.value = '';
    return false;
  }

  // Recibe la respuesta del servidor
  connect() {
    this.http.get<DataResponse>('https://jsonplaceholder.typicode.com/posts/2').subscribe(
      data => {
        console.log('UserId: ' + data.userId);
        console.log('Id: ' + data.id);
        console.log('Title: ' + data.title);
        this.webs.push(data.title);
      },

      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.error('Error: Fallo en el cliente');
        } else {
          console.error('Error: Fallo en el servidor');
        }
      }
    );
  }

  /*******************  MAPAS  *******************/

  // Inicializa el mapa y el marcador
  setUpPosicion() {
    const self = this;
    if (navigator) {
      navigator.geolocation.getCurrentPosition(function (position) {
        // Devuelve los valores del CallBack
        self.latitud_map = position.coords.latitude;
        self.longitud_map = position.coords.longitude;
        self.latitud_marker = position.coords.latitude;
        self.longitud_marker = position.coords.longitude;
        self.showMap = true;
      });
    } else { console.error('Error: No se puede acceder a la localización'); }
  }

  // Obtiene las coordenadas del nuevo marker
  mapClick(event) {
    this.latitud_marker = event.coords.lat;
    this.longitud_marker = event.coords.lng;
  }
}
