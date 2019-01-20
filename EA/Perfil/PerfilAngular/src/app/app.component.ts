import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

interface ActivityResponse {
    name: String;
    user: String;
    description: String;
}

interface ProfileResponse {
    username: String;
    password: String;
    wallet: Number;
    listaOfertada: [ ActivityResponse ];
    listaRecibida: [ ActivityResponse ];
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

    profile: any;
    show: boolean;

    constructor(private http: HttpClient) {
        this.profile = null;
        this.show = false;
    }

    // Recibe la respuesta del servidor
    connect(user) {
        this.http.get<ProfileResponse>('http://localhost:3000/select/' + user.value).subscribe(
            data => {
                this.profile = data;      // El JSON se guarda en profile
                console.log(this.profile);
                this.show = true;
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
}
