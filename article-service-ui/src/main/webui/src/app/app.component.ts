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
  title = 'article-ui';
  ngOnInit(): void {
    this.userService.getUser().subscribe();
    initFlowbite();
  }
}
