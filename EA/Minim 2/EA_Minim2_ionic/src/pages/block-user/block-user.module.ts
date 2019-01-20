import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { BlockUserPage } from './block-user';

@NgModule({
  declarations: [
    BlockUserPage,
  ],
  imports: [
    IonicPageModule.forChild(BlockUserPage),
  ],
})
export class BlockUserPageModule {}
