import { Component } from '@angular/core';
import { IonicPage, ToastController } from 'ionic-angular';
import { UserServiceProvider } from "../../providers/user-service";

@IonicPage()
@Component({
  selector: 'page-block-user',
  templateUrl: 'block-user.html',
})
export class BlockUserPage {

  id: string; // Id's user to dissable

  constructor(public toastCtrl: ToastController, private userService: UserServiceProvider) { }

  blockUser() {
    if(this.id == null) {
      this.ShowMessage('Missing fields');
      return;
    }
    this.userService.block(this.id).subscribe(data => {
      console.log(data.result);
      this.ShowMessage(data.result);
    });
  }

  ShowMessage(msg: string) {
    let toast = this.toastCtrl.create({ message: msg, duration: 3000 });
    toast.present();
  }
}
