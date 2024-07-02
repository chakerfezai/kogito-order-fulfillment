import {Component, computed, inject} from '@angular/core';
import {CartService} from "../service/cart.service";
import {Product} from "../_interface/product";
import {LocalStorageService} from "../service/local.storage.service";
import {addressToString, Customer} from "../_interface/customer";
import {OrderService} from "../service/order.service";
import {Order} from "../_interface/order";
import {Router, Routes} from "@angular/router";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrl: './shopping-cart.component.css'
})
export class ShoppingCartComponent {
  localStorageService = inject(LocalStorageService);
  cartService = inject(CartService);
  orderService = inject(OrderService);
  route = inject(Router);
  cartItems = this.cartService.cartItems();
  totalItems = computed(() => this.cartItems.reduce((sum, item) => sum + item.quantity, 0));
  totalAmount = computed(() => this.cartItems.reduce((total, item) => total + item.amount, 0));


  addItem(newItem: Product) {
    this.cartService.addItem(newItem);
  }

  createOrder() {
    const user = localStorage.getItem('user');
    const customer = null;
   //this.localStorageService.setItem("transactionId", "");
    let order: Order = {};
    if (user) {
      const customer: Customer = JSON.parse(user);
      order = {
        customerId: customer.id,
        totalAmount: this.totalAmount(),
        orderItems: this.cartItems,
        countryOrigin: customer.billingAddress?.country,
        countryDestination: customer.shippingAddress?.country,
        shippingAddress: addressToString(customer.shippingAddress),
      }
      this.orderService.createOrder(order).subscribe(value => {
          console.log("transaction id :", value)
          this.localStorageService.setItem("transactionId", value)
        }, error => console.error(error),
        () => this.route.navigateByUrl('payment'));
    }
    console.log("order", order)
  }

}
