import { Component } from '@angular/core';
import { IonicPage, ToastController } from 'ionic-angular';
import { UserServiceProvider } from "../../providers/user-service";

@IonicPage()
@Component({
  selector: 'page-update-user',
  templateUrl: 'update-user.html',
})
export class UpdateUserPage {

  id: string;
  username: string;
  surname: string;
  role: boolean;

  constructor(public toastCtrl: ToastController, private userService: UserServiceProvider) {
    this.role = false;
  }

  ionViewDidLoad() { }

  update() {
    if(this.username == null || this.surname == null || this.id == null) {
      this.ShowMessage('Missing fields');
      return;
    }
    this.userService.update(this.username, this.surname, this.role, this.id).subscribe(data => {
      console.log(data.result);
      this.ShowMessage(data.result);
    });
  }

  ShowMessage(msg: string) {
    let toast = this.toastCtrl.create({ message: msg, duration: 3000 });
    toast.present();
  }
}
