import {Component, computed, inject, signal} from '@angular/core';
import {CartService} from "../service/cart.service";
import {OrderItem} from "../_interface/order-item";
import {Product} from "../_interface/product";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrl: './shopping-cart.component.css'
})
export class ShoppingCartComponent {
  cartService = inject(CartService);
  cartItems = this.cartService.cartItems();
  totalItems = computed(() => this.cartItems.reduce((sum, item) => sum + item.quantity, 0));
  totalAmount = computed(() => this.cartItems.reduce((total, item) => total + item.totalPrice, 0));

  addItem(newItem: Product) {
    this.cartService.addItem(newItem);
  }

}
