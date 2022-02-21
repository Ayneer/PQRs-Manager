import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-pqrs',
  templateUrl: './pqrs.component.html',
  styleUrls: ['./pqrs.component.scss']
})
export class PqrsComponent implements OnInit {

  @Input('user') user = null;

  NEW_REQUEST = 'Nueva solicitud';
  SEARCH_REQUEST = 'Buscar solicitud';
  btnTitle: string = this.NEW_REQUEST;

  constructor() { }

  ngOnInit() {}

  handleChangeAction() {
    if (this.btnTitle === this.NEW_REQUEST) {
      this.btnTitle = this.SEARCH_REQUEST;
    } else {
      this.btnTitle = this.NEW_REQUEST;
    }
  }

}
