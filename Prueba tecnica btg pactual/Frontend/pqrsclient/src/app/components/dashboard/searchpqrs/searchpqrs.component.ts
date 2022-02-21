import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { PqrsService } from '../services/pqrs-service.service';
const Swal = require('sweetalert2');

@Component({
  selector: 'app-searchpqrs',
  templateUrl: './searchpqrs.component.html',
  styleUrls: ['./searchpqrs.component.scss']
})
export class SearchpqrsComponent implements OnInit {

  @Input('user') user = null;

  subscription1$: Subscription;
  pqr: any = null;
  pqrTypeSelected: any = "0";
  filingNumber: string = "";
  searchingPqr: boolean = false;
  pqrTypes: any[] = [
    { name: 'Petición/Queja', value: "1" },
    { name: 'Reclamo', value: "2" }
  ];
  pqrTypesCast = [
    { name: 'REQUEST', value: "Petición" },
    { name: 'COMPLAIN', value: "Queja" }
  ];

  constructor(
    private pqrService: PqrsService
  ) { }

  ngOnInit() {
  }

  castPqrType(pqrTye: String){
    return this.pqrTypesCast.find(pqrt => pqrt.name === pqrTye).value;
  }

  searchPqr() {
    if (!this.validateSearchPqr()) return;
    const filterByClaim = this.pqrTypeSelected === "1" ? false : true;
    this.searchingPqr = true;
    this.subscription1$ = this.pqrService.getPqrsByUserAndFiling(this.user.id, this.filingNumber, filterByClaim)
      .subscribe(
        pqr => {
          this.searchingPqr = false
          this.pqr = pqr
        },
        ({error}) => {
          this.searchingPqr = false
          this.pqr = null;
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: error.message
          });
        }
      );
  }

  validateSearchPqr(): boolean {
    if (this.pqrTypeSelected === "0" || !this.filingNumber.trim()) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Debe seleccionar una opción y diligenciar un número de radicado valido'
      });
      return false;
    }
    return true;
  }

}
