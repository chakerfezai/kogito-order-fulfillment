import {Component, computed, inject, Input, OnInit} from '@angular/core';
import {CartService} from "../service/cart.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{

  @Input() cartCount:number=0;

  private cartService = inject(CartService);
  cartItems = this.cartService.cartItems();
  totalItems = computed(() => this.cartItems.reduce((sum, item) => sum + item.quantity, 0));

  ngOnInit(): void {
    this.cartCount = this.totalItems();
  }


}
