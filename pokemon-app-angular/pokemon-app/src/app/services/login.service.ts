import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoginRequest } from '../models/loginrequest';
import { LoginResponse } from '../models/loginresponse';
import { RegisterRequest } from '../models/registerrequest';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, private router: Router) { }

  login(loginRequest: LoginRequest): Observable<any> {

    return this.http.post<LoginResponse>(environment.API_URL + "auth/login", loginRequest);
   
  }

  register(registerRequest: RegisterRequest)
  {
      
    this.http.post<LoginResponse>(environment.API_URL + "auth/register", registerRequest)
    .subscribe({
        next: data => {
          console.warn("data: " + data.token); //???
          this.router.navigate(['login']);
        },
        error: error => {
            console.log('Register failed!' + error.message + error );
        }
      })



  }

}
