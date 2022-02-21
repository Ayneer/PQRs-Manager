import { Component, OnInit } from '@angular/core';
import { AppService } from './services/app-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  user: any = null;
  loading: boolean = true;

  constructor(
    private appService: AppService
  ) { }

  ngOnInit(): void {
    this.appService.getCurrentUser()
      .subscribe(user => {
        this.user = user;
        this.loading = false;
      });
  }

}
