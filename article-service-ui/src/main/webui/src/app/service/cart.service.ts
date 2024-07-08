import {computed, Injectable, signal} from '@angular/core';
import {Product} from "../_interface/product";
import {OrderItem} from "../_interface/order-item";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems = signal<OrderItem[]>([]);

  totalAmount = computed(() =>
    this.cartItems().reduce((total, item) =>
      total + (item.product.price * Number(item.quantity)), 0)
  );

  totals = computed(() => this.cartItems().length);

  private calculateTotalAmount(items: OrderItem[]): number {
    return items.reduce((total, item) => total + item.amount * item.quantity, 0);
  }

  addItem(item: Product) {

    this.cartItems.update((currentCart) => {

      const existingItem = currentCart.find(
        (i) => i.product.productId === item.productId
      );

      if (existingItem) {
        // Increment quantity if item already exists
        existingItem.quantity += 1;
        existingItem.amount += item.price;
      } else {
        currentCart?.push({
          product: item,
          productId: item?.productId,
          quantity: 1,
          amount: item.price
        });
      }
      return currentCart;
    });
  }

  removeItem(productId: String) {
    this.cartItems.update((currentCart) => {
      const item = currentCart.find((i) => i.product.productId === productId);
      if (item) {
        currentCart = currentCart.filter(
          (i) => i?.product?.productId !== productId
        );
      }
      return currentCart;
    });
  }
}
