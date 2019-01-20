import { Component } from '@angular/core';
import { IonicPage, NavController, ToastController } from 'ionic-angular';
import { UserAuthProvider } from "../../providers/user-auth";
import { HomePage } from "../home/home";
import { RegisterPage } from "../register/register";

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

  username: string;
  password: string;

  constructor(public navCtrl: NavController, public toastCtrl: ToastController, private userAuth: UserAuthProvider) { }

  ionViewDidLoad() {  }

  login() {
    if(this.username == null || this.password == null) {
      this.ShowMessage('Missing fields');
      return;
    }
    this.userAuth.signIn(this.username, this.password).subscribe(data => {
      console.log(data);
      if (data.name !== this.username) {
        console.log('User without authorization');
        this.ShowMessage('User without authorization');
      } else {
        console.log('Admin user');
        this.ShowMessage('Admin user');
        this.navCtrl.setRoot(HomePage);   //Navigate to home page
      }
    });
  }

  register() {
    this.navCtrl.setRoot(RegisterPage);
  }

  ShowMessage(msg: string) {
    let toast = this.toastCtrl.create({ message: msg, duration: 3000 });
    toast.present();
  }
}
