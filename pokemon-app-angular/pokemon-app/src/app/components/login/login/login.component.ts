import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/models/loginrequest';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginrequest: LoginRequest = {"email": "", "password": ""};

  validatorMessage: string = "";

  constructor(private loginService: LoginService, private router: Router) { }


  ngOnInit(): void {
  }

  
  submit()
  {
    this.validatorMessage = "";
    if (this.loginrequest.email == "" || this.loginrequest.password == "" )
    {
      console.warn("fail" + JSON.stringify(this.loginrequest));
      this.validatorMessage = "Please fill in your username & password"

    }
    else {
      this.login();
    }

  }

  private login(): void {

    this.loginService.login(this.loginrequest).subscribe({
      next: data => {
        localStorage.setItem('token', data.token);
        this.router.navigate(['home']);
      },
      error: error => {
          console.log('Log in failed! ' + error );
          this.validatorMessage = "Wrong username or password"
      }});

  }

}
