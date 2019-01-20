import { Component } from '@angular/core';
import { ToastController } from "ionic-angular";
import { UserServiceProvider } from "../../providers/user-service";
import {User} from "../../models/user.model";

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  search: string;     // Box input
  filter: string;     // Endpoint filter
  option: string;     // Endpoint name
  response: User;     // Result from the server
  showData: boolean;

  constructor(public toastCtrl: ToastController, private userService: UserServiceProvider) {
    this.filter = 'Name';
    this.showData = false;
  }

  endpoint(selection: string) {
    if (selection == 'Name') {
      this.option = 'name';
    } else if (selection == 'Surname') {
      this.option = 'surname';
    } else if (selection == 'Role') {
      this.option = 'role';
    } else if (selection == 'State') {
      this.option = 'state';
    } else if (selection == 'Alphabetical') {
      this.option = 'all';
    } else if (selection == 'Id') {
      this.option = 'id';
    }
  }

  select() {
    this.showData = false;
    this.endpoint(this.filter);
    this.userService.select(this.option, this.search).subscribe(data => {
      console.log(data);
      if(data.result == 'ERROR'){
        this.ShowMessage('Accepted Values (true or false)');
        return;
      }
      this.response = data;
      this.showData = true;
    });
  }

  ShowMessage(msg: string) {
    let toast = this.toastCtrl.create({ message: msg, duration: 3000 });
    toast.present();
  }
}
