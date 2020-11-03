import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-validator',
  templateUrl: './validator.component.html',
  styleUrls: ['./validator.component.css']
})
export class ValidatorComponent implements OnInit {


  @Input()
  public message: string;
  
  constructor() { }

  ngOnInit(): void {
  }

}
