import { Component, OnInit } from '@angular/core';
import { RegisterRequest } from 'src/app/models/registerrequest';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerRequest: RegisterRequest = { "username": "", "email": "", "password": "", "pogoName": ""};

  public validatorMessage: string = "";
  
  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
  }

  submit()
  {
    this.validatorMessage = "";
    if (this.registerRequest.username.length <  4)
    {

      this.validatorMessage = "Please fill in your username & password"

    }


    if (this.validatorMessage == "" ) {
    this.loginService.register(this.registerRequest);
    }
  }

}
