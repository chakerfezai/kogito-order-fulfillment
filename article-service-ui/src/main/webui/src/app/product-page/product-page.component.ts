import {Component, effect, inject, OnInit} from '@angular/core';

import {ProductService} from "../service/product-service";
import {CartService} from "../service/cart.service";
import {Product} from "../_interface/product";


@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.css'],
})
export class ProductPageComponent implements OnInit {
  private productService = inject(ProductService);
  private cartService = inject(CartService);
  products = this.productService.products;

  ngOnInit(): void {
    this.productService.list().subscribe();

  }

  addItem(newItem: Product) {
    this.cartService.addItem(newItem);
  }


}
