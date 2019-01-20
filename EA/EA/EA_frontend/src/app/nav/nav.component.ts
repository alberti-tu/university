import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html'
})
export class NavComponent implements OnInit {

  newSearch: Boolean;

  constructor( private route: ActivatedRoute, private router: Router) {
    this.newSearch = false;
  }

  ngOnInit() { }

  redirect(search) {
    if (search.value !== '') {
      this.router.navigate(['/profile/' + search.value]);
      location.reload ();   // Refresca TODA la vista (solo se tiene que actualizar el body)
    }
  }
}
