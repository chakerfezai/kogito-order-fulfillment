import { Component } from '@angular/core';
import {initFlowbite} from "flowbite";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'article-ui';
  //public appelPath= "../../assets/images/productimg/apple-watch.png";
  ngOnInit(): void {
    initFlowbite();
  }
}
