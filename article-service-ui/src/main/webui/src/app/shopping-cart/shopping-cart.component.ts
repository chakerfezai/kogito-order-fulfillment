import {Component, computed, inject, signal} from '@angular/core';
import {CartService} from "../service/cart.service";
import {Product} from "../_interface/product";
import {LocalStorageService} from "../service/local.storage.service";
import {addressToString, Customer} from "../_interface/customer";
import {OrderService} from "../service/order.service";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrl: './shopping-cart.component.css'
})
export class ShoppingCartComponent {
  localStorageService = inject(LocalStorageService);
  cartService = inject(CartService);
  orderService = inject(OrderService);
  cartItems = this.cartService.cartItems();
  totalItems = computed(() => this.cartItems.reduce((sum, item) => sum + item.quantity, 0));
  totalAmount = computed(() => this.cartItems.reduce((total, item) => total + item.amount, 0));

  addItem(newItem: Product) {
    this.cartService.addItem(newItem);
  }

  createOrder() {
    const user = localStorage.getItem('user');
    const customer = null;

    let order = null;
    if (user) {
      const customer: Customer = JSON.parse(user);
      let orderItem = [];
      order = {
        customerId: customer.id,
        totalAmount: this.totalAmount(),
        orderItems: this.cartItems,
        shippingAddress: addressToString(customer.shippingAddress),
      }
      this.orderService.createOrder(order).subscribe(value => {
        console.log("transaction id :", value)
      });
    }

    console.log("order", order)
  }

}
