import { Component, OnInit } from '@angular/core';
import { RegisterRequest } from 'src/app/models/registerrequest';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerRequest: RegisterRequest = { "username": "", "email": "", "password": "", "pogoName": ""}
  
  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
  }

  submit()
  {
    this.loginService.register(this.registerRequest);
  }

}
