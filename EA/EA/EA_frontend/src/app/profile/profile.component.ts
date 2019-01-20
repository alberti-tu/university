import { Component, OnInit, Input} from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

interface Activity {
  name:            String;        // Campo obligatório para insertar
  latitude:        Number;        // Campo obligatório para insertar
  longitude:       Number;        // Campo obligatório para insertar
  cost:            Number;        // Campo obligatório para insertar
  user1:           String;        // Campo obligatório para insertar
  user2:           String;
  description:     String;
  tags:          [ String ];
  date:            Date;
  isDone:          Boolean;
}

interface User {
  username:        String;        // Campo obligatório para insertar
  password:        String;        // Campo obligatório para insertar
  name:            String;        // Campo obligatório para insertar
  mail:            String;        // Campo obligatório para insertar
  description:     String;
  tags:          [ String ];
  image:           String;
  wallet:          Number;
  rating:          Number;
  numVal:          Number;
  listaOfertada: [ Activity ];
  listaRecibida: [ Activity ];
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styles: []
})
export class ProfileComponent implements OnInit {

  @Input() newSearch: Boolean;
  nameUser: String;
  user: User;
  show: boolean;

  constructor(private http: HttpClient, private route: ActivatedRoute) {
    this.newSearch = false;
    this.route.params.subscribe( params => this.nameUser = params.username );
    console.log('Relativre URL is ' + this.nameUser);
    this.show = false;
  }

  ngOnInit() { this.connect(); }

// TODO conseguir el CODIGO de HTTP REQUEST

  // Recibe la respuesta del servidor
  connect() {
    this.show = false;
    this.http.get<User>('http://localhost:3000/users/select/' + this.nameUser).subscribe(
      data => {
        this.user = data;      // El JSON se guarda en user
        console.log(this.user);
        this.show = true;      // Mostramos el resultado
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

  popupView(activity) { console.log('Ver ' + activity.name); }
  popupEdit(activity) { console.log('Editar ' + activity.name); }
}
