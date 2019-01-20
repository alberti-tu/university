import { Component } from '@angular/core';
import { IonicPage, NavController, ToastController } from 'ionic-angular';
import { LoginPage } from "../login/login";
import { UserAuthProvider } from "../../providers/user-auth";

@IonicPage()
@Component({
  selector: 'page-register',
  templateUrl: 'register.html',
})
export class RegisterPage {

  username: string;
  surname: string;
  password: string;
  role: boolean;

  constructor(public navCtrl: NavController, public toastCtrl: ToastController, private userAuth: UserAuthProvider) {
    this.role = false;
  }

  ionViewDidLoad() {  }

  register() {
    if(this.username == null || this.surname == null || this.password == null) {
      this.ShowMessage('Missing fields');
      return;
    }
    this.userAuth.signUp(this.username, this.surname, this.password, this.role).subscribe(data => {
      console.log(data);
      if(data.result == 'INSERTADO') {
        this.ShowMessage('User inserted correctly');
        this.navCtrl.setRoot(LoginPage);
      } else {
        this.ShowMessage("Existing user, try again");
      }
    });
  }

  backPage() {
    this.navCtrl.setRoot(LoginPage);
  }

  ShowMessage(msg: string) {
    let toast = this.toastCtrl.create({ message: msg, duration: 3000 });
    toast.present();
  }
}
