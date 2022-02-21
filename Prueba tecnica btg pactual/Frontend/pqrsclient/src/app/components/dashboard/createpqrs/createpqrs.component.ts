import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { PqrsService } from '../services/pqrs-service.service';
const Swal = require('sweetalert2');

@Component({
  selector: 'app-createpqrs',
  templateUrl: './createpqrs.component.html',
  styleUrls: ['./createpqrs.component.scss']
})
export class CreatepqrsComponent implements OnInit {

  @Input('user') user = null;

  subscription1$: Subscription;
  subscription2$: Subscription;

  pqrTypeSelected: any = "0";
  creatingPqr: boolean = false;
  body: string = "";
  pqr: any = null;
  filingNumber: string = "";
  pqrTypes: any[] = [
    { name: 'Petición', value: "REQUEST" },
    { name: 'Queja', value: "COMPLAIN" },
    { name: 'Reclamo', value: "CLAIM" }
  ];

  constructor(
    private pqrService: PqrsService
  ) { }

  ngOnInit() {
  }

  createPqr() {
    if (!this.validatePqrToCreate()) return;

    const pqrs = {
      userId: this.user.id,
      type: this.pqrTypeSelected,
      body: this.body
    }

    this.creatingPqr = true;

    if (this.pqrTypeSelected === 'CLAIM') {
      this.subscription1$ = this.pqrService.createClaim(this.body, this.filingNumber)
        .subscribe(
          claim => {
            this.creatingPqr = false
            Swal.fire(
              'Reclamo creado con exito!',
              `Este es el número del radicado: ${claim.filingNumber}`,
              'success'
            );
          },
          ({ error }) => {
            this.creatingPqr = false
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: error.message
            });
          }
        );
    } else {
      this.subscription2$ = this.pqrService.createPqrs(pqrs)
        .subscribe(
          pqr => {
            this.creatingPqr = false
            Swal.fire(
              'Solicitud creada con exito!',
              `Este es el número del radicado: ${pqr.filingNumber}`,
              'success'
            );
          },
          ({error}) => {
            this.creatingPqr = false
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: error.message
            });
          }
        );
    }
  }

  validatePqrToCreate(): boolean {
    if (this.pqrTypeSelected === "0" || !this.body.trim()) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Debe seleccionar una opción y diligenciar todos los campos!'
      });
      return false;
    }
    if (this.pqrTypeSelected === 'CLAIM' && (!this.body.trim() || !this.filingNumber.trim())) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Debe diligenciar todos los campos!'
      });
      return false;
    }
    return true;
  }

}
