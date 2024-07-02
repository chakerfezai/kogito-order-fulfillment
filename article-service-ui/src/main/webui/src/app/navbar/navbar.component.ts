import {Component, computed, inject, Input, OnInit} from '@angular/core';
import {CartService} from "../service/cart.service";
import {LocalStorageService} from "../service/local.storage.service";
import {MessageService} from "../service/message.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {

  private localStorageService = inject(LocalStorageService);
  private messageService = inject(MessageService);
  private cartService = inject(CartService);
  cartItems = this.cartService.cartItems();
  protected count: number = 0;

  ngOnInit(): void {

    this.messageService.getCount().subscribe(value => {
      this.count = this.cartItems.reduce((sum, item) => sum + item.quantity, 0);
    });

  }


}
