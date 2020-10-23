import { Component, OnInit } from '@angular/core';
import { Loginrequest } from 'src/app/models/loginrequest';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginrequest: Loginrequest = {"email": "", "password": ""};

  constructor(private loginService: LoginService) { }


  ngOnInit(): void {
  }

  
  submit()
  {
    if (this.loginrequest.email == "" || this.loginrequest.password == "" )
    {
      console.warn("fail" + JSON.stringify(this.loginrequest));

    }
    else {
      this.loginService.login(this.loginrequest);
    }

  }

}
