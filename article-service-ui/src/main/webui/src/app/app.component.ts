import {Component, inject} from '@angular/core';
import {initFlowbite} from "flowbite";
import {UserService} from "./service/user.service";
import {LocalStorageService} from "./service/local.storage.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  private userService = inject(UserService);
  private localStorageService = inject(LocalStorageService);
  title = 'article-ui';
  //public appelPath= "../../assets/images/productimg/apple-watch.png";
  ngOnInit(): void {
    this.userService.getUser().subscribe();
    initFlowbite();
  }
}
