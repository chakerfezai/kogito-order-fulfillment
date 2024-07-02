import {Component, computed, inject, OnInit} from '@angular/core';

import {ProductService} from "../service/product-service";
import {CartService} from "../service/cart.service";
import {Product} from "../_interface/product";
import {LocalStorageService} from "../service/local.storage.service";
import {MessageService} from "../service/message.service";


@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.css'],
})
export class ProductPageComponent implements OnInit {
  private productService = inject(ProductService);
  private cartService = inject(CartService);
  products = this.productService.products;
  private messageService = inject(MessageService);


  ngOnInit(): void {
    this.productService.list().subscribe();
  }

  addItem(newItem: Product) {
    this.cartService.addItem(newItem);
    this.cartService.totalAmount();
    this.messageService.updateCount(this.cartService.cartItems.length)
  }


}
